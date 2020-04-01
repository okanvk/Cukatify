
import React, { Component } from 'react'

import {connect} from 'react-redux'
import {getSelectedPost} from '../../actions/postActions'
import {getRecommendedPosts} from '../../actions/recommenderActions'
import { Redirect } from 'react-router-dom';
import StarList from "../star/StarList"
import RecPostList from "./RecPostList"


class PostView extends Component {

    componentDidMount() {
        const postId = this.props.match.params.id
        this.props.getSelectedPost(postId);
        this.props.getRecommendedPosts();
    }

    render() {
        if(!this.props.selectedPost){
            return <Redirect to = "/postList" />
        }
        const {title,content,rating,createdAt,category} = this.props.selectedPost

        return (
            <div>
            <div className="item">
            <div className="ui small image"><img alt="." src="https://image.shutterstock.com/image-photo/portrait-surprised-cat-scottish-straight-260nw-499196506.jpg" /></div>
            <div className="content">
                <div  className="ui medium header">{title}</div>
                <div className="description">
                    <p>
                        {content}
                    </p>
                    <div className="meta"><span className="date">{createdAt}</span></div>
                    <div className="meta"><span className="text">Category : {category.name}</span></div>
                    <StarList rating = {rating} />
                </div>

            </div>
        </div>
        <RecPostList posts = {this.props.recommendedPosts}/>
        </div>
        )
    }

}

const mapStateToProps = ({postState,recommenderState}) => {
    return {
        selectedPost : postState.selectedPost,
        recommendedPosts : recommenderState.posts,
    };
}

export default connect(mapStateToProps,{
    getSelectedPost ,
    getRecommendedPosts
})(PostView)