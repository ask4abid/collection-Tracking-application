import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IProosals, Proosals } from 'app/shared/model/proosals.model';
import { ProosalsService } from './proosals.service';
import { ProosalsComponent } from './proosals.component';
import { ProosalsDetailComponent } from './proosals-detail.component';
import { ProosalsUpdateComponent } from './proosals-update.component';

@Injectable({ providedIn: 'root' })
export class ProosalsResolve implements Resolve<IProosals> {
  constructor(private service: ProosalsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProosals> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((proosals: HttpResponse<Proosals>) => {
          if (proosals.body) {
            return of(proosals.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Proosals());
  }
}

export const proosalsRoute: Routes = [
  {
    path: '',
    component: ProosalsComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'ccwApp.proosals.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ProosalsDetailComponent,
    resolve: {
      proosals: ProosalsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ccwApp.proosals.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ProosalsUpdateComponent,
    resolve: {
      proosals: ProosalsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ccwApp.proosals.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ProosalsUpdateComponent,
    resolve: {
      proosals: ProosalsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ccwApp.proosals.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
