import axios from "axios";

const BASE_API_URL = "http://localhost:8080/";

const API_URL = "/api/v1/articles";

const articlesAPI = axios.create({
    baseURL: BASE_API_URL,
});

export { BASE_API_URL, API_URL, articlesAPI };
