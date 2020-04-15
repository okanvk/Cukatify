
import React, { Component } from 'react'
import "./Container.css";
import {NavLink} from 'react-router-dom'

class OpenSideContainer extends Component {



    render() {
        return (
            <div className = "container_comp">
            <div className="ui equal width grid ">
                <div className="row">
                    <div className="column" style = {{marginLeft : 50}}>
                        <NavLink exact className = "ui button nav_button" to = {`/post/list/0`}>All Posts</NavLink>
                    </div>
                    <div className="column">
                    <NavLink exact className = "ui button nav_button" to = {`/post/list/5df787463b36bc3748631a8a`}>Pop Music Posts</NavLink>
                    </div>
                    <div className="column">
                    <NavLink exact className = "ui button nav_button" to = {`/post/list/5df787413b36bc3748631a89`}>Rock Music Posts</NavLink>
                    </div>
                    <div className="column" style = {{marginRight : 50}}>
                    <NavLink exact className = "ui button nav_button" to = {`/post/list/5df7874a3b36bc3748631a8b`}>Classical Music Posts</NavLink>
                    </div>
                </div>
            </div>
            </div>
        )
    }

}

export default OpenSideContainer

