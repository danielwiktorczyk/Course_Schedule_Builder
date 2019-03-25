import React, { Component } from 'react';
import '../css/App.css';
import {BrowserRouter as Router, withRouter} from "react-router-dom";
import Header from "./Header";

class ViewMySchedule extends Component {
    
    render(){
        return (
            <div>
                <Router>
                    <Header />
                </Router>

                <h1>SHOW STUDENT SCHEDULE AFTER ALLOWING THEM TO SELECT SEMESTER</h1>

            </div>
        );
    }
}
export default withRouter(ViewMySchedule);

