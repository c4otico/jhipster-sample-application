import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IFormulaHdr } from 'app/shared/model/formula-hdr.model';
import { getEntities as getFormulaHdrs } from 'app/entities/formula-hdr/formula-hdr.reducer';
import { getEntity, updateEntity, createEntity, reset } from './formuladtl.reducer';
import { IFormuladtl } from 'app/shared/model/formuladtl.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IFormuladtlUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const FormuladtlUpdate = (props: IFormuladtlUpdateProps) => {
  const [frmlidId, setFrmlidId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { formuladtlEntity, formulaHdrs, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/formuladtl');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getFormulaHdrs();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...formuladtlEntity,
        ...values
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="jhipsterSampleApplicationApp.formuladtl.home.createOrEditLabel">
            <Translate contentKey="jhipsterSampleApplicationApp.formuladtl.home.createOrEditLabel">Create or edit a Formuladtl</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : formuladtlEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="formuladtl-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="formuladtl-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="dtlidLabel" for="formuladtl-dtlid">
                  <Translate contentKey="jhipsterSampleApplicationApp.formuladtl.dtlid">Dtlid</Translate>
                </Label>
                <AvField id="formuladtl-dtlid" type="string" className="form-control" name="dtlid" />
              </AvGroup>
              <AvGroup>
                <Label id="dtlseqLabel" for="formuladtl-dtlseq">
                  <Translate contentKey="jhipsterSampleApplicationApp.formuladtl.dtlseq">Dtlseq</Translate>
                </Label>
                <AvField id="formuladtl-dtlseq" type="string" className="form-control" name="dtlseq" />
              </AvGroup>
              <AvGroup>
                <Label id="itemcodeLabel" for="formuladtl-itemcode">
                  <Translate contentKey="jhipsterSampleApplicationApp.formuladtl.itemcode">Itemcode</Translate>
                </Label>
                <AvField
                  id="formuladtl-itemcode"
                  type="text"
                  name="itemcode"
                  validate={{
                    maxLength: { value: 18, errorMessage: translate('entity.validation.maxlength', { max: 18 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="qtyLabel" for="formuladtl-qty">
                  <Translate contentKey="jhipsterSampleApplicationApp.formuladtl.qty">Qty</Translate>
                </Label>
                <AvField id="formuladtl-qty" type="string" className="form-control" name="qty" />
              </AvGroup>
              <AvGroup>
                <Label id="oldItemcodeLabel" for="formuladtl-oldItemcode">
                  <Translate contentKey="jhipsterSampleApplicationApp.formuladtl.oldItemcode">Old Itemcode</Translate>
                </Label>
                <AvField
                  id="formuladtl-oldItemcode"
                  type="text"
                  name="oldItemcode"
                  validate={{
                    maxLength: { value: 18, errorMessage: translate('entity.validation.maxlength', { max: 18 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="refxiNumberLabel" for="formuladtl-refxiNumber">
                  <Translate contentKey="jhipsterSampleApplicationApp.formuladtl.refxiNumber">Refxi Number</Translate>
                </Label>
                <AvField
                  id="formuladtl-refxiNumber"
                  type="text"
                  name="refxiNumber"
                  validate={{
                    maxLength: { value: 18, errorMessage: translate('entity.validation.maxlength', { max: 18 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label for="formuladtl-frmlid">
                  <Translate contentKey="jhipsterSampleApplicationApp.formuladtl.frmlid">Frmlid</Translate>
                </Label>
                <AvInput id="formuladtl-frmlid" type="select" className="form-control" name="frmlid.id">
                  <option value="" key="0" />
                  {formulaHdrs
                    ? formulaHdrs.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/formuladtl" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  formulaHdrs: storeState.formulaHdr.entities,
  formuladtlEntity: storeState.formuladtl.entity,
  loading: storeState.formuladtl.loading,
  updating: storeState.formuladtl.updating,
  updateSuccess: storeState.formuladtl.updateSuccess
});

const mapDispatchToProps = {
  getFormulaHdrs,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(FormuladtlUpdate);
