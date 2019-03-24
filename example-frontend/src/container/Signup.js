import React, { Component } from 'react';
import '../css/App.css';
import axios from 'axios';
import {withRouter} from "react-router-dom";

function validate(firstname, lastname, username, email, pass) {
    // true means invalid, so our conditions got reversed
    return {
        firstname: firstname.length === 0,
        lastname: lastname.length === 0,
        username: username.length === 0,
        email: email.length === 0,
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
            email: "",
            password: "",
            confirmPassword: "",
            everFocusedFirstName: false,
            everFocusedUserName: false,
            everFocusedLastName: false,
            everFocusedEmail: false,
            everFocusedPassword: false,
            everFocusedConfirmPassword: false,
            inFocus: ""
        };
    }
    routeChange() {
        let path = '/';
        this.props.history.push(path);
    }

    handleFirstNameChange = evt => {
        this.setState({ firstname: evt.target.value });
    };

    handleLastNameChange = evt => {
        this.setState({ lastname: evt.target.value });
    };
    handleUserNameChange = evt => {
        this.setState({ username: evt.target.value });
    };

    handleEmailChange = evt => {
        this.setState({ email: evt.target.value });
    };

    handlePasswordChange = evt => {
        this.setState({ password: evt.target.value });
    };
    handleConfirmPasswordChange = evt => {
        this.setState({ confirmPassword: evt.target.value });
    };

    handleSubmit = evt => {
        if (!this.canBeSubmitted()) {
            evt.preventDefault();
            return;
        }
        const { firstname, lastname, email, password} = this.state;
        alert(`Signed up with first name: ${firstname} last name: ${lastname}  email: ${email} password: ${password}`);
    };

    canBeSubmitted() {
        const errors = validate(this.state.firstname, this.state.lastname,this.state.username,  this.state.email, this.state.password, this.state.confirmPassword);
        const isDisabled = Object.keys(errors).some(x => errors[x]);
        return !isDisabled;
    }

    render(){
        const errors = validate(this.state.firstname, this.state.lastname,this.state.username,  this.state.email, this.state.password, this.state.confirmPassword);
        const isDisabled = Object.keys(errors).some(x => errors[x]);
        return(
            <div className="container center- card-signin" id="inside">
                <div >
                    <img className="logo" src={require("../assets/SOEN.jpg")} alt="SOEN SCHEDULER BUILDER"/>
                </div>
                <form onSubmit={this.handleSubmit}>
                    <div>
                        <div className="row">
                            <div><label className="col-12">First Name:<input className="col-12"  type="text" name="firstname" id={'firstname'}  value={this.state.firstname} onChange={this.handleFirstNameChange} /></label></div>
                            <div><label className="col-12">Last Name:<input className="col-12" type="text" name="lastname" id={'lastname'} value={this.state.lastname} onChange={this.handleLastNameChange} /></label></div>
                            <div><label className="col-12">User Name:<input className="col-12"  type="text" name="username" id={'username'}  value={this.state.username} onChange={this.handleUserNameChange} /></label></div>
                            <div><label className="col-12">Email:<input className="col-12" type="text" name="email" id={'email'} value={this.state.email} onChange={this.handleEmailChange}/></label></div>
                            <div><label className="col-12">Password:<input className="col-12" type="password" name="password" id={'pass'} value={this.state.password} onChange={this.handlePasswordChange} /></label></div>
                            <div><label className="col-12">Confirm Password:<input className="col-12" type="password" name="confirmPassword" id={'confirm-pass'} value={this.state.confirmPassword} onChange={this.handleConfirmPasswordChange} /></label></div>
                        </div>
                    </div>
                    <button disabled={isDisabled} className="btn btn-home-log" type="button" value="Submit" onClick={this.register}>Sign up</button>
                    <button className="btn btn-home-log" type="button" value="Submit" onClick={this.routeChange}>Already a member</button>
                </form>
            </div>
        );
    }

    register() {
        let firstName;
        let lastName;
        let userName;
        let email;
        let pass;
        let confirmPass;
        let element;
        element = document.getElementById('firstname').value;
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
            alert("Please enter your last name");
        }
        element = document.getElementById('username');
        if (element != null) {
            userName = element.value;
        }
        else {
            alert("Please enter your user name"); //login returns false
        }
        element = document.getElementById('email');
        if (element != null) {
            email = element.value;
        }
        else {
            email = null;
        }
        let element1 = document.getElementById('pass');
        if (element != null) {
            pass = element1.value;
        }
        else {
            pass = null;
        }
        element = document.getElementById('confirm-pass');
        if (element != null && element.value === pass ) {
            confirmPass = element.value;
        }
        else {
            alert("Please re-enter your password confirmation");
            return false;
        }

        // alert(name +" " + pass);
        axios.post('http://localhost:8080/registration', {
            firstName: firstName,
            lastName: lastName,
            userName: userName,
            email: email,
            confirmPass: confirmPass,
            password: pass
        }).then(res => {
            //   alert("Received Successful response from server!");
            this.setState({loggedIn: res.data});
            if (this.state.loggedIn === true) {
                alert("Registration complete"); // login returns true
                this.props.history.push("/");
            }
            else
                alert("An account is already associated to that email"); //login returns false
        }, err => {
            alert("Server rejected response: REGISTRATION RESPONSE NOT RECEIVED");
        });
    }
}
export default withRouter(Signup);