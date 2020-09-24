import React, { Component } from 'react'
import {Link} from 'react-router-dom';
import { connect } from 'react-redux'
import { getAllStatePosts,togglePost} from '../../actions/postActions'

class PostMenu extends Component {

    componentDidMount(){
        this.props.getAllStatePosts()
    }

    toggle = (id) => {
      this.props.togglePost(id,this.props.history)
    }


    componentDidUpdate(prevProps, prevState){
      if(prevProps.match.params.name !== this.props.match.params.name){
        this.props.getAllStatePosts()
    }
  }

    

    renderList() {
      return this.props.posts.map(post => {
          const systemState = post.approved ? "Make Passive" : "Make Active"
          const icon = post.approved ? <i className="thumbs up outline icon"></i> : <i className="thumbs down outline icon"></i>
          return  <tr key={post.id} className={post.approved ? "" : "error"}>
          <td>{post.title}</td>
          <td><span className="date">{post.createdAt}</span></td>
          <td className={systemState}>
            {icon}
          </td>
          <td>
            {post.category.name}
          </td>
          <td>
          <button className="small blue ui button" onClick={() => this.toggle(post.id)}>{systemState}</button>
          <Link to = {`/post/update/${post.id}/${post.title}`} className="ui secondary button right floated">Update Post</Link>
          </td>
        </tr>
      })
  }
 
 render = () => {
    return (
      <div className="ui grid">
      <div className="three wide column">
    </div>
      <div className="eleven wide column">
      <h4 className="ui horizontal divider header basic segment">
      <i className="tag icon"></i>
    Here is posts on the songly
    </h4>
     <hr></hr>
    <Link to = {`/post/add`} className="ui primary button right floated">Add New Post</Link>

      <table className="ui celled table">
      <thead>
        <tr>
          <th>Title</th>
          <th>Created At</th>
          <th>is Approved</th>
          <th>Category</th>
          <th>Processes</th>
        </tr>
      </thead>
      <tbody>
         {this.renderList()}
      </tbody>
    </table>
      </div>
      <div className="three wide column">
    </div>
      </div>
    )
}

}



const mapStateToProps = ({ postState }) => {
    return { posts: postState.posts };
}

export default connect(mapStateToProps, {
  getAllStatePosts, togglePost
})(PostMenu)