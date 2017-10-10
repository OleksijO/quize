let React = require('react');
let crudFetch = require('crud-fetch');

let URI = "server/registration";

function submitForm(form) {
    form.setState({errorMessage: ''});

    let submitDto = {
        firstName: form.state.firstName,
        lastName: form.state.lastName,
        email: form.state.email,
        password: form.state.password,
        role: form.state.isTutor===true? "TUTOR": "STUDENT"
    };

    if (submitDto.password !== form.state.passwordConfirmation){
        form.setState({errorMessage: 'Password and confirm pwrd fields should be the same'});
        return;
    }

    crudFetch.put(URI, submitDto)
        .then(() => {
            form.setState({
                firstName: "",
                lastName: "",
                email: "",
                password: "",
                isTutor: false
            });
            routie('login')
        })
        .catch((error) => form.setState({errorMessage: error.toString()}));
}

let log = (e) => console.log(e);

module.exports = React.createClass({
    getInitialState: function () {
        return {
            firstName: "",
            lastName: "",
            email: "",
            password: "",
            passwordConfirmation: "",
            isTutor: false,
            errorMessage: ""
        };
    },
    handleSubmit: function (e) {
        e.preventDefault();
        submitForm(this);
    },
    setValue: function (field, event) {
        let object = {};
        object[field] = event.target.type === 'checkbox' ? event.target.checked : event.target.value;
        this.setState(object);
        log(this.state);
        log(event);
    },


    render: function () {
        return (
            <div>
                <div className="row centered-form">
                    <div className="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
                        <div className="panel panel-default">
                            <div className="panel-heading">
                                <h3 className="panel-title">
                                    Please sign up
                                </h3>
                            </div>
                            <div className="panel-body">
                                <form onSubmit={this.handleSubmit}>

                                    <div className="row">
                                        <div className="col-xs-6 col-sm-6 col-md-6">
                                            <div className="form-group">
                                                <input type="text" name="first_name" id="first_name"
                                                       className="form-control input-sm" placeholder="First Name"
                                                       onChange={this.setValue.bind(this, 'firstName')}/>
                                            </div>
                                        </div>
                                        <div className="col-xs-6 col-sm-6 col-md-6">
                                            <div className="form-group">
                                                <input type="text" name="last_name" id="last_name"
                                                       className="form-control input-sm" placeholder="Last Name"
                                                       onChange={this.setValue.bind(this, 'lastName')}/>
                                            </div>
                                        </div>
                                    </div>

                                    <div className="form-group">
                                        <input type="email" name="email" id="email" className="form-control input-sm"
                                               placeholder="Email Address"
                                               onChange={this.setValue.bind(this, 'email')}/>
                                    </div>

                                    <div className="row">
                                        <div className="col-xs-6 col-sm-6 col-md-6">
                                            <div className="form-group">
                                                <input type="password" name="password" id="password"
                                                       className="form-control input-sm" placeholder="Password"
                                                       onChange={this.setValue.bind(this, 'password')}/>
                                            </div>
                                        </div>
                                        <div className="col-xs-6 col-sm-6 col-md-6">
                                            <div className="form-group">
                                                <input type="password" name="password_confirmation"
                                                       id="password_confirmation" className="form-control input-sm"
                                                       placeholder="Confirm Password"
                                                       onChange={this.setValue.bind(this, 'passwordConfirmation')}/>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="row">
                                        <div className="col-xs-6 col-sm-6 col-md-6">
                                            <div className="checkbox checkbox-primary">
                                                <input type="checkbox" name="I am a tutor" id="isTutor"
                                                       onChange={this.setValue.bind(this, 'isTutor')}
                                                       style={{display: false}}/>
                                                <label htmlFor="isTutor">
                                                    &nbsp; I am a tutor
                                                </label>
                                            </div>
                                        </div>
                                        <div className="col-xs-6 col-sm-6 col-md-6">
                                            <input type="submit" value="Register" className="btn btn-info btn-block"/>
                                        </div>
                                    </div>
                                    <div className="row">
                                        <div className="col-xs-12 col-sm-12 col-md-12">
                                            <span style={{color:"red"}}>{this.state.errorMessage}</span>
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
});
