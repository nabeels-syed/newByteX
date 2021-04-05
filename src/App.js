import React from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import { Navigation, Footer, Home, SignUp, SignIn, Forgot, ResetPassword, About, Contact } from "./Components";
function App() {
  return (
    <div className="App">
      <Router>
        <Navigation />
        <Switch>
          <Route path="/" exact component={() => <Home />} />
          <Route path="/sign-up" exact component={() => <SignUp />} />
          <Route path="/sign-in" exact component={() => <SignIn />} />
          <Route path="/forgot" exact component={() => <Forgot />} />
          <Route path="/reset" exact component={() => <ResetPassword />} />
          <Route path="/about" exact component={() => <About />} />
          <Route path="/contact" exact component={() => <Contact />} />
        </Switch>
        <Footer />
      </Router>
    </div>
  );
}

export default App;