import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IFormulaHdr, defaultValue } from 'app/shared/model/formula-hdr.model';

export const ACTION_TYPES = {
  FETCH_FORMULAHDR_LIST: 'formulaHdr/FETCH_FORMULAHDR_LIST',
  FETCH_FORMULAHDR: 'formulaHdr/FETCH_FORMULAHDR',
  CREATE_FORMULAHDR: 'formulaHdr/CREATE_FORMULAHDR',
  UPDATE_FORMULAHDR: 'formulaHdr/UPDATE_FORMULAHDR',
  DELETE_FORMULAHDR: 'formulaHdr/DELETE_FORMULAHDR',
  RESET: 'formulaHdr/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IFormulaHdr>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type FormulaHdrState = Readonly<typeof initialState>;

// Reducer

export default (state: FormulaHdrState = initialState, action): FormulaHdrState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_FORMULAHDR_LIST):
    case REQUEST(ACTION_TYPES.FETCH_FORMULAHDR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_FORMULAHDR):
    case REQUEST(ACTION_TYPES.UPDATE_FORMULAHDR):
    case REQUEST(ACTION_TYPES.DELETE_FORMULAHDR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_FORMULAHDR_LIST):
    case FAILURE(ACTION_TYPES.FETCH_FORMULAHDR):
    case FAILURE(ACTION_TYPES.CREATE_FORMULAHDR):
    case FAILURE(ACTION_TYPES.UPDATE_FORMULAHDR):
    case FAILURE(ACTION_TYPES.DELETE_FORMULAHDR):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_FORMULAHDR_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_FORMULAHDR):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_FORMULAHDR):
    case SUCCESS(ACTION_TYPES.UPDATE_FORMULAHDR):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_FORMULAHDR):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/formula-hdrs';

// Actions

export const getEntities: ICrudGetAllAction<IFormulaHdr> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_FORMULAHDR_LIST,
  payload: axios.get<IFormulaHdr>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IFormulaHdr> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_FORMULAHDR,
    payload: axios.get<IFormulaHdr>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IFormulaHdr> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_FORMULAHDR,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IFormulaHdr> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_FORMULAHDR,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IFormulaHdr> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_FORMULAHDR,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
