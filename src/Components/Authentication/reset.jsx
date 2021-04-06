import React from "react";
import Avatar from "@material-ui/core/Avatar";
import Button from "@material-ui/core/Button";
import CssBaseline from "@material-ui/core/CssBaseline";
import TextField from "@material-ui/core/TextField";
import { Link, withRouter, NavLink } from "react-router-dom";
import Grid from "@material-ui/core/Grid";
import Box from "@material-ui/core/Box";
import GroupIcon from "@material-ui/icons/Group";
import Typography from "@material-ui/core/Typography";
import { makeStyles } from "@material-ui/core/styles";
import Container from "@material-ui/core/Container";

const useStyles = makeStyles((theme) => ({
  paper: {
    marginTop: theme.spacing(7),
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
  },
  avatar: {
    margin: theme.spacing(1),
    backgroundColor: theme.palette.secondary.main,
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

export default function ResetPassword() {
  const classes = useStyles();
  const [firstLoad, setLoad] = React.useState(true);

  const [password, setPassword] = React.useState("");
  const [confirmPassword, setConfirmPassword] = React.useState("");
  const [passwordResetCode, setToken] = React.useState("");

  const handlePasswordChange = (event) => setPassword(event.target.value);
  const handleConfirmPasswordChange = (event) =>
    setConfirmPassword(event.target.value);

  const [message, setMessage] = React.useState("");

  // console.log(window.location.href);
  // console.log(window.location.pathname);
  // console.log(window.location.search);
  var pathArray = window.location.search.split("=");
  var tokenURL = pathArray[1];
  console.log("Token from URL:", tokenURL);

  async function apiFunc(toInput) {
    const response = await fetch("/api/auth/reset", {
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
    });

    let body = await response;
    console.log(body);
    setMessage(body.ok ? "Password Reset successful" : "Password Reset failed");
  }

  const handleSubmit = (variables) => {
    if (password == confirmPassword) {
      alert("Passwords match!");
      const toInput = { password: password, passwordResetCode: tokenURL };
      apiFunc(toInput);
      setPassword("");
      setConfirmPassword("");
      setToken(tokenURL);
      console.log(tokenURL);
    } else {
      alert("Not matching!");
    }
  };

  if (firstLoad) {
    // sampleFunc();
    setLoad(false);
  }

  function Copyright() {
    return (
      <Typography variant="body2" color="textSecondary" align="center">
        {"Copyright © newByteX"} {new Date().getFullYear()}
        {"."}
      </Typography>
    );
  }

  function canBeSubmitted() {
    const { password, confirmPassword } = this.state;
    return password.length > 0 && password.length > 0;
  }

  return (
    <Container component="main" maxWidth="xs">
      <CssBaseline />
      <div className={classes.paper}>
        <Avatar className={classes.avatar}>
          <GroupIcon />
        </Avatar>
        <Typography component="h1" variant="h5">
          Reset Password
        </Typography>
        <form className={classes.form} noValidate>
          <Grid container spacing={2}>
            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                id="password"
                value={password}
                label="New Password"
                type="password"
                name="password"
                autoComplete="password"
                onChange={handlePasswordChange}
              />
            </Grid>

            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                id="confirmPassword"
                value={confirmPassword}
                label="Confirm Password"
                type="password"
                name="confirmPassword"
                autoComplete="confirmPassword"
                onChange={handleConfirmPasswordChange}
              />
            </Grid>
          </Grid>
          <Button
            // type="submit"
            fullWidth
            variant="contained"
            color="primary"
            preventefault
            className={classes.submit}
            onClick={handleSubmit}
          >
            Reset Password
          </Button>
        </form>
        <Typography style={{ margin: 7 }} variant="body1">
          Status: {message}
        </Typography>
      </div>
      <Box mt={8}>
        <Copyright />
      </Box>
    </Container>
  );
}
