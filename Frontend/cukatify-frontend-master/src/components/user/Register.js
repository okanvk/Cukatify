import React, { Component } from 'react'
import { Field, reduxForm } from 'redux-form'
import { connect } from 'react-redux'
import {createNewUser} from '../../actions/securityActions'



class Register extends Component {

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
    renderAge = ({ input, label, meta }) => {
        const className = `field ${meta.error && meta.touched ? 'error' : ''}`
        return (
            <div className={className}>
            <div className="ui left icon input">
                <i className="user icon"></i>
                <input {...input} placeholder = {label} type = "age" />
            </div>
                {this.renderError(meta)}
            </div>
        )
    }
    renderName = ({ input, label, meta }) => {
        const className = `field ${meta.error && meta.touched ? 'error' : ''}`
        return (
            <div className={className}>
            <div className="ui left icon input">
                <i className="user icon"></i>
                <input {...input} placeholder = {label} type = "name" />
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
        this.props.createNewUser(formProps,this.props.history)
    }

 render = () => {
    return (
        <div className="ui middle aligned center aligned grid" style={{marginTop: 10}} >
        <div className="column">
            <h2 className="ui grey image header">
                <div className="content">
                    Register
                </div>
            </h2>
            <div className="ui stacked segment">
            <form onSubmit={this.props.handleSubmit(this.onSubmit)}  className="ui form error">
                <Field name="username" component={this.renderEmail} label="Email" />
                <Field name="password" component={this.renderPassword} label="Password" />
                <Field name="fullName" component={this.renderName} label="Full Name" />
                <Field name="age" component={this.renderAge} label="Age" />
                <button style={{marginTop: 10}} className="ui fluid large grey submit button">Register</button>
            </form>
            </div>
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

    if (!formValues.fullName) {
        errors.fullName = "You must enter a name"
    }

    if (!formValues.age) {
        errors.age = "You must enter a age"
    }

    return errors
}


const form = reduxForm({
    form: 'register',
    validate: validate
})(Register)

const mapStateToProps = ({ security }) => ({
    security : security
})

export default connect(mapStateToProps, {createNewUser})(form);