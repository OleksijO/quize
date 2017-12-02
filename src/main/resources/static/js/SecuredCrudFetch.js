import fetch from "isomorphic-fetch";
import Auth from "./Auth"

export default class SecuredCrudFetch {
}

let buildRequest = (method, payload) => ( {
    method: method,
    credentials: 'same-origin',
    headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
        'Authorization': Auth.getAuthorization()
    },
    body: payload ? JSON.stringify(payload) : undefined,
});

let extractJson = res => {
    if (res.ok) {
        return res.json();
    }
    throw new Error(res.status);
};

SecuredCrudFetch.get = function (url) {
    return new Promise((resolve, reject) => {
        fetch(url, buildRequest('get'))
            .then(res => extractJson(res))
            .then(body => resolve(body))
            .catch(error => reject(error));
    });
};

SecuredCrudFetch.post = function (url, payload) {
    return new Promise((resolve, reject) => {
        fetch(url, buildRequest('post', payload))
            .then(res => extractJson(res))
            .then(body => resolve(body))
            .catch(error => reject(error));
    });
};

SecuredCrudFetch.authPost = function (url, payload) {
    return new Promise((resolve, reject) => {
        fetch(url, payload)
            .then(res => extractJson(res))
            .then(body => resolve(body))
            .catch(error => reject(error));
    });
};


SecuredCrudFetch.put = function (url, payload) {
    return new Promise((resolve, reject) => {
        fetch(url, buildRequest('put', payload))
            .then(res => extractJson(res))
            .then(body => resolve(body))
            .catch(error => reject(error));
    });
};

SecuredCrudFetch.remove = function (url, payload) {
    return new Promise((resolve, reject) => {
        fetch(url, buildRequest('delete', payload))
            .then(res => extractJson(res))
            .then(body => resolve(body))
            .catch(error => reject(error));
    });
};
