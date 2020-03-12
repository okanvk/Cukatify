
import {GET_ARTIST} from '../actions/types' 

const initialState = {
    artist: {
        relatedThingList : []
    }
  };
  

export default (state = initialState,action) => {

    switch(action.type) {
        case GET_ARTIST:
            return {
                ...state,
                artist : action.payload
            };
        default:
            return state;
    }
}