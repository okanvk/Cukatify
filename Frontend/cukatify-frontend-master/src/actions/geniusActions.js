
import geniusapi from '../api/geniusapi'
import {GET_CURRENTLY_LISTENING_SONG} from './types'



export const getCurrentlyListeningSong = (spotifyToken,email) => async (dispatch) => {
    try{
        const response = await geniusapi.get(`enqueue/${spotifyToken}/${email}`)
        dispatch({
            type : GET_CURRENTLY_LISTENING_SONG,
            payload : response.data
        });
    }catch(err){
        console.log(err)
    }
}
