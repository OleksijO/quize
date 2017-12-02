import React from "react";
import {Redirect} from "react-router-dom";

import NavItemSets from "../NavItemSets";
import Auth from "../Auth";
import Routes from "./navigation/Routes";


const Logout = () => {

    console.log("Switching nav bar....");

    NavItemSets.setActiveToDefault();

    console.log("Logging out....");

    Auth.setToken("");

    console.log("Token cleared.");
    console.log("Redirecting to "+ Routes.AFTER_LOGOUT);

    return <Redirect to={Routes.AFTER_LOGOUT} push/>
};

export default Logout;