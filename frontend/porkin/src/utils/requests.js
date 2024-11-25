import axios from "axios";

axios;

export function getUserData() {
  axios
    .get("https://porkin.onrender.com/person/llasantinelli")
    .then((response) => {
      console.log("response data:", response.data);
    })
    .catch((error) => {
      console.error("error fetching data: error");
    });
}
