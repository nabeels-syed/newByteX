import Container from "@material-ui/core/Container";
import React from "react";
import { Zoom } from "react-slideshow-image";

const zoomOutProperties = {
  duration: 5000,
  transitionDuration: 350,
  infinite: true,
  indicators: true,
  scale: 0.4,
  arrows: false,
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
    return <p>No upcoming events at the moment, check back later!</p>;
  return (
    <Container>
      <h2
        style={{ margin: "0 auto", textAlign: "center", paddingBottom: "2em" }}
      >
        InDMix Upcoming Events
      </h2>
      <Slideshow />
    </Container>
  );
};

export default UserFlyerList;
