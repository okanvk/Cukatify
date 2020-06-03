import React, { Component } from 'react';
import { Field, reduxForm } from 'redux-form'
import { connect } from 'react-redux'
import { login, getSpotifyLoginURI, logout } from '../../actions/securityActions'



class Login extends Component {

    

    signInWithSpotify = () => {
        this.props.getSpotifyLoginURI();
    }

    renderError = ({ error, touched }) => {
        if (touched && error) {
            return (
                <div className="ui error message">
                    <div className="header">{error}</div>
                </div>
            )
        }
    }



    renderEmail = ({ input, label, meta }) => {
        const className = `field ${meta.error && meta.touched ? 'error' : ''}`
        return (
            <div className={className}>
            <div className="ui left icon input">
                <i className="user icon"></i>
                <input {...input} placeholder = {label} type = "email" />
            </div>
                {this.renderError(meta)}
            </div>
        )
    }

    renderPassword = ({ input, label, meta }) => {
        const className = `field ${meta.error && meta.touched ? 'error' : 'field'}`
        return (
            <div className={className}>
            <div className="ui left icon input">
                <i className="key icon"></i>
                <input {...input} placeholder = {label} type = "password" />
            </div>
                {this.renderError(meta)}
            </div>
        )
    }


    onSubmit = (formProps) => {
        this.props.login(formProps,this.props.history)
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
                    <form onSubmit={this.props.handleSubmit(this.onSubmit)} className="ui form error">
                        <div className="ui stacked segment">
                        <Field name="username" component={this.renderEmail} label="Email" />
                        <Field name="password" component={this.renderPassword} label="Password" />
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

const validate = (formValues) => {
    const errors = {}
    if (!formValues.username) {
        errors.username = "You must enter a email"
    }

    if (!formValues.password) {
        errors.password = "You must enter a password"
    }

    return errors
}



const form = reduxForm({
    form: 'userLogin',
    validate: validate
})(Login)

const mapStateToProps = ({ security }) => ({
    security : security
})

export default connect(mapStateToProps, { login,logout,getSpotifyLoginURI })(form)