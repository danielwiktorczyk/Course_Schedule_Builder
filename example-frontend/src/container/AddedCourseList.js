import React, { Component } from "react";

class AddedCourseList extends Component {
    constructor(props) {
        super(props);

        this.createTasks = this.createTasks.bind(this);
    }

    // delete(key) {
    //     this.props.delete(key);
    // }

    createTasks(item) {

        return<p className="theList" key={item.key}> {item.text}
            {/*<button onClick={() => this.delete(item.key)} className="col-1 close btn btn-home-log"> </button>*/}
        </p>
    }

    render() {
        var todoEntries = this.props.entries;
        var listItems = todoEntries.map(this.createTasks);


        return (
            <div>
            <p>Selected Courses:</p>
            <ul>{listItems}</ul>
            </div>
        );
    }
};

export default AddedCourseList;