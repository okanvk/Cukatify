
import React, { Component } from 'react'

import {connect} from 'react-redux'
import {getSelectedPost} from '../actions/postActions'
import { Redirect } from 'react-router-dom';
import StarList from "./StarList"

class PostView extends Component {

    componentDidMount() {
        const postId = this.props.match.params.id
        this.props.getSelectedPost(postId);
    }

    render() {
        if(!this.props.selectedPost){
            return <Redirect to = "/postList" />
        }
        const {title,content,rating,createdAt,category} = this.props.selectedPost

        return (
            
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
        )
    }

}

const mapStateToProps = ({postState}) => {
    return {selectedPost : postState.selectedPost};
}

export default connect(mapStateToProps,{
    getSelectedPost 
})(PostView)