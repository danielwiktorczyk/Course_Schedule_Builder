import React, { Component } from "react";

class AddedCourseList extends Component {

    createTasks(item) {
        return<p className="theList" key={item.key}>Selected Courses: {item.text}</p>
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