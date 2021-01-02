import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CcwAppTestModule } from '../../../test.module';
import { VisitsUpdateComponent } from 'app/entities/visits/visits-update.component';
import { VisitsService } from 'app/entities/visits/visits.service';
import { Visits } from 'app/shared/model/visits.model';

describe('Component Tests', () => {
  describe('Visits Management Update Component', () => {
    let comp: VisitsUpdateComponent;
    let fixture: ComponentFixture<VisitsUpdateComponent>;
    let service: VisitsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CcwAppTestModule],
        declarations: [VisitsUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(VisitsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(VisitsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(VisitsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Visits(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Visits();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
