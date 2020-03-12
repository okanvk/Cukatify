
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

        if(!this.state.keyword)
            return;

        const artistName =  this.mapArtistNameToResourceName(this.state.keyword)


        this.setState({keyword : ""})
        this.props.history.push(`/ArtistView/${artistName}`);
    } 

    mapArtistNameToResourceName = (artistName) => {
        return artistName.toLowerCase().split(' ').map(word => word.charAt(0).toUpperCase() + word.slice(1)).join('_')
    }


    render() {
        return (
            <form onSubmit = {this.formSubmit} style = {{marginRight :75}} className="ui form">
                <div className="ui action input">
                    <input type="text" value = {this.state.keyword} onChange = {this.setWord} placeholder = "Search Artist,Group" />
                    <NavLink exact className = "ui button" to = {`/ArtistView/${this.mapArtistNameToResourceName(this.state.keyword)}`}>Search</NavLink>
                </div>
            </form>
        )
    }
}

export default withRouter(SearchBar);