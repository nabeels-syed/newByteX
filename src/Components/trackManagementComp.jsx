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
import list from "./adminTrackList";
import trackListLoading from "./trackListLoading";
import Collapsible from "react-collapsible";

const useStyles = makeStyles((theme) => ({
  paper: {
    marginTop: theme.spacing(20),
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

function TrackManagementComp() {
  const classes = useStyles();

  const [title, setTrackName] = React.useState("");
  const [artist, setTrackArtist] = React.useState("");
  const [releaseDate, setDate] = useState(new Date());
  const [file, setTrackFile] = React.useState();

  const handleTrackNameChange = (event) => {
    console.log("TRACK NAME CHANGE");
    setTrackName(event.target.value);
  };
  const handleTrackArtistChange = (event) => setTrackArtist(event.target.value);
  const handleCalendarClose = () =>
    console.log(`Date is ${releaseDate} -- Calendar closed`);
  const handleCalendarOpen = () => console.log("Calendar opened");
  const handlesetTrackFileChange = (event) => {
    console.log(event);
    console.log("CHANGING FILE --------------------------");
    setTrackFile(event.target.files[0]);
  };

  const [message, setMessage] = React.useState("");

  const [openFileSelector, { filesContent }] = useFilePicker({
    multiple: false,
    accept: [".mp3", ".wav"],
  });

  const ListLoading = trackListLoading(list);
  const [appState, setAppState] = useState({
    loading: false,
    tracks: null,
  });

  async function apiFunc(toInput) {
    const formData = new FormData();

    formData.append("title", toInput.title);
    formData.append("artist", toInput.artist);
    formData.append("releaseDate", toInput.releaseDate);
    formData.append("file", toInput.file);

    const response = await fetch("/api/track/createTrack", {
      method: "POST",
      mode: "cors",
      cache: "no-cache",
      credentials: "same-origin",
      redirect: "follow",
      referrerPolicy: "no-referrer",
      body: formData,
    });
    let body = await response;
    console.log(body);
    setMessage(body.ok ? "Track added successfully" : "Track failed");
    if (
      !alert(
        body.ok
          ? "Track added successfully!"
          : "Error! Track failed to upload. Please try again"
      )
    ) {
      // window.location.reload();
    }
  }

  const handleSubmit = () => {
    const toInput = { title, artist, releaseDate, file };

    apiFunc(toInput);
    setTrackName("");
    setTrackArtist("");
    setDate("");
    setTrackFile();

    console.log(toInput);
    console.log(releaseDate);
  };

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
    <Container component="main" maxWidth={false}>
      <CssBaseline />
      <div className={classes.paper}>
        <Avatar className={classes.avatar}>
          <LibraryMusicIcon />
        </Avatar>
        <Typography component="h1" variant="h5">
          Add Tracks
        </Typography>
        <form className={classes.form} noValidate>
          <Grid container spacing={2}>
            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                id="title"
                value={title}
                label="Track Name"
                name="title"
                autoComplete="title"
                helperText="cannot be blank"
                onChange={handleTrackNameChange}
              />
            </Grid>

            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                id="artist"
                value={artist}
                label="Track Artist"
                type="artist"
                name="artist"
                autoComplete="artist"
                helperText="cannot be blank"
                onChange={handleTrackArtistChange}
              />
            </Grid>

            <Grid item xs={12}>
              <div id="datePicker">
                <DatePicker
                  dateFormat="yyyy-MM-dd"
                  // dateFormatCalendar='yyyy-MM-dd'
                  selected={releaseDate}
                  onChange={(releaseDate) => setDate(releaseDate)}
                  onCalendarClose={handleCalendarClose}
                  onCalendarOpen={handleCalendarOpen}
                  id="datePicker"
                />
              </div>
            </Grid>

            <Grid item xs={12}>
              <div id="filePicker">
                <input
                  type="file"
                  name="file"
                  onChange={handlesetTrackFileChange}
                ></input>
                <Button
                  size="lg"
                  variant="contained"
                  color="light"
                  required
                  className={classes.submit}
                  onClick={openFileSelector}
                >
                  Select File
                </Button>
                <div>
                  {filesContent.map((file, index) =>
                    console.log("Test")(
                      <div>
                        <p>
                          File Selected: <b>{file.name}</b>
                        </p>
                        {/* <p>{file}</p> */}
                        {/* <div key={index}>{file.content}</div> */}
                        <br />
                      </div>
                    )
                  )}
                </div>
              </div>
            </Grid>
          </Grid>

          <Button
            fullWidth
            variant="contained"
            color="info"
            preventefault
            className={classes.submit}
            onClick={handleSubmit}
          >
            Add Track
          </Button>
        </form>
        <Typography style={{ margin: 7 }} variant="body1">
          Status: {message}
        </Typography>
      </div>
      <div className="Tracks">
        <div className="track-container">
          <ListLoading isLoading={appState.loading} tracks={appState.tracks} />
        </div>
      </div>
    </Container>
  );
}

export default TrackManagementComp;
