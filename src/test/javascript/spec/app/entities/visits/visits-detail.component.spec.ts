import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CcwAppTestModule } from '../../../test.module';
import { VisitsDetailComponent } from 'app/entities/visits/visits-detail.component';
import { Visits } from 'app/shared/model/visits.model';

describe('Component Tests', () => {
  describe('Visits Management Detail Component', () => {
    let comp: VisitsDetailComponent;
    let fixture: ComponentFixture<VisitsDetailComponent>;
    const route = ({ data: of({ visits: new Visits(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CcwAppTestModule],
        declarations: [VisitsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(VisitsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(VisitsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load visits on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.visits).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
