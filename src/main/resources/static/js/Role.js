export default class Role{
}

Role.STUDENT = 'STUDENT';
Role.TUTOR = 'TEACHER';
Role.GUEST = 'GUEST';
let current = null;
Role.setCurrent = role => current = role;
Role.getCurrent = role => Role.of(current);

Role.of = (role) => {
    if (role === Role.STUDENT) {
        return Role.STUDENT;
    } else if (role === Role.TUTOR){
        return Role.TUTOR
    }
    return Role.GUEST;
};