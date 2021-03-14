import React from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import { Navigation, Footer, Home, SignUp, SignIn } from "./Components";
function App() {
  return (
    <div className="App">
      <Router>
        <Navigation />
        <Switch>
          <Route path="/" exact component={() => <Home />} />
          <Route path="/sign-up" exact component={() => <SignUp />} />
          <Route path="/sign-in" exact component={() => <SignIn />} />
        </Switch>
        <Footer />
      </Router>
    </div>
  );
}

export default App;