import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'proosals',
        loadChildren: () => import('./proosals/proosals.module').then(m => m.CcwAppProosalsModule),
      },
      {
        path: 'visits',
        loadChildren: () => import('./visits/visits.module').then(m => m.CcwAppVisitsModule),
      },
      {
        path: 'visited-by',
        loadChildren: () => import('./visited-by/visited-by.module').then(m => m.CcwAppVisitedByModule),
      },
      {
        path: 'deliquency',
        loadChildren: () => import('./deliquency/deliquency.module').then(m => m.CcwAppDeliquencyModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class CcwAppEntityModule {}
