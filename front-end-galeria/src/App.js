import React, { Component } from "react";
import { BrowserRouter as Router } from 'react-router-dom';
import GlobalStyle from './styles/global'
import Navbar from './components/Navbar/Navbar'

import Routes from './routes';


class App extends Component {

  render() {
  
    return (
      
      <Router>
        <Navbar/>
        <GlobalStyle />
        <Routes/>
      </Router>
    );
  }
}

export default App;
