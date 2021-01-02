import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IVisits } from 'app/shared/model/visits.model';
import { VisitsService } from './visits.service';

@Component({
  templateUrl: './visits-delete-dialog.component.html',
})
export class VisitsDeleteDialogComponent {
  visits?: IVisits;

  constructor(protected visitsService: VisitsService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.visitsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('visitsListModification');
      this.activeModal.close();
    });
  }
}
