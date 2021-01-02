import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CcwAppSharedModule } from 'app/shared/shared.module';
import { VisitsComponent } from './visits.component';
import { VisitsDetailComponent } from './visits-detail.component';
import { VisitsUpdateComponent } from './visits-update.component';
import { VisitsDeleteDialogComponent } from './visits-delete-dialog.component';
import { visitsRoute } from './visits.route';

@NgModule({
  imports: [CcwAppSharedModule, RouterModule.forChild(visitsRoute)],
  declarations: [VisitsComponent, VisitsDetailComponent, VisitsUpdateComponent, VisitsDeleteDialogComponent],
  entryComponents: [VisitsDeleteDialogComponent],
})
export class CcwAppVisitsModule {}
