import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { ProosalsService } from 'app/entities/proosals/proosals.service';
import { IProosals, Proosals } from 'app/shared/model/proosals.model';

describe('Service Tests', () => {
  describe('Proosals Service', () => {
    let injector: TestBed;
    let service: ProosalsService;
    let httpMock: HttpTestingController;
    let elemDefault: IProosals;
    let expectedResult: IProosals | IProosals[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ProosalsService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Proosals(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA'
      );
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

      it('should create a Proosals', () => {
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

        service.create(new Proosals()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Proosals', () => {
        const returnedFromService = Object.assign(
          {
            unitid: 'BBBBBB',
            businessproposal: 'BBBBBB',
            subproposal: 'BBBBBB',
            relationid: 'BBBBBB',
            mobilenumber: 'BBBBBB',
            accountnumber: 'BBBBBB',
            accounttitle: 'BBBBBB',
            numerofvisits: 'BBBBBB',
            outstandingamount: 'BBBBBB',
            outstandingprofit: 'BBBBBB',
            oddays: 'BBBBBB',
            loanofficer: 'BBBBBB',
            promisetopay: currentDate.format(DATE_FORMAT),
            remarks: 'BBBBBB',
            delequencyreason: 'BBBBBB',
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

      it('should return a list of Proosals', () => {
        const returnedFromService = Object.assign(
          {
            unitid: 'BBBBBB',
            businessproposal: 'BBBBBB',
            subproposal: 'BBBBBB',
            relationid: 'BBBBBB',
            mobilenumber: 'BBBBBB',
            accountnumber: 'BBBBBB',
            accounttitle: 'BBBBBB',
            numerofvisits: 'BBBBBB',
            outstandingamount: 'BBBBBB',
            outstandingprofit: 'BBBBBB',
            oddays: 'BBBBBB',
            loanofficer: 'BBBBBB',
            promisetopay: currentDate.format(DATE_FORMAT),
            remarks: 'BBBBBB',
            delequencyreason: 'BBBBBB',
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

      it('should delete a Proosals', () => {
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
