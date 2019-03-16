import React, { Component } from 'react';
import '../css/App.css';

class SelectCourse extends Component {

    constructor(props) {
        super(props);
        this.state = {loggedIn: true}
    };

    render() {
        return (
            <div className="container">
                <div >
                    <a href="#"><img  className="ScheduleGen-" src={require("../assets/SOEN.jpg")} alt="SOEN SCHEDULER BUILDER"/></a>
                </div>
                <h1>COURSE SELECTION</h1><hr/>

                <h2>Please enter Course number</h2><hr/>
                <div className="row">
                    <input className="col-8 course-name" type="text" name="coursename" value="Ex: SOEN 341"/>
                    <label></label>
                    <button className="col-3 btn btn-home-log">ADD</button>
                </div>


            </div>
        );
    }
}
export default SelectCourse;
