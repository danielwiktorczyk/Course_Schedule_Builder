import React, { Component } from 'react';
import '../css/App.css';
import axios from 'axios';
import {withRouter} from "react-router-dom";

function validate(firstname, lastname, email, pass) {
    // true means invalid, so our conditions got reversed
    return {
        firstname: firstname.length === 0,
        lastname: lastname.length === 0,
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
            email: "",
            password: "",
            everFocusedFirstName: false,
            everFocusedLastName: false,
            everFocusedEmail: false,
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

    handEmailChange = evt => {
        this.setState({ email: evt.target.value });
    };

    handlePasswordChange = evt => {
        this.setState({ password: evt.target.value });
    };

    handleSubmit = evt => {
        if (!this.canBeSubmitted()) {
            evt.preventDefault();
            return;
        }
        const { firstname, lastname, email, password} = this.state;
        alert(`Signed up with firstname: ${firstname} lastname: ${lastname}  email: ${email} password: ${password}`);
    };

    canBeSubmitted() {
        const errors = validate(this.state.firstname, this.state.lastname, this.state.email, this.state.password);
        const isDisabled = Object.keys(errors).some(x => errors[x]);
        return !isDisabled;
    }


//
    render(){
        const errors = validate(this.state.firstname, this.state.lastname, this.state.email, this.state.password);
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
                            <div><label className="col-12">Email:<input className="col-12" type="text" name="email" id={'email'} value={this.state.email} onChange={this.handEmailChange}/></label></div>
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
        var email;
        var pass;
        var element;
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
        }
        element = document.getElementById('email');
        if (element != null) {
            email = element.value;
        }
        else {
            email = null;
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
            email: email,
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