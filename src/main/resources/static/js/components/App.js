import React from 'react';

import NavBar from './navigation/NavBar';

export default class App extends React.Component {
    render() {
        return (
            <div>
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

