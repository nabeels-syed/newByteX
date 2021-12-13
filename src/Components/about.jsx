import Box from "@material-ui/core/Box";
import Container from "@material-ui/core/Container";
import CssBaseline from "@material-ui/core/CssBaseline";
import { makeStyles } from "@material-ui/core/styles";
import Typography from "@material-ui/core/Typography";
import React from "react";
import {
  FaFacebook,
  FaInstagram,
  FaTwitch,
  FaSoundcloud,
} from "react-icons/fa";
import { HiOutlineMail } from "react-icons/hi";
import { SiTiktok } from "react-icons/si";
import image1 from "../Assests/Images/AboutMe.png";

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

export default function About() {
  const classes = useStyles();
  const [firstLoad, setLoad] = React.useState(true);

  function Copyright() {
    return (
      <Typography variant="body2" color="textSecondary" align="center">
        {"newByteX © "} {new Date().getFullYear()}
      </Typography>
    );
  }

  return (
    <Container component="main" maxWidth="s">
      <div class="divBR"></div>
      <div id="main">
        <div className="name">
          <h1>
            <span>About</span> Me
          </h1>
          <img class="aboutMeImage" src={image1} alt="InDMix"></img>
          <p className="details">
            His introduction to music and DJing came from his father. His dad an
            immigrant from Trinidad worked many family events, weddings and
            social outings with fellow Trinidadians playing soca and calypso.
            Records and turntables in the basement were his practice tools
            throughout the years.
          </p>
          <p className="details">
            He started to delve into other genres of music he was growing up
            with and was popular at the time with the circles he socialized
            within.
          </p>
          <p className="details">
            Hip hop, RandB, House and Top 40 were his playing strength and crowd
            pleasers at events. Meeting and networking with other DJs in the
            Greater Toronto area, gave him exposure and recognition for his
            talent.
          </p>
          <p className="details">
            He was one of their first DJs to move from records to computer to
            mix. Educating himself on new software and equipment to bring the
            art of music and sound to the next level.
          </p>
          <p className="details">
            Took a break from DJ public events to focus on his career and
            personal life, had a family, but always still dabbled with music as
            a past time, hobby
          </p>
          <p className="details">
            A trip to Trinidad for Carnival sparked the love for music again,
            and the creative flow came back. He was energized with the new
            sounds coming out of the soca world. Started to mix around old and
            new to create a different vibe.
          </p>
          <p className="details">
            His fans now say he is a versatile DJ, he takes on any genre
            challenge and teaching himself the latest songs and trends. Putting
            in the work to elevate his skills and up to date with the ever
            evolving world of sound.
          </p>
          <br></br>
          <h2>
            <span id="contactH2">Contact Me</span>
          </h2>
          <br></br>
          <a href="https://www.instagram.com/dmixbreedthedj/" target="_blank">
            <FaInstagram class="socialMedia"></FaInstagram>
          </a>
          <a href="http://www.facebook.com/dmixbreedthedj" target="_blank">
            <FaFacebook class="socialMedia"></FaFacebook>
          </a>
          <a
            href="https://www.tiktok.com/@dmixbreedthedj?lang=en"
            target="_blank"
          >
            <SiTiktok class="socialMedia"></SiTiktok>
          </a>
          <a href="https://www.twitch.tv/dmixbreedthedj" target="_blank">
            <FaTwitch class="socialMedia"></FaTwitch>
          </a>
          <a href="http://www.soundcloud.com/" target="_blank">
            <FaSoundcloud class="socialMedia"></FaSoundcloud>
          </a>
          <a href="mailto:dmixbreed@gmail.com" target="_blank">
            <HiOutlineMail class="socialMedia"></HiOutlineMail>
          </a>
          <div class="divBR2"></div>
        </div>
      </div>
      <CssBaseline />
      <Box mt={8} class="copyrightFooter">
        <Copyright />
        <br></br>
      </Box>
    </Container>
  );
}
