import React from 'react';

const ACTIVE = " active";

export default class NavBar extends React.Component {
    constructor(props) {
        super(props);

        let navItems = props.getNavItems();
        this.state= { activeItem: navItems[0] };

        this.setActiveItem = this.setActiveItem.bind(this);
        this.isActiveItem = this.isActiveItem.bind(this);
    }

    setActiveItem(item) {
        this.setState({
            activeItem: item
        })
    }

    isActiveItem(item) {
        return this.state.activeItem === item;
    }

    render() {
        let parent = this;
        return (
            <div>
                <ul className="nav_bar">
                    {this.props.getNavItems().map(function (item) {
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
}

class NavItem extends React.Component {
    constructor(props){
        super(props);
        this.setActive = this.setActive.bind(this);
    }

    render() {
        let activeStyle = this.props.isActive(this.props.item) ? ACTIVE : '';
        return (
            <li className={"nav_item " + activeStyle} onClick={this.setActive}>
                <a className={"nav_item_href " + activeStyle}
                   href={"#" + this.props.item.href}>{this.props.item.title}</a>
            </li>
        )
    }

    setActive() {
        this.props.setActive(this.props.item);
        routie(this.props.item.href);
    }
}