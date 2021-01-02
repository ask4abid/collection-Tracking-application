import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IVisitedBy } from 'app/shared/model/visited-by.model';
import { VisitedByService } from './visited-by.service';

@Component({
  templateUrl: './visited-by-delete-dialog.component.html',
})
export class VisitedByDeleteDialogComponent {
  visitedBy?: IVisitedBy;

  constructor(protected visitedByService: VisitedByService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.visitedByService.delete(id).subscribe(() => {
      this.eventManager.broadcast('visitedByListModification');
      this.activeModal.close();
    });
  }
}
