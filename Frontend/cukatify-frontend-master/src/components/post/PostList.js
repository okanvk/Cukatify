
import React, { Component } from 'react'
import Post from './Post'

import { connect } from 'react-redux'
import { getPosts } from '../../actions/postActions'


class PostList extends Component {


    componentDidMount() {
        this.props.getPosts();
    }

    renderList() {
        return this.props.posts.map(post => {
            return <Post post={post} key={post.id} />
        })
    }

    render() {
        return (
            <div style = {{marginTop : 50}}>
                <h4 className="ui horizontal divider header">
                    <i className="tag icon"></i>
                    Posts
                </h4>
                <div className="ui divided items" style = {{marginTop : 25}}>
                    {this.renderList()}
                </div>
            </div>
        )
    }

}

const mapStateToProps = ({ postState }) => {
    return { posts: postState.posts };
}

export default connect(mapStateToProps, {
    getPosts
})(PostList)