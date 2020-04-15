import React,{Component} from 'react'
import Star from './Star'

class StarList extends Component {



    renderStars(){
        let i = 0;
        let listOfStars = []
        while(i !== this.props.rating){
            listOfStars.push(<Star key = {i} nth = {i+1} marginTop = {this.props.marginTop} />)
            i++;
        }

        return listOfStars.map(el => el);
    }

    render(){
        return (
            <div className="ui star rating left floated meta"  role="radiogroup" tabIndex="-1">
                Rating: {this.renderStars()}
            </div>
        )
    }
}

export default StarList