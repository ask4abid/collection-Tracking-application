import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IVisits, Visits } from 'app/shared/model/visits.model';
import { VisitsService } from './visits.service';
import { VisitsComponent } from './visits.component';
import { VisitsDetailComponent } from './visits-detail.component';
import { VisitsUpdateComponent } from './visits-update.component';

@Injectable({ providedIn: 'root' })
export class VisitsResolve implements Resolve<IVisits> {
  constructor(private service: VisitsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IVisits> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((visits: HttpResponse<Visits>) => {
          if (visits.body) {
            return of(visits.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Visits());
  }
}

export const visitsRoute: Routes = [
  {
    path: '',
    component: VisitsComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'ccwApp.visits.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: VisitsDetailComponent,
    resolve: {
      visits: VisitsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ccwApp.visits.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: VisitsUpdateComponent,
    resolve: {
      visits: VisitsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ccwApp.visits.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: VisitsUpdateComponent,
    resolve: {
      visits: VisitsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ccwApp.visits.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
