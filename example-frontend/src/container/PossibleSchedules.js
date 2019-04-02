import React, { Component } from 'react';
import '../css/App.css';
import {BrowserRouter as Router, withRouter} from "react-router-dom";
import Header from "./Header";
import axios from 'axios';



class PossibleSchedules extends Component {
    constructor(props) {
        super(props);
        this.state = {loggedIn: true};
        this.previous = this.previous.bind(this);
        this.next = this.next.bind(this);
        this.generate = this.generate.bind(this);
        let schedule = this.generate.bind(this.schedule);
    }
    getLocalIt = () => {

        return localStorage.getItem("a");
    }


    generate() {
        console.log(" aaaadd1111111");
        axios.post('http://localhost:8080/generate', {
        }).then(res => {
            const result = res.data;
            this.schedule = result;

                localStorage.setItem("name", this.schedule.courseTrio[0].lecture.name);
                localStorage.setItem("comp", this.schedule.courseTrio[0].lecture.component);
                localStorage.setItem("start", this.schedule.courseTrio[0].lecture.startTime);
                localStorage.setItem("end", this.schedule.courseTrio[0].lecture.endTime);
                localStorage.setItem("classDay", this.schedule.courseTrio[0].lecture.classDays);


            localStorage.setItem("tutname", this.schedule.courseTrio[0].tutorial.name);
            localStorage.setItem("tutcomp", this.schedule.courseTrio[0].tutorial.component);
            localStorage.setItem("tutstart", this.schedule.courseTrio[0].tutorial.startTime);
            localStorage.setItem("tutend", this.schedule.courseTrio[0].tutorial.endTime);
            localStorage.setItem("tutclassDay", this.schedule.courseTrio[0].tutorial.classDays);


            localStorage.setItem("labname", this.schedule.courseTrio[0].lab.name);
            localStorage.setItem("labcomp", this.schedule.courseTrio[0].lab.component);
            localStorage.setItem("labstart", this.schedule.courseTrio[0].lab.startTime);
            localStorage.setItem("labend", this.schedule.courseTrio[0].lab.endTime);
            localStorage.setItem("labclassDay", this.schedule.courseTrio[0].lab.classDays);


            alert(this.schedule.courseTrio[0].lecture.classDays);

            return result;
        }, err => {
            alert("Server rejected response: " + err);
        });

    }

    getLecName = (i) => {

        return localStorage.getItem("name"+{i});

    }
    getLecComponent = () => {
        return localStorage.getItem("compnent");
    }
    getLecStartTime = () => {
        return localStorage.getItem("strat");
    }
    getLecEnd = () => {
        return localStorage.getItem("end");
    }
    getClassDays = () => {
        return localStorage.getItem("classDay")
    }


    next() {
        axios.post('http://localhost:8080/next', {
        }).then(res => {
            const result = res.data;
            return result;
        }, err => {
            alert("Server rejected response: " + err);
        });

    }

    previous() {
        axios.post('http://localhost:8080/previous', {
        }).then(res => {
            const result = res.data;
            return result;
        }, err => {
            alert("Server rejected response: " + err);
        });

    }

        createTable(){
            let table = [];
            let quarterHours = ["00", "15", "30", "45"];
            let times = [];

            // Outer loop
            // 15-min intervals starting at 8am
            for (let i = 8; i < 24; i++) {
                let children = [];

                    // 15-min 4 times/ hour
                    for(let k = 0; k < 4; k++){
                        let time = ("0" + i).slice(-2)+ ":" + quarterHours[k];
                        times.push(time);
                        table.push(
                            <table className="table table-striped">
                                <tbody>
                                {/*<tr>*/}
                                    {/*<th className="col-2" scope="col">#</th>*/}
                                    {/*<th className="col-2" scope="col">Monday</th>*/}
                                    {/*<th className="col-2" scope="col">Tuesday</th>*/}
                                    {/*<th className="col-2" scope="col">Wednesday</th>*/}
                                    {/*<th className="col-2" scope="col">Thursday</th>*/}
                                    {/*<th className="col-2" scope="col">Friday</th>*/}
                                {/*</tr>*/}
                                <tr key={i + ":" + quarterHours[k]}>
                                    {i} : {quarterHours[k]} {children}
                                </tr>
                                </tbody>
                            </table>
                        );
                }
                //weekdays
                for (let j = 0; j < 5; j++) {

                    if(i> 8 && i<=10 && (j===2 || j===3)){

                        children.push(
                            // request course info from db ('course' is used for testing purposes)
                            <td>{this.schedule}</td>
                        )
                    }
                    else {
                        children.push(
                            // request course info from db ('course' is used for testing purposes)
                            // or show empty if there's breaks/gaps
                            <td>...........--</td>
                        )
                    }
                }
            }
            return table;
        }

    render() {

        console.log("coure name", localStorage.getItem("name"));
        console.log("course component", localStorage.getItem("comp"));
        console.log("start time", localStorage.getItem("start"));
        console.log("class days", localStorage.getItem("classDay"));
        console.log("end time", localStorage.getItem("end"));
        return (
            <div>
                <Router>
                    <Header />
                </Router>
            <div className="container-  select-semester">
                <div className="container-">




                    <hr/><h1 className="show-options">Possible schedules for {this.getLocalIt()}</h1>
                    <h4 className="show-options">The Obj {this.getClassDays()}</h4>





                    <div className="row row-for-arrow">

                        <img className="center-arrows" src={require("../assets/double-left.JPG")} alt="left"/>
                        <img className="center-arrows" src={require("../assets/left-arrow.JPG")} alt="left"/>
                        <div className="show-option-num">##</div>
                        <img className="center-arrows" src={require("../assets/right-arrow.JPG")} alt="right"/>
                        <img className="center-arrows" src={require("../assets/double-right.JPG")} alt="right"/>

                        <div className="dropdown">
                            <button className="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown">
                                Preferences
                            </button>
                            <div className="dropdown-menu">
                                <button className="dropdown-item" type="button">Most days off</button>
                                <button className="dropdown-item" type="button">Online classes</button>
                                <button className="dropdown-item" type="button">Time off campus</button>
                            </div>
                        </div>

                    </div>

                    <hr/>
                    <table className=" container table possible table-striped">
                        <tbody className="container">
                        {this.createTable()}
                        </tbody>
                    </table>
                    <div>
                        <h3>Option ##</h3>
                        <button onClick={this.generate}>GENERATE</button>
                    </div>

                </div>
            </div>
            </div>
        );

    }
}
export default withRouter(PossibleSchedules);
