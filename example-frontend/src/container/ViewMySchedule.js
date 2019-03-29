import React, { Component } from 'react';
import '../css/App.css';
import {BrowserRouter as Router, withRouter} from "react-router-dom";
import Header from "./Header";


class ViewMySchedule extends Component {
    constructor(props) {
        super(props);
        this.state = {loggedIn: true};
    }


    // state = {
    //     loading: true,
    //     person: null
    // };
    //
    // async componentDidMount() {
    //     const url = "https://api.randomuser.me/";
    //     const response = await fetch(url);
    //     const data = await response.json();
    //     this.setState({ person: data.results[0], loading: false });
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
        // if (this.state.loading) {
        //     return <div>loading...</div>;
        // }
        //
        // if (!this.state.person) {
        //     return <div>didn't get a person</div>;
        // }

        return (
            <div>
                <Router>
                    <Header />
                </Router>
                <div className="container-  select-semester">
                    <div className="container-">
                        <div className="row">

                                {/*<div>{this.state.person.name.title}</div>*/}
                                {/*<div>{this.state.person.name.first}</div>*/}


                        </div>
                        <table className=" container table possible table-striped">
                            <tbody className="container">
                            {this.createTable()}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        );
    }
}
export default withRouter(ViewMySchedule);
