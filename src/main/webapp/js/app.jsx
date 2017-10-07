let React = require('react');

let RegistrationForm = require('./RegisterForm.jsx');
let NavBar = require('./NavBar.jsx');
let HelloWorld = require('./HelloWorld.jsx');
let CheckBoxQuestion = require('./CheckBoxQuestion.jsx');

let REGISTER = 'register';
let HOME = '';
let SUBJECTS = 'subjects';
let ABOUT = 'about';

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


