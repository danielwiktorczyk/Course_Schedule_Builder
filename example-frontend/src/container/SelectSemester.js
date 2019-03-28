import React, { Component } from 'react';
import '../css/App.css';
import {withRouter} from "react-router-dom";
import {BrowserRouter as Router} from 'react-router-dom';
import Header from './Header';

class SelectSemester extends Component {
    render() {
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
                        <button className="col-2 btn btn-home-log" onClick={this.routeChange}>Summer 2018</button>
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

    routeChange() {
        let path = '/SelectCourse';
        this.props.history.push(path);
    }
}
export default withRouter(SelectSemester);
