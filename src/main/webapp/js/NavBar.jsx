let React = require('react');

let ACTIVE = " active";

module.exports = React.createClass({
    getInitialState:() => {activeItem: this.props.items[0]},
    setActiveItem: (item) => this.setState({activeItem: item}),
    isActiveItem: (item) => this.state.activeItem === item,
    render: () => (
        <div>
            <ul className="nav_bar">
                {this.props.items.map((item) =>
                    <NavItem item={item}
                             isActive={this.isActiveItem}
                             setActive={this.setActiveItem}
                             key={item.title + item.href}
                    />
                )}
            </ul>
        </div>
    )
})
;

let NavItem = React.createClass({

    render: function () {
        let activeStyle = this.props.isActive(this.props.item) ? ACTIVE : '';
        return (
            <li className={"nav_item " + activeStyle} onClick={this.setActive}>
                <a className={"nav_item_href " + activeStyle}
                   href={"#" + this.props.item.href}>{this.props.item.title}</a>
            </li>
        )
    },
    setActive: function () {
        this.props.setActive(this.props.item);
        routie(this.props.item.href);
    }
});