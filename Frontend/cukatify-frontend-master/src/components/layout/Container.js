
import React, { Component } from 'react'
import "./Container.css";
import {withRouter, NavLink} from 'react-router-dom'

class Container extends Component {



    render() {
        return (
            <div className = "container_comp">
            <div className="ui equal width grid ">
                <div className="row">
                    <div className="column" style = {{marginLeft : 50}}>
                        <NavLink exact className = "ui button nav_button" to = {`/post/lists`}>All Posts</NavLink>
                    </div>
                    <div className="column">
                    <NavLink exact className = "ui button nav_button" to = {`/post/lists`}>Pop Music Posts</NavLink>
                    </div>
                    <div className="column">
                    <NavLink exact className = "ui button nav_button" to = {`/post/lists`}>Rock Music Posts</NavLink>
                    </div>
                    <div className="column" style = {{marginRight : 50}}>
                    <NavLink exact className = "ui button nav_button" to = {`/post/lists`}>Classical Music Posts</NavLink>
                    </div>
                </div>
            </div>
            </div>
        )
    }

}

export default Container

