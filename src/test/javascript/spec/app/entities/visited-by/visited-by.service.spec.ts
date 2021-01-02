import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { VisitedByService } from 'app/entities/visited-by/visited-by.service';
import { IVisitedBy, VisitedBy } from 'app/shared/model/visited-by.model';

describe('Service Tests', () => {
  describe('VisitedBy Service', () => {
    let injector: TestBed;
    let service: VisitedByService;
    let httpMock: HttpTestingController;
    let elemDefault: IVisitedBy;
    let expectedResult: IVisitedBy | IVisitedBy[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(VisitedByService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new VisitedBy(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            vistdate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a VisitedBy', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            vistdate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            vistdate: currentDate,
          },
          returnedFromService
        );

        service.create(new VisitedBy()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a VisitedBy', () => {
        const returnedFromService = Object.assign(
          {
            unitid: 'BBBBBB',
            firstname: 'BBBBBB',
            lastname: 'BBBBBB',
            status: 'BBBBBB',
            employeeid: 'BBBBBB',
            role: 'BBBBBB',
            designation: 'BBBBBB',
            vistdate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            vistdate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of VisitedBy', () => {
        const returnedFromService = Object.assign(
          {
            unitid: 'BBBBBB',
            firstname: 'BBBBBB',
            lastname: 'BBBBBB',
            status: 'BBBBBB',
            employeeid: 'BBBBBB',
            role: 'BBBBBB',
            designation: 'BBBBBB',
            vistdate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            vistdate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a VisitedBy', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
