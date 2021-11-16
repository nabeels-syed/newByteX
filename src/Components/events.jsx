import Avatar from "@material-ui/core/Avatar";
import Box from "@material-ui/core/Box";
import Button from "@material-ui/core/Button";
import Container from "@material-ui/core/Container";
import CssBaseline from "@material-ui/core/CssBaseline";
import Grid from "@material-ui/core/Grid";
import { makeStyles } from "@material-ui/core/styles";
import TextField from "@material-ui/core/TextField";
import Typography from "@material-ui/core/Typography";
import LibraryMusicIcon from "@mui/icons-material/LibraryMusic";
import React, { useEffect, useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { useFilePicker } from "use-file-picker";
import list from "./userTrackList";
import eventListLoading from "./eventListLoading";
import eventBannerLoading from "./eventBannerLoading";

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

function Events() {
  const classes = useStyles();
  const [firstLoad, setLoad] = React.useState(true);

  const EventBannerLoading = eventBannerLoading(list);
  const [appState, setAppState] = useState({
    loading: false,
    tracks: null,
  });

  function Copyright() {
    return (
      <Typography variant="body2" color="textSecondary" align="center">
        {"newByteX Â© "} {new Date().getFullYear()}
      </Typography>
    );
  }

  useEffect(() => {
    setAppState({ loading: true });
    const apiUrl = `/api/flyer/getAllFlyers`;
    fetch(apiUrl)
      .then((res) => res.json())
      .then((tracks) => {
        setAppState({ loading: false, events: events });
      });
  }, [setAppState]);
  return (
    <Container component="main" maxWidth="xs">
      <CssBaseline />
      <div className={classes.paper}>
        <br></br>
        <br></br>
        <br></br>
        <Avatar className={classes.avatar}>
          <LibraryMusicIcon />
        </Avatar>
      </div>
      <div className="Tracks">
        <div className="track-container">
          <EventBannerLoading isLoading={appState.loading} events={appState.events} />
        </div>
        <Box mt={8} class="copyrightFooter">
          <Copyright />
          <br></br>
        </Box>
      </div>
    </Container>
  );
}
export default Tracks;