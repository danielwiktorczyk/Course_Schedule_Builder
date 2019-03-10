import React, { Component } from 'react';
import '../css/App.css';
import axios from 'axios';


class SelectCourse extends Component {


    render(){
        return(

            <div className="container center card-signup" id="inside">

                    <h1>SELECT COURSE HERE</h1>


            </div>
        );
    }

    login() {
        var name;
        var pass;
        var element;
        element = document.getElementById('user');
        if (element != null) {
            name = element.value;
        }
        else {
            name = null;
        }
        element = document.getElementById('pass');
        if (element != null) {
            pass = element.value;
        }
        else {
            pass = null;
        }


        // alert(name +" " + pass);
        axios.post('http://localhost:8080/login', {
            username: name,
            password: pass
        }).then(res => {
            //   alert("Received Successful response from server!");
            this.setState({loggedIn: res.data});
            if (this.state.loggedIn === true)
                alert("logged in"); // login returns true
            else
                alert("invalid password"); //login returns false
        }, err => {
            alert("Server rejected response: " + err);
        });

    }

}

export default SelectCourse;
