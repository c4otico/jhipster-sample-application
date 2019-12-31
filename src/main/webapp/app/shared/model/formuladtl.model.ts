import { IFormulaHdr } from 'app/shared/model/formula-hdr.model';

export interface IFormuladtl {
  id?: number;
  dtlid?: number;
  dtlseq?: number;
  itemcode?: string;
  qty?: number;
  oldItemcode?: string;
  refxiNumber?: string;
  frmlid?: IFormulaHdr;
}

export const defaultValue: Readonly<IFormuladtl> = {};
