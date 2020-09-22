
import springapi from '../api/springapi'
import {GET_POSTS,GET_SELECTED_POST, SAVE_POST,UPDATE_POST} from '../actions/types'

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

export const getAllStatePosts = () => 
    async (dispatch) => {
      const response = await springapi.get("/posts/findAllStatePosts")
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

  export const rateAPost = (post_id,rating) => 
  async (dispatch) => {
    await springapi.patch(`/posts/rate/${post_id}/${rating}`)
    
}

export const togglePost = (id,history) => 
    async (dispatch) => {
        await springapi.patch("/posts/toggle/"+id)
        history.push("/post/menu/"+Math.random())
    }

export const updatePost = (formData) =>
    async (dispatch) => {
        const config = {
            headers: {
                'content-type': 'multipart/form-data'
            }
        }
        
        const response = await springapi.post('posts/update',formData,config)
        dispatch({
          type : UPDATE_POST,
          payload : response.data.data
      })
    }