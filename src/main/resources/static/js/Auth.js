export default class Auth {
}

let token = "";

Auth.setToken = (newToken) => token = "Bearer " + newToken;

Auth.getAuthorization = () => token;

Auth.getAuthObject = (username, password) => ({
    method: 'post',
    headers: {
        'Authorization': 'Basic ' + btoa('trusted-app:secret'),
        'Content-Type': 'application/x-www-form-urlencoded'
    },
    body: "grant_type=password" + //client_credentials
    "&username=" + username +
    '&password='+ password
});