import React, { Component } from 'react';
import '../css/App.css';
import {BrowserRouter as Router, withRouter} from "react-router-dom";
import Header from "./Header";
// import axios from "axios";

class AddOrDrop extends Component {

    constructor(props) {
        super(props);
        this.options = this.options.bind(this);
        this.state = {loggedIn: true};

    }

    options() {
    //Getting Value
    var selObj = document.getElementById("selectSemesterDropAdd");
    var selValue = selObj.options[selObj.selectedIndex].text;

    //Setting Value
    document.getElementById("textFieldTextJS").value = selValue;
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
                            <span>Select Semester:</span>
                            <select id="selectSemesterDropAdd" className="form-control btn btn-home-log dropdown-toggle" onChange={this.options}>
                                <option value="summer" >Summer</option>
                                <option value="fall" >Fall</option>
                                <option value="winter" >Winter</option>
                            </select>
                        </div>

                        <input type="text" id="textFieldTextJS" className="form-control"
                               placeholder="get value on option select"/>

                        <div className="form-group row">
                            <label className=" col-form-label">Course Name:</label>
                            <div className="col">
                                <input type="text" className="form-control" placeholder="Ex: SOEN341"/>
                            </div>
                        </div>

                        <div className="row">
                            <button className="col btn btn-home-log add_button">ADD</button>
                            <button className="col btn btn-home-log add_button">DROP</button>
                        </div>
                        <div className="row">
                            <button className="col btn btn-home-log swap_button">Swap</button>
                        </div>
                    </div>

                </div>

            </div>
        );
    }
}
export default withRouter(AddOrDrop);

