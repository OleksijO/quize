let React = require('react');

module.exports = React.createClass({
    render: function () {
        return <h5> My name is {this.props.name} and I love {this.props.action}! </h5>;
    }
});