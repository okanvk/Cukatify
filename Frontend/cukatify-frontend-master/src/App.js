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
import Routes from './components/Routes';


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
          <Routes />
        </div>
      </BrowserRouter> 
      </Provider>
    );

  }

}

export default App;
