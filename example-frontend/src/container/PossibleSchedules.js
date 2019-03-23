import React, { Component } from 'react';
import '../css/App.css';
import {BrowserRouter as Router, withRouter} from "react-router-dom";
import Header from "./Header";


class PossibleSchedules extends Component {
    constructor(props) {
        super(props);
        this.state = {loggedIn: true};
    }

    createTable2(){
        let options = [];

        // Outer loop
        /*TODO
        here we should loop for every course, show associated sections & times
        should iterate till it reaches the number of coursed selected by user
        */
        for (let i = 0; i < 5; i++) {
          //  let sections = [];
        }
        return options;
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
                        let time = i + ":" + quarterHours[k];
                        times.push(time);
                        table.push(
                            <table className="table table-striped">
                                <tbody>
                                <tr>
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
                            <td>course</td>
                        )
                    }
                    else {
                        children.push(
                            // request course info from db ('course' is used for testing purposes)
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
                <Router ClassName="header-fix">
                    <Header />
                </Router>
            <div className="container-  select-semester">
                <div className="container-">
                    <img className="logo- semester" src={require("../assets/SOEN.jpg")} alt="SOEN SCHEDULER BUILDER"/>
                    <h1>Possible schedules</h1>
                    <hr/>

                    <table className=" container table possible table-striped">
                        <tbody className="container">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Monday</th>
                            <th scope="col">Tuesday</th>
                            <th scope="col">Wednesday</th>
                            <th scope="col">Thursday</th>
                            <th scope="col">Friday</th>
                        </tr>
                        </thead>
                        {this.createTable()}
                        </tbody>
                    </table>
                    <h3>Option 1</h3>
                </div>
            </div>
            </div>
        );
    }
}
export default withRouter(PossibleSchedules);
