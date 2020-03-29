import springapi from '../api/springapi'
import {GET_ARTIST,SET_CURRENT_USER} from '../actions/types'

export const getArtist = (artistName,history) => 
      async (dispatch) => {
        try{
            const response = await springapi.get(`/artists/findArtistByName/${artistName}`)
            if(response.status === 200){
                dispatch({
                    type : GET_ARTIST,
                    payload : response.data
            })}
        }catch(err){
            dispatch({
                type: SET_CURRENT_USER,
                payload: {}
              });
        }
    }

