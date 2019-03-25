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

       </div>

        );
    }
}
export default withRouter(MyProfile);

