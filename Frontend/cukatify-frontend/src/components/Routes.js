
import React,{Component} from 'react'
import PostList from './PostList';
import NotFound from './NotFound'

import { Route, Switch } from "react-router-dom";
import PostView from './PostView';
import Resource from './Resource';
import Login from './Login'

class Routes extends Component {

    render() {
        return (
            <div className="ui container">
            <Switch>
              <Route exact path="/" component={PostList}></Route>
              <Route exact path="/login" component={Login}></Route>
              <Route exact path="/resource/:keyword" component={Resource}></Route>
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