import React, { Component } from 'react';
import '../css/App.css';
import {withRouter} from "react-router-dom";
import {BrowserRouter as Router} from 'react-router-dom';
import Header from './Header';

class SelectSemester extends Component {

    render() {

        const summer2018 =  "summer 2018";
        return (
            <div>
                <Router>
                    <Header />
                </Router>
                <div className="container-  select-semester">

                    <div className="container-">
                        <img className="logo-select-sem semester" src={require("../assets/SOEN-LOGO.JPG")} alt="SOEN SCHEDULER BUILDER"/>
                    </div>
                    <hr/>
                    <h2 className="adjust-h2">Select a semester to start</h2>
                    <hr/>
                    <div className="wrapper row select-semester-button">
                        <button className="col-2 btn btn-home-log" onClick={() => this.routeChange(summer2018)}>{summer2018}</button>
                        <button className="col-2 btn btn-home-log" onClick={this.routeChange}>Fall 2018</button>
                        <button className="col-2 btn btn-home-log" onClick={this.routeChange}>Winter 2019</button>
                        <button className="col-2 btn btn-home-log" onClick={this.routeChange}>Fall / Winter 2019</button>
                    </div>
                </div>
            </div>

    );
    }

    constructor(props) {
        super(props);
            this.state = {loggedIn: true};
            this.routeChange = this.routeChange.bind(this);

    };

    routeChange = (btnLabel) => {
        let path = '/SelectCourse';
        console.log("test1 ", btnLabel);
        localStorage.setItem("a1", btnLabel);
        console.log("*/*/*//* ", localStorage.getItem("a1"));
        this.props.history.push(path);
    }
}
export default withRouter(SelectSemester);
