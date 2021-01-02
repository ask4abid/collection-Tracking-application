import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IVisits, Visits } from 'app/shared/model/visits.model';
import { VisitsService } from './visits.service';

@Component({
  selector: 'jhi-visits-update',
  templateUrl: './visits-update.component.html',
})
export class VisitsUpdateComponent implements OnInit {
  isSaving = false;
  promisetopayDp: any;

  editForm = this.fb.group({
    id: [],
    unitid: [null, [Validators.required, Validators.maxLength(10)]],
    businessproposal: [null, [Validators.required, Validators.maxLength(10)]],
    subproposal: [null, [Validators.required, Validators.maxLength(10)]],
    promisetopay: [],
    remarks: [null, [Validators.required, Validators.maxLength(100)]],
    visitedby: [null, [Validators.required, Validators.maxLength(30)]],
    employeeid: [null, [Validators.required, Validators.maxLength(10)]],
  });

  constructor(protected visitsService: VisitsService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ visits }) => {
      this.updateForm(visits);
    });
  }

  updateForm(visits: IVisits): void {
    this.editForm.patchValue({
      id: visits.id,
      unitid: visits.unitid,
      businessproposal: visits.businessproposal,
      subproposal: visits.subproposal,
      promisetopay: visits.promisetopay,
      remarks: visits.remarks,
      visitedby: visits.visitedby,
      employeeid: visits.employeeid,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const visits = this.createFromForm();
    if (visits.id !== undefined) {
      this.subscribeToSaveResponse(this.visitsService.update(visits));
    } else {
      this.subscribeToSaveResponse(this.visitsService.create(visits));
    }
  }

  private createFromForm(): IVisits {
    return {
      ...new Visits(),
      id: this.editForm.get(['id'])!.value,
      unitid: this.editForm.get(['unitid'])!.value,
      businessproposal: this.editForm.get(['businessproposal'])!.value,
      subproposal: this.editForm.get(['subproposal'])!.value,
      promisetopay: this.editForm.get(['promisetopay'])!.value,
      remarks: this.editForm.get(['remarks'])!.value,
      visitedby: this.editForm.get(['visitedby'])!.value,
      employeeid: this.editForm.get(['employeeid'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVisits>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
