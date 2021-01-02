import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDeliquency } from 'app/shared/model/deliquency.model';
import { DeliquencyService } from './deliquency.service';

@Component({
  templateUrl: './deliquency-delete-dialog.component.html',
})
export class DeliquencyDeleteDialogComponent {
  deliquency?: IDeliquency;

  constructor(
    protected deliquencyService: DeliquencyService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.deliquencyService.delete(id).subscribe(() => {
      this.eventManager.broadcast('deliquencyListModification');
      this.activeModal.close();
    });
  }
}
