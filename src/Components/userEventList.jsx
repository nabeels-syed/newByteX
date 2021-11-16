import Container from "@material-ui/core/Container";
import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import { Zoom } from "react-slideshow-image";
import image1 from "../Assests/Images/1.jpeg";
import image2 from "../Assests/Images/2.jpeg";
import image3 from "../Assests/Images/3.jpeg";
const images = [image1, image2, image3];

const zoomOutProperties = {
    duration: 5000,
    transitionDuration: 350,
    infinite: true,
    indicators: true,
    scale: 0.4,
    arrows: false,
  };


const useStyles = makeStyles((theme) => ({
  rootDiv: {
    display: 'flex',
    flexWrap: 'wrap',
    justifyContent: 'space-around',
    overflow: 'hidden',
    backgroundColor: theme.palette.background.paper,
  },
  trackBox: {
    width: '100%',
    height: 150,
  },
  indivTrack: {

  },
}));

const EventBanner = () => {
    return (
      <div className="slide-container">
        <Zoom {...zoomOutProperties}>
          {images.map((each, index) => (
            <img key={index} style={{ width: "100%", height: "30%" }} src={each} />
          ))}
        </Zoom>
      </div>
    );
  };

const UserEventList = (props) => {
  const classes = useStyles();
  const { events } = props;

  const setEventSource = (id) => {
    return `http://localhost:8080/api/flyer/streamFlyer/${id}`
  }

  if (!events || events.length === 0) return <p>No events at the moment, check back later!</p>;
  return (
    <Container className={classes.rootDiv}>
      <h2 >InDMix Events</h2>
      {events.map((event) => {
        return (
          <Container component="div" className={classes.trackBox}>
            {/* <div key={track.id} className={classes.indivTrack}>
              <span className="track-id">{track.id} --</span>
              <span className="track-title">{track.title} --</span>
              <span className="track-artist"> {track.artist} --</span>
              <span>
                <audio type="audio/mpeg" controls volume="true">
                  <source src={setEventSource(track.id)} type="audio/mpeg"></source>
                  Your browser does not support the audio element.
                </audio>
              </span>
            </div> */}
            <EventBanner />
          </Container>
        );
      })}
    </Container>
  );
};

export default UserEventList;
