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
                <div className="header-right">
                    <a href="#">My Profile</a>
                    <a href="#">View my Schedule</a>
                    <a href="#">Logout</a>
                </div>
            </div>
        );
    }
}
export default withRouter(Header);

