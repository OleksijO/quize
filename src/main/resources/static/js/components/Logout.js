import React from "react";
import {Redirect} from "react-router-dom";

import NavItemSets from "../NavItemSets";
import Auth from "../Auth";
import Routes from "./navigation/Routes";


const Logout = () => {
    NavItemSets.setActiveToDefault();
    console.log("Logging out....");
    console.log("Redirecting to "+ Routes.AFTER_LOGOUT);
    Auth.setToken("");
    console.log("Token cleared.");
    return <Redirect to={Routes.AFTER_LOGOUT} push/>
};

export default Logout;