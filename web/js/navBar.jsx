let ACTIVE = " active";

let items = [
    {
        title: 'Home',
        href: '#'
    },
    {
        title: 'Subjects',
        href: '#subjects'
    },
    {
        title: 'About',
        href: '#about'
    },
];

let NavBar = React.createClass({
    getInitialState: function () {
        return {
            activeItem: items[0]
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
                    {items.map(function (item) {
                        return (
                            <NavItem item={item}
                                     isActive = {parent.isActiveItem}
                                     setActive = {parent.setActiveItem}
                                     key = {item.title + item.href}
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
                <a className={"nav_item_href " + activeStyle} href={this.props.item.href}>{this.props.item.title}</a>
            </li>
        )
    },
    setActive: function () {
        this.props.setActive(this.props.item);
    }
});

ReactDOM.render(
    <NavBar/>,
    document.getElementById("navBar")
);
