import React, { Component } from 'react';
import '../css/App.css';
import {withRouter} from "react-router-dom";

class Footer extends Component {


    render(){
        return (
            <footer>
                <div className="footer">
                    <div className="">
                        <a >About Us</a>
                        <a >Play a Game!</a>
                    </div>
                </div>
            </footer>

        );
    }
}
export default withRouter(Footer);

