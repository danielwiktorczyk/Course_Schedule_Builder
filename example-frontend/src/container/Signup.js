import React, { Component } from 'react';
import '../css/App.css';
import axios from 'axios';
import Signin from "./Signin";
import { Redirect } from 'react-router-dom'


class Signup extends Component {

    constructor(props) {
        super(props);
        this.state = {courseCheck: 'Not yet generated'}
        this.state = {loggedIn: false}
        this.login = this.login.bind(this)
    }
//
    render(){
        return(

            <div className="container center card-signup" id="inside">
                <div >
                    <img src="../assets/SOEN.jpg" alt="SOEN SCHEDULER BUILDER"/>
                    {/*<h1 id={"header"}>Welcome to Concordia Student Course Planner</h1>*/}
                </div>
                <form>
                    <div className="container">
                        <div className="row">
                            <label className="col-6">First Name:<input className="col-6" type="text" name="firstname" id={'firstname'}/></label>
                            <label className="col-6">Last Name:<input className="col-6" type="text" name="lastname" id={'lastname'}/></label>
                            <label className="col-6">Email:<input className="col-6" type="text" name="email" id={'email'}/></label>
                            <label className="col-6">Password:<input className="col-6" type="password" name="password" id={'pass'} /></label>
                            {/*<label className="col-12">First Name:<input className="col-12" type="text" name="firstName"  id={'firstname'}/></label>*/}
                            {/*<label className="col-12">Last Name:<input className="col-12" type="text" name="lastName" id={'lastname'} /></label>*/}
                            {/*<label className="col-12">Email:<input className="col-12" type="text" name="email" id={'email'}/></label>*/}
                        </div>
                    </div>
                    <button className="btn btn-home-log" type="submit" value="Submit" onClick={this.login}>Sign up</button>




                </form>
            </div>


        );
    }

    login() {
        var firstName;
        var lastName;
        var email;
        var pass;
        var element;
        element = document.getElementById('firstname');
        if (element != null) {
            firstName = element.value;
        }
        else {
            firstName = null;
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
            }
            else
                alert("An account is already associated to that email"); //login returns false
        }, err => {
            alert("Server rejected response: REGISTRATION RESPONSE NOT RECEIVED");
        });

    }



}

export default Signup;
