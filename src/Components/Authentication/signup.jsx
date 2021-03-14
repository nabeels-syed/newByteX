import React from "react";

function SignUp() {
  return (

<div className='a-container-signup'>
   <div className='a-box-signup'>
      <form>
         <h3>Register</h3>
         <div className="form-group">
            <label>Full Name</label>
            <input type="text" className="contact-input" placeholder="First name" />
         </div>
         <div className="form-group">
            <label>Email</label>
            <input type="email" className="contact-input" placeholder="Enter email" />
         </div>
         <div className="form-group">
            <label>Address</label>
            <input type="text" className="contact-input" placeholder="Enter address" />
         </div>
         <div className="form-group">
            <label>Phone Number</label>
            <input type="text" className="contact-input" placeholder="Enter phone number" />
         </div>
         <div className="form-group">
            <label>Password</label>
            <input type="password" className="contact-input" placeholder="Enter password" />
         </div>
         <br></br>
         <div className='s-b-text'>
            <button type="submit" className="cv-btn">Register</button>
         </div>
         <p className="forgot-password text-right">
            Already registered <a href="sign-in">log in?</a>
         </p>
      </form>
   </div>
</div>
  )
}

export default SignUp;