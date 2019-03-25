import React, { Component } from 'react';
import '../css/App.css';
import {withRouter} from "react-router-dom";
import axios from "axios";

class Header extends Component {

    constructor(props) {
        super(props);
        this.state = {loggedIn: true};
        this.goToMyProfile = this.goToMyProfile.bind(this);
        this.goToSchedule = this.goToSchedule.bind(this);
        this.logout = this.logout.bind(this);

    }

    goToMyProfile() {
        let path = '/MyProfile';
        this.props.history.push(path);
    }

    goToSchedule(){
        let path = '/ViewMySchedule';
        this.props.history.push(path);

    }
    logout() {
        let name;
        let pass;

        axios.post('http://localhost:8080/logout', {
            username: name,
            password: pass
        }).then(res => {
            this.setState({loggedIn: res.data});
            if (this.state.loggedIn === true) {
                this.state = {loggedIn: false};
                this.props.history.push("/");
            }
        }, err => {
            alert("Server rejected response: " + err);
        });
    }
    render(){
        return (
            <div className="header">
                <ul>
                    <li><a className="btn header-buttons" type="button" onClick={this.goToMyProfile}>My Profile</a></li>
                    <li><a className="btn header-buttons" type="button" onClick={this.goToSchedule}>My Schedule</a></li>
                    <li><a className="btn header-buttons" type="button" onClick={this.logout}>Logout</a></li>
                </ul>

            </div>
        );
    }
}
export default withRouter(Header);

