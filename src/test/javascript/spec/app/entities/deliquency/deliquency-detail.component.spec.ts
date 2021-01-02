import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CcwAppTestModule } from '../../../test.module';
import { DeliquencyDetailComponent } from 'app/entities/deliquency/deliquency-detail.component';
import { Deliquency } from 'app/shared/model/deliquency.model';

describe('Component Tests', () => {
  describe('Deliquency Management Detail Component', () => {
    let comp: DeliquencyDetailComponent;
    let fixture: ComponentFixture<DeliquencyDetailComponent>;
    const route = ({ data: of({ deliquency: new Deliquency(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CcwAppTestModule],
        declarations: [DeliquencyDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(DeliquencyDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DeliquencyDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load deliquency on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.deliquency).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
