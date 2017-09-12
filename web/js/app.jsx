let ACTIVE = " active";
let navItems = [
    {
        title: 'Hello world',
        href: ''
    },
    {
        title: 'Check Box Question',
        href: 'subjects'
    },
    {
        title: 'About',
        href: 'about'
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


let NavBar = React.createClass({
    getInitialState: function () {
        return {
            activeItem: navItems[0]
        }
    },
    setActiveItem: function (item) {
        this.setState({
            activeItem: item
        })
    },
    isActiveItem: function (item) {
        return this.state.activeItem === item;

    },
    render: function () {
        let parent = this;
        return (
            <div>
                <ul className="nav_bar">
                    {this.props.items.map(function (item) {
                        return (
                            <NavItem item={item}
                                     isActive={parent.isActiveItem}
                                     setActive={parent.setActiveItem}
                                     key={item.title + item.href}
                            />);
                    })}
                </ul>
            </div>
        )
    }
});

let NavItem = React.createClass({

    render: function () {
        let activeStyle = this.props.isActive(this.props.item) ? ACTIVE : '';
        return (
            <li className={"nav_item " + activeStyle} onClick={this.setActive}>
                <a className={"nav_item_href " + activeStyle} href={"#"+this.props.item.href}>{this.props.item.title}</a>
            </li>
        )
    },
    setActive: function () {
        this.props.setActive(this.props.item);
        routie(this.props.item.href);
    }
});


let CheckBoxQuestion = React.createClass({
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

let CheckBoxAnswer = React.createClass({
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

let HelloWorld = React.createClass({
    render: function () {
        return <h5> My name is {this.props.name} and I love {this.props.action}! </h5>;
    }
});

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


routie(navItems[0].href, function () {
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

routie(navItems[1].href, function () {
    ReactDOM.render(
        <CheckBoxQuestion question={question}/>,
        document.getElementById("content")
    );
});

routie(navItems[2].href, function () {
    ReactDOM.render(
        <div>ABOUT PAGE CONTENT</div>,
        document.getElementById("content")
    );
});


