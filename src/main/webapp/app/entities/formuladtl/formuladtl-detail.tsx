import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './formuladtl.reducer';
import { IFormuladtl } from 'app/shared/model/formuladtl.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFormuladtlDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const FormuladtlDetail = (props: IFormuladtlDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { formuladtlEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="jhipsterSampleApplicationApp.formuladtl.detail.title">Formuladtl</Translate> [<b>{formuladtlEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="dtlid">
              <Translate contentKey="jhipsterSampleApplicationApp.formuladtl.dtlid">Dtlid</Translate>
            </span>
          </dt>
          <dd>{formuladtlEntity.dtlid}</dd>
          <dt>
            <span id="dtlseq">
              <Translate contentKey="jhipsterSampleApplicationApp.formuladtl.dtlseq">Dtlseq</Translate>
            </span>
          </dt>
          <dd>{formuladtlEntity.dtlseq}</dd>
          <dt>
            <span id="itemcode">
              <Translate contentKey="jhipsterSampleApplicationApp.formuladtl.itemcode">Itemcode</Translate>
            </span>
          </dt>
          <dd>{formuladtlEntity.itemcode}</dd>
          <dt>
            <span id="qty">
              <Translate contentKey="jhipsterSampleApplicationApp.formuladtl.qty">Qty</Translate>
            </span>
          </dt>
          <dd>{formuladtlEntity.qty}</dd>
          <dt>
            <span id="oldItemcode">
              <Translate contentKey="jhipsterSampleApplicationApp.formuladtl.oldItemcode">Old Itemcode</Translate>
            </span>
          </dt>
          <dd>{formuladtlEntity.oldItemcode}</dd>
          <dt>
            <span id="refxiNumber">
              <Translate contentKey="jhipsterSampleApplicationApp.formuladtl.refxiNumber">Refxi Number</Translate>
            </span>
          </dt>
          <dd>{formuladtlEntity.refxiNumber}</dd>
          <dt>
            <Translate contentKey="jhipsterSampleApplicationApp.formuladtl.frmlid">Frmlid</Translate>
          </dt>
          <dd>{formuladtlEntity.frmlid ? formuladtlEntity.frmlid.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/formuladtl" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/formuladtl/${formuladtlEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ formuladtl }: IRootState) => ({
  formuladtlEntity: formuladtl.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(FormuladtlDetail);
