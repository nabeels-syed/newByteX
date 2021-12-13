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
import trackListLoading from "./trackListLoading";
import AuthService from "../services/auth-service";

const useStyles = makeStyles((theme) => ({
  paper: {
    marginTop: theme.spacing(15),
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
  trackSection: {
    width: "90%",
  },
}));

function Tracks() {
  const classes = useStyles();
  const [firstLoad, setLoad] = React.useState(true);
  const authService = AuthService.getInstance();

  const TrackListLoading = trackListLoading(list);
  const [appState, setAppState] = useState({
    loading: false,
    tracks: null,
  });

  function Copyright() {
    return (
      <Typography variant="body2" color="textSecondary" align="center">
        {"newByteX © "} {new Date().getFullYear()}
      </Typography>
    );
  }

  useEffect(() => {
    setAppState({ loading: true });
    const apiUrl = `/api/track/getAllTracks`;
    fetch(apiUrl)
      .then((res) => res.json())
      .then((tracks) => {
        setAppState({ loading: false, tracks: tracks });
      });
  }, [setAppState]);
  return (
    <Container className={classes.trackSection} component="main">
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
          <h2>InDMix Tracks</h2>
          <TrackListLoading
            isLoading={appState.loading}
            tracks={appState.tracks}
          />
        </div>
        <Box mt={8} class="copyrightFooter">
          <br></br>
          <Copyright />
          <br></br>
        </Box>
      </div>
    </Container>
  );
}
export default Tracks;
