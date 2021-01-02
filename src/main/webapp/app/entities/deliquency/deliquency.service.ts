import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDeliquency } from 'app/shared/model/deliquency.model';

type EntityResponseType = HttpResponse<IDeliquency>;
type EntityArrayResponseType = HttpResponse<IDeliquency[]>;

@Injectable({ providedIn: 'root' })
export class DeliquencyService {
  public resourceUrl = SERVER_API_URL + 'api/deliquencies';

  constructor(protected http: HttpClient) {}

  create(deliquency: IDeliquency): Observable<EntityResponseType> {
    return this.http.post<IDeliquency>(this.resourceUrl, deliquency, { observe: 'response' });
  }

  update(deliquency: IDeliquency): Observable<EntityResponseType> {
    return this.http.put<IDeliquency>(this.resourceUrl, deliquency, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDeliquency>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDeliquency[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
