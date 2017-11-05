import React from 'react';

import CheckBoxAnswer from './CheckBoxAnswer.js';

const question = {
    text: "Which of the following countries border Italy?",
    answers: [
        "Switzerland",
        "France",
        "The Netherlands",
        "Germany",
        "Austria",
        "Slovenia"
    ]
};

export default class CheckBoxQuestion extends React.Component {
    render() {
        return (
            <div className="questioncontainer">
                <div className="questionstem">
                    <div className="questiontext">
                        <br/>
                        {question.text}
                        <br/>
                    </div>
                </div>
                <br/>
                <ul>
                    {question.answers.map(function (answer) {
                        return <CheckBoxAnswer key={answer} answer={answer}/>
                    })}
                </ul>
            </div>
        );
    }
}
