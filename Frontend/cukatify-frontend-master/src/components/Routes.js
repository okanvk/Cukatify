
import React,{Component} from 'react'
import PostList from './post/PostList';
import NotFound from './NotFound'
import Register from './user/Register'
import { Route, Switch } from "react-router-dom";
import PostView from './post/PostView';
import Login from './auth/Login'
import ArtistView from './artist/ArtistView';
import PostAdd from './post/PostAdd';
import SecuredRoute from "../utils/SecuredRoute";
import SpotifyPage from './spotify/SpotifyPage';
import UserList from './user/UserList'
import AdminRoute from '../utils/AdminRoute';
import PostMenu from './post/PostMenu'
import PostUpdate from './post/PostUpdate'
import UserProfile from './user/UserProfile'



class Routes extends Component {

    render() {
        return (
            <Switch>
            <Route exact path="/" component={Login}></Route>
            <Route exact path="/login" component={Login}></Route>
            <Route exact path="/register" component={Register}></Route>
            <Route exact path="/spotify" component={SpotifyPage}></Route>
            <AdminRoute exact path="/user/list/:name" component={UserList}></AdminRoute>
            <AdminRoute exact path="/post/menu/:name" component={PostMenu}></AdminRoute>
            <SecuredRoute exact path="/user/profile/:email" component={UserProfile}></SecuredRoute>
              <SecuredRoute  path="/post/list/:id" component={PostList}></SecuredRoute>
              <SecuredRoute  path="/post/update/:id/:title" component={PostUpdate}></SecuredRoute>
              <SecuredRoute  path="/post/add" component={PostAdd}></SecuredRoute>
              <SecuredRoute  path="/post/:id" component={PostView}></SecuredRoute>
              <SecuredRoute exact path="/artistView/:name" component={ArtistView}></SecuredRoute>
              <Route path="*" component={NotFound}></Route>
            </Switch>
        )
    }
}

export default Routes;