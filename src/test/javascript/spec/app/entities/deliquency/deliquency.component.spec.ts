import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CcwAppTestModule } from '../../../test.module';
import { DeliquencyComponent } from 'app/entities/deliquency/deliquency.component';
import { DeliquencyService } from 'app/entities/deliquency/deliquency.service';
import { Deliquency } from 'app/shared/model/deliquency.model';

describe('Component Tests', () => {
  describe('Deliquency Management Component', () => {
    let comp: DeliquencyComponent;
    let fixture: ComponentFixture<DeliquencyComponent>;
    let service: DeliquencyService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CcwAppTestModule],
        declarations: [DeliquencyComponent],
      })
        .overrideTemplate(DeliquencyComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DeliquencyComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DeliquencyService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Deliquency(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.deliquencies && comp.deliquencies[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
