// src/services/api.js
import axios from "axios";

const BASE_URL = "http://localhost:8083";

// Fetch word and type for home page
export const fetchWordMeaning = () => {
    return axios.get(`${BASE_URL}/word-meaning-home`);
};

// Save user-submitted meaning
export const saveWordMeaning = (formData) => {
    return axios.post(`${BASE_URL}/save`, formData);
};

// Fetch AI-generated meaning
export const fetchAIWord = (word) => {
    return axios.get(`${BASE_URL}/ai`, {
        params: { word },
    });
};
