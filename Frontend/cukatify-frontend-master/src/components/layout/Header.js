import React, { Component } from "react";

import SearchBar from "./SearchBar";
import {  NavLink } from "react-router-dom";
import "./Header.css";
import { connect } from "react-redux";
import { logout } from "../../actions/securityActions";


class Header extends Component {


    logout = () => {
        this.props.logout().then(result => {
            window.location.href = "/login";
        });
        
      }

    render() {
        const { validToken, user } = this.props.security;

        let headerLinks;
        const userIsNotAuthenticated = (
            <NavLink exact to="/login" className = "ui button" style = {{marginLeft: 25}}>
                            Sign In
                            </NavLink>
          );

        const userIsAuthenticated = (
            <button onClick={this.logout}
             className = "ui button" style = {{marginLeft: 25}}>
                             Logout
                            </button>
          );

        if (validToken && user) {
            headerLinks = userIsAuthenticated;
          } else {
            headerLinks = userIsNotAuthenticated;
          }

        return (
            <div className="width_header">
            <div className="ui inverted segment ">
                <div className="ui inverted secondary menu">
                    <nav>
                        <ul>
                            <li>
                                <NavLink to="/post/list" exact activeClassName="active-link">
                                    Posts
                                </NavLink>
                            </li>
                            <li>
                                <NavLink to="/spotify" exact activeClassName="active-link">Spotify Page</NavLink>
                            </li>
                            <li>
                                <NavLink to="/time" exact activeClassName="active-link">About</NavLink>
                            </li>
                        </ul>
                    </nav>
                    <div className="right menu">
                    <div className="item">
                            <SearchBar />
                            {headerLinks}
                        </div>
                </div>
                </div>  
            </div>

            </div>
            
        );
    }
}

const mapStateToProps = state => ({
    security: state.security
  });
  
  export default connect(
    mapStateToProps,
    { logout }
  )(Header);
  
