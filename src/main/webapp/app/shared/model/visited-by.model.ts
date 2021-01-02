import { Moment } from 'moment';

export interface IVisitedBy {
  id?: number;
  unitid?: string;
  firstname?: string;
  lastname?: string;
  status?: string;
  employeeid?: string;
  role?: string;
  designation?: string;
  vistdate?: Moment;
  proosalsId?: number;
}

export class VisitedBy implements IVisitedBy {
  constructor(
    public id?: number,
    public unitid?: string,
    public firstname?: string,
    public lastname?: string,
    public status?: string,
    public employeeid?: string,
    public role?: string,
    public designation?: string,
    public vistdate?: Moment,
    public proosalsId?: number
  ) {}
}
