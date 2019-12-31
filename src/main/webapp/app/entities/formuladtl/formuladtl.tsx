import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './formuladtl.reducer';
import { IFormuladtl } from 'app/shared/model/formuladtl.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFormuladtlProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Formuladtl = (props: IFormuladtlProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { formuladtlList, match } = props;
  return (
    <div>
      <h2 id="formuladtl-heading">
        <Translate contentKey="jhipsterSampleApplicationApp.formuladtl.home.title">Formuladtls</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="jhipsterSampleApplicationApp.formuladtl.home.createLabel">Create new Formuladtl</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {formuladtlList && formuladtlList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.formuladtl.dtlid">Dtlid</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.formuladtl.dtlseq">Dtlseq</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.formuladtl.itemcode">Itemcode</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.formuladtl.qty">Qty</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.formuladtl.oldItemcode">Old Itemcode</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.formuladtl.refxiNumber">Refxi Number</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.formuladtl.frmlid">Frmlid</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {formuladtlList.map((formuladtl, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${formuladtl.id}`} color="link" size="sm">
                      {formuladtl.id}
                    </Button>
                  </td>
                  <td>{formuladtl.dtlid}</td>
                  <td>{formuladtl.dtlseq}</td>
                  <td>{formuladtl.itemcode}</td>
                  <td>{formuladtl.qty}</td>
                  <td>{formuladtl.oldItemcode}</td>
                  <td>{formuladtl.refxiNumber}</td>
                  <td>{formuladtl.frmlid ? <Link to={`formula-hdr/${formuladtl.frmlid.id}`}>{formuladtl.frmlid.id}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${formuladtl.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${formuladtl.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${formuladtl.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          <div className="alert alert-warning">
            <Translate contentKey="jhipsterSampleApplicationApp.formuladtl.home.notFound">No Formuladtls found</Translate>
          </div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ formuladtl }: IRootState) => ({
  formuladtlList: formuladtl.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Formuladtl);
