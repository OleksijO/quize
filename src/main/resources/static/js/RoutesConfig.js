import ReactDOM from "react-dom";
import {BrowserRouter, Route} from 'react-router-dom'

import Routes from "./components/navigation/Routes"
import App from "./components/App";
import CheckBoxQuestion from "./components/CheckBoxQuestion";
import Home from "./components/Home";
import RegisterForm from "./components/RegisterForm";
import LoginForm from "./components/LoginForm";
import About from "./components/About";

export default class RoutesConfig {
}

RoutesConfig.apply = function () {

    ReactDOM.render((
        <BrowserRouter>
            <App>
                <Route path={Routes.HOME} component={Home}/>
                <Route path={Routes.SUBJECTS} component={CheckBoxQuestion}/>
                <Route path={Routes.ABOUT} component={About}/>
                <Route path={Routes.REGISTER} component={RegisterForm}/>
                <Route path={Routes.LOGIN} component={LoginForm}/>
            </App>
        </BrowserRouter>

    ), document.getElementById('app'))

};