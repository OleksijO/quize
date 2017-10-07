let React = require('react');

let CheckBoxAnswer = require('./CheckBoxAnswer.jsx');

module.exports = React.createClass({
    render: function () {
        return (
            <div className="questioncontainer">
                <div className="questionstem">
                    <div className="questiontext">
                        <br/>
                        {this.props.question.text}
                        <br/>
                    </div>
                </div>
                <br/>
                <ul>
                    {this.props.question.answers.map(function (answer) {
                        return <CheckBoxAnswer key={answer} answer={answer}/>
                    })}
                </ul>
            </div>
        );
    }
});
