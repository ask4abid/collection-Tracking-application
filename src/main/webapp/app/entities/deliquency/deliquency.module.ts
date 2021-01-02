import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CcwAppSharedModule } from 'app/shared/shared.module';
import { DeliquencyComponent } from './deliquency.component';
import { DeliquencyDetailComponent } from './deliquency-detail.component';
import { DeliquencyUpdateComponent } from './deliquency-update.component';
import { DeliquencyDeleteDialogComponent } from './deliquency-delete-dialog.component';
import { deliquencyRoute } from './deliquency.route';

@NgModule({
  imports: [CcwAppSharedModule, RouterModule.forChild(deliquencyRoute)],
  declarations: [DeliquencyComponent, DeliquencyDetailComponent, DeliquencyUpdateComponent, DeliquencyDeleteDialogComponent],
  entryComponents: [DeliquencyDeleteDialogComponent],
})
export class CcwAppDeliquencyModule {}
