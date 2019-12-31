import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './formula-hdr.reducer';
import { IFormulaHdr } from 'app/shared/model/formula-hdr.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IFormulaHdrUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const FormulaHdrUpdate = (props: IFormulaHdrUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { formulaHdrEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/formula-hdr');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...formulaHdrEntity,
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
          <h2 id="jhipsterSampleApplicationApp.formulaHdr.home.createOrEditLabel">
            <Translate contentKey="jhipsterSampleApplicationApp.formulaHdr.home.createOrEditLabel">Create or edit a FormulaHdr</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : formulaHdrEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="formula-hdr-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="formula-hdr-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="xiNumberLabel" for="formula-hdr-xiNumber">
                  <Translate contentKey="jhipsterSampleApplicationApp.formulaHdr.xiNumber">Xi Number</Translate>
                </Label>
                <AvField
                  id="formula-hdr-xiNumber"
                  type="text"
                  name="xiNumber"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    maxLength: { value: 18, errorMessage: translate('entity.validation.maxlength', { max: 18 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="frmlnameLabel" for="formula-hdr-frmlname">
                  <Translate contentKey="jhipsterSampleApplicationApp.formulaHdr.frmlname">Frmlname</Translate>
                </Label>
                <AvField
                  id="formula-hdr-frmlname"
                  type="text"
                  name="frmlname"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    maxLength: { value: 50, errorMessage: translate('entity.validation.maxlength', { max: 50 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="frmlTypeLabel" for="formula-hdr-frmlType">
                  <Translate contentKey="jhipsterSampleApplicationApp.formulaHdr.frmlType">Frml Type</Translate>
                </Label>
                <AvField
                  id="formula-hdr-frmlType"
                  type="text"
                  name="frmlType"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    maxLength: { value: 32, errorMessage: translate('entity.validation.maxlength', { max: 32 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="profileNumberLabel" for="formula-hdr-profileNumber">
                  <Translate contentKey="jhipsterSampleApplicationApp.formulaHdr.profileNumber">Profile Number</Translate>
                </Label>
                <AvField
                  id="formula-hdr-profileNumber"
                  type="text"
                  name="profileNumber"
                  validate={{
                    maxLength: { value: 18, errorMessage: translate('entity.validation.maxlength', { max: 18 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="userNumberLabel" for="formula-hdr-userNumber">
                  <Translate contentKey="jhipsterSampleApplicationApp.formulaHdr.userNumber">User Number</Translate>
                </Label>
                <AvField
                  id="formula-hdr-userNumber"
                  type="text"
                  name="userNumber"
                  validate={{
                    maxLength: { value: 18, errorMessage: translate('entity.validation.maxlength', { max: 18 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="bookNumberLabel" for="formula-hdr-bookNumber">
                  <Translate contentKey="jhipsterSampleApplicationApp.formulaHdr.bookNumber">Book Number</Translate>
                </Label>
                <AvField
                  id="formula-hdr-bookNumber"
                  type="text"
                  name="bookNumber"
                  validate={{
                    maxLength: { value: 18, errorMessage: translate('entity.validation.maxlength', { max: 18 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="mfgNumberLabel" for="formula-hdr-mfgNumber">
                  <Translate contentKey="jhipsterSampleApplicationApp.formulaHdr.mfgNumber">Mfg Number</Translate>
                </Label>
                <AvField
                  id="formula-hdr-mfgNumber"
                  type="text"
                  name="mfgNumber"
                  validate={{
                    maxLength: { value: 18, errorMessage: translate('entity.validation.maxlength', { max: 18 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="mfglocationLabel" for="formula-hdr-mfglocation">
                  <Translate contentKey="jhipsterSampleApplicationApp.formulaHdr.mfglocation">Mfglocation</Translate>
                </Label>
                <AvField
                  id="formula-hdr-mfglocation"
                  type="text"
                  name="mfglocation"
                  validate={{
                    maxLength: { value: 8, errorMessage: translate('entity.validation.maxlength', { max: 8 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="cbaliascodeLabel" for="formula-hdr-cbaliascode">
                  <Translate contentKey="jhipsterSampleApplicationApp.formulaHdr.cbaliascode">Cbaliascode</Translate>
                </Label>
                <AvField
                  id="formula-hdr-cbaliascode"
                  type="text"
                  name="cbaliascode"
                  validate={{
                    maxLength: { value: 18, errorMessage: translate('entity.validation.maxlength', { max: 18 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="formula-hdr-description">
                  <Translate contentKey="jhipsterSampleApplicationApp.formulaHdr.description">Description</Translate>
                </Label>
                <AvField
                  id="formula-hdr-description"
                  type="text"
                  name="description"
                  validate={{
                    maxLength: { value: 800, errorMessage: translate('entity.validation.maxlength', { max: 800 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="customerLabel" for="formula-hdr-customer">
                  <Translate contentKey="jhipsterSampleApplicationApp.formulaHdr.customer">Customer</Translate>
                </Label>
                <AvField
                  id="formula-hdr-customer"
                  type="text"
                  name="customer"
                  validate={{
                    maxLength: { value: 32, errorMessage: translate('entity.validation.maxlength', { max: 32 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="baseApplicationLabel" for="formula-hdr-baseApplication">
                  <Translate contentKey="jhipsterSampleApplicationApp.formulaHdr.baseApplication">Base Application</Translate>
                </Label>
                <AvField
                  id="formula-hdr-baseApplication"
                  type="text"
                  name="baseApplication"
                  validate={{
                    maxLength: { value: 32, errorMessage: translate('entity.validation.maxlength', { max: 32 }) }
                  }}
                />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/formula-hdr" replace color="info">
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
  formulaHdrEntity: storeState.formulaHdr.entity,
  loading: storeState.formulaHdr.loading,
  updating: storeState.formulaHdr.updating,
  updateSuccess: storeState.formulaHdr.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(FormulaHdrUpdate);
