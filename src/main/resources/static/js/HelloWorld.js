import React from 'react';

export default class HelloWorld extends React.Component {
    render() {
        return <h5> My name is {this.props.name} and I love {this.props.action}! </h5>;
    }
}