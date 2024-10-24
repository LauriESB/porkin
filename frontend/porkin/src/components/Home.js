import transparentIcon from "../../public/images/porkin-transparent.png";
import "../utils/nameHelper.js";
import { getFirstName } from "../utils/nameHelper.js";
import "./UserData.js";
import { userData } from "./UserData.js";

function createHome() {
  return `<main id="home-main">
      <header id="home-header">
        <h3>Bem vindo(a), ${getFirstName(userData.fullName)}</h3>
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="32" height="32">
          <path d="M5 18H19V11.0314C19 7.14806 15.866 4 12 4C8.13401 4 5 7.14806 5 11.0314V18ZM12 2C16.9706 2 21 6.04348 21 11.0314V20H3V11.0314C3 6.04348 7.02944 2 12 2ZM9.5 21H14.5C14.5 22.3807 13.3807 23.5 12 23.5C10.6193 23.5 9.5 22.3807 9.5 21Z"></path>
        </svg>
      </header>
      <div id="new-value">
        <label for="input-value">Nova despesa</label>
        <div id="input-value">
          <p>R$</p>
          <input id="new-value-input" type="text" placeholder="0,00">
        </div>
        <label for="participants">Dividir com</label>
        <div id="participants">
          <button id="add-participants-button">
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24">
              <path d="M11 11V5H13V11H19V13H13V19H11V13H5V11H11Z"></path>
            </svg>
          </button>
        </div>
      </div>
      <button id="home-divide-button">
        Dividir Agora
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" height="24" width="24">
          <path d="M16.1716 10.9999L10.8076 5.63589L12.2218 4.22168L20 11.9999L12.2218 19.778L10.8076 18.3638L16.1716 12.9999H4V10.9999H16.1716Z"></path>
        </svg>
        <img class="transparent-icon" src="${transparentIcon}" alt="">
      </button>
    </main>`
}

export function displayHome(element) {
  element.innerHTML += createHome();

  const valueInput = document.getElementById("new-value-input");

  valueInput.addEventListener("input", (event) => {
    let value = event.target.value.replace(/\D/g, '');

    if (value.length === 0) {
      value = '00';
    } else if (value.length === 1) {
      value = '0' + value;
    }

    value = (parseInt(value) / 100).toFixed(2).replace('.', ',');

    event.target.value = value;
  })

  
}