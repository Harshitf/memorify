
import axios from 'axios';
import keycloack from '../keycloack/keycloack.js';
// 1. Create the Axios instance

const httpAuthenticated = axios.create({
    baseURL: 'http://localhost:8080/api',
    timeout: 5000,
});

httpAuthenticated.interceptors.request.use(
    (config) => {
        const token = keycloak.token;

        if (token) {
            config.headers['Authorization'] = `Bearer ${token}`;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

export default httpAuthenticated;