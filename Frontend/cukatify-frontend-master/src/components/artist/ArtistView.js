import React, { Component } from 'react'
import { connect } from 'react-redux';
import { getArtist } from '../../actions/artistActions'
import NotFound from "../../artist_not_found.jpeg"



class ArtistView extends Component {

    componentDidMount() {
        const artistName = this.props.match.params.name
        this.props.getArtist(artistName,this.props.history);
    }

    findRelated = (artistUrl) => {
        const artistName = artistUrl.split('/')[4]
        this.props.getArtist(artistName,this.props.history);
    }

    componentDidUpdate(prevProps, prevState){
        if(prevProps.match.params.name !== this.props.match.params.name){
            const artistName = this.props.match.params.name
            this.props.getArtist(artistName);
        }
      }

    renderRelatedThings = (things) => {
        return things.map(t => {
            if(t.image === null){
                t.image = NotFound
            }
            return (
                <div style={{marginTop: 5}} itemsScope itemType = "http://dbpedia.org/ontology/associatedMusicalArtist" key = {t.url}>
                <div className="image">
                <img itemProp = "image" src={t.image} alt = "." style = {{width : 250, height : 300}} />
                </div>
                <button onClick={() => this.findRelated(t.url)} itemProp = "name"  className="ui grey button">{t.name}</button>
            </div> 
            )
        })

    } 

    render() {
        if(!this.props.artist){
            return <NotFound/>
        }
        const { page, name, description,imageUrl,relatedThingList } = this.props.artist;
        return (
            <div>
            <div style = {{marginTop : 50}}>
            <h4 className="ui horizontal divider header">
                <i className="music icon"></i>
                Artist & Band
            </h4>
        </div>
            <div itemScope itemType = "http://dbpedia.org/ontology/Band" style = {{marginTop : 15}}>
            <div className="image">
                <img itemProp = "image" src={imageUrl} alt = "." />
            </div>
                <div className="item">
                    <div className="content">
                        <h4 className="header" itemProp = "name">{name}</h4>
                        <div className="meta">
                            <span>Description</span>
                        </div>
                        <div className="description">
                            <p itemProp = "description">{description}</p>
                        </div>
                        <div className="extra">
                            <a itemProp = "page" href = {page} target="_blank">Page</a>
                       </div>
                    </div>
                </div>
            </div>
            <div className="ui four column doubling stackable grid container">
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