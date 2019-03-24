import React, { Component } from 'react';
import './css/App.css';

import {BrowserRouter as Router, Route} from 'react-router-dom';

import Header from './container/Header';
import Footer from './container/Footer';
import Signup from './container/Signup';
import Signin from './container/Signin';
import SelectSemester from "./container/SelectSemester";
import SelectCourse from "./container/SelectCourse";
import PossibleSchedules from "./container/PossibleSchedules";
import ViewMySchedule from './container/ViewMySchedule';
import MyProfile from './container/MyProfile';
import PlayGame from './container/PlayGame';


class App extends Component {

  render() {
    return (

        <Router key={0}>
          <div key={1} className="App">
              <p></p>
              <div  key={2} className="container">
              <Route key={3} exact path="/" render={props => (<React.Fragment><Signin /></React.Fragment>)} />
              <Route key={4} path="/Signup" component={Signup} />
              <Route key={5} path="/SelectSemester" component={SelectSemester} />
              <Route key={6} path="/SelectCourse" component={SelectCourse} />
              <Route key={7} path="/PossibleSchedules" component={PossibleSchedules} />
              <Route key={8} path="/Header" component={Header} />
              <Route key={9} path="/ViewMySchedule" component={ViewMySchedule} />
              <Route key={10} path="/MyProfile" component={MyProfile} />
              <Route key={11} path="/PlayGame" component={PlayGame} />
              <Footer/>
            </div>
          </div>
        </Router>
    );
  }
}
export default App;