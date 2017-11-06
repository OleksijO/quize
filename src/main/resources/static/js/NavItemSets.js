import Role from './Role'
import Routes from './components/navigation/Routes'

const NAV_ITEMS_DEFAULT = [
    {
        title: 'Home',
        href: Routes.HOME
    },
    {
        title: 'Registration',
        href: Routes.REGISTER
    },
    {
        title: 'Login',
        href: Routes.LOGIN
    },
    {
        title: 'About',
        href: Routes.ABOUT
    }
];

const NAV_ITEMS_STUDENT = [
    {
        title: 'Home',
        href: Routes.HOME
    },
    {
        title: 'Subjects',
        href: Routes.SUBJECTS
    },
    {
        title: 'Logout',
        href: Routes.LOGOUT
    }
];

const NAV_ITEMS_TUTOR = [
    {
        title: 'Home',
        href: Routes.HOME
    },
    {
        title: 'Subjects',
        href: Routes.SUBJECTS
    },
    {
        title: 'Add Test',
        href: Routes.ADD_TEST
    },
    {
        title: 'Logout',
        href: Routes.LOGOUT
    }
];

export default class NavItemSets {
}

NavItemSets.setByRole = function(role) {
    console.log("Setting nav items by role: "+role+" ...");
    if (role === Role.STUDENT) {
        NavItemSets.active = NAV_ITEMS_STUDENT;
        console.log("Nav items set to: STUDENT");
    } else if (role === Role.TUTOR) {
        NavItemSets.active = NAV_ITEMS_TUTOR;
        console.log("Nav items set to: TUTOR");
    } else {
        NavItemSets.active = NAV_ITEMS_DEFAULT;
        console.log("Nav items set to: default");
    }
};

NavItemSets.setActiveToDefault = function () {
    console.log("Resetting nav items ... ");
    NavItemSets.active = NAV_ITEMS_DEFAULT;
    console.log("Nav items set to: default");
};

NavItemSets.active =  NAV_ITEMS_DEFAULT;
