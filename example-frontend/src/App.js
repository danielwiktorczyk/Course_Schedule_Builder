import React, { Component } from 'react';
import Signin from './container/Signin';
import './css/App.css';
// import Signup from './container/Signup';

class App extends Component {

  render() {
    return (
      <div className="App">
        <Signin />
        {/*<Signup />*/}

      </div>
    );
  }
}
export default App;
