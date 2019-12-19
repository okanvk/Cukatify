import React, { Component } from "react";

import SearchBar from './SearchBar'

class Header extends Component {

    constructor(props){
        super(props)
        this.state = {activeItem : 'main'}

    }

    handleItemClick = (e) => {
        const activeItem = e.target.value
        this.setState({activeItem})
    }


    render() {
         
         const activeMain = this.state.activeItem === "main" ? "active" : ""
         const activeCukats = this.state.activeItem === "cukats" ? "active" : ""
         console.log(activeMain,activeCukats,this.state)
        return (
            <div className="ui inverted segment">
                <div className="ui inverted secondary pointing menu">
                    <button value = "main" onClick = {this.handleItemClick} className={"item" + activeMain}>Main Page</button>
                    <button value = "cukats" onClick = {this.handleItemClick} className={"item" + activeCukats}>Cukats</button>
                    <div className="right menu">
                        <div className="item">
                            <div className="ui icon input">
                                <SearchBar />
                                <i aria-hidden="true" className="search icon"></i>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Header;
