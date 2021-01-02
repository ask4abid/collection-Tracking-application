import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CcwAppTestModule } from '../../../test.module';
import { VisitedByUpdateComponent } from 'app/entities/visited-by/visited-by-update.component';
import { VisitedByService } from 'app/entities/visited-by/visited-by.service';
import { VisitedBy } from 'app/shared/model/visited-by.model';

describe('Component Tests', () => {
  describe('VisitedBy Management Update Component', () => {
    let comp: VisitedByUpdateComponent;
    let fixture: ComponentFixture<VisitedByUpdateComponent>;
    let service: VisitedByService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CcwAppTestModule],
        declarations: [VisitedByUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(VisitedByUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(VisitedByUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(VisitedByService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new VisitedBy(123);
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
        const entity = new VisitedBy();
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
