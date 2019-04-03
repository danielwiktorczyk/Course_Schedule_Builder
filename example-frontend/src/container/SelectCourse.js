import React, { Component } from 'react';
import '../css/App.css';
import AddedCourseList from "./AddedCourseList";
import {BrowserRouter as Router, withRouter} from "react-router-dom";
import Header from "./Header";
import axios from 'axios';
// import { Tooltip } from 'react-bootstrap';


function validate(coursename) {
    // true means invalid, so our conditions got reversed
    return {
        coursename: coursename.length === 0
    };
}

class SelectCourse extends Component {

    constructor(props) {
        super(props);
        this.state = {loggedIn: true};
        this.routeChange = this.routeChange.bind(this);
        this.generateSchedule = this.generateSchedule.bind(this);
        this.addItem = this.addItem.bind(this);
        this.clearList = this.clearList.bind(this);
        //  this.add_course = this.add_course.bind(this);

        this.state = {
            items: [],
            coursename: "",
            show: false
        };

    }

    _showMessage = (bool) => {
        if (this.state.showMessage === false){
            this.setState({showMessage: true})
        }else{
            this.setState({showMessage: false})
        }
    };


    routeChange() {
        let path = '/SelectSemester';
        this.props.history.push(path);
    }

    generateSchedule() {
        let path = '/PossibleSchedules';
        this.props.history.push(path);
    }

    addItem(e) {
        if (this._inputElement.value !== "") {
            let newItem = {
                text: this._inputElement.value,
                key: Date.now()
            };
            let message = this._inputElement.value;
            axios.post('http://localhost:8080/addCourseToWishListFall', {
                message

            }).then(res => {
                if ("Course added!" === res.data) {
                    alert("course added");
                }
                else
                    alert("Please enter your course info in this format: SOEN 341");
            }, err => {
                alert("Server rejected response: COURSE INFO NOT RECEIVED");
            });

            this.setState((prevState) => {
                return {
                    items: prevState.items.concat(newItem)
                };
            });

            this._inputElement.value = "";
            e.preventDefault();
        }

    }
    clearList(){
        window.location.reload();
    }


    handleCourseNameChange = evt => {
        this.setState({ coursename: evt.target.value });
    };

    handleSubmit = evt => {
        if (!this.canBeSubmitted()) {
            evt.preventDefault();
            return;
        }
        const { coursename} = this.state;
        alert(`Signed up with coursename: ${coursename}`);
    };

    canBeSubmitted() {
        const errors = validate(this.state.coursename);
        const isDisabled = Object.keys(errors).some(x => errors[x]);
        return !isDisabled;
    }

    getLocalItem = () => {

        return localStorage.getItem("a");
    };
    
    render(){
        const errors = validate(this.state.coursename);
        const isDisabled = Object.keys(errors).some(x => errors[x]);

        return (
            <div>
                <Router>
                    <Header />
                </Router>
                <div className="container- s-course">

                    <div >
                        <img  className="ScheduleGen- logo-select-sem" src={require("../assets/SOEN-LOGO.JPG")} alt="SOEN SCHEDULER BUILDER"/>
                    </div>
                    <hr/><h2 className="adjust-h2">COURSE SELECTION  {this.getLocalItem()}</h2><hr/>

                    <div>
                        <form onSubmit={this.addItem}>
                            <div className="row">
                                <input id="course-name" ref={(a) => this._inputElement = a} className="col-8 course-name" value={this.state.coursename} onChange={this.handleCourseNameChange} type="text" name="coursename" placeholder="Ex: SOEN 341"/>
                                <button  className="col-3 btn btn-home-log">ADD</button>
                            </div>
                        </form>
                        <AddedCourseList entries={this.state.items} />
                    </div>

                    <div>
                        <button className="col-3 btn btn-home-log select-semester-options" disabled={isDisabled} onClick={this.generateSchedule}>GENERATE</button>
                        <button className="col-3 btn btn-home-log select-semester-options" onClick={this.routeChange}>CHANGE SEMESTER</button>
                        <button className="col-3 btn btn-home-log select-semester-options" onClick={this.clearList}>CLEAR LIST</button>
                        <button className="col-3 btn btn-home-log select-semester-options" onClick={this._showMessage.bind(null, true)}>ADD PREFERENCES</button>
                        { this.state.showMessage && (
                            <div className="preferences">
                                <h6>Choose the Days/ Times You Would Like to Be away from Campus:</h6>
                                <label className="newwrapper">Monday
                                    <input type="checkbox"/>
                                        <span className="checkmark"></span>
                                </label>
                                    <div className="choices">
                                        <label className="wrapping radio-inline">No Mornings
                                            <input type="radio" name="radio"/>
                                                <span className="check-mark"></span>
                                        </label>
                                        <label className="wrapping radio-inline">No Evenings
                                            <input type="radio" name="radio"/>
                                                <span className="check-mark"></span>
                                        </label>
                                        <label className="wrapping radio-inline">All Day off
                                            <input type="radio" name="radio"/>
                                            <span className="check-mark"></span>
                                        </label>
                                    </div>

                                <label className="newwrapper">Tuesday
                                    <input type="checkbox"/>
                                        <span className="checkmark"></span>
                                </label>
                                <div className="choices">
                                    <label className="wrapping radio-inline">No Mornings
                                        <input type="radio" name="radio"/>
                                        <span className="check-mark"></span>
                                    </label>
                                    <label className="wrapping radio-inline">No Evenings
                                        <input type="radio" name="radio"/>
                                        <span className="check-mark"></span>
                                    </label>
                                    <label className="wrapping radio-inline">All Day off
                                        <input type="radio" name="radio"/>
                                        <span className="check-mark"></span>
                                    </label>
                                </div>
                                <label className="newwrapper">Wednesday
                                    <input type="checkbox"/>
                                        <span className="checkmark"></span>
                                </label>
                                <div className="choices">
                                    <label className="wrapping radio-inline">No Mornings
                                        <input type="radio" name="radio"/>
                                        <span className="check-mark"></span>
                                    </label>
                                    <label className="wrapping radio-inline">No Evenings
                                        <input type="radio" name="radio"/>
                                        <span className="check-mark"></span>
                                    </label>
                                    <label className="wrapping radio-inline">All Day off
                                        <input type="radio" name="radio"/>
                                        <span className="check-mark"></span>
                                    </label>
                                </div>
                                <label className="newwrapper">Thursday
                                    <input type="checkbox"/>
                                        <span className="checkmark"></span>
                                </label>
                                <div className="choices">
                                    <label className="wrapping radio-inline">No Mornings
                                        <input type="radio" name="radio"/>
                                        <span className="check-mark"></span>
                                    </label>
                                    <label className="wrapping radio-inline">No Evenings
                                        <input type="radio" name="radio"/>
                                        <span className="check-mark"></span>
                                    </label>
                                    <label className="wrapping radio-inline">All Day off
                                        <input type="radio" name="radio"/>
                                        <span className="check-mark"></span>
                                    </label>
                                </div>
                                <label className="newwrapper">Friday
                                    <input type="checkbox"/>
                                        <span className="checkmark"></span>
                                </label>
                                <div className="choices">
                                    <label className="wrapping radio-inline">No Mornings
                                        <input type="radio" name="radio"/>
                                        <span className="check-mark"></span>
                                    </label>
                                    <label className="wrapping radio-inline">No Evenings
                                        <input type="radio" name="radio"/>
                                        <span className="check-mark"></span>
                                    </label>
                                    <label className="wrapping radio-inline">All Day off
                                        <input type="radio" name="radio"/>
                                        <span className="check-mark"></span>
                                    </label>
                                </div>

                            </div>
                        ) }

                    </div>
                </div>
            </div>
        );
    }
}
export default withRouter(SelectCourse);
