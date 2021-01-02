import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CcwAppTestModule } from '../../../test.module';
import { DeliquencyUpdateComponent } from 'app/entities/deliquency/deliquency-update.component';
import { DeliquencyService } from 'app/entities/deliquency/deliquency.service';
import { Deliquency } from 'app/shared/model/deliquency.model';

describe('Component Tests', () => {
  describe('Deliquency Management Update Component', () => {
    let comp: DeliquencyUpdateComponent;
    let fixture: ComponentFixture<DeliquencyUpdateComponent>;
    let service: DeliquencyService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CcwAppTestModule],
        declarations: [DeliquencyUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(DeliquencyUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DeliquencyUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DeliquencyService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Deliquency(123);
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
        const entity = new Deliquency();
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
