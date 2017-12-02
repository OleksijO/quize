export default class Auth {
}

let token = "";

Auth.setToken = (newToken) => token = "Bearer " + newToken;

Auth.getAuthorization = () => token;