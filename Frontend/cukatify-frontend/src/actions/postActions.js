
import springapi from '../api/springapi'
import {GET_POSTS,GET_SELECTED_POST, SAVE_POST} from '../actions/types'

export const getPosts = () => 
      async (dispatch) => {
        const response = await springapi.get("/post/findAll")
        dispatch({
            type : GET_POSTS,
            payload : response.data.data
        })
    }
export const getSelectedPost = (id) => 
    async (dispatch) => {
      const response = await springapi.get(`/post/find/${id}`)
      dispatch({
          type : GET_SELECTED_POST,
          payload : response.data.data
      })
  }
export const savePost = (formData) =>
  async (dispatch) => {
      const config = {
          headers: {
              'content-type': 'multipart/form-data'
          }
      }
      
      const response = await springapi.post('post/save',formData,config)
      dispatch({
        type : SAVE_POST,
        payload : response.data.data
    })
  }

