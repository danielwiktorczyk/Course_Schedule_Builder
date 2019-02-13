import React, { Component } from 'react';
import '../css/App.css';
import axios from 'axios';

class Main extends Component {

  constructor(props) {
    super(props);
    this.state = {courseCheck: 'Not yet generated'}
    this.state = {loggedIn: false}
    this.coursePull = this.coursePull.bind(this)
      this.login = this.login.bind(this)
  }

  //
  // Tutorial Example
  //
  coursePull() {
    axios.get("http://localhost:8080/course").then(res => {
      alert("Received Successful response from server!");
      this.setState({courseCheck: res.data.courseId});
    }, err => {
      alert("Server rejected response with: " + err);
    });
  }

//
//
//
  render(){
    return(
        <div className="container center" id="inside">
        <div >
        <a href="https://www.concordia.ca//"><img src="concordia_logo.png" alt="concordia logo pic" width="200px" length="100px" /></a>
        </div>
        <h1 id={"header"}>Welcome to Concordia Student Course Planner</h1>
    <form >
    <div className="container">
        <div className="row">
        <label className="col-12">User Name:<input className="col-12" type="text" name="name"  id={'user'} /></label>
        <label className="col-12 password-field">Password:<input className="col-12" type="password" name="password"  id={'pass'} /></label>
        </div>
        </div>
        <button className="btn btn-success" type="submit" value="Submit" onClick={this.login}>Login</button>&nbsp;&nbsp;
        <button className="btn btn-success" type="reset" value="Reset" onClick={this.reset}>Clear</button>

        </form>
            <button onClick={this.coursePull}>Generate!</button>
            <div>Course: {this.state.courseCheck}</div>
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
      if (this.state.loggedIn == true)
        alert("logged in"); // login returns true
      else
          alert("invalid password"); //login returns false
    }, err => {
      alert("Server rejected response: " + err);
    });

  }

}

export default Main;
