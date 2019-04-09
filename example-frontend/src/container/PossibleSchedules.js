import React, { Component } from 'react';
import '../css/App.css';
import {BrowserRouter as Router, withRouter} from "react-router-dom";
import Header from "./Header";
import axios from 'axios';
import moment from "moment";

class PossibleSchedules extends Component {
    constructor(props) {
        super(props);
        this.enroll = this.enroll.bind(this);
        this.state = {
            loggedIn: true,
            loading: true,
            person: null,
            pref: false,
            data : [],
            offset: 0,
            size : 0,
        };
    }
    toggle = (bool) => {
        if (this.state.pref === false){
            this.setState({pref: true})

        }else{
            this.setState({pref: false})
        }
        // axios.post('http://localhost:8080/') ///not sure where to post to

    };


    // paginate with respect to next
    next = ()  => {
        if(true){
            axios.post('http://localhost:8080/next')
                .then((res) => {
                    this.setState(prevState =>{
                        return{
                            offset : prevState.offset +1,
                            data : res.data.courseTrio

                        }
                    })
                })
                .catch((error) => {
                    console.log(error)
                })
        }
    };

    // paginate with respect to previous
    previous = ()  => {
        if(true){
            axios.post('http://localhost:8080/previous')
                .then((res) => {
                    this.setState(prevState =>{
                        return{
                            offset : prevState.offset -1,
                            data : res.data.courseTrio,
                        }
                    })
                })
                .catch((error) => {
                    console.log(error)
                })
        }
    };

    // get the data by default
    async componentDidMount() {
        const {offset}  = this.state
        axios.post('http://localhost:8080/generate')
            .then((res) => {
                this.setState({data : res.data.courseTrio , size : res.data.size})
            })
            .catch((error) => {
                console.log(error)
            })

    }

    enroll(){
        if( localStorage.getItem("a") === "FALL 2019"){
            axios.post('http://localhost:8080/enrollFall', {
            }).then(res => {
            }, err => {
                alert("Server rejected response: " + err);
            });
        }

        if( localStorage.getItem("a") === "WINTER 2019"){
            axios.post('http://localhost:8080/enrollWinter', {
            }).then(res => {
            }, err => {
                alert("Server rejected response: " + err);
            });
        }

        if( localStorage.getItem("a") === "SUMMER 2018"){
            axios.post('http://localhost:8080/enrollSummer', {
            }).then(res => {
            }, err => {
                alert("Server rejected response: " + err);
            });
        }
        let path = '/ViewMySchedule';
        this.props.history.push(path);
        window.location.reload();



    }

    getLocalIt = () => {

        return localStorage.getItem("a");
    };

    render() {
        // if (this.state.loading) {
        //     return <div>loading...</div>;
        // }
        //
        // if (!this.state.person) {
        //     return <div>didn't get a person</div>;
        // }
        const {data} = this.state;
        const timeTable = ['8 AM' , '9 AM' ,'10 AM' ,'11 AM' , '12 PM' , '1 PM', '2 PM' , '3 PM' , '4 PM' , '5 PM' , '6 PM' , '7 PM' , '8 PM' , '9 PM' , '10 PM' ]
        const schedule = [JSON.parse(JSON.stringify(data))]
        const arry = [];
        schedule[0].map((item,key) => {
            return(
                arry.push(item.lecture) &&
                arry.push(item.lab) && arry.push(item.tutorial)
            )
        });
        return (

            <div>
                <Router>
                    <Header />
                </Router>
                <div className="container select-semester show-schedule">
                    <div className="table__wrapper">
                        <div className="row">

                            {/*<div>{this.state.person.name.title}</div>*/}
                            {/*<div>{this.state.person.name.first}</div>*/}
                        </div>

                        <div className="table_heading_wrapper">
                            <hr/>
                            <h3 className="table_heading">WEEKLY SCHEDULE FOR {this.getLocalIt()}</h3>
                            <hr/>
                        </div>
                        <div className="row">
                            <button className="select-this-option btn btn-home-log" onClick={this.enroll}>Enroll in this Schedule</button>
                        </div>
                        <div className="button__wrapper">
                            <div className="position_buttons">
                                <button className="col-1 btn btn-home-log" onClick={this.previous}>Prev.</button>
                                /
                                <button className="col-1 btn btn-home-log" onClick={this.next}>Next</button>

                                <button className="btn btn-home-log toggle-pref" onClick={this.toggle}>Toggle Preferences</button>

                            </div>

                        </div>

                        <table className="table">
                            <thead>
                            <tr>
                                <th>Time/ period</th>
                                <th>Monday</th>
                                <th>Tuesday</th>
                                <th>Wednesday</th>
                                <th>Thursday</th>
                                <th>Friday</th>
                            </tr>
                            </thead>
                            <tbody className="tbody">
                            {
                                timeTable.map((time, key) => {
                                    return(
                                        arry.map((item,key) => {
                                            return(
                                                <Row key={key} time={time} unique={key}  {...item} />
                                            )
                                        })
                                    );
                                })
                            }
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        );
    }
}


const createCells = ({...classDays}, name , component , startTime , endTime, time , unique, section) => {
    var td = [];
    for(let i = 0 ; i < 5 ; i++ ){
        let check  = time === moment().startOf('day').add(startTime, 'minutes').format('h A')
        check ? (
            classDays[i] ? td.push( <td key={i}>{ classDays[i] && name } <br/>{classDays[i] && component }  <br/>{classDays[i] && section }<br/> {classDays[i] && moment().startOf('day').add(startTime, 'minutes').format('hh:mm A') + ' - ' + moment().startOf('day').add(endTime, 'minutes').format('hh:mm A')}</td>) : td.push(<td key={i}></td>)
        ) : unique === 0 ? td.push(<td key={i}></td>) : td.push()
    }
    return td;
};
const Row = ({startTime, endTime, classDays, name , component, time ,unique, section}) => (
    <tr>
        {unique === 0 ? <td><strong>{time}</strong></td> :  time === moment().startOf('day').add(startTime, 'minutes').format('h A') ? <td></td> :null }
        { name && createCells(classDays, name , component , startTime , endTime, time , unique, section) }
    </tr>
);
export default withRouter(PossibleSchedules);
