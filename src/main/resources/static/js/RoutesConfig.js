import ReactDOM from "react-dom";
import {HashRouter,Route} from 'react-router-dom'

import Routes from "./components/navigation/Routes"
import App from "./components/App";
import CheckBoxQuestion from "./components/CheckBoxQuestion";
import Home from "./components/Home";
import RegisterForm from "./components/RegisterForm";
import LoginForm from "./components/LoginForm";
import About from "./components/About";
import Logout from "./components/Logout";

export default class RoutesConfig {
}

RoutesConfig.apply = function () {

    ReactDOM.render((
        // <BrowserRouter>
        <HashRouter>
            <App>
                <Route exact path={createPath(Routes.HOME)} component={Home}/>
                <Route path={createPath(Routes.SUBJECTS)} component={CheckBoxQuestion}/>
                <Route path={createPath(Routes.ABOUT)} component={About}/>
                <Route path={createPath(Routes.REGISTER)} component={RegisterForm}/>
                <Route path={createPath(Routes.LOGIN)} component={LoginForm}/>
                <Route path={createPath(Routes.LOGOUT)} component={Logout}/>

            </App>

        </HashRouter>
        // </BrowserRouter>

    ), document.getElementById('app'))

};

let createPath = function(path){
    return "/"+path;
};