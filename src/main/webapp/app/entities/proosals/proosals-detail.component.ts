import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProosals } from 'app/shared/model/proosals.model';

@Component({
  selector: 'jhi-proosals-detail',
  templateUrl: './proosals-detail.component.html',
})
export class ProosalsDetailComponent implements OnInit {
  proosals: IProosals | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ proosals }) => (this.proosals = proosals));
  }

  previousState(): void {
    window.history.back();
  }
}
