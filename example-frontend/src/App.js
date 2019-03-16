import React, { Component } from 'react';
import './css/App.css';

import Signup from './container/Signup';
import Signin from './container/Signin';
import Header from "./container/Header";
import {BrowserRouter as Router, Route} from 'react-router-dom';
import SelectSemester from "./container/SelectSemester";
import SelectCourse from "./container/SelectCourse";

class App extends Component {

  render() {
    return (
        <Router>
        <div className="App">
          <div className="container">
            <Header />
            <Route exact path="/" render={props => (<React.Fragment><Signin /></React.Fragment>)} />
            <Route path="/Signup" component={Signup} />
            <Route path="/SelectSemester" component={SelectSemester} />
            <Route path="/SelectCourse" component={SelectCourse} />
          </div>
        </div>
        </Router>
    );
  }
}
export default App;
