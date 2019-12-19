
import React, { Component } from 'react'
import './SearchBar.css'


class SearchBar extends Component {

    render() {
        return (
            <form className="ui form">
                <div className="field">
                    <input type="text" placeholder = "Search Artist,Group" />
                </div>
            </form>
        )
    }

}

export default SearchBar;