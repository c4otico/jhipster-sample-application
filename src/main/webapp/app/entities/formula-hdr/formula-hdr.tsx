import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './formula-hdr.reducer';
import { IFormulaHdr } from 'app/shared/model/formula-hdr.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFormulaHdrProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const FormulaHdr = (props: IFormulaHdrProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { formulaHdrList, match } = props;
  return (
    <div>
      <h2 id="formula-hdr-heading">
        <Translate contentKey="jhipsterSampleApplicationApp.formulaHdr.home.title">Formula Hdrs</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="jhipsterSampleApplicationApp.formulaHdr.home.createLabel">Create new Formula Hdr</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {formulaHdrList && formulaHdrList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.formulaHdr.xiNumber">Xi Number</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.formulaHdr.frmlname">Frmlname</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.formulaHdr.frmlType">Frml Type</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.formulaHdr.profileNumber">Profile Number</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.formulaHdr.userNumber">User Number</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.formulaHdr.bookNumber">Book Number</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.formulaHdr.mfgNumber">Mfg Number</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.formulaHdr.mfglocation">Mfglocation</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.formulaHdr.cbaliascode">Cbaliascode</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.formulaHdr.description">Description</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.formulaHdr.customer">Customer</Translate>
                </th>
                <th>
                  <Translate contentKey="jhipsterSampleApplicationApp.formulaHdr.baseApplication">Base Application</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {formulaHdrList.map((formulaHdr, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${formulaHdr.id}`} color="link" size="sm">
                      {formulaHdr.id}
                    </Button>
                  </td>
                  <td>{formulaHdr.xiNumber}</td>
                  <td>{formulaHdr.frmlname}</td>
                  <td>{formulaHdr.frmlType}</td>
                  <td>{formulaHdr.profileNumber}</td>
                  <td>{formulaHdr.userNumber}</td>
                  <td>{formulaHdr.bookNumber}</td>
                  <td>{formulaHdr.mfgNumber}</td>
                  <td>{formulaHdr.mfglocation}</td>
                  <td>{formulaHdr.cbaliascode}</td>
                  <td>{formulaHdr.description}</td>
                  <td>{formulaHdr.customer}</td>
                  <td>{formulaHdr.baseApplication}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${formulaHdr.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${formulaHdr.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${formulaHdr.id}/delete`} color="danger" size="sm">
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
            <Translate contentKey="jhipsterSampleApplicationApp.formulaHdr.home.notFound">No Formula Hdrs found</Translate>
          </div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ formulaHdr }: IRootState) => ({
  formulaHdrList: formulaHdr.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(FormulaHdr);
