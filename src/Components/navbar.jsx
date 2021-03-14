import React, { userState, useState } from "react";
import { Link, withRouter } from "react-router-dom";

function Navigation(props) {

  const [nav, setNav] = useState(false);

  const changeBackround = () => {
      if(window.scrollY >= 50) {
          setNav(true);
      } else {
          setNav(false);
      }
  }
  
  window.addEventListener('scroll', changeBackround);
  
  return (
    <nav className={nav ? 'nav active' : 'nav'}>
    <a href='#' className='logo'>
        {/* <img src={logo} alt=''/> */}
        <p>LOGO HERE</p>
    </a>
    <input type='checkbox' className='menu-btn' id='menu-btn'/>
    <label className='menu-icon' for='menu-btn'>
        <span className='nav-icon'></span>
    </label>
    <ul className='menu'>
        <li><a href='/' className='active'>Home</a></li>
        <li><a href='/sign-up'>Sign Up</a></li>
        <li><a href='/sign-in'>Sign In</a></li>
    </ul>
</nav>
  )
}

export default withRouter(Navigation);