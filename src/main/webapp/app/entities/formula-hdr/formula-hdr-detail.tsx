import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './formula-hdr.reducer';
import { IFormulaHdr } from 'app/shared/model/formula-hdr.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFormulaHdrDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const FormulaHdrDetail = (props: IFormulaHdrDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { formulaHdrEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="jhipsterSampleApplicationApp.formulaHdr.detail.title">FormulaHdr</Translate> [<b>{formulaHdrEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="xiNumber">
              <Translate contentKey="jhipsterSampleApplicationApp.formulaHdr.xiNumber">Xi Number</Translate>
            </span>
          </dt>
          <dd>{formulaHdrEntity.xiNumber}</dd>
          <dt>
            <span id="frmlname">
              <Translate contentKey="jhipsterSampleApplicationApp.formulaHdr.frmlname">Frmlname</Translate>
            </span>
          </dt>
          <dd>{formulaHdrEntity.frmlname}</dd>
          <dt>
            <span id="frmlType">
              <Translate contentKey="jhipsterSampleApplicationApp.formulaHdr.frmlType">Frml Type</Translate>
            </span>
          </dt>
          <dd>{formulaHdrEntity.frmlType}</dd>
          <dt>
            <span id="profileNumber">
              <Translate contentKey="jhipsterSampleApplicationApp.formulaHdr.profileNumber">Profile Number</Translate>
            </span>
          </dt>
          <dd>{formulaHdrEntity.profileNumber}</dd>
          <dt>
            <span id="userNumber">
              <Translate contentKey="jhipsterSampleApplicationApp.formulaHdr.userNumber">User Number</Translate>
            </span>
          </dt>
          <dd>{formulaHdrEntity.userNumber}</dd>
          <dt>
            <span id="bookNumber">
              <Translate contentKey="jhipsterSampleApplicationApp.formulaHdr.bookNumber">Book Number</Translate>
            </span>
          </dt>
          <dd>{formulaHdrEntity.bookNumber}</dd>
          <dt>
            <span id="mfgNumber">
              <Translate contentKey="jhipsterSampleApplicationApp.formulaHdr.mfgNumber">Mfg Number</Translate>
            </span>
          </dt>
          <dd>{formulaHdrEntity.mfgNumber}</dd>
          <dt>
            <span id="mfglocation">
              <Translate contentKey="jhipsterSampleApplicationApp.formulaHdr.mfglocation">Mfglocation</Translate>
            </span>
          </dt>
          <dd>{formulaHdrEntity.mfglocation}</dd>
          <dt>
            <span id="cbaliascode">
              <Translate contentKey="jhipsterSampleApplicationApp.formulaHdr.cbaliascode">Cbaliascode</Translate>
            </span>
          </dt>
          <dd>{formulaHdrEntity.cbaliascode}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="jhipsterSampleApplicationApp.formulaHdr.description">Description</Translate>
            </span>
          </dt>
          <dd>{formulaHdrEntity.description}</dd>
          <dt>
            <span id="customer">
              <Translate contentKey="jhipsterSampleApplicationApp.formulaHdr.customer">Customer</Translate>
            </span>
          </dt>
          <dd>{formulaHdrEntity.customer}</dd>
          <dt>
            <span id="baseApplication">
              <Translate contentKey="jhipsterSampleApplicationApp.formulaHdr.baseApplication">Base Application</Translate>
            </span>
          </dt>
          <dd>{formulaHdrEntity.baseApplication}</dd>
        </dl>
        <Button tag={Link} to="/formula-hdr" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/formula-hdr/${formulaHdrEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ formulaHdr }: IRootState) => ({
  formulaHdrEntity: formulaHdr.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(FormulaHdrDetail);
