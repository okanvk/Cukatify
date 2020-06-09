import postrecapi from '../api/postrecapi'
import spotifyrecapi from '../api/spotifyrecapi'
import listeningactivityapi from '../api/listeningactivitiyapi'


import {GET_REC_POSTS,GET_REC_SONGS,GET_USERS_RECENT_LISTENING_ACTIVITY} from './types'

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
        dispatch({
            type : GET_REC_SONGS,
            payload : response.data.tracks
        });
    }catch(err){
        console.log(err)
    }
}

export const getUsersListeningActivity = () => async (dispatch) => {
    try{
        const response = await listeningactivityapi.get(`tracks/getLastFive`)
        dispatch({
            type : GET_USERS_RECENT_LISTENING_ACTIVITY,
            payload : response.data
        });
    }catch(err){
        console.log(err)
    }
}