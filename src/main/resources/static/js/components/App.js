import React from 'react';

import NavItemSets from '../NavItemSets'

import NavBar from './navigation/NavBar';
import Home from "./Home";
import CheckBoxQuestion from "./CheckBoxQuestion";
import RegistrationForm from "./RegisterForm";
import LoginForm from "./LoginForm";
import Routes from './navigation/Routes'

const question = {
    text: "Which of the following countries border Italy?",
    answers: [
        "Switzerland",
        "France",
        "The Netherlands",
        "Germany",
        "Austria",
        "Slovenia"
    ]
};

export default class App extends React.Component{
    constructor(props){
        super(props);

        this.getNavItems = this.getNavItems.bind(this);
        this.setNavItems = this.setNavItems.bind(this);
        this.setContent = this.setContent.bind(this);

        let home = <Home />;
        let checkBox = <CheckBoxQuestion question={question}/>;
        let about = <div>ABOUT PAGE CONTENT</div>;
        let registration = <RegistrationForm />;
        let login = <LoginForm setNavItems = {this.setNavItems}/>;

        // setting initial content
        this.setContent(home);

        //configuring routing
        routie(Routes.HOME, () => this.setContent(home));
        routie(Routes.SUBJECTS, () => this.setContent(checkBox));
        routie(Routes.ABOUT, () => this.setContent(about));
        routie(Routes.REGISTER, () => this.setContent(registration));
        routie(Routes.LOGIN, () => this.setContent(login));

        this.state = {
            navItems: NavItemSets.getDefault(),
            content: home
        };
    }

    setContent(content){
        this.setState({content: content});
    }

    getNavItems(){
        return this.state.navItems;
    }

    setNavItems(navItems){
        this.state.navItems = navItems;
    }

    render() {
        return (
            <div>
                <div className="row justify-content-center">
                    <div className="col-10">
                        <div id="navBar">
                            <NavBar getNavItems = {this.getNavItems} />
                        </div>
                    </div>
                </div>
                <div className="row justify-content-center">
                    <div className="col-10">
                        <div id="content">
                            {this.state.content}
                        </div>
                    </div>
                </div>
            </div>
        );
    }

}

