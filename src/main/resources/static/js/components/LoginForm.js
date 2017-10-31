import React from 'react';
import crudFetch from 'crud-fetch';
import Routes from './navigation/Routes'
import NavItemSets from "../NavItemSets";

const URI = Routes.LOGIN;
const log = (e) => console.log(e);

function submitForm(form) {
    form.setState({errorMessage: ''});

    let submitDto = {
        email: form.state.email,
        password: form.state.password,
    };

    crudFetch.post(URI, submitDto)
        .then((user) => {

            form.props.setNavItems(NavItemSets.getByRole(user.role));

            form.setState({
                email: "",
                password: "",
                errorMessage: ""
            });
            routie(Routes.AFTER_LOGIN)
        })
        .catch((error) => form.setState({errorMessage: 'A pair of specified user and password does not exist'}));
}

export default class LoginForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            email: "",
            password: "",
            errorMessage: ""
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
        log(this.state);
        log(event);
    }

    render() {
        return (
            <div>
                <div className="row centered-form">
                    <div className="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
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
                                        <div className="col-xs-12 col-sm-12 col-md-12">
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
