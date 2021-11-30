import Container from "@material-ui/core/Container";
import React from "react";
import { Zoom } from "react-slideshow-image";

const zoomOutProperties = {
  duration: 5000,
  transitionDuration: 350,
  infinite: true,
  indicators: true,
  scale: 0.4,
  arrows: true,
  canSwipe: true,
};

const UserFlyerList = (props) => {
  const { flyers } = props;
  console.log(flyers);

  const Slideshow = () => {
    return (
      <div className="slide-container">
        <Zoom {...zoomOutProperties}>
          {flyers.map((flyer) => (
            <img
              key={flyer.id}
              style={{ width: "45%", margin: "0 auto", paddingTop: "0" }}
              src={setFlyerSource(flyer.id)}
              alt={flyer.eventName}
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
    return (
      <p>
        <br></br>
        <h2
          class="upcomingEventsHeading"
          style={{
            margin: "0 auto",
            textAlign: "center",
            paddingBottom: "2em",
            fontSize: "2rem",
          }}
        >
          InDMix Upcoming Events
        </h2>
        <b style={{ fontSize: "1rem" }}>
          No upcoming events at the moment, check back later!
        </b>
      </p>
    );
  return (
    <Container>
      <br></br>
      <h2
        class="upcomingEventsHeading"
        style={{
          margin: "0 auto",
          textAlign: "center",
          paddingBottom: "2em",
          fontSize: "2rem",
        }}
      >
        InDMix Upcoming Events
      </h2>
      <Slideshow />
    </Container>
  );
};

export default UserFlyerList;
