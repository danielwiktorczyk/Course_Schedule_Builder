import React, { Component } from "react";

class AddedCourseList extends Component {
    constructor(props) {
        super(props);

        this.createTasks = this.createTasks.bind(this);
    }

    delete(key) {
        this.props.delete(key);
    }

    createTasks(item) {

        return<p className="theList" key={item.key}>Selected Courses: {item.text}
            <button onClick={() => this.delete(item.key)} className="col-3 btn btn-home-log">X</button></p>


    }

    render() {
        var todoEntries = this.props.entries;
        var listItems = todoEntries.map(this.createTasks);


        return (
            <ul>{listItems}</ul>


        );
    }
};

export default AddedCourseList;