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
}

export default Main;
