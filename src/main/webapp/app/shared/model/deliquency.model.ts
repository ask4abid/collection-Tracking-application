import { IProosals } from 'app/shared/model/proosals.model';

export interface IDeliquency {
  id?: number;
  observatoinid?: string;
  observation?: string;
  proosals?: IProosals[];
}

export class Deliquency implements IDeliquency {
  constructor(public id?: number, public observatoinid?: string, public observation?: string, public proosals?: IProosals[]) {}
}
