import React, { Component } from 'react';
import '../css/App.css';
import {BrowserRouter as Router, withRouter} from "react-router-dom";
import Header from "./Header";
import axios from 'axios';
import moment from 'moment';

class ViewMySchedule extends Component {
    constructor(props) {
        super(props);
        this.editSchedule =this.editSchedule.bind(this);
        this.state = {
            loggedIn: true,
            loading: true,
            person: null,
            data: [],
            offset: 0,
            size: 0,
        };
    }

    // print=()=>{
    //     window.print();
    // }

    componentDidMount() {

        console.log('PrintThisComponent mounted!')

    }



    //
    // async componentDidMount() {
    //     const url = "https://api.randomuser.me/";
    //     const response = await fetch(url);
    //     const data = await response.json();
    //     this.setState({ person: data.results[0], loading: false });
    // }

    // paginate with respect to next
    summer = ()  => {
        if(true){
            axios.post('http://localhost:8080/SummerSchedule')
                .then((res) => {
                    if (res.data == ""){
                        alert("You do not have a Summer schedule");
                        return;
                    }
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
    }

    fall = ()  => {
        if(true){
            axios.post('http://localhost:8080/fallSchedule')
                .then((res) => {
                    if (res.data == ""){
                        alert("You do not have a Fall schedule");
                        return;
                    }
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
    }

    // paginate with respect to previous
    winter = ()  => {
        if(true){
            axios.post('http://localhost:8080/WinterSchedule')
                .then((res) => {
                    if (res.data == ""){
                        alert("You do not have a Winter schedule");
                        return;
                    }
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
    }



    // get the data by default
    async componentDidMount() {
        const {offset}  = this.state
        axios.post('http://localhost:8080/fallSchedule')
            .then((res) => {
                if (res.data == '')
                    return;
                this.setState({data : res.data.courseTrio , size : res.data.size})
            })
            .catch((error) => {
                console.log(error)
            })

    }

    editSchedule() {
        let path = '/AddOrDrop';
        this.props.history.push(path);
    }

    render() {
        // if (this.state.loading) {
        //     return <div>loading...</div>;
        // }
        //
        // if (!this.state.person) {
        //     return <div>didn't get a person</div>;
        // }
        const {data} = this.state
        const timeTable = ['8 AM' , '9 AM' ,'10 AM' ,'11 AM' , '12 PM' , '1 PM', '2 PM' , '3 PM' , '4 PM' , '5 PM' , '6 PM' , '7 PM' , '8 PM' , '9 PM' , '10 PM' ]
        const schedule = [JSON.parse(JSON.stringify(data))]
        const arry = []
        schedule[0].map((item,key) => {
            return(
                arry.push(item.lecture) &&
                arry.push(item.lab) && arry.push(item.tutorial)
            )
        })


        return (

            <div>
                <Router>
                    <Header />
                </Router>
                <div className="container select-semester show-schedule">
                    <div className="table__wrapper">
                        <div className="table_heading_wrapper">


                            <hr/>
                            <h3 className="table_heading">My Weekly Schedule {localStorage.getItem("a")}</h3>
                            <button id="add-drop" className="col-2 btn btn-home-log" onClick={this.editSchedule}>Add / Drop</button>
                            <button className="col-1 btn btn-home-log print" onClick={() => window.print()}  >
                                <i className="fa fa-print" ></i></button>
                            <hr/>
                            <button className="col-2 btn btn-home-log" onClick={this.fall}>Fall</button>
                            <button className="col-2 btn btn-home-log" onClick={this.winter}>Winter</button>
                            <button className="col-2 btn btn-home-log" onClick={this.summer}>Summer</button>

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
    var td = []
    for(let i = 0 ; i < 5 ; i++ ){
        let check  = time === moment().startOf('day').add(startTime, 'minutes').format('h A')
        check ? (
            classDays[i] ? td.push( <td key={i}>{ classDays[i] && name } <br/>{classDays[i] && component }  <br/>{classDays[i] && section }<br/> {classDays[i] && moment().startOf('day').add(startTime, 'minutes').format('hh:mm A') + ' - ' + moment().startOf('day').add(endTime, 'minutes').format('hh:mm A')}</td>) : td.push(<td key={i}></td>)
        ) : unique === 0 ? td.push(<td key={i}></td>) : td.push()
    }
    return td;
}
const Row = ({startTime, endTime, classDays, name , component, time ,unique, section}) => (
    <tr>
        {unique === 0 ? <td><strong>{time}</strong></td> :  time === moment().startOf('day').add(startTime, 'minutes').format('h A') ? <td></td> :null }
        { name && createCells(classDays, name , component , startTime , endTime, time , unique, section) }
    </tr>
)

export default withRouter(ViewMySchedule);
