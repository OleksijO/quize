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

export default class App extends React.Component {
    constructor(props) {
        super(props);

        this.getNavItems = this.getNavItems.bind(this);
        this.setNavItems = this.setNavItems.bind(this);
        this.insertContent = this.insertContent.bind(this);

        this.state = {
            navItems: NavItemSets.getDefault(),
        };
    }

    getNavItems() {
        return this.state.navItems;
    }

    setNavItems(navItems) {
        this.setState({navItems:  navItems});
    }

    render() {
        return (
            <div>
                <div className="row justify-content-center">
                    <div className="col-10">
                        <div id="navBar">
                            <NavBar getNavItems={this.getNavItems} initPath={this.props.path}/>
                        </div>
                    </div>
                </div>
                <div className="row justify-content-center">
                    <div className="col-10">
                        <div id="content">
                            {this.insertContent()}
                        </div>
                    </div>
                </div>
            </div>
        );
    }

    insertContent() {
        switch (this.props.path) {
            case Routes.SUBJECTS:
                return <CheckBoxQuestion question={question}/>;
                break;
            case Routes.ABOUT:
                return <div>ABOUT PAGE CONTENT</div>;
                break;
            case Routes.REGISTER:
                return <RegistrationForm/>;
                break;
            case Routes.LOGIN:
                return <LoginForm setNavItems={this.setNavItems}/>;
                break;
            default:
                return <Home />
        }
    }
}

