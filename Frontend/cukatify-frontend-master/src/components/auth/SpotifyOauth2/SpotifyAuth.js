import React from 'react'
import { NavLink } from 'react-router-dom';
import {authEndpoint,clientId,redirectUri,scopes} from './Constants'


class GoogleAuth extends React.Component {

    componentDidMount() {

        let hashParams = {};
        let e, r = /([^&;=]+)=?([^&;]*)/g,
          q = window.location.hash.substring(1);
        while ( e = r.exec(q)) {
          hashParams[e[1]] = decodeURIComponent(e[2]);
        }
  
        if(!hashParams.access_token) {
          window.location.href = 'https://accounts.spotify.com/authorize?client_id=230be2f46909426b8b80cac36446b52a&scope=playlist-read-private%20playlist-read-collaborative%20playlist-modify-public%20user-read-recently-played%20playlist-modify-private%20ugc-image-upload%20user-follow-modify%20user-follow-read%20user-library-read%20user-library-modify%20user-read-private%20user-read-email%20user-top-read%20user-read-playback-state&response_type=token&redirect_uri=http://localhost:3000/callback';
        } else {
          this.props.setToken(hashParams.access_token);
        }
    }  



    render(){
        return (
            <button  style = {{marginTop : 15}} className="ui fluid large grey submit grey button">
                            <i className="spotify icon"></i>
                            Sign With Spotify
                        </button>
        )
    }

}



export default GoogleAuth;