
import {combineReducers} from 'redux'
import postsReducer from './postsReducer'
import artistReducer from './artistReducer'


export default combineReducers({
    postState: postsReducer,
    artistState: artistReducer
});