
import React, { Component } from 'react'
import Post from './Post'

import { connect } from 'react-redux'
import { getPosts ,getSpecificPosts} from '../../actions/postActions'
import OpenSideContainer from '../layout/Container';

class PostList extends Component {

    bringData = (id) => {
        if (id == 0){
            this.props.getPosts();
        }else{
            this.props.getSpecificPosts(id)
        }

    }

    componentDidMount() {
        const id = this.props.match.params.id
        this.bringData(id)
    }

    componentDidUpdate(prevProps, prevState){
        if(prevProps.match.params.id !== this.props.match.params.id){
            const id = this.props.match.params.id
            this.bringData(id)
        }
      }

    renderList() {
        return this.props.posts.map(post => {
            return <Post post={post} key={post.id} />
        })
    }

    render() {
        return (
            <div>
                <OpenSideContainer />
            <div style = {{marginTop : 50}}>
                <h4 className="ui horizontal divider header">
                    <i className="tag icon"></i>
                    Posts
                </h4>
                <div className="ui divided items" style = {{marginTop : 25}}>
                    {this.renderList()}
                </div>
            </div>
            </div>
        )
    }

}

const mapStateToProps = ({ postState }) => {
    return { posts: postState.posts };
}

export default connect(mapStateToProps, {
    getPosts,
    getSpecificPosts
})(PostList)