
import React, { Component } from 'react'
import { Field, reduxForm } from 'redux-form'
import { savePost } from '../../actions/postActions'
import { getCategories } from '../../actions/categoryActions'
import { connect } from 'react-redux'
import DropdownList from 'react-widgets/lib/DropdownList'
import 'react-widgets/dist/css/react-widgets.css'
import "./PostAdd.css";


class PostAdd extends Component {

    constructor(props) {
        super(props)
        this.state = { image: "" }

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

    renderInput = ({ input, label, meta }) => {
        const className = `field ${meta.error && meta.touched ? 'error' : ''}`
        return (
            <div className={className}>
                <label>{label}</label>
                <input {...input} />
                {this.renderError(meta)}
            </div>
        )
    }

    renderCheckBox = ({ input, label, meta }) => {
        return (
            <div className="field">
                <label>{label}</label>
                <input {...input} type="checkbox" defaultChecked = {true} />
                {this.renderError(meta)}
            </div>
        )
    }
    renderDropdownList = ({ input, data, valueField, textField, meta, category }) => {
        return (
            <div className="field">
                <label className="field">Category</label>
                <DropdownList
                    {...input}
                    data={data}
                    valueField={valueField}
                    textField={textField}
                    onChange={input.onChange}
                />
                {this.renderError(meta)}
            </div>
        )
    };



    onFileChangeHandler = (e) => {
        e.preventDefault();
        this.setState({image : e.target.files[0]})
    };


    componentDidMount() {
        this.props.getCategories()
    }




    onSubmit = (formProps) => {


        const formData = new FormData();
        formData.append('file', this.state.image);

        formData.append('title',formProps.title);
        formData.append('content',formProps.content);
        formData.append('description',formProps.description);
        if(formProps.isApproved === undefined){
            formData.append('isApproved',1)
        }else{
            formData.append('isApproved',formProps.isApproved);
        }
        formData.append('categoryId',formProps.categoryModel.id)
        this.props.savePost(formData)
    }



    render() {


        return (
            <form onSubmit={this.props.handleSubmit(this.onSubmit)} className="ui form error">
                <Field name="title" component={this.renderInput} label="Enter Title" />
                <Field name="content" component={this.renderInput} label="Enter Content" />
                <Field name="description" component={this.renderInput} label="Enter Description" />
                <Field
                    name="categoryModel"
                    component={this.renderDropdownList}
                    data={this.props.categories}
                    valueField="id"
                    textField="name"
                    label="Select Category"
                />
                <Field name="isApproved" component={this.renderCheckBox} label="Is Approved" />
                <div style = {{margin : 25}}>
                    <label htmlFor="hidden-new-file" className="ui icon button">
                        <i className="cloud icon"></i>
                        Open File
                    </label>
                    <input type="file" id="hidden-new-file" onChange = {this.onFileChangeHandler} accept="image/*" style={{ display: "none" }} />
                </div>
                <button className="ui button primary">Submit</button>
            </form>
        )
    }

}

const validate = (formValues) => {
    const errors = {}
    if (!formValues.title) {
        errors.title = "You must enter a title"
    }

    if (!formValues.description) {
        errors.description = "You must enter a description"
    }

    if (!formValues.content) {
        errors.content = "You must enter a content"
    }

    if (!formValues.categoryModel) {
        errors.categoryModel = "You must select a category"
    }

    return errors
}




const mapStateToProps = ({ postState, categoryState }) => {

    return {
        savedPost: postState.savedPost,
        categories: categoryState.categories
    };
}


const form = reduxForm({
    form: 'postAdd',
    validate: validate
})(PostAdd)

export default connect(mapStateToProps, { savePost, getCategories })(form)
