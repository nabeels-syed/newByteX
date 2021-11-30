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
      <div id="main">
        <div className="name">
          <h1>
            <span>About</span> Me
          </h1>
          <img class="aboutMeImage" src={image1} alt="InDMix"></img>
          <p className="details">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
            eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim
            ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut
            aliquip ex ea commodo consequat. Duis aute irure dolor in
            reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
            pariatur. Excepteur sint occaecat cupidatat non proident, sunt in
            culpa qui officia deserunt mollit anim id est laborum.
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
