import React, { Component } from 'react';
import '../css/App.css';
import {withRouter} from "react-router-dom";

class AboutUs extends Component {

    render(){
        return (
            <div className="container about-us-img">
                <div >
                    <img className="aboutTeam" src={require("../assets/About.jpg")} alt="THE PROCRASTINATORS TEAM MEMBERS"/>
                </div>
            </div>
        );
    }
}
export default withRouter(AboutUs);

