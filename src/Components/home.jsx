import React from "react";
import Avatar from "@material-ui/core/Avatar";
import Button from "@material-ui/core/Button";
import CssBaseline from "@material-ui/core/CssBaseline";
import TextField from "@material-ui/core/TextField";
import { Link, withRouter, NavLink } from "react-router-dom";
import Grid from "@material-ui/core/Grid";
import Box from "@material-ui/core/Box";
import GroupIcon from "@material-ui/icons/Group";
import Typography from "@material-ui/core/Typography";
import { makeStyles } from "@material-ui/core/styles";
import Container from "@material-ui/core/Container";

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

export default function Home() {
    const classes = useStyles();
    const [firstLoad, setLoad] = React.useState(true);

    function Copyright() {
        return (
            <Typography variant="body2" color="textSecondary" align="center">
                {"Copyright © newByteX"} {new Date().getFullYear()}
                {"."}
            </Typography>
        );
    }

    return (
        <Container component="main" maxWidth="s">
            <div id='main'>
                <div className='name'>
                <h1><span>Hello!</span> Welcome to your new site!</h1>
                <p className='details'>Explore mixes, products and watch livestreams!</p>
                </div>
            </div>
            <CssBaseline />
            <Box mt={8}>
                <Copyright />
            </Box>
        </Container>
    );
}