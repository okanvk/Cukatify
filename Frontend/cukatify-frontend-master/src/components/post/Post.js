
import React, { Component } from 'react'
import {Link} from 'react-router-dom';
import 'react-rater/lib/react-rater.css'

class Post extends Component {



    render() {

        const { id,description, title, createdAt,category,rating } = this.props.post

        return (
            <div className="item">
                <div className="ui small image"><img alt="." src="https://image.shutterstock.com/image-photo/portrait-surprised-cat-scottish-straight-260nw-499196506.jpg" /></div>
                <div className="content">
                    <div  className="ui medium header">{title}</div>
                    <div className="description">
                        <p>
                            {description}
                        </p>
                        <div className="meta"><span className="date">{createdAt}</span></div>
                        <div className="meta"><span className="text">Category : {category.name}</span></div>
                        <Link to = {`/post/${id}`} className="ui primary basic button right floated">View Post</Link>

                    
                    </div>

                </div>
            </div>
        )
    }

}

export default Post;