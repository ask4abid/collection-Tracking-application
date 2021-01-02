import { Moment } from 'moment';
import { IVisitedBy } from 'app/shared/model/visited-by.model';

export interface IProosals {
  id?: number;
  unitid?: string;
  businessproposal?: string;
  subproposal?: string;
  relationid?: string;
  mobilenumber?: string;
  accountnumber?: string;
  accounttitle?: string;
  numerofvisits?: string;
  outstandingamount?: string;
  outstandingprofit?: string;
  oddays?: string;
  loanofficer?: string;
  promisetopay?: Moment;
  remarks?: string;
  delequencyreason?: string;
  visitedBies?: IVisitedBy[];
  visitsId?: number;
  deliquencyId?: number;
}

export class Proosals implements IProosals {
  constructor(
    public id?: number,
    public unitid?: string,
    public businessproposal?: string,
    public subproposal?: string,
    public relationid?: string,
    public mobilenumber?: string,
    public accountnumber?: string,
    public accounttitle?: string,
    public numerofvisits?: string,
    public outstandingamount?: string,
    public outstandingprofit?: string,
    public oddays?: string,
    public loanofficer?: string,
    public promisetopay?: Moment,
    public remarks?: string,
    public delequencyreason?: string,
    public visitedBies?: IVisitedBy[],
    public visitsId?: number,
    public deliquencyId?: number
  ) {}
}
