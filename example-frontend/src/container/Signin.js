import React, { Component } from 'react';
import '../css/App.css';
import axios from 'axios';
import {withRouter} from "react-router-dom";

function validate(name, pass) {
  // true means invalid, so our conditions got reversed
  return {
    name: name.length === 0,
    password: pass.length === 0
  };
}

class Signin extends Component {

  constructor(props) {
    super(props);
    this.state = {courseCheck: 'Not yet generated'};
    this.state = {loggedIn: false};
    this.login = this.login.bind(this);
    this.routeChange = this.routeChange.bind(this);
    this.state = {
      name: "",
      password: "",
      everFocusedUsername: false,
      everFocusedPassword: false,
      inFocus: ""
    };
  }

  routeChange() {
    let path = '/Signup';
    this.props.history.push(path);
  }

  handlnameChange = evt => {
    this.setState({ name: evt.target.value });
  };

  handlePasswordChange = evt => {
    this.setState({ password: evt.target.value });
  };

  handleSubmit = evt => {
    if (!this.canBeSubmitted()) {
      evt.preventDefault();
      return;
    }
    const { name, password} = this.state;
    alert(`Signed up with name: ${name} password: ${password}`);
  };

  canBeSubmitted() {
    const errors = validate(this.state.name, this.state.password);
    const isDisabled = Object.keys(errors).some(x => errors[x]);
    return !isDisabled;
  }


//
  render(){
    const errors = validate(this.state.name, this.state.password);
    const isDisabled = Object.keys(errors).some(x => errors[x]);
    return(

        <div className="container center card-signup" id="inside">
          <div >
            <img className="logo" src={require("../assets/SOEN.jpg")} alt="SOEN SCHEDULER BUILDER"/>
          </div>
          <form onSubmit={this.handleSubmit}>

              <div className="row">
                <div> <label className="col-12">User Name:<input className="col-12" type="text" name="name" id={'user'} value={this.state.name} onChange={this.handlnameChange} /></label></div>
                <div> <label className="col-12">Password:<input className="col-12" type="password" name="password" id={'pass'} value={this.state.password} onChange={this.handlePasswordChange}  /></label></div>
              </div>

            <button disabled={isDisabled} className="btn btn-home-log" type="button" value="Submit" onClick={this.login}>Sign in</button>
            <button className="btn btn-home-log" type="button" value="Submit" onClick={this.routeChange}>Sign up</button>
          </form>
        </div>
    );
  }

  login() {
    var name;
    var pass;
    var element;
    element = document.getElementById('user');
    if (element != null) {
      name = element.value;
    }
    else {
      name = null;
    }
    element = document.getElementById('pass');
    if (element != null) {
      pass = element.value;
    }
    else {
      pass = null;
    }


    // alert(name +" " + pass);
    axios.post('http://localhost:8080/login', {
      username: name,
      password: pass
    }).then(res => {
      //   alert("Received Successful response from server!");
      this.setState({loggedIn: res.data});
      if (this.state.loggedIn === true) {
        alert("logged in"); // login returns true
        this.props.history.push("/SelectSemester");
      }
      else
        alert("invalid password"); //login returns false
    }, err => {
      alert("Server rejected response: " + err);
    });

  }

}
export default withRouter(Signin);