
import React, { Component } from 'react'
import {Link} from 'react-router-dom';
import springapi from '../../api/springapi'
import "./Deneme.css";

class Deneme extends Component {


    onFileChangeHandler = (e) => {
        e.preventDefault();
       const formData = new FormData();
        formData.append('file', e.target.files[0]);
        springapi.post('post/upload',formData)
            .then(res => {
                    console.log(res.data.data);
                    alert("File uploaded successfully.")
            })
    };


    render(){
        return(
            <div className="container">
                <div className="row">
                    <div className="col-md-6">
                            <div className="form-group files color">
                                <label>Upload Your File </label>
                                <input type="file" className="form-control" name="file" onChange={this.onFileChangeHandler}/>
                            </div>
                    </div>
                </div>
                <div className="ui fluid segment">
                    <input type="file" onChange={this.onFileChangeHandler} className="inputfile" id="embedpollfileinput" />
              
                <label for="embedpollfileinput" className="ui huge red right floated button">
                  <i className="ui upload icon"></i> 
                  Upload image
                </label>
                </div>
            </div>
        )
      }

}

export default Deneme;