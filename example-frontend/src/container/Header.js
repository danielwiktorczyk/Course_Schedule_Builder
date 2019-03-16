import React from 'react';
import { Link } from 'react-router-dom';
import '../css/App.css';
import axios from 'axios';

function Header() {
    return (
        <header; style={headerStyle}>
            <Link; style={linkStyle}; to="/">Sign in</Link> | <Link style={linkStyle} to="/;Signup;">Sign up</Link> | <Link style={linkStyle} to="/SelectCourse;">Select course</Link>
        </header>;
    )
}

const headerStyle = {
    background: 'darkred',
    color: '#fff',
    textAlign: 'center',
    padding: '10px'
};

const linkStyle = {
    color: '#fff',
    textDecoration: 'none'
};

export default Header;