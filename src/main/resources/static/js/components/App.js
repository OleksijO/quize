import React from 'react';

import NavBar from './navigation/NavBar';
import Role from '../Role';

export default class App extends React.Component {
    render() {
        return (
            <div>
                <div className="row">
                    <div className="col-12 text-right">
                        {"[ " + Role.getCurrent() + " ]"}
                    </div>
                </div>
                <div className="row justify-content-center">
                    <div className="col-10">
                        <NavBar getNavItems={this.getNavItems} initPath={this.props.path}/>
                    </div>
                </div>
                <div className="row justify-content-center">
                    <div className="col-10">
                        {this.props.children}
                    </div>
                </div>
            </div>
        );
    }
}

