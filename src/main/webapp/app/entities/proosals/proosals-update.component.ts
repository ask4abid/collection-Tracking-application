import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IProosals, Proosals } from 'app/shared/model/proosals.model';
import { ProosalsService } from './proosals.service';
import { IVisits } from 'app/shared/model/visits.model';
import { VisitsService } from 'app/entities/visits/visits.service';
import { IDeliquency } from 'app/shared/model/deliquency.model';
import { DeliquencyService } from 'app/entities/deliquency/deliquency.service';

type SelectableEntity = IVisits | IDeliquency;

@Component({
  selector: 'jhi-proosals-update',
  templateUrl: './proosals-update.component.html',
})
export class ProosalsUpdateComponent implements OnInit {
  isSaving = false;
  visits: IVisits[] = [];
  deliquencies: IDeliquency[] = [];
  promisetopayDp: any;

  editForm = this.fb.group({
    id: [],
    unitid: [null, [Validators.required, Validators.maxLength(10)]],
    businessproposal: [null, [Validators.required, Validators.maxLength(10)]],
    subproposal: [null, [Validators.required, Validators.maxLength(10)]],
    relationid: [null, [Validators.required, Validators.maxLength(10)]],
    mobilenumber: [null, [Validators.required, Validators.maxLength(15)]],
    accountnumber: [null, [Validators.required, Validators.maxLength(20)]],
    accounttitle: [null, [Validators.required, Validators.maxLength(40)]],
    numerofvisits: [null, [Validators.required, Validators.maxLength(10)]],
    outstandingamount: [null, [Validators.required, Validators.maxLength(100)]],
    outstandingprofit: [null, [Validators.required, Validators.maxLength(100)]],
    oddays: [null, [Validators.required, Validators.maxLength(10)]],
    loanofficer: [null, [Validators.required, Validators.maxLength(10)]],
    promisetopay: [],
    remarks: [null, [Validators.required, Validators.maxLength(100)]],
    delequencyreason: [null, [Validators.required, Validators.maxLength(100)]],
    visitsId: [],
    deliquencyId: [],
  });

  constructor(
    protected proosalsService: ProosalsService,
    protected visitsService: VisitsService,
    protected deliquencyService: DeliquencyService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ proosals }) => {
      this.updateForm(proosals);

      this.visitsService.query().subscribe((res: HttpResponse<IVisits[]>) => (this.visits = res.body || []));

      this.deliquencyService.query().subscribe((res: HttpResponse<IDeliquency[]>) => (this.deliquencies = res.body || []));
    });
  }

  updateForm(proosals: IProosals): void {
    this.editForm.patchValue({
      id: proosals.id,
      unitid: proosals.unitid,
      businessproposal: proosals.businessproposal,
      subproposal: proosals.subproposal,
      relationid: proosals.relationid,
      mobilenumber: proosals.mobilenumber,
      accountnumber: proosals.accountnumber,
      accounttitle: proosals.accounttitle,
      numerofvisits: proosals.numerofvisits,
      outstandingamount: proosals.outstandingamount,
      outstandingprofit: proosals.outstandingprofit,
      oddays: proosals.oddays,
      loanofficer: proosals.loanofficer,
      promisetopay: proosals.promisetopay,
      remarks: proosals.remarks,
      delequencyreason: proosals.delequencyreason,
      visitsId: proosals.visitsId,
      deliquencyId: proosals.deliquencyId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const proosals = this.createFromForm();
    if (proosals.id !== undefined) {
      this.subscribeToSaveResponse(this.proosalsService.update(proosals));
    } else {
      this.subscribeToSaveResponse(this.proosalsService.create(proosals));
    }
  }

  private createFromForm(): IProosals {
    return {
      ...new Proosals(),
      id: this.editForm.get(['id'])!.value,
      unitid: this.editForm.get(['unitid'])!.value,
      businessproposal: this.editForm.get(['businessproposal'])!.value,
      subproposal: this.editForm.get(['subproposal'])!.value,
      relationid: this.editForm.get(['relationid'])!.value,
      mobilenumber: this.editForm.get(['mobilenumber'])!.value,
      accountnumber: this.editForm.get(['accountnumber'])!.value,
      accounttitle: this.editForm.get(['accounttitle'])!.value,
      numerofvisits: this.editForm.get(['numerofvisits'])!.value,
      outstandingamount: this.editForm.get(['outstandingamount'])!.value,
      outstandingprofit: this.editForm.get(['outstandingprofit'])!.value,
      oddays: this.editForm.get(['oddays'])!.value,
      loanofficer: this.editForm.get(['loanofficer'])!.value,
      promisetopay: this.editForm.get(['promisetopay'])!.value,
      remarks: this.editForm.get(['remarks'])!.value,
      delequencyreason: this.editForm.get(['delequencyreason'])!.value,
      visitsId: this.editForm.get(['visitsId'])!.value,
      deliquencyId: this.editForm.get(['deliquencyId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProosals>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
