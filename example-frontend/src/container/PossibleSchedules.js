import React, { Component } from 'react';
import '../css/App.css';
import {BrowserRouter as Router, withRouter} from "react-router-dom";
import Header from "./Header";


class PossibleSchedules extends Component {
    constructor(props) {
        super(props);
        this.state = {loggedIn: true};
    }

    // createTable will be used to show tables with possible sections on the right-hand side of the screen
    // createTable2(){
    //     let options = [];
    //
    //     // Outer loop
    //     /*TODO
    //     here we should loop for every course, show associated sections & times
    //     should iterate till it reaches the number of coursed selected by user
    //     */
    //     for (let i = 0; i < 5; i++) {
    //         //let sections = [];
    //     }
    //     return options;
    // }

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
                            <td>course</td>
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
        return (
            <div>
                <Router>
                    <Header />
                </Router>
            <div className="container-  select-semester">
                <div className="container-">
                    <hr/><h1 className="show-options">Possible schedules</h1>
                    <div className="row row-for-arrow">
                        <img className="center-arrows" src={require("../assets/double-left.JPG")} alt="left"/>
                        <img className="center-arrows" src={require("../assets/left-arrow.JPG")} alt="left"/>
                        <div className="show-option-num">##</div>
                        <img className="center-arrows" src={require("../assets/right-arrow.JPG")} alt="right"/>
                        <img className="center-arrows" src={require("../assets/double-right.JPG")} alt="right"/>
                    </div>
                    <hr/>
                    <table className=" container table possible table-striped">
                        <tbody className="container">
                        {this.createTable()}
                        </tbody>
                    </table>
                    <div>
                        <h3>Option ##</h3>

                        {/*<div className="dropdown">*/}
                            {/*<a className="btn btn-secondary dropdown-toggle" role="button"*/}
                               {/*id="dropdownMenuLink" data-toggle="dropdown">*/}
                                {/*Dropdown link*/}
                            {/*</a>*/}

                            {/*<div className="dropdown-menu" aria-labelledby="dropdownMenuLink">*/}
                                {/*<a className="dropdown-item" href="#">Most days off</a>*/}
                                {/*<a className="dropdown-item" href="#">Online classes</a>*/}
                                {/*<a className="dropdown-item" href="#">No online classes</a>*/}
                                {/*<a className="dropdown-item" href="#">Time off campus</a>*/}
                            {/*</div>*/}
                        {/*</div>*/}

                        <div className="dropdown">
                            <button className="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenu2"
                                    data-toggle="dropdown">
                                Preferences
                            </button>
                            <div className="dropdown-menu">
                                <button className="dropdown-item" type="button">Most days off</button>
                                <button className="dropdown-item" type="button">Online classes</button>
                                <button className="dropdown-item" type="button">Time off campus</button>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
            </div>
        );
    }
}
export default withRouter(PossibleSchedules);
