
import React,{Component} from 'react'
import PostList from './post/PostList';
import NotFound from './NotFound'

import { Route, Switch } from "react-router-dom";
import PostView from './post/PostView';
import Login from './auth/Login'
import ArtistView from './artist/ArtistView';
import PostAdd from './post/PostAdd';
import SecuredRoute from "../utils/SecuredRoute";



class Routes extends Component {

    render() {
        return (
            <Switch>
            <Route exact path="/login" component={Login}></Route>
              <SecuredRoute  path="/post/list/:id" component={PostList}></SecuredRoute>
              <SecuredRoute  path="/post/add" component={PostAdd}></SecuredRoute>
              <SecuredRoute  path="/post/:id" component={PostView}></SecuredRoute>
              <SecuredRoute exact path="/artistView/:name" component={ArtistView}></SecuredRoute>
              <SecuredRoute path="*" component={NotFound}></SecuredRoute>
            </Switch>
        )
    }
}

export default Routes;