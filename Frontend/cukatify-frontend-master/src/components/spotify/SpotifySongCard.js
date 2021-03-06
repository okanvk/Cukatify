import React from "react";

export const SpotifySongCard = props => (
    <div className="ui card">
            <div className="content">
              <div className="header">{props.song_name}</div>
            </div>
            <div className="content">
              <h4 className="ui sub header">from {props.artist_name}</h4>
              <div className="ui small feed">
                <div className="event">
                  <div className="content">
                    <div className="summary">
                       <a>{props.song_album_name}</a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div className="extra content">
              <button onClick={() => window.open(props.track_href_play,"_blank")} className="ui green button">
              <i className="spotify icon"></i>
              Listen to the song</button>
            </div>
          </div>  
)