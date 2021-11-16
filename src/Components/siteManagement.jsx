import Container from "@material-ui/core/Container";
import { makeStyles } from "@material-ui/core/styles";
import Typography from "@material-ui/core/Typography";
import React from "react";
import "react-datepicker/dist/react-datepicker.css";
import Collapsible from "react-collapsible";
import TrackManagementComp from "./trackManagementComp";
import FlyerManagementComp from "./flyerManagementComp";

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
  collapsibleConatainers: {
    backgroundColor: "gold",
    width: "100%",
  },
  openedCollapsibleConatainers: {
    backgroundColor: "grey",
    width: "100%",
  },
}));

function SiteManagement() {
  const classes = useStyles();
  
  function Copyright() {
    return (
      <Typography variant="body2" color="textSecondary" align="center">
        {"newByteX © "} {new Date().getFullYear()}
      </Typography>
    );
  }

  return (
    <Container>
      <Collapsible
        className={classes.collapsibleConatainers}
        openedClassName={classes.openedCollapsibleConatainers}
        trigger="Manage Music Tracks"
      >
      </Collapsible>

      <TrackManagementComp></TrackManagementComp>
      <FlyerManagementComp></FlyerManagementComp>
      <Copyright></Copyright>
      <br></br>
    </Container>
  );
}

export default SiteManagement;
