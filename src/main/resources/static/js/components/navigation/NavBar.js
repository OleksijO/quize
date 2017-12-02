import React from 'react';
import NavItemSets from "../../NavItemSets";
import NavBarItem from "./NavBarItem";

export default class NavBar extends React.Component {
    render() {
        let items = NavItemSets.active;
        // BrowserRouter
        // let activePath = document.location.pathname.substring(1);
        // HashRouter
        let activePath = document.location.hash.substring(2);
        console.log(">>> Current pathname:"+document.location.hash);
        if (!items.map((item) => item.href).includes(activePath)) {
            activePath = '';
        }
        return (
            <div>
                <ul className="nav_bar">
                    {items.map(function (item) {
                        return (
                            <NavBarItem item={item}
                                     isActive={item.href === activePath}
                                     key={item.title + item.href}
                            />);
                    })}
                </ul>
            </div>
        )
    }
}

