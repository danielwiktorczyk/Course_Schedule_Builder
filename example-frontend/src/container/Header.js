import React, { Component } from 'react';
import '../css/App.css';
import {withRouter} from "react-router-dom";

class Header extends Component {

    constructor(props) {
        super(props);
        this.state = {loggedIn: true};
        }

    render(){
        return (
            <div className="header">
                {/*<div >*/}
                    {/*<img className="header-logo" src={require("../assets/SOEN.jpg")} alt="SOEN SCHEDULER BUILDER"/>*/}
                {/*</div>*/}
                <div className="header-right">
                    <a >My Profile</a>
                    <a >View my Schedule</a>
                    <a >Logout</a>
                </div>
            </div>
        );
    }
}
export default withRouter(Header);

