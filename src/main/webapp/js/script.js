import { attach } from "../library/store.js";
import App from "../component/App.js";
import logLayer from "../component/log-layer.js";
import dataAPI from "../util/db.js";
import requestToApi from "../util/requestToApi.js";

requestToApi('todos', 'GET')
    .then((data) => {
        dataAPI.load(data)
        dispatch('createInitData', dataAPI.get())
        attach(App, document.querySelector('.todoapp'))
    })
    .catch((error) => {
        console.error('Error:', error);
    });

document.querySelector('.log-layer') !== null && attach(logLayer, document.querySelector('.log-layer'))