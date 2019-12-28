
import React, { Component } from 'react'

import {connect} from 'react-redux'
import {getSelectedPost} from '../actions/postActions'
import { Redirect } from 'react-router-dom';


class PostView extends Component {



    componentDidMount() {
        const postId = this.props.match.params.id
        this.props.getSelectedPost(postId);
    }



    render() {
        if(!this.props.selectedPost){
            return <Redirect to = "/postList" />
        }
        return (
            <div className="ui divided items">
                {console.log(this.props.selectedPost)}
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