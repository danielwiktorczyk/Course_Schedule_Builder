import React, { Component } from 'react';
import '../css/App.css';
import {BrowserRouter as Router, withRouter} from "react-router-dom";
import Header from "./Header";
// import axios from "axios";

class AddOrDrop extends Component {

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

                <div className="container add_drop_frame">
                    <div >
                        <img className="logo drop_logo" src={require("../assets/SOEN-LOGO.JPG")} alt="SOEN SCHEDULER BUILDER"/>
                    </div>
                    <div className="adding_dropping_course">
                        <div className="row sel-s">
                            <div className="dropdown sel-s"><span>Select Semester:</span>
                                <button className="btn btn-home-log dropdown-toggle" type="button"
                                        data-toggle="dropdown">Semester
                                    <span className="caret"></span></button>
                                <ul className="dropdown-menu">
                                    <li className="dropdown-li"><a href="#">Summer</a></li>
                                    <li className="dropdown-li"><a href="#">Fall</a></li>
                                    <li className="dropdown-li"><a href="#">Winter</a></li>
                                </ul>
                            </div>
                        </div>
                        <div className="form-group row">
                            <label className=" col-form-label">Course Name:</label>
                            <div className="col">
                                <input type="password" className="form-control" placeholder="Ex: SOEN341"/>
                            </div>
                        </div>

                        <div className="row">
                            <button className="col btn btn-home-log add_button">ADD</button>
                            <button className="col btn btn-home-log add_button">DROP</button>

                        </div>
                    </div>

                </div>

            </div>
        );
    }
}
export default withRouter(AddOrDrop);

