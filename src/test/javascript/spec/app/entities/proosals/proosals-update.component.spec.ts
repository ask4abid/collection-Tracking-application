import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CcwAppTestModule } from '../../../test.module';
import { ProosalsUpdateComponent } from 'app/entities/proosals/proosals-update.component';
import { ProosalsService } from 'app/entities/proosals/proosals.service';
import { Proosals } from 'app/shared/model/proosals.model';

describe('Component Tests', () => {
  describe('Proosals Management Update Component', () => {
    let comp: ProosalsUpdateComponent;
    let fixture: ComponentFixture<ProosalsUpdateComponent>;
    let service: ProosalsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CcwAppTestModule],
        declarations: [ProosalsUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ProosalsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProosalsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProosalsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Proosals(123);
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
        const entity = new Proosals();
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
