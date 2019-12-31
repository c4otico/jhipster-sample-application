import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IFormuladtl, defaultValue } from 'app/shared/model/formuladtl.model';

export const ACTION_TYPES = {
  FETCH_FORMULADTL_LIST: 'formuladtl/FETCH_FORMULADTL_LIST',
  FETCH_FORMULADTL: 'formuladtl/FETCH_FORMULADTL',
  CREATE_FORMULADTL: 'formuladtl/CREATE_FORMULADTL',
  UPDATE_FORMULADTL: 'formuladtl/UPDATE_FORMULADTL',
  DELETE_FORMULADTL: 'formuladtl/DELETE_FORMULADTL',
  RESET: 'formuladtl/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IFormuladtl>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type FormuladtlState = Readonly<typeof initialState>;

// Reducer

export default (state: FormuladtlState = initialState, action): FormuladtlState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_FORMULADTL_LIST):
    case REQUEST(ACTION_TYPES.FETCH_FORMULADTL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_FORMULADTL):
    case REQUEST(ACTION_TYPES.UPDATE_FORMULADTL):
    case REQUEST(ACTION_TYPES.DELETE_FORMULADTL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_FORMULADTL_LIST):
    case FAILURE(ACTION_TYPES.FETCH_FORMULADTL):
    case FAILURE(ACTION_TYPES.CREATE_FORMULADTL):
    case FAILURE(ACTION_TYPES.UPDATE_FORMULADTL):
    case FAILURE(ACTION_TYPES.DELETE_FORMULADTL):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_FORMULADTL_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_FORMULADTL):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_FORMULADTL):
    case SUCCESS(ACTION_TYPES.UPDATE_FORMULADTL):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_FORMULADTL):
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

const apiUrl = 'api/formuladtls';

// Actions

export const getEntities: ICrudGetAllAction<IFormuladtl> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_FORMULADTL_LIST,
  payload: axios.get<IFormuladtl>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IFormuladtl> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_FORMULADTL,
    payload: axios.get<IFormuladtl>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IFormuladtl> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_FORMULADTL,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IFormuladtl> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_FORMULADTL,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IFormuladtl> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_FORMULADTL,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
