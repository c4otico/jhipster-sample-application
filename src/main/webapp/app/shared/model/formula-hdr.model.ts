export interface IFormulaHdr {
  id?: number;
  xiNumber?: string;
  frmlname?: string;
  frmlType?: string;
  profileNumber?: string;
  userNumber?: string;
  bookNumber?: string;
  mfgNumber?: string;
  mfglocation?: string;
  cbaliascode?: string;
  description?: string;
  customer?: string;
  baseApplication?: string;
}

export const defaultValue: Readonly<IFormulaHdr> = {};
