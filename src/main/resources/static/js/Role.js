export default class Role{
}

Role.STUDENT = 'STUDENT';
Role.TUTOR = 'TEACHER';
Role.GUEST = 'GUEST';
Role.current = null;
Role.setCurrent = role => Role.current = role;

Role.of = (role) => {
    if (role === Role.STUDENT) {
        return Role.STUDENT;
    } else if (role === Role.TUTOR){
        return Role.TUTOR
    }
    return Role.GUEST;
};