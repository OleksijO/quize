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
    if (role === Role.STUDENT) {
        NavItemSets.active = NAV_ITEMS_STUDENT;
    } else if (role === Role.TUTOR) {
        NavItemSets.active = NAV_ITEMS_TUTOR;
    }
    NavItemSets.active = NAV_ITEMS_DEFAULT;
};

NavItemSets.getByRole = function (role) {
    if (role === Role.STUDENT) {
        return NAV_ITEMS_STUDENT;
    } else if (role === Role.TUTOR) {
        return NAV_ITEMS_TUTOR;
    }
    return NAV_ITEMS_DEFAULT;
};

NavItemSets.setActiveToDefault = function () {
    NavItemSets.active = NAV_ITEMS_DEFAULT;
};

NavItemSets.active =  NAV_ITEMS_DEFAULT;
