import { GET_CURRENTLY_LISTENING_SONG,GET_USERS_RECENT_LISTENING_ACTIVITY,GET_SPOTIFY_USER } from "../actions/types";

const initialState = {
    listeningSong: [],
    usersListeningSongs : [],
    selectedUser : {},
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
        case GET_SPOTIFY_USER:
                return {
                    ...state,
                    selectedUser : action.payload
                };
        default:
            return state;
    }
}