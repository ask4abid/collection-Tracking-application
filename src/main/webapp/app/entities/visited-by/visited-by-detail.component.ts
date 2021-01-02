import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IVisitedBy } from 'app/shared/model/visited-by.model';

@Component({
  selector: 'jhi-visited-by-detail',
  templateUrl: './visited-by-detail.component.html',
})
export class VisitedByDetailComponent implements OnInit {
  visitedBy: IVisitedBy | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ visitedBy }) => (this.visitedBy = visitedBy));
  }

  previousState(): void {
    window.history.back();
  }
}
