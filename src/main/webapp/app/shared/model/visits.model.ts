import { Moment } from 'moment';
import { IProosals } from 'app/shared/model/proosals.model';

export interface IVisits {
  id?: number;
  unitid?: string;
  businessproposal?: string;
  subproposal?: string;
  promisetopay?: Moment;
  remarks?: string;
  visitedby?: string;
  employeeid?: string;
  proosals?: IProosals[];
}

export class Visits implements IVisits {
  constructor(
    public id?: number,
    public unitid?: string,
    public businessproposal?: string,
    public subproposal?: string,
    public promisetopay?: Moment,
    public remarks?: string,
    public visitedby?: string,
    public employeeid?: string,
    public proosals?: IProosals[]
  ) {}
}
