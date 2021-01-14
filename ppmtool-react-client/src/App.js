
import './App.css';
import Dashboard from './components/Dashboard';
import Header from './components/Layout/Header';
import "bootstrap/dist/css/bootstrap.min.css"; 
import {BrowserRouter as Router, Route } from "react-router-dom";
import addProject from './components/Project/addProject';

function App() {
  return (
    <Router>
      <div className="App">
        <Header />
        <Route exact path="/dashboard" component={Dashboard} />
        <Route exact path="/addProject" component={addProject} />
      </div>
    </Router>
  );
}

export default App;
