import React, { Component } from 'react';
import '../css/App.css';
import axios from 'axios';

class Main extends Component {

  constructor(props) {
    super(props);
    this.state = {ponged: 'Not yet generated'}

    this.ping = this.ping.bind(this);
  }

  ping() {
    axios.get("http://localhost:8080/pong").then(res => {
      alert("Received Successful response from server!");
      this.setState({ponged: res.data.prereq});
    }, err => {
      alert("Server rejected response with: " + err);
    });
  }




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
        <label className="col-12">User Name:<input className="col-12" type="text" name="name" value="waqar" id={'user'} /></label>
        <label className="col-12 password-field">Password:<input className="col-12" type="password" name="password" value="password" id={'pass'} /></label>
        </div>
        </div>
        <button className="btn btn-success" type="submit" value="Submit" onClick={this.login()}>Login</button>&nbsp;&nbsp;
        <button className="btn btn-success" type="reset" value="Reset" onClick={this.reset}>Clear</button>
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


    alert(pass);
    axios.post('http://localhost:8080/login/', {
      username: '$name',
      password: '$pass'
    }) /*
    axios.get("http://localhost:8080/login/").then(res => {
      alert("Received Successful response from server!");
      this.setState({ponged: res.data.prereq});
    }, err => {
      alert("Server rejected response DERP: " + err);
    });
    */
  }


/*
  render() {
    return (
      <div className="Main">
        <header className="App-header">
          <h1 className="App-title">Class Schedule Test</h1>
        </header>
        <p className="App-intro">
          <div>
            <button onClick={this.ping}>Generate!</button>
            <div>Course: {this.state.ponged}</div>
          </div>
        </p>
      </div>
    );
  }

  */
}

export default Main;
