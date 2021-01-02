import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IVisits } from 'app/shared/model/visits.model';

type EntityResponseType = HttpResponse<IVisits>;
type EntityArrayResponseType = HttpResponse<IVisits[]>;

@Injectable({ providedIn: 'root' })
export class VisitsService {
  public resourceUrl = SERVER_API_URL + 'api/visits';

  constructor(protected http: HttpClient) {}

  create(visits: IVisits): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(visits);
    return this.http
      .post<IVisits>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(visits: IVisits): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(visits);
    return this.http
      .put<IVisits>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IVisits>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IVisits[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(visits: IVisits): IVisits {
    const copy: IVisits = Object.assign({}, visits, {
      promisetopay: visits.promisetopay && visits.promisetopay.isValid() ? visits.promisetopay.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.promisetopay = res.body.promisetopay ? moment(res.body.promisetopay) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((visits: IVisits) => {
        visits.promisetopay = visits.promisetopay ? moment(visits.promisetopay) : undefined;
      });
    }
    return res;
  }
}
