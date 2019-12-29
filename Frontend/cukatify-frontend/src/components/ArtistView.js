import React, { Component } from 'react'
import { connect } from 'react-redux';
import { getArtist } from '../actions/artistActions'
import { Redirect } from 'react-router-dom';


class ArtistView extends Component {

    componentDidMount() {
        const artistName = this.props.match.params.name
        this.props.getArtist(artistName);
    }

    componentDidUpdate(prevProps, prevState){
        if(prevProps.match.params.name !== this.props.match.params.name){
            const artistName = this.props.match.params.name
            this.props.getArtist(artistName);
        }
      }

    render() {
        if(!this.props.artist){
            return(
                <div className="ui error message center">
            <i className="close icon"></i>
                <div className="header">
                    There were some errors
                </div>
                <ul className="list">
                    <li>This isn't the web page you are looking for.</li>
                </ul>
            </div>
            )
        }
        const { page, name, description,imageUrl } = this.props.artist;
        return (
            <div className="ui items">
            <div className="image">
                <img src={imageUrl} alt = "." />
            </div>
                <div className="item">
                    <div className="content">
                        <h4 className="header">{name}</h4>
                        <div className="meta">
                            <span>Description</span>
                        </div>
                        <div className="description">
                            <p>{description}</p>
                        </div>
                        <div className="extra">
                            <a href = {page}>Page</a>
                       </div>
                    </div>
                </div>
            </div>
        )
    }

}

const mapStateToProps = ({ artistState }) => {
    return { artist: artistState.artist };
}

export default connect(mapStateToProps, {
    getArtist
})(ArtistView);