import React, { Component } from "react";

import SearchBar from "./SearchBar";
import {  NavLink } from "react-router-dom";
import "./Header.css";
import SpotifyAuth from "./auth/SpotifyOauth2/SpotifyAuth"

class Header extends Component {




    render() {

        return (
            <div className="ui inverted segment">
                <div className="ui inverted secondary pointing menu">
                    <nav>
                        <ul>
                            <li>
                                <NavLink to="/" exact activeClassName="active-link">
                                    Home
                                </NavLink>
                            </li>
                            <li>
                                <NavLink to="/pictures" exact activeClassName="active-link">Pictures</NavLink>
                            </li>
                            <li>
                                <NavLink to="/time" exact activeClassName="active-link">Time</NavLink>
                            </li>
                        </ul>
                    </nav>
                    <div className="right menu">
                    <div className="item">
                            <SearchBar />
                            <NavLink exact to="/login" className = "ui button" style = {{marginLeft: 25}}>
                            Sign In
                            </NavLink>
                        </div>
                </div>
                </div>
            </div>
        );
    }
}

export default Header;
