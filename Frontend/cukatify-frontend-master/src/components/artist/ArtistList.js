import React from 'react'
import {Artist} from '../artist/Artist'
import './ArtistList.styles.css'

export const ArtistList = (props) => {
    return (
        <div className="artist-list">
        {
            props.artists.map((artist) =>
            <Artist key = {artist.name} artist = {artist} />
        )}
        </div>
    )
}