
import {combineReducers} from 'redux'
import postsReducer from './postsReducer'


export default combineReducers({
    postState: postsReducer
});