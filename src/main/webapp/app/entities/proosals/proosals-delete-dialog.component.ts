import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProosals } from 'app/shared/model/proosals.model';
import { ProosalsService } from './proosals.service';

@Component({
  templateUrl: './proosals-delete-dialog.component.html',
})
export class ProosalsDeleteDialogComponent {
  proosals?: IProosals;

  constructor(protected proosalsService: ProosalsService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.proosalsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('proosalsListModification');
      this.activeModal.close();
    });
  }
}
