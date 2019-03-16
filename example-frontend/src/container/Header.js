import React from 'react';
import { Link } from 'react-router-dom';
import '../css/App.css';

function Header() {
    return (
        <header className="headerStyle">
            <Link className="linkStyle" to="/">Sign in</Link> | <Link className="linkStyle" to="/Signup">Sign up</Link>
        </header>
    )
}
export default Header;