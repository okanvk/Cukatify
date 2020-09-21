
import springapi from '../api/springapi'
import postrecapi from '../api/postrecapi'
import spotifyrecapi from '../api/spotifyrecapi'

import { SET_CURRENT_USER, GET_USER } from "../actions/types";
import jwt_decode from "jwt-decode";

export const createNewUser = (newUser, history) => async dispatch => {
  try {
    await springapi.post("/users/register", newUser)
  } catch (err) {
    console.log(err)
    history.push("/register?err=1")
  }
};

export const getUser = () => async dispatch => {
  const res = await springapi.get("/usersecurity/");
  dispatch({
    type: GET_USER,
    payload: res.data
  });
};

export const getSpotifyLoginURI = (history) => async dispatch => {
  try {
    const response = await springapi.get("/spotify/loginURI");
    window.location.href = response.data;
  }catch(err){
    console.log(err)
  }
  console.log(1)
}


export const updateUser = (updateUser, history) => async dispatch => {
  try {
    await springapi.put("/usersecurity/update", updateUser);
    history.push("/dashboard");
  } catch (err) {
    console.log(err)
  }
};

export const login = (LoginRequest,history) => async dispatch => {
  try {
    const res = await springapi.post("/users/login", LoginRequest);

    const { token } = res.data;

    localStorage.setItem("jwtToken", token);

    setJWTToken(token);

    const decoded = jwt_decode(token);
    
    console.log(decoded)
    dispatch({
      type: SET_CURRENT_USER,
      payload: decoded
    });


    if(decoded.scopes.includes("ADMIN")){
      history.push("/user/list/"+Math.random())
    }else{
    history.push("/post/list/0")
    }
    
  } catch (err) {
    console.log(err)
  }
};

export const setToken = (token) => async dispatch => {
  try {

    localStorage.setItem("jwtToken", token);

    setJWTToken(token);

    const decoded = jwt_decode(token);

    console.log(decoded)
    dispatch({
      type: SET_CURRENT_USER,
      payload: decoded
    });

    return decoded
    
  }catch(err){
    localStorage.clear();
  }
}



export const setJWTToken = token => {
  if (token) {
    springapi.defaults.headers.common["Authorization"] = token;
    postrecapi.defaults.headers.common["Authorization"] = token;
    spotifyrecapi.defaults.headers.common["Authorization"] = token;
  } else {
    delete springapi.defaults.headers.common["Authorization"];
    delete postrecapi.defaults.headers.common["Authorization"];
    delete spotifyrecapi.defaults.headers.common["Authorization"];
  }
};


export const logout = () => 
      async (dispatch) => {
        try {
          localStorage.removeItem("jwtToken");
          setJWTToken(false);
          dispatch({
              type: SET_CURRENT_USER,
              payload: {user: {},
              validToken: false}
            });
        }catch(err){
          console.log(err)
        }
    }
