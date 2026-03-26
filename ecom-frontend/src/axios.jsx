import axios from "axios";

const API = axios.create({
  baseURL: "https://ecommerce-project-1-nh9p.onrender.com/api",
});
delete API.defaults.headers.common["Authorization"];
export default API;
