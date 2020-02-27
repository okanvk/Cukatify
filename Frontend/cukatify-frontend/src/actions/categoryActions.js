import springapi from '../api/springapi'
import {GET_CATEGORIES} from './types'

export const getCategories = () => 
async (dispatch) => {
  const response = await springapi.get("/category/findAll")
  dispatch({
      type : GET_CATEGORIES,
      payload : response.data.data
  })
}