
import {combineReducers} from 'redux'
import postsReducer from './postsReducer'
import artistReducer from './artistReducer'
import {reducer as formReducer} from "redux-form";
import categoryReducer from './categoryReducer'
import securityReducer from './securityReducer'
import recommenderReducer from './recommenderReducer'


export default combineReducers({
    postState: postsReducer,
    artistState: artistReducer,
    form : formReducer,
    categoryState: categoryReducer,
    security: securityReducer,
    recommenderState : recommenderReducer,
});