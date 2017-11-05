import React from "react";
import {Link} from "react-router-dom";

const ACTIVE = " active";

export default class NavBarItem extends React.Component {
    render() {
        let activeStyle = this.props.isActive ? ACTIVE : '';
        return (
            <li className={"nav_item " + activeStyle}>
                <Link
                    className={"nav_item_href " + activeStyle}
                    to={"/" + this.props.item.href}>
                    {this.props.item.title}
                </Link>
            </li>
        )
    }
}