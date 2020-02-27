import React, { Component } from 'react';
import './App.css';
import Header from './components/layout/Header';
import { BrowserRouter } from 'react-router-dom';
import Routes from './components/Routes'

class App extends Component {

  render() {

    return (
      <BrowserRouter>
        <div className="App" >
          <Header />
          <Routes />
        </div>
      </BrowserRouter> 
    );

  }

}

export default App;
