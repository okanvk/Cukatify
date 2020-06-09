
import React from "react";


export const CurrentlyListeningSong = props => (
  <div className="ui items">
<div className="item">
  <div className="image">
    <img src={props.albumImg}/>
  </div>
  <div className="content">
    <p className="header">{props.definition}</p>
    <div className="meta">
      <span>Lyrics</span>
    </div>
    <div className="description">
      <p>{props.lyrics}</p>
    </div>
    <div className="extra">
    <button onClick={() => window.open(props.spotifyUrl,"_blank")} className="ui green button">
    <i className="spotify icon"></i>
    Listen to the song</button>
    </div>
  </div>
</div>
</div>
);


