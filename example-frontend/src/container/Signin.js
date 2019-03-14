import React, { Component } from 'react';
import '../css/App.css';
import axios from 'axios';

class Signin extends Component {

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
          <form >
            <div className="container">
              <div className="row">
                <label className="col-6">User Name:<input className="col-6" type="text" name="name" id={'user'}/></label>
                <label className="col-6">Password:<input className="col-6" type="password" name="password" id={'pass'} /></label>
                {/*<label className="col-12">First Name:<input className="col-12" type="text" name="firstName"  id={'firstname'}/></label>*/}
                {/*<label className="col-12">Last Name:<input className="col-12" type="text" name="lastName" id={'lastname'} /></label>*/}
                {/*<label className="col-12">Email:<input className="col-12" type="text" name="email" id={'email'}/></label>*/}
              </div>
            </div>
            <button className="btn btn-home-log" type="button" value="Submit" onClick={this.login}>Sign in</button>




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
      }
      else
        alert("invalid password"); //login returns false
    }, err => {
      alert("Server rejected response: " + err);
    });

  }

}

export default Signin;
