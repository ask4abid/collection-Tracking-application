import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDeliquency, Deliquency } from 'app/shared/model/deliquency.model';
import { DeliquencyService } from './deliquency.service';

@Component({
  selector: 'jhi-deliquency-update',
  templateUrl: './deliquency-update.component.html',
})
export class DeliquencyUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    observatoinid: [null, [Validators.required, Validators.maxLength(10)]],
    observation: [null, [Validators.required, Validators.maxLength(20)]],
  });

  constructor(protected deliquencyService: DeliquencyService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ deliquency }) => {
      this.updateForm(deliquency);
    });
  }

  updateForm(deliquency: IDeliquency): void {
    this.editForm.patchValue({
      id: deliquency.id,
      observatoinid: deliquency.observatoinid,
      observation: deliquency.observation,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const deliquency = this.createFromForm();
    if (deliquency.id !== undefined) {
      this.subscribeToSaveResponse(this.deliquencyService.update(deliquency));
    } else {
      this.subscribeToSaveResponse(this.deliquencyService.create(deliquency));
    }
  }

  private createFromForm(): IDeliquency {
    return {
      ...new Deliquency(),
      id: this.editForm.get(['id'])!.value,
      observatoinid: this.editForm.get(['observatoinid'])!.value,
      observation: this.editForm.get(['observation'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDeliquency>>): void {
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
