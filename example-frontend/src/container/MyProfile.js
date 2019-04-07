import React, { Component } from 'react';
import '../css/App.css';
import {BrowserRouter as Router, withRouter} from "react-router-dom";
import Header from "./Header";
import axios from "axios";


class MyProfile extends Component {
    constructor(props) {
        super(props);
        this.state = {
            loggedIn: true,
            data: "",
            prereqs: []
        };
        // this.getUserInfo = this.getUserInfo().bind(this);
    }
    async componentDidMount() {
        axios.post('http://localhost:8080/User')
            .then((res) => {
                this.setState({data : res.data} )
                let numberOfPrereqs = res.data.prereqs.length;
                for(let i=0; i<numberOfPrereqs;i++) {
                    if(i < numberOfPrereqs-1)
                        this.setState({prereqs: this.state.prereqs + res.data.prereqs[i] + ", "});
                    else
                        this.setState({prereqs: this.state.prereqs + res.data.prereqs[i] + ". "});
                }
            })
            .catch((error) => {
                console.log(error)
            })

    }


    render(){
        return (
       <div>
           <Router>
                   <Header />
           </Router>

           <div className="container adjust-item student-profile" id="student">
               <div >
                   <img className="student-profile-logo " src={require("../assets/SOEN-LOGO.JPG")} alt="SOEN SCHEDULER BUILDER"/>
               </div>
           <div className="myProfile">
           <h2>Personal Profile </h2>
               <hr/>
               <div className="row">
                   <img className="col-2 student-img" src={require("../assets/Student.JPG")} alt="SOEN SCHEDULER BUILDER"/>
                   {/*{this.getUserInfo()}*/}
                   {/*get variables from db--- need to check user is logged in first!*/}
                   <div className="col-10 myProfile-inner">
                       <ul>
                           <li>First Name: {this.state.data.firstName}</li>
                           <li>Last Name: {this.state.data.lastName}</li>
                           <li>Username: {this.state.data.username}</li>
                           <li>E-mail Address: {this.state.data.email}</li>
                           <li>Courses Taken: {this.state.prereqs}</li>
                       </ul>
                   </div>
               </div>

               </div>
           </div>
       </div>

        );
    }
}
export default withRouter(MyProfile);

