import springapi from '../api/springapi'
import {GET_ARTIST} from '../actions/types'

export const getArtist = (artistName) => 
      async (dispatch) => {
        const response = await springapi.get(`/artist/findArtistByName/${artistName}`)
        dispatch({
            type : GET_ARTIST,
            payload : response.data
        })
    }

