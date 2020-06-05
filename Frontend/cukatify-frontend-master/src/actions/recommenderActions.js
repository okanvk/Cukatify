import postrecapi from '../api/postrecapi'
import spotifyrecapi from '../api/spotifyrecapi'

import {GET_REC_POSTS} from './types'

export const getRecommendedPosts = (id) => 
      async (dispatch) => {
        try{
            const response = await postrecapi.get(`recommend/${id}`)
                dispatch({
                    type : GET_REC_POSTS,
                    payload : response.data.posts
            })
        }catch(err){
            dispatch({
                type: GET_REC_POSTS,
                payload: []
              });
        }
    }

export const getSpotifyRecommendedSongs = () => async (dispatch) => {
    try{
        const response = await spotifyrecapi.get(`recommend`)
        console.log(response)
    }catch(err){
        console.log(err)
    }
}

