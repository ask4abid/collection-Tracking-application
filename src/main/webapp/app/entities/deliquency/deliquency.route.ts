import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDeliquency, Deliquency } from 'app/shared/model/deliquency.model';
import { DeliquencyService } from './deliquency.service';
import { DeliquencyComponent } from './deliquency.component';
import { DeliquencyDetailComponent } from './deliquency-detail.component';
import { DeliquencyUpdateComponent } from './deliquency-update.component';

@Injectable({ providedIn: 'root' })
export class DeliquencyResolve implements Resolve<IDeliquency> {
  constructor(private service: DeliquencyService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDeliquency> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((deliquency: HttpResponse<Deliquency>) => {
          if (deliquency.body) {
            return of(deliquency.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Deliquency());
  }
}

export const deliquencyRoute: Routes = [
  {
    path: '',
    component: DeliquencyComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ccwApp.deliquency.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DeliquencyDetailComponent,
    resolve: {
      deliquency: DeliquencyResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ccwApp.deliquency.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DeliquencyUpdateComponent,
    resolve: {
      deliquency: DeliquencyResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ccwApp.deliquency.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DeliquencyUpdateComponent,
    resolve: {
      deliquency: DeliquencyResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ccwApp.deliquency.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
