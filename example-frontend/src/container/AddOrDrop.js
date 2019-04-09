import React, { Component } from 'react';
import '../css/App.css';
import {BrowserRouter as Router, withRouter} from "react-router-dom";
import Header from "./Header";
import axios from "axios";
// import axios from "axios";

class AddOrDrop extends Component {

    constructor(props) {
        super(props);
        this.options = this.options.bind(this);
        this.state = {loggedIn: true};
        this.drop = this.drop.bind(this);
        this.add = this.add.bind(this);
    }

    drop(){
        let course;
        let semester;
        course = document.getElementById('courseInput').value;
        semester = document.getElementById("textFieldTextJS").value
        if (semester === "Fall") {
            axios.post('http://localhost:8080/dropFall', {
                message: course,
            }).then(res => {
                if ((res.data === "Course Dropped")) {
                    this.props.history.push('/ViewMySchedule');
                    window.location.reload();
                } else {
                    alert(res.data);
                }

            }, err => {
                alert("Server rejected response: " + err);
            });
        }

        if (semester === "Winter") {
            axios.post('http://localhost:8080/dropWinter', {
                message: course,
            }).then(res => {
                if ((res.data === "Course Dropped")) {
                    this.props.history.push('/ViewMySchedule');
                    window.location.reload();
                } else
                    alert(res.data);
            }, err => {
                alert("Server rejected response: " + err);
            });
        }

        if (semester === "Summer") {
            axios.post('http://localhost:8080/dropSummer', {
                message: course,
            }).then(res => {
                if ((res.data === "Course Dropped")) {
                    this.props.history.push('/ViewMySchedule');
                    window.location.reload();
                } else
                    alert(res.data);
            }, err => {
                alert("Server rejected response: " + err);
            });
        }
    }


    add(){
        let course;
        let semester;
        course = document.getElementById('courseInput').value;
        semester = document.getElementById("textFieldTextJS").value

        if (semester === "Fall"){
            axios.post('http://localhost:8080/addFall', {
                message: course,
            }).then(res => {
                if ((res.data === "Success")) {
                    this.props.history.push('/PossibleSchedules');
                    window.location.reload();
                } else {
                    alert(res.data);
                }

            }, err => {
                alert("Server rejected response: " + err);
            });
        }

        if (semester === "Winter"){
            axios.post('http://localhost:8080/addWinter', {
                message: course,
            }).then(res => {
                if ((res.data === "Success")) {
                    this.props.history.push('/PossibleSchedules');
                    window.location.reload();
                } else {
                    alert(res.data);
                }

            }, err => {
                alert("Server rejected response: " + err);
            });
        }

        if (semester === "Summer"){
            axios.post('http://localhost:8080/addSummer', {
                message: course,
            }).then(res => {
                if ((res.data === "Success")) {
                    this.props.history.push('/PossibleSchedules');
                    window.location.reload();
                } else {
                    alert(res.data);
                }

            }, err => {
                alert("Server rejected response: " + err);
            });
        }
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
                                <input type="text" className="form-control" placeholder="Ex: SOEN341" id={'courseInput'}/>
                            </div>
                        </div>

                        <div className="row">
                            <button className="col btn btn-home-log add_button" onClick={this.add}>ADD</button>
                            <button className="col btn btn-home-log add_button" onClick={this.drop}>DROP</button>

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

