import springapi from '../api/springapi'
import { GET_USERS } from "../actions/types";

export const getUsers = () => 
      async (dispatch) => {
        const response = await springapi.get("/secureusers/findAll")
        dispatch({
            type : GET_USERS,
            payload : response.data
        })
    }

export const toggleUser = (username,history) => 
    async (dispatch) => {
        await springapi.patch("/secureusers/toggle/"+username)
        history.push("/user/list/"+Math.random())
    }

export const makeAdmin = (username,history) => 
    async (dispatch) => {
        await springapi.patch("/secureusers/admin/"+username)
        history.push("/user/list/"+Math.random())
    }