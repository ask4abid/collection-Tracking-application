import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CcwAppSharedModule } from 'app/shared/shared.module';
import { VisitedByComponent } from './visited-by.component';
import { VisitedByDetailComponent } from './visited-by-detail.component';
import { VisitedByUpdateComponent } from './visited-by-update.component';
import { VisitedByDeleteDialogComponent } from './visited-by-delete-dialog.component';
import { visitedByRoute } from './visited-by.route';

@NgModule({
  imports: [CcwAppSharedModule, RouterModule.forChild(visitedByRoute)],
  declarations: [VisitedByComponent, VisitedByDetailComponent, VisitedByUpdateComponent, VisitedByDeleteDialogComponent],
  entryComponents: [VisitedByDeleteDialogComponent],
})
export class CcwAppVisitedByModule {}
