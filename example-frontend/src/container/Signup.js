import React, { Component } from 'react';
import '../css/App.css';
import axios from 'axios';
import {withRouter} from "react-router-dom";

function validate(firstname, lastname, username, pass) {
    // true means invalid, so our conditions got reversed
    return {
        firstname: firstname.length === 0,
        lastname: lastname.length === 0,
        username: username.length === 0,
        password: pass.length === 0
    };
}

class Signup extends Component {

    constructor(props) {
        super(props);
        this.state = {courseCheck: 'Not yet generated'};
        this.state = {loggedIn: false};
        this.register = this.register.bind(this);
        this.routeChange = this.routeChange.bind(this);
        this.state = {
            firstname: "",
            lastname: "",
            username: "",
            password: "",
            everFocusedFirstName: false,
            everFocusedLastName: false,
            everFocusedUsername: false,
            everFocusedPassword: false,
            inFocus: ""
        };
    }

    routeChange() {
        let path = '/';
        this.props.history.push(path);
    }

    handlFirstnameChange = evt => {
        this.setState({ firstname: evt.target.value });
    };

    handlLastnameChange = evt => {
        this.setState({ lastname: evt.target.value });
    };

    handUsernameChange = evt => {
        this.setState({ username: evt.target.value });
    };

    handlePasswordChange = evt => {
        this.setState({ password: evt.target.value });
    };

    handleSubmit = evt => {
        if (!this.canBeSubmitted()) {
            evt.preventDefault();
            return;
        }
        const { firstname, lastname, username, password} = this.state;
        alert(`Signed up with firstname: ${firstname} lastname: ${lastname}  username: ${username} password: ${password}`);
    };

    canBeSubmitted() {
        const errors = validate(this.state.firstname, this.state.lastname, this.state.username, this.state.password);
        const isDisabled = Object.keys(errors).some(x => errors[x]);
        return !isDisabled;
    }


//
    render(){
        const errors = validate(this.state.firstname, this.state.lastname, this.state.username, this.state.password);
        const isDisabled = Object.keys(errors).some(x => errors[x]);
        return(
            <div className="container center- card-signin" id="inside">
                <div >
                    <img className="logo" src={require("../assets/SOEN.jpg")} alt="SOEN SCHEDULER BUILDER"/>
                </div>
                <form onSubmit={this.handleSubmit}>
                    <div>
                        <div className="row">
                            <div><label className="col-12">First Name:<input className="col-12"  type="text" name="firstname" id={'firstname'}  value={this.state.firstname} onChange={this.handlFirstnameChange} /></label></div>
                            <div><label className="col-12">Last Name:<input className="col-12" type="text" name="lastname" id={'lastname'} value={this.state.lastname} onChange={this.handlLastnameChange} /></label></div>
                            <div><label className="col-12">Username:<input className="col-12" type="text" name="username" id={'username'} value={this.state.username} onChange={this.handUsernameChange}/></label></div>
                            <div><label className="col-12">Password:<input className="col-12" type="password" name="password" id={'pass'} value={this.state.password} onChange={this.handlePasswordChange} /></label></div>
                        </div>
                    </div>
                    <button disabled={isDisabled} className="btn btn-home-log" type="button" value="Submit" onClick={this.register}>Sign up</button>
                    <button className="btn btn-home-log" type="button" value="Submit" onClick={this.routeChange}>Already a memeber</button>
                </form>
            </div>
        );
    }

    register() {
        var firstName;
        var lastName;
        var username;
        var pass;
        var element;
        element = document.getElementById('firstname');
        if (element != null) {
            firstName = element.value;
        }
        else {
            alert("Please enter your first name"); //login returns false
        }
        element = document.getElementById('lastname');
        if (element != null) {
            lastName = element.value;
        }
        else {
            lastName = null;
        }
        element = document.getElementById('username');
        if (element != null) {
            username = element.value;
        }
        else {
            username = null;
        }
        element = document.getElementById('pass');
        if (element != null) {
            pass = element.value;
        }
        else {
            pass = null;
        }

        // alert(name +" " + pass);
        axios.post('http://localhost:8080/registration', {
            firstName: firstName,
            lastName: lastName,
            username: username,
            password: pass
        }).then(res => {
            //   alert("Received Successful response from server!");
            this.setState({loggedIn: res.data});
            if (this.state.loggedIn === true) {
                alert("Registration complete"); // login returns true
                this.props.history.push("/");
            }
            else
                alert("An account is already associated to that username"); //login returns false
        }, err => {
            alert("Server rejected response: REGISTRATION RESPONSE NOT RECEIVED");
        });
    }
}
export default withRouter(Signup);