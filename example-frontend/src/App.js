import React, { Component } from 'react';
import './css/App.css';

import {BrowserRouter as Router, Route} from 'react-router-dom';

import SelectSemester from "./container/SelectSemester";
import SelectCourse from "./container/SelectCourse";
import PossibleSchedules from "./container/PossibleSchedules";
import Signup from './container/Signup';
import Signin from './container/Signin';


class App extends Component {

  render() {
    return (
        <Router>
          <div className="App">
            <div className="container">
              <Route exact path="/" render={props => (<React.Fragment><Signin /></React.Fragment>)} />
              <Route path="/Signup" component={Signup} />
              <Route path="/SelectSemester" component={SelectSemester} />
              <Route path="/SelectCourse" component={SelectCourse} />
              <Route path="/PossibleSchedules" component={PossibleSchedules} />

            </div>
          </div>
        </Router>
    );
  }
}
export default App;