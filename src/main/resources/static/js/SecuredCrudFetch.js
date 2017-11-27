import fetch from "isomorphic-fetch";
import Auth from "./Auth"

export default class SecuredCrudFetch {
}

SecuredCrudFetch.get = function(url) {
    return new Promise((resolve, reject) => {
        fetch(url, {
            method: 'get',
            credentials: 'same-origin',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'Authorization': Auth.getAuthorization()
            },
        }).then(res => {
            if (res.ok) {
                return res.json();
            }
            throw new Error(res);
        })
            .then(body => resolve(body))
            .catch(error => reject(error));
    });
};

SecuredCrudFetch.post = function(url, payload) {
    return new Promise((resolve, reject) => {
        fetch(url, {
            method: 'post',
            credentials: 'same-origin',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'Authorization': Auth.getAuthorization()
            },
            body: JSON.stringify(payload),
        }).then(res => {
            if (res.ok) {
                return res.json();
            }
            throw new Error(res.status);
        })
            .then(body => resolve(body))
            .catch(error => reject(error));
    });
};

SecuredCrudFetch.authPost = function(url, payload) {
    return new Promise((resolve, reject) => {
        fetch(url, payload).then(res => {
            if (res.ok) {
                return res.json();
            }
            throw new Error(res.status);
        })
            .then(body => resolve(body))
            .catch(error => reject(error));
    });
};


SecuredCrudFetch.put = function(url, payload) {
    return new Promise((resolve, reject) => {
        fetch(url, {
            method: 'put',
            credentials: 'same-origin',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'Authorization': Auth.getAuthorization()
            },
            body: JSON.stringify(payload),
        }).then(res => {
            if (res.ok) {
                return res.json();
            }
            throw new Error(res.status);
        })
            .then(body => resolve(body))
            .catch(error => reject(error));
    });
};

SecuredCrudFetch.remove = function(url, payload) {
    return new Promise((resolve, reject) => {
        fetch(url, {
            method: 'delete',
            credentials: 'same-origin',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'Authorization': Auth.getAuthorization()
            },
            body: JSON.stringify(payload),
        }).then(res => {
            if (res.ok) {
                return res.json();
            }
            throw new Error(res.status);
        })
            .then(body => resolve(body))
            .catch(error => reject(error));
    });
};
