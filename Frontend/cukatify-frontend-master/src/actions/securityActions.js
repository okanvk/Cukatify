
import springapi from '../api/springapi'
import postrecapi from '../api/postrecapi'
import { SET_CURRENT_USER, GET_USER } from "../actions/types";
import jwt_decode from "jwt-decode";

export const createNewUser = (newUser, history) => async dispatch => {
  try {
    await springapi.post("/users/register", newUser);
    history.push("/login");
  } catch (err) {
    console.log(err)
  }
};

export const getUser = () => async dispatch => {
  const res = await springapi.get("/usersecurity/");
  dispatch({
    type: GET_USER,
    payload: res.data
  });
};



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

    dispatch({
      type: SET_CURRENT_USER,
      payload: decoded
    });

    history.push("/post/list")
    
  } catch (err) {
    console.log(err)
  }
};



export const setJWTToken = token => {
  if (token) {
    springapi.defaults.headers.common["Authorization"] = token;
    postrecapi.defaults.headers.common["Authorization"] = token;
  } else {
    delete springapi.defaults.headers.common["Authorization"];
    delete postrecapi.defaults.headers.common["Authorization"];
  }
};


export const logout = () => 
      async (dispatch) => {
        localStorage.removeItem("jwtToken");
        setJWTToken(false);
        console.log("logout")
        dispatch({
            type: SET_CURRENT_USER,
            payload: {}
          });
    }
