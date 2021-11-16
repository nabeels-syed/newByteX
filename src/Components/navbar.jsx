import React, { useState } from "react";
import { withRouter } from "react-router-dom";
import logo from "../Assests/Images/Logo.png";

function Navigation(props) {
  const [nav, setNav] = useState(false);

  const changeBackround = () => {
    if (window.scrollY >= 50) {
      setNav(true);
    } else {
      setNav(false);
    }
  };

  window.addEventListener("scroll", changeBackround);

  return (
    <nav className={nav ? "nav active" : "nav"}>
      <a href="/">
        <p>
          <img src={logo} alt="InDMix" class="navLogo" />
        </p>
      </a>
      <input type="checkbox" className="menu-btn" id="menu-btn" />
      <label className="menu-icon" for="menu-btn">
        <span className="nav-icon"></span>
      </label>
      <ul className="menu">
        <li>
          <a href="/" className="active">
            Home
          </a>
        </li>
        <li>
          <a href="/about">About</a>
        </li>
        <li>
          <a
            href="https://dmixbreed.creator-spring.com/"
            target="_blank"
            rel="noreferrer"
          >
            Merch
          </a>
        </li>
        <li>
          <a href="/tracks">Tracks</a>
        </li>
        <li>
          <a href="/contact">Contact</a>
        </li>
        <li>
          <div>&nbsp; &nbsp; &nbsp;</div>
        </li>
        <li>
          <a href="/sign-up">Sign Up</a>
        </li>
        <li>
          <a href="/sign-in">Sign In</a>
        </li>
        <li>
          <div>&nbsp; &nbsp; &nbsp;</div>
        </li>
        {/* <li>
          <a href="/siteManagement">Site Management</a>
        </li> */}
      </ul>
    </nav>
  );
}

export default withRouter(Navigation);
