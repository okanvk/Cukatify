import springapi from '../api/springapi'
import {GET_ARTIST} from '../actions/types'

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
                type : GET_ARTIST,
                payload : {
                    relatedThingList : []
                }
            })
            history.push("/login")
        }
    }

