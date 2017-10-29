import React from 'react';

import RegistrationForm from './RegisterForm.js';
import NavBar from './NavBar.js';
import HelloWorld from './HelloWorld.js';
import CheckBoxQuestion from './CheckBoxQuestion.js';


const REGISTER = 'register';
const HOME = '';
const SUBJECTS = 'subjects';
const ABOUT = 'about';

let navItems = [
    {
        title: 'Hello world',
        href: HOME
    },
    {
        title: 'Check Box Question',
        href: SUBJECTS
    },
    {
        title: 'About',
        href: ABOUT
    },
    {
        title: 'Registration',
        href: REGISTER
    },
];

let question = {
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

// Navigation initialization

ReactDOM.render(
    <NavBar items={navItems}/>,
    document.getElementById("navBar")
);

ReactDOM.render(
    <div>
        <HelloWorld name="Nastya" action="eating"/>
        <HelloWorld name="Katya" action="football"/>
        <HelloWorld name="Vasya" action="walking"/>
        <HelloWorld name="Ivan" action="swimming"/>
    </div>,
    document.getElementById("content")
);

// Routing configuration

routie(HOME, function () {
    ReactDOM.render(
        <div>
            <HelloWorld name="Nastya" action="eating"/>
            <HelloWorld name="Katya" action="football"/>
            <HelloWorld name="Vasya" action="walking"/>
            <HelloWorld name="Ivan" action="swimming"/>
        </div>,
        document.getElementById("content")
    );
});

routie(SUBJECTS, function () {
    ReactDOM.render(
        <CheckBoxQuestion question={question}/>,
        document.getElementById("content")
    );
});

routie(ABOUT, function () {
    ReactDOM.render(
        <div>ABOUT PAGE CONTENT</div>,
        document.getElementById("content")
    );
});

routie(REGISTER, function () {
    ReactDOM.render(
        <RegistrationForm />,
        document.getElementById("content")
    );
});


