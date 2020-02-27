import React, { Component } from 'react'
import { connect } from 'react-redux';
import { getArtist } from '../../actions/artistActions'



class ArtistView extends Component {

    componentDidMount() {
        const artistName = this.props.match.params.name
        this.props.getArtist(artistName);
    }

    findRelated = (artistUrl) => {
        const artistName = artistUrl.split('/')[4]
        this.props.getArtist(artistName);
    }

    componentDidUpdate(prevProps, prevState){
        if(prevProps.match.params.name !== this.props.match.params.name){
            const artistName = this.props.match.params.name
            this.props.getArtist(artistName);
        }
      }

    renderRelatedThings = (things) => {
        return things.map(t => {
            return (
                <div>
                <button onClick={() => this.findRelated(t.url)} key = {t.name} className="item">{t.name}</button>
            </div> 
            )
        })
    } 

    render() {
        if(!this.props.artist){
            return(
                <div className="ui error message center">
            <i className="close icon"></i>
                <div className="header">
                    There were some errors
                </div>
                <div className="list">
                    <li>This isn't the web page you are looking for.</li>
                </div>
            </div>
            )
        }
        const { page, name, description,imageUrl,relatedThingList } = this.props.artist;
        return (
            <div>
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
            <div className = "ui list">
            {relatedThingList.length !== 0 ? this.renderRelatedThings(relatedThingList) : <div>
                    -
                </div>}
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