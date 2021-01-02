import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IVisitedBy, VisitedBy } from 'app/shared/model/visited-by.model';
import { VisitedByService } from './visited-by.service';
import { VisitedByComponent } from './visited-by.component';
import { VisitedByDetailComponent } from './visited-by-detail.component';
import { VisitedByUpdateComponent } from './visited-by-update.component';

@Injectable({ providedIn: 'root' })
export class VisitedByResolve implements Resolve<IVisitedBy> {
  constructor(private service: VisitedByService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IVisitedBy> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((visitedBy: HttpResponse<VisitedBy>) => {
          if (visitedBy.body) {
            return of(visitedBy.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new VisitedBy());
  }
}

export const visitedByRoute: Routes = [
  {
    path: '',
    component: VisitedByComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'ccwApp.visitedBy.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: VisitedByDetailComponent,
    resolve: {
      visitedBy: VisitedByResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ccwApp.visitedBy.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: VisitedByUpdateComponent,
    resolve: {
      visitedBy: VisitedByResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ccwApp.visitedBy.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: VisitedByUpdateComponent,
    resolve: {
      visitedBy: VisitedByResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ccwApp.visitedBy.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
