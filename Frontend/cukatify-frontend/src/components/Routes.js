
import React,{Component} from 'react'
import PostList from './post/PostList';
import NotFound from './NotFound'

import { Route, Switch } from "react-router-dom";
import PostView from './post/PostView';
import Login from './auth/Login'
import ArtistView from './artist/ArtistView';
import PostAdd from './post/PostAdd';
import Deneme from './post/Deneme';

class Routes extends Component {

    render() {
        return (
            <Switch>
              <Route exact path="/" component={PostList}></Route>
              <Route exact path="/deneme" component={Deneme}></Route>
              <Route exact path="/post/add" component={PostAdd}></Route>
              <Route exact path="/login" component={Login}></Route>
              <Route exact path="/artistView/:name" component={ArtistView}></Route>
              <Route exact path="/post/:id" component={PostView}></Route>
              
              <Route exact path="/time" component={PostList}></Route>
              <Route exact path="/pictures" component={PostList}></Route>
              <Route path="*" component={NotFound}></Route>
            </Switch>
        )
    }
}

export default Routes;