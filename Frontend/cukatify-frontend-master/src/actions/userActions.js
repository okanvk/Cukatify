import springapi from '../api/springapi'
import { GET_USERS } from "../actions/types";

export const getUsers = () => 
      async (dispatch) => {
        const response = await springapi.get("/users/findAll")
        dispatch({
            type : GET_USERS,
            payload : response.data
        })
    }

export const toggleUser = (username) => 
    async (dispatch) => {
        await springapi.patch("/users/toggle/"+username)
    }