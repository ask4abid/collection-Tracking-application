import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IVisitedBy, VisitedBy } from 'app/shared/model/visited-by.model';
import { VisitedByService } from './visited-by.service';
import { IProosals } from 'app/shared/model/proosals.model';
import { ProosalsService } from 'app/entities/proosals/proosals.service';

@Component({
  selector: 'jhi-visited-by-update',
  templateUrl: './visited-by-update.component.html',
})
export class VisitedByUpdateComponent implements OnInit {
  isSaving = false;
  proosals: IProosals[] = [];
  vistdateDp: any;

  editForm = this.fb.group({
    id: [],
    unitid: [null, [Validators.required, Validators.maxLength(10)]],
    firstname: [null, [Validators.required, Validators.maxLength(10)]],
    lastname: [null, [Validators.required, Validators.maxLength(10)]],
    status: [null, [Validators.required, Validators.maxLength(30)]],
    employeeid: [null, [Validators.required, Validators.maxLength(10)]],
    role: [null, [Validators.required, Validators.maxLength(10)]],
    designation: [null, [Validators.required, Validators.maxLength(10)]],
    vistdate: [],
    proosalsId: [],
  });

  constructor(
    protected visitedByService: VisitedByService,
    protected proosalsService: ProosalsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ visitedBy }) => {
      this.updateForm(visitedBy);

      this.proosalsService.query().subscribe((res: HttpResponse<IProosals[]>) => (this.proosals = res.body || []));
    });
  }

  updateForm(visitedBy: IVisitedBy): void {
    this.editForm.patchValue({
      id: visitedBy.id,
      unitid: visitedBy.unitid,
      firstname: visitedBy.firstname,
      lastname: visitedBy.lastname,
      status: visitedBy.status,
      employeeid: visitedBy.employeeid,
      role: visitedBy.role,
      designation: visitedBy.designation,
      vistdate: visitedBy.vistdate,
      proosalsId: visitedBy.proosalsId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const visitedBy = this.createFromForm();
    if (visitedBy.id !== undefined) {
      this.subscribeToSaveResponse(this.visitedByService.update(visitedBy));
    } else {
      this.subscribeToSaveResponse(this.visitedByService.create(visitedBy));
    }
  }

  private createFromForm(): IVisitedBy {
    return {
      ...new VisitedBy(),
      id: this.editForm.get(['id'])!.value,
      unitid: this.editForm.get(['unitid'])!.value,
      firstname: this.editForm.get(['firstname'])!.value,
      lastname: this.editForm.get(['lastname'])!.value,
      status: this.editForm.get(['status'])!.value,
      employeeid: this.editForm.get(['employeeid'])!.value,
      role: this.editForm.get(['role'])!.value,
      designation: this.editForm.get(['designation'])!.value,
      vistdate: this.editForm.get(['vistdate'])!.value,
      proosalsId: this.editForm.get(['proosalsId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVisitedBy>>): void {
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

  trackById(index: number, item: IProosals): any {
    return item.id;
  }
}
