import axios from "axios";

const BASE_API_URL = "https://dev-news-web-scrapper.herokuapp.com/";

const API_URL = "/api/v1/articles";

const articlesAPI = axios.create({
    baseURL: BASE_API_URL,
});

export { BASE_API_URL, API_URL, articlesAPI };
