import React, { Component } from 'react';
import './App.css';
import Header from './components/Header';
import PostList from './components/PostList'

class App extends Component {
 
  render() {

    return (
      <div className = "App" >
        <Header />
        <div className = "ui container">
        <PostList />
        </div>
      </div>
    );

  }

}

export default App;
