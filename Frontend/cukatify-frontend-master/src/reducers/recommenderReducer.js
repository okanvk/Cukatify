
import {GET_REC_POSTS,GET_REC_SONGS} from '../actions/types' 

const initialState = {
    posts: [],
    songs: []
  };
  

export default (state = initialState,action) => {
    switch(action.type) {
        case GET_REC_POSTS:
            return {
                ...state,
                posts : [...action.payload]
            };
        case GET_REC_SONGS:
            return {
                ...state,
                songs : [...action.payload]
                };
        default:
            return state;
    }
}