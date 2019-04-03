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
        let schedule = null;
    }
    getLocalIt = () => {return localStorage.getItem("a");};

    generate() {
        axios.post('http://localhost:8080/generate', {
        }).then(res => {
            const result = res.data;
            this.schedule = result;
            alert(this.schedule.courseTrio[0].lecture.name);
            return result;
            // localStorage.setItem("name", this.schedule);
        }, err => {
            alert("Server rejected response: " + err);
        });
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
                            <td>course</td>
                        )
                    }
                    else {
                        children.push(
                            <td>...........--</td>
                        )
                    }
                }
            }
            return table;
        }

    render() {

        return (
            <div>
                <Router>
                    <Header />
                </Router>
            <div className="container-  select-semester">
                <div className="container-">
                    <hr/><h1 className="show-options">POSSIBILITIES FOR {this.getLocalIt()}</h1>
                    <div className="row row-for-arrow">


                    </div>

                    <hr/>
                    <img className="center-arrows left-arrow-position" src={require("../assets/left-arrow.JPG")} alt="left"/>

                    <table className=" container table possible table-striped">
                        <tbody className="container">
                        {this.createTable()}
                        </tbody>
                    </table>
                    <img className="center-arrows right-arrow-position" src={require("../assets/right-arrow.JPG")} alt="left"/>

                </div>
            </div>
            </div>
        );

    }
}
export default withRouter(PossibleSchedules);
