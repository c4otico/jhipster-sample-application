import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import FormulaHdr from './formula-hdr';
import FormulaHdrDetail from './formula-hdr-detail';
import FormulaHdrUpdate from './formula-hdr-update';
import FormulaHdrDeleteDialog from './formula-hdr-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={FormulaHdrDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={FormulaHdrUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={FormulaHdrUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={FormulaHdrDetail} />
      <ErrorBoundaryRoute path={match.url} component={FormulaHdr} />
    </Switch>
  </>
);

export default Routes;
