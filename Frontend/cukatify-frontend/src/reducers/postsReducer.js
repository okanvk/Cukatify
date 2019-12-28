
import {GET_POSTS, GET_SELECTED_POST} from '../actions/types' 

const initialState = {
    posts: [],
    selectedPost: {}
  };
  

export default (state = initialState,action) => {

    switch(action.type) {
        case GET_POSTS:
            return {
                ...state,
                posts : [...action.payload]
            };
        case GET_SELECTED_POST:
            console.log(1)
            return {
                ...state,
                selectedPost : action.payload
            };
        default:
            return state;
    }
}