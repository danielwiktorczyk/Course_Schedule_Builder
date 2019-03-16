import React, { Component } from 'react';
import '../css/App.css';
import {withRouter} from "react-router-dom";

class SelectSemester extends Component {
    constructor(props) {
        super(props);
            this.state = {loggedIn: true};
            this.routeChange = this.routeChange.bind(this);

    };
    routeChange() {
        let path = '/SelectCourse';
        this.props.history.push(path);
    }

    render() {
        return (
            <div className="container select-semester">
                <div className="container">
                    <a href="#"><img className="logo" src={require("../assets/SOEN.jpg")} alt="SOEN SCHEDULER BUILDER"/></a>
                </div>
                <h3>Visualize possible schedules for a selection of courses to quickly plan your semester!</h3>
                <hr/>
                <h2>Select a semester to start</h2>
                <hr/>
                <div className="wrapper row select-semester-button">
                    <button className="col-3 btn btn-home-log" onClick={this.routeChange}>Summer 2018</button>
                    <button className="col-3 btn btn-home-log" onClick={this.routeChange}>Fall 2018</button>
                    <button className="col-3 btn btn-home-log" onClick={this.routeChange}>Winter 2019</button>
                </div>
            </div>
        );
    }
}
export default withRouter(SelectSemester);
