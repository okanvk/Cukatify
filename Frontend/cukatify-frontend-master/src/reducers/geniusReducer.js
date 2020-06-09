import { GET_CURRENTLY_LISTENING_SONG,GET_USERS_RECENT_LISTENING_ACTIVITY } from "../actions/types";

const initialState = {
    listeningSong: [],
    usersListeningSongs : [],
  };
  

export default (state = initialState,action) => {

    switch(action.type) {
        case GET_CURRENTLY_LISTENING_SONG:
            return {
                ...state,
                listeningSong : action.payload
            };
        case GET_USERS_RECENT_LISTENING_ACTIVITY:
            return {
                ...state,
                usersListeningSongs : action.payload
            };
        default:
            return state;
    }
}