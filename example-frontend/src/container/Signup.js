import React, { Component } from 'react';
import '../css/App.css';
import axios from 'axios';
import {withRouter} from "react-router-dom";



class Signup extends Component {

    constructor(props) {
        super(props);
        this.state = {courseCheck: 'Not yet generated'};
        this.state = {loggedIn: false};
        this.register = this.register.bind(this);
        this.routeChange = this.routeChange.bind(this);
    };

    routeChange() {
        let path = '/';
        this.props.history.push(path);
    }
//
    render(){
        return(
            <div className="container center- card-signin" id="inside">
                <div >
                    <img className="logo" src={require("../assets/SOEN.jpg")} alt="SOEN SCHEDULER BUILDER"/>
                </div>
                <form>
                    <div>
                        <div className="row">
                            <div><label className="col-12">First Name:<input className="col-12" type="text" name="firstname" id={'firstname'}/></label></div>
                            <div><label className="col-12">Last Name:<input className="col-12" type="text" name="lastname" id={'lastname'}/></label></div>
                            <div><label className="col-12">Email:<input className="col-12" type="text" name="email" id={'email'}/></label></div>
                            <div><label className="col-12">Password:<input className="col-12" type="password" name="password" id={'pass'} /></label></div>
                        </div>
                    </div>
                    <button className="btn btn-home-log" type="button" value="Submit" onClick={this.register}>Sign up</button>
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