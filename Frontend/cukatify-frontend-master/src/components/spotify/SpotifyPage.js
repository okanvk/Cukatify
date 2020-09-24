
import React, { Component } from 'react';
import { SpotifySongCard } from './SpotifySongCard';
import {SpotifyListeningActivityCard} from './SpotifyListeningActivityCard';
import {CurrentlyListeningSong} from './SpotifyCurrentlyListeningSong'
import { connect } from 'react-redux';
import { setToken } from '../../actions/securityActions'
import { Redirect } from 'react-router-dom'
import {getSpotifyRecommendedSongs,getUsersListeningActivity} from '../../actions/recommenderActions'
import {getCurrentlyListeningSong} from '../../actions/geniusActions'

class SpotifyPage extends Component {


  
  componentDidMount() {
    if(window.location.hash){
    var hash = decodeURIComponent(window.location.hash).split("#")[1]
    const promise =this.props.setToken(hash)
    promise.then(result => {
      this.props.getCurrentlyListeningSong(result.accessToken,result.fullName,result.sub).then(result => {
        this.props.getUsersListeningActivity();
      })
    })
    this.props.getSpotifyRecommendedSongs();
    
   

    }

  }

  refreshListeningSong = () => {
    const {accessToken,fullName,sub} = this.props.user
    this.props.getCurrentlyListeningSong(accessToken,fullName,sub)
  }

  renderRecommendedMusics = () => {
    return this.props.songs.map(song => {
      return (
        <div className="column"  key = {song.track_href_play}>
          <SpotifySongCard  song_name = {song.track_name} artist_name = {song.artist_name} album_name = {song.album_name} track_href_play = {song.track_href_play}   />
        </div>
      )}
    )}

    renderListeningActivity = () => {
      return this.props.listeningActivity.map(song => {
        return (
          <SpotifyListeningActivityCard key = {song.id} listener_email={song.listenerEmail} song_name = {song.name} artist_name = {song.artistName} listener = {song.fullName} track_href_play = {song.link}   />
        )
      })
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
        {this.renderRecommendedMusics()}
      </div>
        </div>


        <div className="three wide column">
        <h4 className="ui horizontal divider header basic segment">
        <i className="tag icon"></i>
      Here is the songly listening history.
      </h4>
      {this.renderListeningActivity()}
      </div>
       </div>
       <h4 className="ui horizontal divider header basic segment">
       <i className="tag icon"></i>
     Currently Listening Song
     </h4>
    
       <CurrentlyListeningSong albumImg = {this.props.listeningSong.albumImg}
       lyrics = {this.props.listeningSong.lyrics}
       definition = {this.props.listeningSong.name}
       spotifyUrl = {this.props.listeningSong.link}
       />
       <button onClick={() => this.refreshListeningSong()} className="ui blue button">
       Find Currently Listening Song and Lyrics</button>
      </div>
    )
  }

}

const mapStateToProps = ({ recommenderState,geniusState,security }) => {
  return { songs: recommenderState.songs,
           listeningSong : geniusState.listeningSong,
           listeningActivity : geniusState.usersListeningSongs,
           user : security.user
           };
}


export default connect(mapStateToProps,{setToken,getSpotifyRecommendedSongs,getCurrentlyListeningSong,getUsersListeningActivity})(SpotifyPage);