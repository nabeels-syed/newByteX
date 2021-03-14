import React from "react";

function SignIn() {
  return (

<div className='a-container-signup'>
   <div className='a-box-signin'>
      <form>
         <h3>Log In</h3>
         <div className="form-group">
            <label>Email</label>
            <input type="email" className="contact-input" placeholder="Enter email" />
         </div>
         <div className="form-group">
            <label>Password</label>
            <input type="password" className="contact-input" placeholder="Enter password" />
         </div>
         <br></br><br></br>
         <div className='s-b-text'>
            <button type="submit" className="cv-btn">Register</button>
         </div>
         <p className="forgot-password text-right">
            Not registered? <a href="sign-up">Sign up!</a>
         </p>
      </form>
   </div>
</div>
  )
}

export default SignIn;