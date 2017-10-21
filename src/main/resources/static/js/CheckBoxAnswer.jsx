let React = require('react');

module.exports = React.createClass({
    render: function () {
        return (
            <ul><input
                onClick=""
                type="checkbox" className="check_box"
                id={"checkbox " + this.props.answer}/>
                <label
                    id={"label for " + this.props.answer}
                    htmlFor={"checkbox " + this.props.answer}>{this.props.answer}</label>
            </ul>
        );
    }
});