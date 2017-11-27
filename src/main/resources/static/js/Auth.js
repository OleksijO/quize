export default class Auth {
}

Auth.token = "";

Auth.setToken = (newToken) => Auth.token = "Bearer " + newToken;

Auth.getAuthorization = () => Auth.token;