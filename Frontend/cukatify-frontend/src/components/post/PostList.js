
import React, { Component } from 'react'
import Post from './Post'

import {connect} from 'react-redux'
import {getPosts} from '../../actions/postActions'


class PostList extends Component {


    componentDidMount() {
        this.props.getPosts();
    }

    renderList() {
        return this.props.posts.map(post => {
            return <Post post = {post} key = {post.id} />
        })
    }

    render() {
        return (
            <div className="ui divided items">
                {this.renderList()}
            </div>
        )
    }

}

const mapStateToProps = ({postState}) => {
    return {posts : postState.posts};
}

export default connect(mapStateToProps,{
    getPosts 
})(PostList)