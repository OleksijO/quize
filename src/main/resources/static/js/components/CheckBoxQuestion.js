import React from 'react';

import CheckBoxAnswer from './CheckBoxAnswer.js';

export default class CheckBoxQuestion extends React.Component {
    render() {
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
}
