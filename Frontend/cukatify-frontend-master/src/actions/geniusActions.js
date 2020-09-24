
import geniusapi from '../api/geniusapi'
import springapi from '../api/springapi'
import {GET_CURRENTLY_LISTENING_SONG, GET_SPOTIFY_USER} from './types'



export const getCurrentlyListeningSong = (spotifyToken,fullName,username) => async (dispatch) => {
    try{
        const response = await geniusapi.get(`enqueue/${spotifyToken}/${fullName}/${username}`)
        dispatch({
            type : GET_CURRENTLY_LISTENING_SONG,
            payload : response.data
        });
    }catch(err){
        console.log(err)
    }
}
export const getSpotifyUser = () => async dispatch => {
    const res = await springapi.get("/secureusers/");
    dispatch({
      type: GET_SPOTIFY_USER,
      payload: res.data
    });
  };
  
