import React, { Component } from 'react';
import '../css/App.css';
// import dateFns from "date-fns";
// import Calendar from "./components/Calendar";
import {withRouter} from "react-router-dom";


class PossibleSchedules extends Component {
    constructor(props) {
        super(props);
        this.state = {loggedIn: true};
    }

    createTable2(){
        let options = [];

        // Outer loop
        /*TODO
        here we should loop for every course, show associates sections & times
        should iterate till it reaches the number of coursed selected by user
        */
        for (let i = 0; i < 5; i++) {
            let sections = [];
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

                    //weekdays
                    for (let j = 0; j < 5; j++) {
                        children.push(
                            // request course info from db ('course' is used for testing purposes)
                            <td>course</td>
                        )
                    }
                    // 15-min 4 times/ hour
                    for(let k = 0; k < 4; k++){
                        let time = i + ":" + quarterHours[k];
                        if(i < 10){
                            time = "0" + time;
                        }
                        times.push(time);
                        table.push(
                            <table className="table table-striped">
                                {/*<thead>*/}
                                {/*<tr>*/}
                                    {/*<th scope="col">#</th>*/}
                                    {/*<th scope="col">Monday</th>*/}
                                    {/*<th scope="col">Tuesday</th>*/}
                                    {/*<th scope="col">Wednesday</th>*/}
                                    {/*<th scope="col">Thursday</th>*/}
                                    {/*<th scope="col">Friday</th>*/}
                                {/*</tr>*/}
                                {/*</thead>*/}
                                <tbody>
                                <tr>
                                    {i} : {quarterHours[k]} {children}
                                </tr>
                                </tbody>
                            </table>

                            // <table className=" container table possible table-striped">
                            //     <tbody className="container">
                            //          <th> MONDAY</th>
                            //             <tr> {i} : {quarterHours[j]} {children}</tr>
                            //
                            //     </tbody>
                            // </table>
                        );
                }
            }
            return table;
        }

    render() {
        return (
            <div className="container-  select-semester">
                <div className="container-">
                    <a href="/"><img className="logo- semester" src={require("../assets/SOEN.jpg")} alt="SOEN SCHEDULER BUILDER"/></a>
                    <h1>Possible schedules</h1>
                    <hr/>
                    <table className=" container table possible table-striped">
                        <tbody className="container">
                        <thead>
                        <tr>
                            <th scope="col"></th>
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
        );
    }
}
export default withRouter(PossibleSchedules);
