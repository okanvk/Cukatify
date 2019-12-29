
import React,{Component} from 'react'
import PostList from './PostList';
import NotFound from './NotFound'

import { Route, Switch } from "react-router-dom";
import PostView from './PostView';
import Login from './Login'
import ArtistView from './ArtistView';

class Routes extends Component {

    render() {
        return (
            <div className="ui container">
            <Switch>
              <Route exact path="/" component={PostList}></Route>
              <Route exact path="/login" component={Login}></Route>
              <Route exact path="/artistView/:name" component={ArtistView}></Route>
              <Route exact path="/post/:id" component={PostView}></Route>
              <Route exact path="/time" component={PostList}></Route>
              <Route exact path="/pictures" component={PostList}></Route>
              <Route path="*" component={NotFound}></Route>
            </Switch>
          </div>
        )
    }
}

export default Routes;