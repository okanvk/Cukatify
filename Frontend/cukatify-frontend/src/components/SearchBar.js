
import React, { Component } from 'react'
import './SearchBar.css'
import {withRouter, NavLink} from 'react-router-dom'


class SearchBar extends Component {

    constructor(props){
        super(props)
        
        this.state = {keyword : ""}
    }

    setWord = (e) => {
        this.setState({keyword : e.target.value})
    }

    formSubmit = (e) => {
        e.preventDefault();

        this.props.history.push(`/resource/${this.state.keyword}`);
    } 


    render() {
        return (
            <form onSubmit = {this.formSubmit} style = {{marginRight :75}} className="ui form">
                <div className="ui action input">
                    <input type="text" value = {this.state.keyword} onChange = {this.setWord} placeholder = "Search Artist,Group" />
                    <NavLink exact className = "ui button" to = {`/resource/${this.state.keyword}`}>Search</NavLink>
                </div>
            </form>
        )
    }
}

export default withRouter(SearchBar);