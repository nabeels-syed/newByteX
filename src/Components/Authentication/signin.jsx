import Avatar from "@material-ui/core/Avatar";
import Box from "@material-ui/core/Box";
import Button from "@material-ui/core/Button";
import Container from "@material-ui/core/Container";
import CssBaseline from "@material-ui/core/CssBaseline";
import Grid from "@material-ui/core/Grid";
import { makeStyles } from "@material-ui/core/styles";
import TextField from "@material-ui/core/TextField";
import Typography from "@material-ui/core/Typography";
import AccountCircleSharpIcon from "@mui/icons-material/AccountCircleSharp";
import React from "react";
import { NavLink } from "react-router-dom";
import Cookies from 'universal-cookie';

const useStyles = makeStyles((theme) => ({
  paper: {
    marginTop: theme.spacing(7),
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
  },
  avatar: {
    margin: theme.spacing(1),
    backgroundColor: theme.palette.primary.dark,
  },
  form: {
    width: "100%", // Fix IE 11 issue.
    marginTop: theme.spacing(3),
  },
  submit: {
    margin: theme.spacing(3, 0, 2),
  },
  textField: {
    marginLeft: theme.spacing(1),
    marginRight: theme.spacing(1),
    width: "100%",
  },
}));

export default function SignIn() {
  const classes = useStyles();
  const [firstLoad, setLoad] = React.useState(true);

  const [email, setEmail] = React.useState("");
  const [password, setPassword] = React.useState("");

  const handleEmailChange = (event) => setEmail(event.target.value);
  const handlePasswordChange = (event) => setPassword(event.target.value);

  const [message, setMessage] = React.useState("");

  async function apiFunc(toInput) {
    const response = await fetch("/api/auth/login", {
      method: "POST",
      mode: "cors",
      cache: "no-cache",
      credentials: "same-origin",
      headers: {
        "Content-Type": "application/json",
      },
      redirect: "follow",
      referrerPolicy: "no-referrer",
      body: JSON.stringify(toInput),
    })
    .then(response => response.json())
    .then(response => {

    if (!response.token || response.token == null) {
      console.log("No token")
      alert("Error! Failed to sign in. Please check your email and/or password and try again.");
    } else {
      const cookies = new Cookies();
      cookies.set('token', {key: response.token}, {path: '/', expires: new Date(Date.now()+86400)});
      console.log("Signed in")
      alert("Successfully sign in!");
    }

    // let body = await response;
    // console.log(body);
    // setMessage(body.ok ? "Sign in successful" : "Sign in failed");
    // if (
    //   !alert(
    //     body.ok
    //       ? "Successfully sign in!"
    //       : "Error! Failed to sign in. Please check your email and/or password and try again."
    //   )
    // ) {
    //   // window.location.reload();
    // }
});
  }

  const handleSubmit = (variables) => {
    const toInput = { email, password };
    apiFunc(toInput);
    setEmail("");
    setPassword("");
  };

  if (firstLoad) {
    // sampleFunc();
    setLoad(false);
  }

  function Copyright() {
    return (
      <Typography variant="body2" color="textSecondary" align="center">
        {"newByteX © "} {new Date().getFullYear()}
      </Typography>
    );
  }

  return (
    <Container component="main" maxWidth="xs">
      <CssBaseline />
      <div className={classes.paper}>
        <br></br>
        <br></br>
        <br></br>
        <Avatar className={classes.avatar}>
          <AccountCircleSharpIcon />
        </Avatar>
        <Typography component="h1" variant="h5">
          Sign In
        </Typography>
        <form className={classes.form} noValidate>
          <Grid container spacing={2}>
            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                id="email"
                value={email}
                label="Email"
                name="email"
                autoComplete="email"
                onChange={handleEmailChange}
              />
            </Grid>

            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                id="password"
                value={password}
                label="Password"
                type="password"
                name="password"
                autoComplete="password"
                onChange={handlePasswordChange}
              />
            </Grid>
          </Grid>
          <Button
            // type="submit"
            fullWidth
            variant="contained"
            color="info"
            preventefault
            className={classes.submit}
            onClick={handleSubmit}
          >
            Sign In
          </Button>
          <Grid container>
            <Grid item xs>
              <NavLink to="/forgot">Forgot Password?</NavLink>
            </Grid>
            <Grid item>
              <NavLink to="/sign-up">Don't have an account? Sign Up</NavLink>
            </Grid>
          </Grid>
        </form>
        <Typography style={{ margin: 7 }} variant="body1">
          Status: {message}
        </Typography>
      </div>
      <Box mt={8} class="copyrightFooter">
        <Copyright />
        <br></br>
      </Box>
    </Container>
  );
}
