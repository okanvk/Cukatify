import React, { Component } from 'react';
import './App.css';
import Header from './components/layout/Header';
import { BrowserRouter } from 'react-router-dom';

import { Provider } from "react-redux";
import store from "./store";
import { SET_CURRENT_USER } from "./actions/types";
import { logout } from "./actions/securityActions";
import jwt_decode from "jwt-decode";
import { setJWTToken } from "./actions/securityActions";




import PostList from './components/post/PostList';
import NotFound from './components/NotFound'

import { Route, Switch } from "react-router-dom";
import PostView from './components/post/PostView';
import Login from './components/auth/Login'
import ArtistView from './components/artist/ArtistView';
import PostAdd from './components/post/PostAdd';
import SecuredRoute from "./utils/SecuredRoute";


setJWTToken(localStorage.setJWTToken)



const jwtToken = localStorage.jwtToken;

if (jwtToken) {
  setJWTToken(jwtToken);
  const decoded_jwtToken = jwt_decode(jwtToken);
  store.dispatch({
    type: SET_CURRENT_USER,
    payload: decoded_jwtToken
  });

  const currentTime = Date.now() / 1000;
  if (decoded_jwtToken.exp < currentTime) {
    store.dispatch(logout());
    window.location.href = "/login";
    
  }
}

class App extends Component {

  render() {

    return (
      <Provider store={store}>
      <BrowserRouter>
        <div className="App" >
          <Header />
          <Switch>
            <Route exact path="/login" component={Login}></Route>
            <SecuredRoute exact path="/" component={PostList}></SecuredRoute>
            <SecuredRoute exact path="/post/add" component={PostAdd}></SecuredRoute>
            <SecuredRoute exact path="/artistView/:name" component={ArtistView}></SecuredRoute>
            <SecuredRoute exact path="/post/:id" component={PostView}></SecuredRoute>
            <SecuredRoute exact path="/time" component={PostList}></SecuredRoute>
            <SecuredRoute exact path="/pictures" component={PostList}></SecuredRoute>
            <SecuredRoute path="*" component={NotFound}></SecuredRoute>
          </Switch>

        </div>
      </BrowserRouter> 
      </Provider>
    );

  }

}

export default App;
