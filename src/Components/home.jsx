import React from "react";
import Navbar from './navbar';

function Home() {
  return (
      <div id='main'>
          <Navbar/>
          <div className='name'>
              <h1><span>Hello!</span> Welcome to your new site!</h1>
              <p className='details'>Explore mixes, products and watch livestreams!</p>
          </div>
      </div>
  )
}

export default Home;