import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Formuladtl from './formuladtl';
import FormuladtlDetail from './formuladtl-detail';
import FormuladtlUpdate from './formuladtl-update';
import FormuladtlDeleteDialog from './formuladtl-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={FormuladtlDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={FormuladtlUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={FormuladtlUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={FormuladtlDetail} />
      <ErrorBoundaryRoute path={match.url} component={Formuladtl} />
    </Switch>
  </>
);

export default Routes;
