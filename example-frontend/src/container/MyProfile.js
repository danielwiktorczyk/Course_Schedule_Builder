import React, { Component } from 'react';
import '../css/App.css';
import {BrowserRouter as Router, withRouter} from "react-router-dom";
import Header from "./Header";
import signin from "./Signin";
//import $ from 'jquery';
// npm install jquery --save is used in case we end up using jQuery (AJAX is a better option here)


class MyProfile extends Component {
    constructor(props) {
        super(props);
        this.state = {loggedIn: true};

    }
    getUserInfo(){
        let username= signin.name;
        return username;
    }

    render(){
        return (
       <div>
           <Router>
                   <Header />
           </Router>

           <h2>Personal Profile {this.getUserInfo()}</h2>
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

