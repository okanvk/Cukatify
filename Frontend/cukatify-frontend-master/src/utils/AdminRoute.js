import React from "react";
import { Route, Redirect } from "react-router-dom";
import { connect } from "react-redux";

const AdminRoute = ({ component: Component, security, ...otherProps }) => (
  <Route
    {...otherProps}
    render={props =>
        security.user.scopes.includes("ADMIN") ? (
        <Component {...props} />
      ) : (
        <Redirect to="/post/list/0" />
      )
    }
  />
);



const mapStateToProps = ({security}) => {
  return { security: security }
};

export default connect(mapStateToProps)(AdminRoute);
