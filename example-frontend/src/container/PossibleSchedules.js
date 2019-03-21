import React, { Component } from 'react';
import '../css/App.css';
import {withRouter} from "react-router-dom";


class PossibleSchedules extends Component {
    constructor(props) {
        super(props);
        this.state = {loggedIn: true};
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
                            <td> SOEN</td>
                        )
                    }
                    // 15-min 4 times/ hour
                    for(let j = 0; j < 4; j++){
                        let time = i + ":" + quarterHours[j];
                        if(i < 10){
                            time = "0" + time;
                        }
                        times.push(time);
                        table.push(<tr> {i} : {quarterHours[j]}  {children}</tr>);
                }
            }
            return table;
        }

    render() {
        return (
            <div className="container-  select-semester">
                <div className="container-">
                    <a href="#"><img className="logo semester" src={require("../assets/SOEN.jpg")} alt="SOEN SCHEDULER BUILDER"/></a>
                    <table className="table table-striped">
                        <tbody>
                        {this.createTable()}
                        </tbody>
                    </table>
                </div>
            </div>

        );
    }
}
export default withRouter(PossibleSchedules);

// createTable(){
//     let table = [];
//
//     // Outer loop to create parent
//     for (let i = 0; i < 5; i++) {
//         let children = [];
//         //Inner loop to create children
//         for (let j = 0; j < 5; j++) {
//             children.push(
//                 <td> td{`Column ${j + 1}`}</td>
//             )
//         }
//         //Create the parent and add the children
//         table.push(<tr> tr{children}</tr>);
//     }
//     return table;
// }