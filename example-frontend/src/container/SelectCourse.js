import React, { Component } from 'react';
import '../css/App.css';
import AddedCourseList from "./AddedCourseList";
import {withRouter} from "react-router-dom";


class SelectCourse extends Component {

    constructor(props) {
        super(props);
        this.state = {loggedIn: true};
        this.routeChange = this.routeChange.bind(this);
        this.generateSchedule = this. generateSchedule.bind(this);
        this.addItem = this.addItem.bind(this);
        this.deleteItem = this.deleteItem.bind(this);
        this.state = {
            items: []
        };
    };

    routeChange() {
        let path = '/SelectSemester';
        this.props.history.push(path);
    }
    generateSchedule() {
        let path = '/PossibleSchedules';
        this.props.history.push(path);
    }

    deleteItem(key) {
        var filteredItems = this.state.items.filter(function (item) {
            return (item.key !== key);
        });

        this.setState({
            items: filteredItems
        });
    }

    addItem(e) {
        if (this._inputElement.value !== "") {
            var newItem = {
                text: this._inputElement.value,
                key: Date.now()
            };

            this.setState((prevState) => {
                return {
                    items: prevState.items.concat(newItem)
                };
            });

            this._inputElement.value = "";
            e.preventDefault();
        }
    }


    render(){
        return (
            <div className="container s-course">

                <div >
                    <img  className="ScheduleGen-" src={require("../assets/SOEN.jpg")} alt="SOEN SCHEDULER BUILDER"/>
                </div>
                <h1>COURSE SELECTION</h1><hr/>

                <h2>Please enter Course number</h2><hr/>

                <div>
                <form onSubmit={this.addItem}>
                <div className="row">
                    <input ref={(a) => this._inputElement = a} className="col-8 course-name" type="text" name="coursename" placeholder="Ex: SOEN 341"/>
                    <button className="col-3 btn btn-home-log">ADD</button>
                </div>
                </form>
                <AddedCourseList entries={this.state.items}  delete={this.deleteItem}/>
                </div>

                <div>
                    <button className="col-3 btn btn-home-log" onClick={this.generateSchedule}>GENERATE</button>
                    <button className="col-3 btn btn-home-log" onClick={this.routeChange}>CHANGE SEMESTER</button>
                </div>


            </div>
        );
    }
}
export default withRouter(SelectCourse);