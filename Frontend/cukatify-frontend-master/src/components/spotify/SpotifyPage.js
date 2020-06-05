
import React, { Component } from 'react';
import { SpotifySongCard } from './SpotifySongCard';
import { connect } from 'react-redux';
import { setToken } from '../../actions/securityActions'
import { Redirect } from 'react-router-dom'
import {getSpotifyRecommendedSongs} from '../../actions/recommenderActions'

class SpotifyPage extends Component {



  componentDidMount() {
    if(window.location.hash){
    var hash = decodeURIComponent(window.location.hash).split("#")[1]
    this.props.setToken(hash)
    this.props.getSpotifyRecommendedSongs()
    }

  }

  render() {

    if(!window.location.hash){
      return <Redirect
            to="/login"
            />;
    }
    return (

      

      <div>
       <div className="ui grid">
        <div className="twelve wide column">
        <h4 className="ui horizontal divider header basic segment">
        <i className="tag icon"></i>
      Here is the recommended songs for you.
      </h4>
        <div className="ui four column doubling stackable grid container">
        <div className="column">
          <SpotifySongCard />
        </div>
        <div className="column">
          <SpotifySongCard />
        </div>
        <div className="column">
          <SpotifySongCard />
        </div>
        <div className="column">
          <SpotifySongCard />
        </div>
      </div>
        </div>
        <div className="three wide column">
        <h4 className="ui horizontal divider header basic segment">
        <i className="tag icon"></i>
      Here is the cukatify listening history.
      </h4>
      <SpotifySongCard />
      <SpotifySongCard />
      <SpotifySongCard />
      </div>
       </div>
      </div>
    )
  }

}

const mapStateToProps = ({ artistState }) => {
  return { artist: artistState.artist };
}


export default connect(mapStateToProps,{setToken,getSpotifyRecommendedSongs})(SpotifyPage);