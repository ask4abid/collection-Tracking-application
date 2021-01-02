import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { VisitsService } from 'app/entities/visits/visits.service';
import { IVisits, Visits } from 'app/shared/model/visits.model';

describe('Service Tests', () => {
  describe('Visits Service', () => {
    let injector: TestBed;
    let service: VisitsService;
    let httpMock: HttpTestingController;
    let elemDefault: IVisits;
    let expectedResult: IVisits | IVisits[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(VisitsService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Visits(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', currentDate, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            promisetopay: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Visits', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            promisetopay: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            promisetopay: currentDate,
          },
          returnedFromService
        );

        service.create(new Visits()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Visits', () => {
        const returnedFromService = Object.assign(
          {
            unitid: 'BBBBBB',
            businessproposal: 'BBBBBB',
            subproposal: 'BBBBBB',
            promisetopay: currentDate.format(DATE_FORMAT),
            remarks: 'BBBBBB',
            visitedby: 'BBBBBB',
            employeeid: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            promisetopay: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Visits', () => {
        const returnedFromService = Object.assign(
          {
            unitid: 'BBBBBB',
            businessproposal: 'BBBBBB',
            subproposal: 'BBBBBB',
            promisetopay: currentDate.format(DATE_FORMAT),
            remarks: 'BBBBBB',
            visitedby: 'BBBBBB',
            employeeid: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            promisetopay: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Visits', () => {
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
