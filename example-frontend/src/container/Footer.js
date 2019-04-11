import React, { Component } from 'react';
import '../css/App.css';
import {withRouter} from "react-router-dom";

class Footer extends Component {
    constructor(props) {
        super(props);
        this.goToPlayGame = this.goToPlayGame.bind(this);
        this.goToAboutUs = this.goToAboutUs.bind(this);
    }


    goToPlayGame() {

        let path = 'PlayGame.html';
        this.props.history.push(path);

    }

    goToAboutUs(){
        let path = '/AboutUs';
        this.props.history.push(path);

    }

    render(){
        return (
            <footer>
                <div className="footer">
                    <div className="">
                        <a onClick={this.goToAboutUs}>About Us</a>
                        <a href='PlayGame.html' target='_blank'>Play A Game!</a> /* the target blank opens it in a new tab (we can remove it) */

                    </div>
                </div>
            </footer>

        );
    }
}
export default withRouter(Footer);

