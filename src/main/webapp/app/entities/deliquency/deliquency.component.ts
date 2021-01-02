import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDeliquency } from 'app/shared/model/deliquency.model';
import { DeliquencyService } from './deliquency.service';
import { DeliquencyDeleteDialogComponent } from './deliquency-delete-dialog.component';

@Component({
  selector: 'jhi-deliquency',
  templateUrl: './deliquency.component.html',
})
export class DeliquencyComponent implements OnInit, OnDestroy {
  deliquencies?: IDeliquency[];
  eventSubscriber?: Subscription;

  constructor(protected deliquencyService: DeliquencyService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.deliquencyService.query().subscribe((res: HttpResponse<IDeliquency[]>) => (this.deliquencies = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInDeliquencies();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IDeliquency): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInDeliquencies(): void {
    this.eventSubscriber = this.eventManager.subscribe('deliquencyListModification', () => this.loadAll());
  }

  delete(deliquency: IDeliquency): void {
    const modalRef = this.modalService.open(DeliquencyDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.deliquency = deliquency;
  }
}
