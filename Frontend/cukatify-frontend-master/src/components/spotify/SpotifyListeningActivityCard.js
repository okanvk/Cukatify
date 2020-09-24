import React from "react";
import {  NavLink } from "react-router-dom";


export const SpotifyListeningActivityCard = props => (
    <div className="ui card">
            <div className="content">
              <div className="header">{props.song_name}</div>
            </div>
            <div className="content">
              <h4 className="ui sub header">Listener : {props.listener}</h4>
            </div>
            <div className="extra content">
              <button onClick={() => window.open(props.track_href_play,"_blank")} className="ui green button">
              <i className="spotify icon"></i>
              Listen to the song</button>
              <NavLink style={{'marginTop' : 5 }} to={`/user/profile/${props.listener_email}`} className="ui blue button">
              Look {props.listener} profile</NavLink>
            </div>

          </div>  
)