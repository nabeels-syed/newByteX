import Box from "@material-ui/core/Box";
import Container from "@material-ui/core/Container";
import CssBaseline from "@material-ui/core/CssBaseline";
import { makeStyles } from "@material-ui/core/styles";
import Typography from "@material-ui/core/Typography";
import { Zoom } from "react-slideshow-image";
import React, { useEffect, useState } from "react";
import image1 from "../Assests/Images/1.jpeg";
import image2 from "../Assests/Images/2.jpeg";
import image3 from "../Assests/Images/3.jpeg";
import list from "./userFlyerList";
import imageListLoading from "./imageListLoading";

const images = [image1, image2, image3];

const useStyles = makeStyles((theme) => ({
  paper: {
    marginTop: theme.spacing(7),
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
  },
  avatar: {
    margin: theme.spacing(1),
    backgroundColor: theme.palette.secondary.main,
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

const zoomOutProperties = {
  duration: 5000,
  transitionDuration: 350,
  infinite: true,
  indicators: true,
  scale: 0.4,
  arrows: false,
};

const Slideshow = () => {
  return (
    <div className="slide-container">
      <Zoom {...zoomOutProperties}>
        {images.map((each, index) => (
          <img key={index} style={{ width: "100%" }} src={each} />
        ))}
      </Zoom>
    </div>
  );
};

function Home() {
  const classes = useStyles();
  const [firstLoad, setLoad] = React.useState(true);

  const ImageListLoading = imageListLoading(list);
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
    const apiUrl = `/api/flyer/getAllFlyers`;
    fetch(apiUrl)
      .then((res) => res.json())
      .then((flyers) => {
        setAppState({ loading: false, flyers: flyers });
        console.log(flyers)
      });
  }, [setAppState]);
  return (
    <Container component="main" maxWidth="s">
      <div id="main">
        <div className="name">
          {/* <h1><span>Hello!</span> Welcome to your new site!</h1>
                    <p className='details'>Explore mixes, products and watchoffjwe livestreams!</p> */}
        </div>
        {/* <Container component="main" maxWidth="xs">
                <Slideshow/>
                </Container> */}
        {/* <div className='name' class='child'>
                    <iframe src="https://player.twitch.tv/?channel=trainwreckstv&parent=localhost" frameborder="0" allowfullscreen="true" scrolling="no" height="378" width="760"></iframe>
                </div> */}

        <div id="parent">
          <div class="slideChild">
            <Slideshow />
          </div>
          <div class="slideChild">
            <div className="Flyers">
            <div className="flyer-container">
              <ImageListLoading isLoading={appState.loading} flyers={appState.flyers} />
            </div>
          </div>
          </div>
          <div class="child" id="twitch">
            <iframe
              src="https://player.twitch.tv/?channel=dmixbreedthedj&parent=localhost"
              class="twitchPlayer"
              frameborder="0"
              allowfullscreen="true"
              scrolling="no"
              height="378"
              width="760"
            ></iframe>
            <iframe
              id="chat_embed"
              src="https://www.twitch.tv/embed/dmixbreedthedj/chat?parent=localhost"
              class="twitchChat"
              height="500"
              width="350"
            ></iframe>
            <section id="scroller" class="demo">
              <a href="#twitch">
                <span></span>Twitch
              </a>
            </section>
          </div>
        </div>
        <Box mt={8}>
          <Copyright />
          <br></br>
        </Box>
      </div>
      <CssBaseline />
    </Container>
  );
}
export default Home;

// export default function Home() {
//   const classes = useStyles();
//   const [firstLoad, setLoad] = React.useState(true);

//   function Copyright() {
//     return (
//       <Typography variant="body2" color="textSecondary" align="center">
//         {"newByteX © "} {new Date().getFullYear()}
//       </Typography>
//     );
//   }

//   return (
//     <Container component="main" maxWidth="s">
//       <div id="main">
//         <div className="name">
//           {/* <h1><span>Hello!</span> Welcome to your new site!</h1>
//                     <p className='details'>Explore mixes, products and watchoffjwe livestreams!</p> */}
//         </div>
//         {/* <Container component="main" maxWidth="xs">
//                 <Slideshow/>
//                 </Container> */}
//         {/* <div className='name' class='child'>
//                     <iframe src="https://player.twitch.tv/?channel=trainwreckstv&parent=localhost" frameborder="0" allowfullscreen="true" scrolling="no" height="378" width="760"></iframe>
//                 </div> */}

//         <div id="parent">
//           <div class="slideChild">
//             <Slideshow />
//           </div>
//           <div class="slideChild">
//             <UserFlyerList />
//           </div>
//           <div class="child" id="twitch">
//             <iframe
//               src="https://player.twitch.tv/?channel=dmixbreedthedj&parent=localhost"
//               class="twitchPlayer"
//               frameborder="0"
//               allowfullscreen="true"
//               scrolling="no"
//               height="378"
//               width="760"
//             ></iframe>
//             <iframe
//               id="chat_embed"
//               src="https://www.twitch.tv/embed/dmixbreedthedj/chat?parent=localhost"
//               class="twitchChat"
//               height="500"
//               width="350"
//             ></iframe>
//             <section id="scroller" class="demo">
//               <a href="#twitch">
//                 <span></span>Twitch
//               </a>
//             </section>
//           </div>
//         </div>
//         <Box mt={8}>
//           <Copyright />
//           <br></br>
//         </Box>
//       </div>
//       <CssBaseline />
//     </Container>
//   );
// }

