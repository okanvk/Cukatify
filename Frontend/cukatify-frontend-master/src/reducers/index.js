
import {combineReducers} from 'redux'
import postsReducer from './postsReducer'
import artistReducer from './artistReducer'
import {reducer as formReducer} from "redux-form";
import categoryReducer from './categoryReducer'
import securityReducer from './securityReducer'
import recommenderReducer from './recommenderReducer'
import geniusReducer from './geniusReducer'
import userReducer from './userReducer'


export default combineReducers({
    postState: postsReducer,
    artistState: artistReducer,
    form : formReducer,
    categoryState: categoryReducer,
    userState: userReducer,
    security: securityReducer,
    recommenderState : recommenderReducer,
    geniusState: geniusReducer,
});