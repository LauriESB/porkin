import "./style.css";
import "./components/NavigationBar";
import { displayNavBar } from "./components/NavigationBar";
import "./components/Home.js";
import { displayHome } from "./components/Home.js";
import { localStorageKey } from "./components/Home.js";
import { displayLoginScreen } from "./components/AuthScreen.js";
import { getProfilePicture, sendFriendRequest } from "./utils/requests.js";

const content = document.getElementById("content");

window.addEventListener("load", () => {
  if (localStorage.getItem(localStorageKey)) {
    localStorage.removeItem(localStorageKey);
  }
});

if ("serviceWorker" in navigator) {
  window.addEventListener("load", () => {
    navigator.serviceWorker
      .register("/service-worker.js")
      .then((registration) =>
        console.log("ServiceWorker registered:", registration)
      )
      .catch((err) => console.error("ServiceWorker registration failed:", err));
  });
}

displayLoginScreen(content);
