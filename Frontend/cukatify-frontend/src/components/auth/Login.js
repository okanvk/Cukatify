import React, { Component } from 'react';

class Login extends Component {

    signInWithSpotify = () => {
   
    }

    render() {

        return (
            <div className="ui middle aligned center aligned grid" >
                <div className="column">
                    <h2 className="ui grey image header">
                        <div className="content">
                            Log-in to your account
                        </div>
                    </h2>
                    <form className="ui large form">
                        <div className="ui stacked segment">
                            <div className="field">
                                <div className="ui left icon input">
                                    <i className="user icon"></i>
                                    <input type="text" name="email" placeholder="E-mail address" />
                                </div>
                            </div>
                            <div className="field">
                                <div className="ui left icon input">
                                    <i className="lock icon"></i>
                                    <input type="password" name="password" placeholder="Password" />
                                </div>
                            </div>
                            <button className="ui fluid large grey submit button">Login</button>
                            <button onClick = {this.signInWithSpotify}  style = {{marginTop : 15}} className="ui fluid large grey submit grey button">
                            <i className="spotify icon"></i>
                            Sign With Spotify
                        </button>
                        </div>
                    </form>
                </div>
            </div>
        )
    }
}

export default Login