import ReactDOM from "react-dom";
import Routes from "./components/navigation/Routes"
import App from "./components/App";

export default class RoutesConfig{}

RoutesConfig.apply = function(){
    routie(Routes.HOME, renderApp(Routes.HOME));
    routie(Routes.SUBJECTS, renderApp(Routes.SUBJECTS));
    routie(Routes.ABOUT, renderApp(Routes.ABOUT));
    routie(Routes.REGISTER, renderApp(Routes.REGISTER));
    routie(Routes.LOGIN, renderApp(Routes.LOGIN));
    routie(Routes.HOME);

};

function renderApp(path){
    return function() {ReactDOM.render(<App path={path} />, document.getElementById("app"))}
}