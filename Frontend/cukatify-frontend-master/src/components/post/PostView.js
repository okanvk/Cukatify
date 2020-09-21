
import React, { Component } from 'react'

import {connect} from 'react-redux'
import {getSelectedPost, rateAPost} from '../../actions/postActions'
import {getRecommendedPosts} from '../../actions/recommenderActions'
import { Redirect } from 'react-router-dom';
import RecPostList from "./RecPostList"
import OpenSideContainer from '../layout/Container';
import Rater from 'react-rater'
import 'react-rater/lib/react-rater.css'


class PostView extends Component {

    componentDidMount() {
        const postId = this.props.match.params.id
        this.props.getSelectedPost(postId);
        this.props.getRecommendedPosts(postId);
    }

    componentDidUpdate(prevProps, prevState){
        if(prevProps.match.params.id !== this.props.match.params.id){
            const id = this.props.match.params.id
            this.props.getSelectedPost(id);
            this.props.getRecommendedPosts(id);
        }
      }

      ratePost = (rating,id) => {
        this.props.rateAPost(rating,id)
      }

    render() {
        if(!this.props.selectedPost){
            return <Redirect to = "/post/list/0" />
        }
        const {id,title,content,rating,createdAt,category,totalRating} = this.props.selectedPost

        return (
            <div>
            <OpenSideContainer/>
            <div style = {{marginTop : 50}}>
            <h4 className="ui horizontal divider header">
                <i className="tag icon"></i>
                Post
            </h4>
        </div>
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
                    Average Rating : <Rater total={5} rating={totalRating} interactive={false}  />
                
                </div>
                Your Rating : <Rater total={5} rating={rating} onRate = {(rating) => this.ratePost(rating.rating,id)} />

                <div >
                
                
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
    getRecommendedPosts,
    rateAPost,
})(PostView)