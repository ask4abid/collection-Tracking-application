import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CcwAppSharedModule } from 'app/shared/shared.module';
import { ProosalsComponent } from './proosals.component';
import { ProosalsDetailComponent } from './proosals-detail.component';
import { ProosalsUpdateComponent } from './proosals-update.component';
import { ProosalsDeleteDialogComponent } from './proosals-delete-dialog.component';
import { proosalsRoute } from './proosals.route';

@NgModule({
  imports: [CcwAppSharedModule, RouterModule.forChild(proosalsRoute)],
  declarations: [ProosalsComponent, ProosalsDetailComponent, ProosalsUpdateComponent, ProosalsDeleteDialogComponent],
  entryComponents: [ProosalsDeleteDialogComponent],
})
export class CcwAppProosalsModule {}
