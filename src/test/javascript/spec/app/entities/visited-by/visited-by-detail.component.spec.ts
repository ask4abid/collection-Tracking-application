import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CcwAppTestModule } from '../../../test.module';
import { VisitedByDetailComponent } from 'app/entities/visited-by/visited-by-detail.component';
import { VisitedBy } from 'app/shared/model/visited-by.model';

describe('Component Tests', () => {
  describe('VisitedBy Management Detail Component', () => {
    let comp: VisitedByDetailComponent;
    let fixture: ComponentFixture<VisitedByDetailComponent>;
    const route = ({ data: of({ visitedBy: new VisitedBy(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CcwAppTestModule],
        declarations: [VisitedByDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(VisitedByDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(VisitedByDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load visitedBy on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.visitedBy).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
