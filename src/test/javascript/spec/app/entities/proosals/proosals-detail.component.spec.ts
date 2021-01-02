import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CcwAppTestModule } from '../../../test.module';
import { ProosalsDetailComponent } from 'app/entities/proosals/proosals-detail.component';
import { Proosals } from 'app/shared/model/proosals.model';

describe('Component Tests', () => {
  describe('Proosals Management Detail Component', () => {
    let comp: ProosalsDetailComponent;
    let fixture: ComponentFixture<ProosalsDetailComponent>;
    const route = ({ data: of({ proosals: new Proosals(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CcwAppTestModule],
        declarations: [ProosalsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ProosalsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProosalsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load proosals on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.proosals).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
