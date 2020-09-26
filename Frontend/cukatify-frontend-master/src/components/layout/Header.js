import React, { Component } from "react";

import SearchBar from "./SearchBar";
import { NavLink } from "react-router-dom";
import "./Header.css";
import { connect } from "react-redux";
import logo from '../../components/logo192.png'; 

import { logout } from "../../actions/securityActions";


class Header extends Component {


    logout = () => {
        this.props.logout().then(result => {
            window.location.href = "/login";
        });

    }

    render() {
        const { validToken, user } = this.props.security;
        let headerLinks
        let headerState
        const userIsNotAuthenticated = (
            <NavLink exact to="/login" className="ui button" style={{ marginLeft: 25 }}>
                Sign In
            </NavLink>
        );

        const userIsAuthenticated = (
            <div className="item">
                <SearchBar />
                <button onClick={this.logout}
                    className="ui button" style={{ marginLeft: 25 }}>
                    Logout
            </button>
            </div>
        );





        const userIsNormalUser = (
            <ul>
                <li>
                    <NavLink to="/post/list" exact activeClassName="active-link">
                        Posts
             </NavLink>
                </li>

            </ul>
        )

        const userIsSpotifyUser = (
            <ul>
                <li>
                    <NavLink to="/post/list" exact activeClassName="active-link">
                        Posts
                    </NavLink>
                </li>
                
                <li>
                    <NavLink to="/spotify" exact activeClassName="active-link">
                        Spotify Page
                    </NavLink>
                </li>
                

            </ul>
        )

        
        const userIsAdminUser = (
            <ul>
                <li>
                    <NavLink to="/post/list" exact activeClassName="active-link">
                        Posts
                 </NavLink>
                </li>
                <li>
                    <NavLink to="/post/menu/1" exact activeClassName="active-link">
                        Post Panel
                 </NavLink>
                </li>
                <li>
                    <NavLink to="/user/list/1" exact activeClassName="active-link">
                        User Panel
                 </NavLink>
                </li>

            </ul>
        )




        if (validToken && user) {
            if (user.scopes.includes("ADMIN")) {
                if (user.scopes.includes("SPOTIFY")) {
                    headerState = userIsSpotifyUser
                } else {
                    
                    headerState = userIsAdminUser
                }
            } else {
                if (user.scopes.includes("SPOTIFY")) {
                    headerState = userIsSpotifyUser
                }else{
                headerState = userIsNormalUser
                }
            }
            headerLinks = userIsAuthenticated;
        } else {
            headerLinks = userIsNotAuthenticated;
        }



        return (
            <div className="width_header">
                <div className="ui inverted segment ">
                    <div className="ui inverted secondary menu">
                    <img src={logo} style={{marginRight: 10}} alt="" width={36} height={36}/>
                        <nav>
                            {headerState}

                        </nav>
                        <div className="right menu">
                            {headerLinks}

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

