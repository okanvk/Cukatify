
import springapi from '../api/springapi'
import {GET_POSTS,GET_SELECTED_POST, SAVE_POST} from '../actions/types'

export const getSpecificPosts = (id) => 
      async (dispatch) => {
        const response = await springapi.get(`/posts/findByCategoryId/${id}`)
        dispatch({
            type : GET_POSTS,
            payload : response.data.data
        })
      }

export const getPosts = () => 
      async (dispatch) => {
        const response = await springapi.get("/posts/findAll")
        dispatch({
            type : GET_POSTS,
            payload : response.data.data
        })
    }
export const getSelectedPost = (id) => 
    async (dispatch) => {
      const response = await springapi.get(`/posts/find/${id}`)
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
      
      const response = await springapi.post('posts/save',formData,config)
      dispatch({
        type : SAVE_POST,
        payload : response.data.data
    })
  }

