import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDeliquency } from 'app/shared/model/deliquency.model';

@Component({
  selector: 'jhi-deliquency-detail',
  templateUrl: './deliquency-detail.component.html',
})
export class DeliquencyDetailComponent implements OnInit {
  deliquency: IDeliquency | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ deliquency }) => (this.deliquency = deliquency));
  }

  previousState(): void {
    window.history.back();
  }
}
