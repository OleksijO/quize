import React from 'react';
import {Redirect} from "react-router-dom";
import jwt_decode from 'jwt-decode';

import Routes from './navigation/Routes'
import NavItemSets from "../NavItemSets";
import SecuredCrudFetch from "../SecuredCrudFetch";
import Auth from "../Auth";
import Role from "../Role";


const OAUTH_URI = Routes.OAUTH;

function submitForm(form) {
    form.setState({errorMessage: ''});

    console.log("Requesting login info for " + form.state.email);

    let authObj = Auth.getAuthObject(form.state.email, form.state.password);
    SecuredCrudFetch.authPost(OAUTH_URI, authObj)
        .then((res) => {
            if (res && res.access_token) {
                let token = res.access_token;
                let decodedToken = jwt_decode(token);
                console.log(decodedToken);
                let role = decodedToken.authorities[0].substring(5);
                Role.setCurrent(Role.of(role));

                console.log("Found role: [" + Role.getCurrent() + "]");

                Auth.setToken(token);

                form.setState({
                    email: "",
                    password: "",
                    errorMessage: ""
                });

                console.log("\n");
                console.log("Authorized:");
                console.log(decodedToken);
                console.log("\n");
                console.log("SECURITY ROLE [" + role + "] GRANTED");
                console.log("Switching nav bar for " + Role.getCurrent());
                console.log("Redirecting to after-login page...");
                console.log("\n");

                NavItemSets.setByRole(Role.getCurrent());
                form.setState({isRedirected: true});
            }
        })
        .catch((err) => {
            let errorMessage = "! Not Authorized !: " +
                'A pair of specified user and password does not exist (' + err + ')';
            console.log(errorMessage);
            form.setState({
                errorMessage: errorMessage
            });
        });

}


export default class LoginForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            email: "",
            password: "",
            errorMessage: "",
            isRedirected: false
        };
        this.handleSubmit = this.handleSubmit.bind(this);
        this.setValue = this.setValue.bind(this);
    }

    handleSubmit(e) {
        e.preventDefault();
        submitForm(this);
    }

    setValue(field, event) {
        let object = {};
        object[field] = event.target.type === 'checkbox' ? event.target.checked : event.target.value;
        this.setState(object);
    }

    render() {
        if (this.state.isRedirected && Role.getCurrent() !== Role.GUEST) {
            return <Redirect to={Routes.AFTER_LOGIN}/>
        }
        return (
            <div>
                <div className="row centered-form">
                    <div className="col-xs-10 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
                        <div className="panel panel-default">
                            <div className="panel-heading">
                                <h3 className="panel-title">
                                    Please log in
                                </h3>
                            </div>
                            <div className="panel-body">
                                <form onSubmit={this.handleSubmit}>

                                    <div className="form-group">
                                        <input type="email" name="email" id="email" className="form-control input-sm"
                                               placeholder="Email Address"
                                               onChange={this.setValue.bind(this, 'email')}/>
                                    </div>

                                    <div className="form-group">
                                        <input type="password" name="password"
                                               id="password" className="form-control input-sm"
                                               placeholder="Password"
                                               onChange={this.setValue.bind(this, 'password')}/>
                                    </div>

                                    <div className="row">
                                        <input type="submit" value="Log in" className="btn btn-info btn-block"/>
                                    </div>
                                    <div className="row">
                                        <div className="col-xs-10 col-sm-12 col-md-12">
                                            <span style={{color: "red"}}>{this.state.errorMessage}</span>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        );
    }
}
