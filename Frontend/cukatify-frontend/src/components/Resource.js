
import React, { Component } from 'react'


class Resource extends Component {



    render() {
        return (
            <div>{this.props.match.params.keyword}</div>
        )
        
    }

}

export default Resource;