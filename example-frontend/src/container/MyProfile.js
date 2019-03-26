import React, { Component } from 'react';
import '../css/App.css';
import {BrowserRouter as Router, withRouter} from "react-router-dom";
import Header from "./Header";

class MyProfile extends Component {
    constructor(props) {
        super(props);
        this.state = {loggedIn: true};
    }

    render(){
        return (
       <div>
           <Router>
                   <Header />
           </Router>

           <h2>Personal Profile</h2>
           {/*get variables from db--- need to check user is logged in first!*/}
           <ul>
               <li>First Name: </li>
               <li>Last Name: </li>
               <li>Student ID:</li>
               <li>E-mail Address:</li>
               <li>Credits completed:</li>
           </ul>

       </div>

        );
    }
}
export default withRouter(MyProfile);

