import React, { Component } from 'react';
import '../css/App.css';
import AddedCourseList from "./AddedCourseList";
import {BrowserRouter as Router, withRouter} from "react-router-dom";
import Header from "./Header";
 import axios from 'axios';


class SelectCourse extends Component {

    constructor(props) {
        super(props);
        this.state = {loggedIn: true};
        this.routeChange = this.routeChange.bind(this);
        this.generateSchedule = this.generateSchedule.bind(this);
        this.addItem = this.addItem.bind(this);
        this.deleteItem = this.deleteItem.bind(this);
        // this.add_course = this.add_course.bind(this);

        this.state = {
            items: []
        };
    };

    routeChange() {
        let path = '/SelectSemester';
        this.props.history.push(path);
    }
    generateSchedule() {
        let path = '/PossibleSchedules';
        this.props.history.push(path);
    }

    deleteItem(key) {
        let filteredItems = this.state.items.filter(function (item) {
            return (item.key !== key);
        });

        this.setState({
            items: filteredItems
        });
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
                if (true == res.data) {
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
    getLocalItem = () => {
        console.log("*/*/*//* ", localStorage.getItem("a1"));
        return localStorage.getItem("a1");
    }
    render(){
        console.log("test 2", localStorage.getItem("a1"));
        return (
            <div>
                <Router>
                    <Header />
                </Router>
            <div className="container- s-course">

                <div >
                    <img  className="ScheduleGen- logo-select-sem" src={require("../assets/SOEN-LOGO.JPG")} alt="SOEN SCHEDULER BUILDER"/>
                </div>
                <hr/><h2 className="adjust-h2">COURSE SELECTION {this.getLocalItem()}</h2><hr/>

                <div>
                <form onSubmit={this.addItem}>
                <div className="row">
                    <input id="course-name" ref={(a) => this._inputElement = a} className="col-8 course-name" type="text" name="coursename" placeholder="Ex: SOEN 341"/>
                    <button  className="col-3 btn btn-home-log">ADD</button>
                    {/*onClick={this.add_course}*/}
                </div>
                </form>
                <AddedCourseList entries={this.state.items}  delete={this.deleteItem}/>
                </div>

                <div>
                    <button className="col-3 btn btn-home-log select-semester-options" onClick={this.generateSchedule}>GENERATE</button>
                    <button className="col-3 btn btn-home-log select-semester-options" onClick={this.routeChange}>CHANGE SEMESTER</button>
                </div>
            </div>
            </div>
        );
    }

    // add_course() {
    //
    //     let name = document.getElementById('course-name');
    //     if (name != null) {
    //         name = name.value;
    //     }
    //     else {
    //         alert("Please enter your course name"); //user input = null
    //     }
    //
    //     axios.post('http://localhost:8080/course', {
    //         name: name,
    //
    //     }).then(res => {
    //         if (name === Course().getName) {
    //            alert("course added");
    //         }
    //         else
    //             alert("Please enter your course info in this format: SOEN 341");
    //     }, err => {
    //         alert("Server rejected response: COURSE INFO NOT RECEIVED");
    //     });
    // }
}
export default withRouter(SelectCourse);