import Button from "@material-ui/core/Button";
import Container from "@material-ui/core/Container";
import { ArrowRightSharp } from "@mui/icons-material";
import React from "react";
import { useRef } from "react";

const AdminTrackList = (props) => {
  const { tracks } = props;
  const audioUrl = useRef();
  const [id, setTrackId] = React.useState("");
  //const [acquiredId, setAcquiredId] = React.useState("");

  async function apiFunc(toInput) {
    const response = await fetch("/api/track/deleteTrack", {
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
  }
    
  const handleDelete = (id) => {
    const toInput = { id };
    apiFunc(toInput);
  };

  const setTrackSource = (id) => {
    return `http://localhost:8080/api/track/streamTrack/${id}`
  }

  if (!tracks || tracks.length === 0) return <p>No tracks uploaded</p>;
  return (
    <ul>
      <h2 className="list-head">My music tracks</h2>
      {tracks.map((track) => {
        return (
          <Container component="list">
            <div key={track.id} className="trackContainer">
              <span className="track-id">{track.id} --</span>
              <span className="track-title">{track.title} --</span>
              <span className="track-artist"> {track.artist} --</span>
              <span>
                <audio type="audio/mpeg" controls volume="true">
                  <source src={setTrackSource(track.id)} type="audio/mpeg"></source>
                  Your browser does not support the audio element.
                </audio>
              </span>

              <Button
                // type="submit"
                fullWidth
                variant="contained"
                color="primary"
                preventefault
                onClick={() => {
                  const confirmBox = window.confirm(
                    "Deleting track, please confirm."
                  );
                  if (confirmBox === true) {
                    console.log(track.id);
                    handleDelete(track.id);
                    // window.location.reload(true);
                  }
                }}
              >
                Delete track
              </Button>
            </div>
          </Container>
        );
      })}
    </ul>
  );
};

export default AdminTrackList;