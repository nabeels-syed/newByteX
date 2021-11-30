import Container from "@material-ui/core/Container";
import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import { Grid } from "@material-ui/core";

const useStyles = makeStyles((theme) => ({
  trackBox: {
    display: "flex",
    flexWrap: "wrap",
    width: "33%",
    height: "15em",
    margin: "0 0 2rem 0",
    alignContent: "flex-start",
  },
  trackDetails: {
    width: "100%",
    margin: 0,
    padding: 0,
  },
  trackTitle: {
    fontWeight: "bold",
    fontSize: "2em",
  },
  trackSubtitles: {
    fontStyle: "italic",
  },
  audioElement: {
    alignSelf: "flex-end",
  },
}));

const UserTrackList = (props) => {
  const classes = useStyles();
  const { tracks } = props;

  const setTrackSource = (id) => {
    return `http://localhost:8080/api/track/streamTrack/${id}`;
  };

  if (!tracks || tracks.length === 0)
    return <p>No tracks at the moment, check back later!</p>;
  return (
    <Grid class="rootDiv">
      {tracks.map((track) => {
        return (
          <Container
            maxWidth={false}
            component="div"
            className={classes.trackBox}
          >
            <p className={`${classes.trackDetails} ${classes.trackTitle}`}>
              {" "}
              {track.title}
            </p>
            <p className={`${classes.trackDetails} ${classes.trackSubtitles}`}>
              Artist: {track.artist}
            </p>
            <p className={`${classes.trackDetails} ${classes.trackSubtitles}`}>
              Release Date: {track.releaseDate}
            </p>
            <div className={classes.audioElement}>
              <audio type="audio/mpeg" controls volume="true" download>
                <source
                  src={setTrackSource(track.id)}
                  type="audio/mpeg"
                ></source>
                Your browser does not support the audio element.
              </audio>
            </div>
            {/* <a href={setTrackSource(track.id)} download={track.fileName}>Download</a> */}
          </Container>
        );
      })}
    </Grid>
  );
};

export default UserTrackList;
