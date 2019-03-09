import React, { Component } from 'react';
import '../css/App.css';
import axios from 'axios';

class Signup extends Component {

//
    render(){
        return(
            <div><h2>Home</h2></div>


        );
    }
    login() {
        // alert(name +" " + pass);
        axios.post('http://localhost:8080/signup', {
        }).then(res => {
        });

    }

}

export default Signup;
