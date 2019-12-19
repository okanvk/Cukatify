
import React, { Component } from 'react'
import Post from './Post'

class PostList extends Component {

    render() {
        return (
            <div className="ui divided items">
                <Post />
                <Post />
            </div>
        )
    }

}

export default PostList