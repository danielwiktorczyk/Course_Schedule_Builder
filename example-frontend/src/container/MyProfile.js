import React, { Component } from 'react';
import '../css/App.css';
import {BrowserRouter as Router, withRouter} from "react-router-dom";
import Header from "./Header";
// import signin from "./Signin";
// import $ from 'jquery';
// npm install jquery --save is used in case we end up using jQuery (AJAX is a better option here)


class MyProfile extends Component {
    constructor(props) {
        super(props);
        this.state = {loggedIn: true};
        // this.getUserInfo = this.getUserInfo().bind(this);
    }

    // getUserInfo(){
    //     let userName= signin.name;
    //     return userName;
    // }

    render(){
        return (
       <div>
           <Router>
                   <Header />
           </Router>

           <div className="container adjust-item student-profile" id="student">
               <div >
                   <img className="student-profile-logo " src={require("../assets/SOEN-LOGO.JPG")} alt="SOEN SCHEDULER BUILDER"/>
               </div>
           <div className="myProfile">
           <h2>Personal Profile </h2>
               <hr/>
               <div className="row">
                   <img className="col-2 student-img" src={require("../assets/Student.JPG")} alt="SOEN SCHEDULER BUILDER"/>
                   {/*{this.getUserInfo()}*/}
                   {/*get variables from db--- need to check user is logged in first!*/}
                   <div className="col-10 myProfile-inner">
                       <ul>
                           <li>First Name: </li>
                           <li>Last Name: </li>
                           <li>Student ID:</li>
                           <li>E-mail Address:</li>
                           <li>Courses Taken:</li>
                           <li>Credits completed:</li>
                       </ul>
                   </div>
               </div>

               </div>
           </div>
       </div>

        );
    }
}
export default withRouter(MyProfile);

