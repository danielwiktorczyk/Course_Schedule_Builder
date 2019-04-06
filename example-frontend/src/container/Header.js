import React, { Component } from 'react';
import '../css/App.css';
import {withRouter} from "react-router-dom";

class Header extends Component {

    constructor(props) {
        super(props);
        this.state = {loggedIn: true};
        this.goToMyProfile = this.goToMyProfile.bind(this);
        this.goToSchedule = this.goToSchedule.bind(this);
        this.enroll = this.enroll.bind(this);
        this.logOut = this.logOut.bind(this);

    }
    goToMyProfile() {
        let path = '/MyProfile';
        this.props.history.push(path);
        window.location.reload();
    }
    logOut() {
        let path = '/';
        this.props.history.push(path);
        window.location.reload();
    }

    goToSchedule(){
        let path = '/ViewMySchedule';
        this.props.history.push(path);
        window.location.reload();

    }
    enroll(){
        let path = '/selectSemester';
        this.props.history.push(path);
        window.location.reload();

    }

    render(){
        return (
            <div className="header">
                <ul>
                    <a className="btn header-buttons" onClick={this.goToMyProfile}>My Profile</a>
                    <a className="btn header-buttons" onClick={this.goToSchedule}>My Schedule</a>
                    <a className="btn header-buttons" onClick={this.enroll}>Enroll</a>
                    <a className="btn header-buttons" onClick={this.logOut}>Sign Out</a>
                </ul>

            </div>
        );
    }
}
export default withRouter(Header);

