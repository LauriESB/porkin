import "./style.css";
import "./components/NavigationBar";
import { displayNavBar } from "./components/NavigationBar";
import "./components/Home.js";
import { displayHome } from "./components/Home.js";
import { localStorageKey } from "./components/Home.js";
import { displayLoginScreen } from "./components/AuthScreen.js";
import { sendFriendRequest } from "./utils/requests.js";

const content = document.getElementById("content");

window.addEventListener("load", () => {
  if (localStorage.getItem(localStorageKey)) {
    localStorage.removeItem(localStorageKey);
  }
});

window.addEventListener("load", () => {
  const localStorageUserKey = "currentUser";
  const currentUserData = localStorage.getItem(localStorageUserKey);

  if (currentUserData) {
    const parsedUserData = JSON.parse(currentUserData);
    displayHome(content, parsedUserData);
  } else {
    displayLoginScreen(content);
  }
});
