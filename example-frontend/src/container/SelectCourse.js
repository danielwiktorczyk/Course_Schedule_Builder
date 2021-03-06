import React, { Component } from 'react';
import '../css/App.css';
import AddedCourseList from "./AddedCourseList";
import {BrowserRouter as Router, withRouter} from "react-router-dom";
import Header from "./Header";
 import axios from 'axios';


function validate(coursename) {
    // true means invalid, so our conditions got reversed
    return {
        coursename: coursename.length === 0
    };
}

class SelectCourse extends Component {

    constructor(props) {
        super(props);
        this.state = {loggedIn: true};
        this.routeChange = this.routeChange.bind(this);
        this.generateSchedule = this.generateSchedule.bind(this);
        this.addItem = this.addItem.bind(this);
        this.clearList = this.clearList.bind(this);
        //  this.add_course = this.add_course.bind(this);

        this.state = {
            items: [],
            coursename: "",
            showMessage: false,
            showOptionsMonday: false,
            showOptionsTuesday: false,
            showOptionsWednesday: false,
            showOptionsThursday: false,
            showOptionsFriday: false
        };
    }
    _showMessage = (bool) => {
        if (this.state.showMessage === false){
            this.setState({showMessage: true})

        }else{
            this.setState({showMessage: false})
        }
    };
    _showOptionsMonday = (bool) =>{
        if (this.state.showOptionsMonday === false){
            this.setState({showOptionsMonday: true})
        }else{
            this.setState({showOptionsMonday: false})
        }
    };_showOptionsTuesday = (bool) =>{
        if (this.state.showOptionsTuesday === false){
            this.setState({showOptionsTuesday: true})
        }else{
            this.setState({showOptionsTuesday: false})
        }
    };_showOptionsWednesday = (bool) =>{
        if (this.state.showOptionsWednesday === false){
            this.setState({showOptionsWednesday: true})
        }else{
            this.setState({showOptionsWednesday: false})
        }
    };_showOptionsThursday = (bool) =>{
        if (this.state.showOptionsThursday === false){
            this.setState({showOptionsThursday: true})
        }else{
            this.setState({showOptionsThursday: false})
        }
    };_showOptionsFriday = (bool) =>{
        if (this.state.showOptionsFriday === false){
            this.setState({showOptionsFriday: true})
        }else{
            this.setState({showOptionsFriday: false})
        }
    };

    routeChange() {
        let path = '/SelectSemester';
        this.props.history.push(path);
    }

    generateSchedule() {
        let path = '/PossibleSchedules';
        this.props.history.push(path);
    }

    addItem(e) {
        function parseStringCourse(course) {

            return course.toUpperCase().replace(/ /g, "");
        }

        if (this._inputElement.value !== "") {
            let newItem = {
                text: this._inputElement.value,
                key: Date.now()
            };

            let message = parseStringCourse(this._inputElement.value);

            if( localStorage.getItem("a") === "FALL 2019") {
                axios.post('http://localhost:8080/addCourseToWishListFall', {
                    message

                }).then(res => {
                    if ("Course added!" == res.data) {
                    } else
                        alert(res.data);
                }, err => {
                    alert("Server rejected response: COURSE INFO NOT RECEIVED");
                });
            }

            if( localStorage.getItem("a") === "WINTER 2019") {
                axios.post('http://localhost:8080/addCourseToWishListWinter', {
                    message

                }).then(res => {
                    if ("Course added!" == res.data) {
                    } else
                        alert(res.data);
                }, err => {
                    alert("Server rejected response: COURSE INFO NOT RECEIVED");
                });
            }

            if( localStorage.getItem("a") === "SUMMER 2018") {
                axios.post('http://localhost:8080/addCourseToWishListSummer', {
                    message

                }).then(res => {
                    if ("Course added!" == res.data) {
                    } else
                        alert(res.data);
                }, err => {
                    alert("Server rejected response: COURSE INFO NOT RECEIVED");
                });
            }

            this.setState((prevState) => {
                return {
                    items: prevState.items.concat(newItem)
                };
            });

            this._inputElement.value = "";
            e.preventDefault();
        }

    }
    clearList(){
        axios.post('http://localhost:8080/clear', {
        }).then(res => {
        }, err => {
            alert("Server rejected response: COURSE INFO NOT RECEIVED");
        });
        window.location.reload();
    }



    handleCourseNameChange = evt => {
        this.setState({ coursename: evt.target.value });
    };

    handleSubmit = evt => {
        if (!this.canBeSubmitted()) {
            evt.preventDefault();
            return;
        }
        const { coursename} = this.state;
        alert(`Signed up with coursename: ${coursename}`);
    };

    canBeSubmitted() {
        const errors = validate(this.state.coursename);
        const isDisabled = Object.keys(errors).some(x => errors[x]);
        return !isDisabled;
    }

    getLocalItem = () => {

        return localStorage.getItem("a");
    };

    applyPref = () =>{
        let mm = false;
        let me = false;
        let mall = false;

        let tm = false;
        let te = false;
        let tall = false;

        let wm = false;
        let we = false;
        let wall = false;

        let thm = false;
        let the = false;
        let thall = false;

        let fm = false;
        let fe = false;
        let fall = false;

        if ( document.getElementById("mm").checked)
            mm = true ;
        else mm = false;

        if ( document.getElementById("me").checked)
            me = true ;
        else  me = false;

        if ( document.getElementById("mall").checked)
            mall = true ;
        else mall = false;

        if ( !document.getElementById("mm").checked&& !document.getElementById("me").checked && !document.getElementById("mall").checked)
                mm = me = mall = false;


        if ( document.getElementById("tm").checked)
            tm = true ;
        else tm =false;

        if ( document.getElementById("te").checked)
            te = true ;
        else te =false;





        if ( document.getElementById("tall").checked)
            tall = true ;
        else tall = false;

        if ( document.getElementById("wm").checked)
            wm = true ;
        else wm = false;

        if ( document.getElementById("we").checked)
            we = true ;
        else we = false;

        if ( document.getElementById("wall").checked)
            wall = true ;
        else wall =false;

        if ( document.getElementById("thm").checked)
            thm = true ;
        else thm = false;

        if ( document.getElementById("the").checked)
            the = true ;
        else the = false;

        if ( document.getElementById("thall").checked)
            thall = true ;
        else thall = false;

        if ( document.getElementById("fm").checked)
            fm = true ;
        else fm = false;

        if ( document.getElementById("fe").checked)
            fe = true ;
        else fe = false;

        if ( document.getElementById("fall").checked)
            fall = true ;
        else fall =false;

        if(true){
        axios.post("http://localhost:8080/userPreferences", {
            mm: mm , me : me , mall: mall ,
            tm: tm , te : te , tall: tall ,
            wm: wm , we : we , wall: wall ,
            thm: thm , the : the , thall: thall ,
            fm: fm , fe : fe , fall: fall
        }).then( res=>
        { if (res.data === "Success"){

        }
        else {
            alert(res.data);
        }

        })
            .catch((error) => {
                console.log(error)
            })
        }

    }
    
    render(){
        const errors = validate(this.state.coursename);
        const isDisabled = Object.keys(errors).some(x => errors[x]);

        return (
            <div>
                <Router>
                    <Header />
                </Router>
                <div className="container- s-course">

                    <div >
                        <img  className="ScheduleGen- logo-select-sem" src={require("../assets/SOEN-LOGO.JPG")} alt="SOEN SCHEDULER BUILDER"/>
                    </div>
                    <hr/><h2 className="adjust-h2">COURSE SELECTION FOR {this.getLocalItem()}</h2><hr/>

                    <div>
                        <form onSubmit={this.addItem}>
                            <div className="row">
                                <input id="course-name" ref={(a) => this._inputElement = a} className="col-8 course-name" value={this.state.coursename} onChange={this.handleCourseNameChange} type="text" name="coursename" placeholder="Ex: SOEN 341"/>
                                <button  className="col-3 btn btn-home-log">ADD</button>
                                {/*onClick={this.add_course}*/}
                            </div>
                        </form>
                        <AddedCourseList entries={this.state.items} />
                    </div>

                    <div>
                        <button className="col-3 btn btn-home-log select-semester-options" disabled={isDisabled} onClick={this.generateSchedule}>GENERATE</button>
                        <button className="col-3 btn btn-home-log select-semester-options" onClick={this.routeChange}>CHANGE SEMESTER</button>
                        <button className="col-3 btn btn-home-log select-semester-options" onClick={this.clearList}>CLEAR LIST</button>
                        <button className="col-3 btn btn-home-log select-semester-options" onClick={this._showMessage}>ADD PREFERENCES</button>
                        { this.state.showMessage && (
                            <div className="preferences">
                                <h6>Choose the Days/ Times You Would Like to Be away from Campus:</h6>
                                <label className="newwrapper">Monday
                                    <input type="checkbox"/>
                                        <span className="checkmark" onClick={this._showOptionsMonday}></span>
                                </label>
                                    <div className="choices">
                                        <label className="wrapping radio-inline">No Mornings
                                            <input id = "mm" type="radio" name="radio1"/>
                                                <span className="check-mark"></span>
                                        </label>
                                        <label className="wrapping radio-inline">No Evenings
                                            <input id = "me" type="radio" name="radio1"/>
                                                <span className="check-mark"></span>
                                        </label>
                                        <label className="wrapping radio-inline">All Day off
                                            <input id = "mall" type="radio" name="radio1"/>
                                            <span className="check-mark"></span>
                                        </label>
                                    </div>
                                <label className="newwrapper">Tuesday
                                    <input type="checkbox"/>
                                        <span className="checkmark" onClick={this._showOptionsTuesday}></span>
                                </label>
                                    <div className="choices">
                                        <label className="wrapping radio-inline">No Mornings
                                            <input id = "tm" type="radio" name="radio2"/>
                                            <span className="check-mark"></span>
                                        </label>
                                        <label className="wrapping radio-inline">No Evenings
                                            <input id = "te" type="radio" name="radio2"/>
                                            <span className="check-mark"></span>
                                        </label>
                                        <label className="wrapping radio-inline">All Day off
                                            <input id = "tall" type="radio" name="radio2"/>
                                            <span className="check-mark"></span>
                                        </label>
                                    </div>
                                <label className="newwrapper">Wednesday
                                    <input type="checkbox"/>
                                        <span className="checkmark" onClick={this._showOptionsWednesday}></span>
                                </label>
                                    <div className="choices">
                                        <label className="wrapping radio-inline">No Mornings
                                            <input id = "wm" type="radio" name="radio3"/>
                                            <span className="check-mark"></span>
                                        </label>
                                        <label className="wrapping radio-inline">No Evenings
                                            <input id = "we" type="radio" name="radio3"/>
                                            <span className="check-mark"></span>
                                        </label>
                                        <label className="wrapping radio-inline">All Day off
                                            <input id = "wall" type="radio" name="radio3"/>
                                            <span className="check-mark"></span>
                                        </label>
                                    </div>
                                <label className="newwrapper">Thursday
                                    <input type="checkbox"/>
                                        <span className="checkmark" onClick={this._showOptionsThursday}></span>
                                </label>
                                    <div className="choices">
                                        <label className="wrapping radio-inline">No Mornings
                                            <input id = "thm" type="radio" name="radio4"/>
                                            <span className="check-mark"></span>
                                        </label>
                                        <label className="wrapping radio-inline">No Evenings
                                            <input id = "the" type="radio" name="radio4"/>
                                            <span className="check-mark"></span>
                                        </label>
                                        <label className="wrapping radio-inline">All Day off
                                            <input id = "thall" type="radio" name="radio4"/>
                                            <span className="check-mark"></span>
                                        </label>
                                    </div>
                                <label className="newwrapper" >Friday
                                    <input type="checkbox"/>
                                        <span className="checkmark" onClick={this._showOptionsFriday}></span>
                                </label>
                                    <div className="choices">
                                        <label className="wrapping radio-inline">No Mornings
                                            <input id = "fm" type="radio" name="radio5"/>
                                            <span className="check-mark"></span>
                                        </label>
                                        <label className="wrapping radio-inline">No Evenings
                                            <input id = "fe" type="radio" name="radio5"/>
                                            <span className="check-mark"></span>
                                        </label>
                                        <label className="wrapping radio-inline">All Day off
                                            <input  id = "fall" type="radio" name="radio15"/>
                                            <span className="check-mark"></span>
                                        </label>
                                    </div>
                                <button id="save_changes" className="btn generate-with-pref" onClick={this.applyPref}>Apply Preferences</button>
                            </div>
                        ) }
                    </div>
                </div>
            </div>
        );
    }
}
export default withRouter(SelectCourse);
