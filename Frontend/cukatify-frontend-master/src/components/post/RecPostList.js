import React from 'react';
import "./RecPostList.css";
import { Link } from 'react-router-dom';
import StarList from "../star/StarList"


const RecPostList = (props) => {
    const { posts } = props

    const renderedPosts = posts.map(post => {
        return <div className="column">
            <div className="item">
                <div className="content">
                    <h3 className="header">{post.title}</h3>
                    <div className="ui small image"><img alt="." src="https://image.shutterstock.com/image-photo/portrait-surprised-cat-scottish-straight-260nw-499196506.jpg" /></div>
                    <div className="meta">
                        <span>{post.description}</span>
                    </div>
                </div>
                <div style={{ marginTop: 10 }}>
                    <div className="meta"><span className="date">{post.createdAt}</span></div>
                    <div style={{ marginTop: 10 }} >
                        <StarList rating={post.rating} />
                    </div>
                    <Link to={`/post/${post._id}`} className="ui primary basic button right floated">View Post</Link>
                </div>
            </div>

        </div>
    })

    return (
        <div>
            <h4 className="ui horizontal divider header" style={{ marginTop: 15 }}>
                <i className="thumbs up icon"></i>
            Posts, you might like
            </h4>
            <div className="ui three column grid" style={{ marginTop: 25 }}>
                {renderedPosts}
            </div>
        </div>
    )
}
export default RecPostList;