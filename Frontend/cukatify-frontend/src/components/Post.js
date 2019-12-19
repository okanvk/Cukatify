
import React, { Component } from 'react'

class Post extends Component {

    render() {
        return (
                <div className="item">
                    <div className="ui small image"><img alt = "." src="https://image.shutterstock.com/image-photo/portrait-surprised-cat-scottish-straight-260nw-499196506.jpg" /></div>
                    <div className="content">
                        <a href = "/" className="header">Cute Dog</a>
                        <div className="description">
                            <p>
                                Cute dogs come in a variety of shapes and sizes. Some cute dogs are cute for their
                                adorable faces, others for their tiny stature, and even others for their massive size.
                            </p>
                            <p>Many people also have their own barometers for what makes a cute dog.</p>
                        </div>
                    </div>
                </div>
        )
    }

}

export default Post;