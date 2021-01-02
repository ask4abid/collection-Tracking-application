import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IVisitedBy } from 'app/shared/model/visited-by.model';

type EntityResponseType = HttpResponse<IVisitedBy>;
type EntityArrayResponseType = HttpResponse<IVisitedBy[]>;

@Injectable({ providedIn: 'root' })
export class VisitedByService {
  public resourceUrl = SERVER_API_URL + 'api/visited-bies';

  constructor(protected http: HttpClient) {}

  create(visitedBy: IVisitedBy): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(visitedBy);
    return this.http
      .post<IVisitedBy>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(visitedBy: IVisitedBy): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(visitedBy);
    return this.http
      .put<IVisitedBy>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IVisitedBy>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IVisitedBy[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(visitedBy: IVisitedBy): IVisitedBy {
    const copy: IVisitedBy = Object.assign({}, visitedBy, {
      vistdate: visitedBy.vistdate && visitedBy.vistdate.isValid() ? visitedBy.vistdate.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.vistdate = res.body.vistdate ? moment(res.body.vistdate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((visitedBy: IVisitedBy) => {
        visitedBy.vistdate = visitedBy.vistdate ? moment(visitedBy.vistdate) : undefined;
      });
    }
    return res;
  }
}
