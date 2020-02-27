
import {combineReducers} from 'redux'
import postsReducer from './postsReducer'
import artistReducer from './artistReducer'
import {reducer as formReducer} from "redux-form";
import categoryReducer from './categoryReducer'


export default combineReducers({
    postState: postsReducer,
    artistState: artistReducer,
    form : formReducer,
    categoryState: categoryReducer
});