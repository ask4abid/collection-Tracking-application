import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProosals } from 'app/shared/model/proosals.model';

type EntityResponseType = HttpResponse<IProosals>;
type EntityArrayResponseType = HttpResponse<IProosals[]>;

@Injectable({ providedIn: 'root' })
export class ProosalsService {
  public resourceUrl = SERVER_API_URL + 'api/proosals';

  constructor(protected http: HttpClient) {}

  create(proosals: IProosals): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(proosals);
    return this.http
      .post<IProosals>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(proosals: IProosals): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(proosals);
    return this.http
      .put<IProosals>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IProosals>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IProosals[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(proosals: IProosals): IProosals {
    const copy: IProosals = Object.assign({}, proosals, {
      promisetopay: proosals.promisetopay && proosals.promisetopay.isValid() ? proosals.promisetopay.format(DATE_FORMAT) : undefined,
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
      res.body.forEach((proosals: IProosals) => {
        proosals.promisetopay = proosals.promisetopay ? moment(proosals.promisetopay) : undefined;
      });
    }
    return res;
  }
}
