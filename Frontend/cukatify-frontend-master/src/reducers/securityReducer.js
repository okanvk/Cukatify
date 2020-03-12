import { SET_CURRENT_USER, GET_USER } from "../actions/types";

const initialState = {
  user: {},
  validToken: false
};

const booleanActionPayload = payload => {
  if(payload){
    return true
  }
  return false
};

export default function(state = initialState, action) {
  switch (action.type) {
    case SET_CURRENT_USER:
      return {
        ...state,
        validToken: booleanActionPayload(action.payload),
        user: action.payload
      };

    case GET_USER:
      return {
        ...state,
        validToken: booleanActionPayload(action.payload),
        user: action.payload
      };

    default:
      return state;
  }
}
