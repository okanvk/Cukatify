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
                t.image = "https://scontent.fbtz1-5.fna.fbcdn.net/v/t1.0-9/22490000_139070100050194_8080531935287282476_n.jpg?_nc_cat=103&_nc_sid=85a577&_nc_ohc=XR_X2TUeMLMAX-kpIqP&_nc_ht=scontent.fbtz1-5.fna&oh=0d79df2bd87395b6fb31806bcbb843bf&oe=5EA37843"
            }
            return (
                <div key = {t.url}>
                <div className="image">
                <img src={t.image} alt = "." style = {{width : 250, height : 300}} />
                </div>
                <button onClick={() => this.findRelated(t.url)}  className="item">{t.name}</button>
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