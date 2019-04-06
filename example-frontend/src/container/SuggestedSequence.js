import React, { Component } from 'react';
import '../css/App.css';
import {BrowserRouter as Router, withRouter} from "react-router-dom";
import Header from "./Header";
 import axios from "axios";


class SuggestedSequence extends Component {
    constructor(props) {
        super(props);
        this.state = {
            loggedIn: true,
            courseCompleted : [],
        };
    }

    async componentDidMount() {
        axios.post('http://localhost:8080/progression')
            .then((res) => {
                this.setState({coursesCompleted: res.data})
            })
            .catch((error) => {
                console.log(error)
            })
    }

    render(){
        //array for first semester
        function ListSem1Courses(props) {
            return <li>{props.value}</li>;
        }

        function CoursesList1(props) {
            const courses1 = props.courses1;
            return (
                <ul>
                    {courses1.map((course) =>
                        <ListSem1Courses key={course.toString()} value={course} />
                    )}
                </ul>
            );
        }

        //array for second semester
        function ListSem2Courses(props) {
            return <li>{props.value}</li>;
        }

        function CoursesList2(props) {
            const courses2 = props.courses2;
            return (
                <ul>
                    {courses2.map((course) =>
                        <ListSem2Courses key={course.toString()} value={course} />
                    )}
                </ul>
            );
        }
        //array for third semester
        function ListSem3Courses(props) {
            return <li>{props.value}</li>;
        }

        function CoursesList3(props) {
            const courses3 = props.courses3;
            return (
                <ul>
                    {courses3.map((course) =>
                        <ListSem3Courses key={course.toString()} value={course} />
                    )}
                </ul>
            );
        }
        //array for semester 4
        function ListSem4Courses(props) {
            return <li>{props.value}</li>;
        }

        function CoursesList4(props) {
            const courses4 = props.courses4;
            return (
                <ul>
                    {courses4.map((course) =>
                        <ListSem4Courses key={course.toString()} value={course} />
                    )}
                </ul>
            );
        }
        //array for semester 5
        function ListSem5Courses(props) {
            return <li>{props.value}</li>;
        }

        function CoursesList5(props) {
            const courses5 = props.courses5;
            return (
                <ul>
                    {courses5.map((course) =>
                        <ListSem5Courses key={course.toString()} value={course} />
                    )}
                </ul>
            );
        }
        //array for semester 6
        function ListSem6Courses(props) {
            return <li>{props.value}</li>;
        }

        function CoursesList6(props) {
            const courses6 = props.courses6;
            return (
                <ul>
                    {courses6.map((course) =>
                        <ListSem6Courses key={course.toString()} value={course} />
                    )}
                </ul>
            );
        }
        //array for semester 7
        function ListSem7Courses(props) {
            return <li><span>{props.value}</span></li>;
        }

        function CoursesList7(props) {
            const courses7 = props.courses7;
            return (
                <ul>
                    {courses7.map((course) =>
                        <ListSem7Courses key={course.toString()} value={course} />
                    )}
                </ul>
            );
        }
        //array for semester 8
        function ListSem8Courses(props) {
            return <li>{props.value}</li>;
        }

        function CoursesList8(props) {
            const courses8 = props.courses8;
            return (
                <ul>
                    {courses8.map((course) =>
                        <ListSem8Courses key={course.toString()} value={course} />
                    )}
                </ul>
            );
        }

        const courses1 = ["COMP232" , "COMP248", "ENGR201", "ENGR213", "General Elective"];
        const courses2= ["COMP249" , "SOEN228", "ENGR233", "SOEN287", "Basic Science 1"];
        const courses3= ["COMP384" , "COMP352", "ENCS282", "ENGR202", "Basic Science 2"];
        const courses4= ["COMP346" , "ELEC275", "ENGR371", "SOEN331", "SOEN341"];
        const courses5= ["COMP335" , "SOEN384", "ENGR391", "SOEN342", "SOEN343"];
        const courses6= ["SOEN344" , "SOEN345", "SOEN357", "SOEN390", "Elective 1"];
        const courses7= ["ENGR301" , "SOEN321", "SOEN490", "Elective 2", "Elective 3"];
        const courses8= ["ENGR392" , "SOEN385", "SOEN490", "Elective 4", "Elective 5"];

        return (
            <div className="sequence">
                <Router>
                    <Header />
                </Router>
                <div className="course_seq">
                    <hr/>
                    <h3 className="middle">Software Engineering - General Program </h3>
                    <hr/>
                    <div className="row">
                        <div className="col-6 add_space">
                            <h4><i className="fa fa-book"> </i> Semester 1</h4>
                            <CoursesList1 courses1={courses1} />
                            <h4><i className="fa fa-book"> </i> Semester 2</h4>
                            <CoursesList2 courses2={courses2} />
                            <h4><i className="fa fa-book"> </i> Semester 3</h4>
                            <CoursesList3 courses3={courses3} />
                            <h4><i className="fa fa-book"> </i> Semester 4</h4>
                            <CoursesList4 courses4={courses4} />
                        </div>
                        <div className="col-6 add_space">
                            <h4><i className="fa fa-book"> </i> Semester 5</h4>
                            <CoursesList5 courses5={courses5} />
                            <h4><i className="fa fa-book"> </i> Semester 6</h4>
                            <CoursesList6 courses6={courses6} />
                            <h4><i className="fa fa-book"> </i> Semester 7</h4>
                            <CoursesList7 courses7={courses7} />
                            <h4><i className="fa fa-book"> </i> Semester 8</h4>
                            <CoursesList8 courses8={courses8} />
                        </div>
                    </div>

                </div>

            </div>

        );
    }
}
export default withRouter(SuggestedSequence);

