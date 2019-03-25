import React, { Component } from 'react';
import '../css/App.css';
import {BrowserRouter as Router, withRouter} from "react-router-dom";
import Header from "./Header";

class MyProfile extends Component {


    render(){
        return (
       <div>
           <Router>
                   <Header />
           </Router>


           <h2>Personal Profile</h2>

       </div>

        );
    }
}
export default withRouter(MyProfile);

