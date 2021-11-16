import Container from "@material-ui/core/Container";
import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import { Zoom } from "react-slideshow-image";

const useStyles = makeStyles((theme) => ({
  rootDiv: {
    display: "flex",
    flexWrap: "wrap",
    justifyContent: "space-around",
    overflow: "hidden",
    backgroundColor: theme.palette.background.paper,
  },
  trackBox: {
    width: "100%",
    height: 150,
  },
  indivTrack: {},
}));

const zoomOutProperties = {
  duration: 5000,
  transitionDuration: 350,
  infinite: true,
  indicators: true,
  scale: 0.4,
  arrows: false,
};

const UserFlyerList = (props) => {
  const classes = useStyles();
  const { flyers } = props;

  const Slideshow = () => {
    return (
      <div className="slide-container">
        <Zoom {...zoomOutProperties}>
          {flyers.map((flyer) => (
            <img
              key={flyer.id}
              style={{ width: "100%" }}
              src={setFlyerSource(flyer.id)}
            />
          ))}
        </Zoom>
      </div>
    );
  };

  const setFlyerSource = (id) => {
    return `http://localhost:8080/api/flyer/streamFlyer/${id}`;
  };

  if (!flyers || flyers.length === 0)
    return <p>No upcoming events at the moment, check back later!</p>;
  return (
    <Container className={classes.rootDiv}>
      <h2>InDMix Upcoming Events</h2>
      <Slideshow />
    </Container>
  );
};

export default UserFlyerList;
