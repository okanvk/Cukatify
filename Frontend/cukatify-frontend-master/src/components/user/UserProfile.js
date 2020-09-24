import React, { Component } from 'react'
import {getSpotifyUser} from '../../actions/geniusActions'
import { connect } from 'react-redux';


class UserProfile extends Component {

   componentDidMount() {
      this.props.getSpotifyUser()
    }

 render = () => {
    return (
       <div>
       <div className="ui yellow message">
  <i className="star icon"></i>
  Here is the {this.props.selectedUser.fullName} profile
</div>
<div className="ui items">
<div className="item">
<div className="image">
  <img src={this.props.selectedUser.imageUrl}/>
</div>
<div className="content">
  <a className="header">{this.props.selectedUser.fullName}</a>
  <div className="meta">
    <span className="cinema">Joined At <span className="date">{this.props.selectedUser.created_At}</span></span>
  </div>
  <div className="description">
    <p>There isn't any entry about this user.</p>
  </div>
  <div className="extra">
  <button className="ui circular facebook icon button">
  <i className="facebook icon"></i>
</button>
<button className="ui circular twitter icon button">
  <i className="twitter icon"></i>
</button>
<button className="ui circular youtube icon button">
  <i className="youtube icon"></i>
</button>
<button className="ui circular instagram icon button">
  <i className="instagram icon"></i>
</button>
  </div>
</div>
</div>

</div>
       </div>
    )
}

}


const mapStateToProps = ({ geniusState }) => {
   return { selectedUser: geniusState.selectedUser
            };
 }

export default connect(mapStateToProps,{getSpotifyUser})(UserProfile);;