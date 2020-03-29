import React from "react";

import "./Artist.styles.css";

export const Artist = props => (
  <div className="artist-container">
    <div key={props.url}>
      <div className="image">
        <img src={props.image} alt="." style={{ width: 250, height: 300 }} />
      </div>
      <button className="item">{props.name}</button>
    </div>
  </div>
);
