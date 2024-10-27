import transparentIcon from "../../public/images/porkin-transparent.png";
import "../utils/nameHelper.js";
import { getFirstName } from "../utils/nameHelper.js";
import "./UserData.js";
import { friends, userData } from "./UserData.js";
import gsap from "gsap";

let selectedParticipants = [
  {
    fullName: "Você",
    profilePicture: userData.profilePicture,
    username: userData.username,
  },
];

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
    </main>`;
}

export function displayHome(element) {
  element.innerHTML += createHome();

  const addParticipantsButton = document.getElementById(
    "add-participants-button"
  );
  const valueInput = document.getElementById("new-value-input");
  const homeParticipantsContainer = document.getElementById("participants");

  valueInput.addEventListener("input", (event) => {
    let value = event.target.value.replace(/\D/g, "");

    if (value.length === 0) {
      value = "00";
    } else if (value.length === 1) {
      value = "0" + value;
    }

    value = (parseInt(value) / 100).toFixed(2).replace(".", ",");

    event.target.value = value;
  });

  addParticipantsButton.addEventListener("click", () => {
    displayAddParticipants(element);
    selectedParticipants = [
      {
        fullName: "Você",
        profilePicture: userData.profilePicture,
        username: userData.username,
      },
    ];
  });

  homeParticipantsContainer.addEventListener("click", () => {
    displayAddParticipants(element);
  });
}

function createFriendsList(array) {
  let friendsList = "";

  array.forEach((friend) => {
    friendsList += `
      <div class="friend">
        <div>
          <img
            class="friend-picture"
            src="${friend.profilePicture}"
            alt="${friend.fullName}'s picture"
          />
          <div class="friend-name-container">
            <p class="friend-name">${friend.fullName}</p>
            <p class="friend-username">@${friend.username}</p>
          </div>
        </div>
        <input type="checkbox" class="friend-checkbox" value="${friend.username}" />
      </div>
    `;
  });
  return friendsList;
}

function createParticipantsList(selectedParticipants) {
  let participantsList = "";
  selectedParticipants.forEach((participant) => {
    participantsList += `
      <div class="participant">
        <div class="participant-picture">
          <img
            src="${participant.profilePicture}"
            alt="${participant.fullName}'s profile picture"
          />
          <button class="remove-participant-button">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              viewBox="0 0 24 24"
              width="20px"
              weight="20px"
              fill="#020617"
            >
              <path
                d="M11.9997 10.5865L16.9495 5.63672L18.3637 7.05093L13.4139 12.0007L18.3637 16.9504L16.9495 18.3646L11.9997 13.4149L7.04996 18.3646L5.63574 16.9504L10.5855 12.0007L5.63574 7.05093L7.04996 5.63672L11.9997 10.5865Z"
              ></path>
            </svg>
          </button>
        </div>
        <p class="participant-name">${getFirstName(participant.fullName)}</p>
      </div>
    `;
  });

  return participantsList;
}

function createAddParticipants() {
  return `
    <search id="add-participants-container">
      <header>
        <div class="add-participants-screen-label">
          <button class="add-participants-back-button">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              viewBox="0 0 24 24"
              width="32"
              weight="32"
              fill="#020617"
            >
              <path
                d="M7.82843 10.9999H20V12.9999H7.82843L13.1924 18.3638L11.7782 19.778L4 11.9999L11.7782 4.22168L13.1924 5.63589L7.82843 10.9999Z"
              ></path>
            </svg>
          </button>
          <div>
            <p>Adicionar participantes</p>
            <p>Selecione seus amigos</p>
          </div>
        </div>
        <div class="participants-label">
          <p>Dividir com</p>
          <button class="clear-array-button">Limpar</button>
        </div>
        <div class="participants-array-pictures">
          
        </div>
        <div class="search-friend-input-container">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 24 24"
            fill="#020617"
            width="24"
            height="24"
          >
            <path
              d="M18.031 16.6168L22.3137 20.8995L20.8995 22.3137L16.6168 18.031C15.0769 19.263 13.124 20 11 20C6.032 20 2 15.968 2 11C2 6.032 6.032 2 11 2C15.968 2 20 6.032 20 11C20 13.124 19.263 15.0769 18.031 16.6168ZM16.0247 15.8748C17.2475 14.6146 18 12.8956 18 11C18 7.1325 14.8675 4 11 4C7.1325 4 4 7.1325 4 11C4 14.8675 7.1325 18 11 18C12.8956 18 14.6146 17.2475 15.8748 16.0247L16.0247 15.8748Z"
            ></path>
          </svg>
          <input
            type="text"
            placeholder="Procure seus amigos"
            class="search-friend-input"
          />
        </div>
      </header>
      <label for="friends-list" id="friends-list-label"> Amigos </label>
      <div id="friends-list">

      </div>
      <button id="accept-participants">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          viewBox="0 0 24 24"
          fill="currentColor"
          width="24"
          height="24"
        >
          <path
            d="M12 22C6.47715 22 2 17.5228 2 12C2 6.47715 6.47715 2 12 2C17.5228 2 22 6.47715 22 12C22 17.5228 17.5228 22 12 22ZM12 20C16.4183 20 20 16.4183 20 12C20 7.58172 16.4183 4 12 4C7.58172 4 4 7.58172 4 12C4 16.4183 7.58172 20 12 20ZM11.0026 16L6.75999 11.7574L8.17421 10.3431L11.0026 13.1716L16.6595 7.51472L18.0737 8.92893L11.0026 16Z"
          ></path>
        </svg>
        Aceitar
      </button>
    </search>
  `;
}

function createHomeParticipantsIcons(selectedParticipants) {
  let homeParticipantsIcons = "";
  selectedParticipants.forEach((participant) => {
    homeParticipantsIcons += `
      <img class="participant-photo" src="${participant.profilePicture}">
    `;
  });

  return homeParticipantsIcons;
}

function displayHomeParticipants(element, selectedParticipants) {
  element.innerHTML += createHomeParticipantsIcons(selectedParticipants);
}

function displayAddParticipants(element) {
  element.innerHTML = createAddParticipants();

  const participantsContainer = document.querySelector(
    ".participants-array-pictures"
  );
  const friendsListContainer = document.getElementById("friends-list");
  const searchFriend = document.querySelector(".search-friend-input");
  const clearButton = document.querySelector(".clear-array-button");
  const backButton = document.querySelector(".add-participants-back-button");
  const acceptButton = document.getElementById("accept-participants");

  displayParticipantsList(participantsContainer, selectedParticipants);
  displayFriendsList(friendsListContainer, friends.list);

  searchFriend.addEventListener("input", (event) => {
    friendsListContainer.innerHTML = "";
    displayFriendsList(friendsListContainer, filterFriends(searchFriend.value));
  });

  clearButton.addEventListener("click", () => {
    const friendsCheckboxes = document.querySelectorAll(".friend-checkbox");
    selectedParticipants = selectedParticipants.filter(
      (friend) => friend.username === userData.username
    );
    displayParticipantsList(participantsContainer, selectedParticipants);
    friendsCheckboxes.forEach((checkbox) => {
      const friendUsername = checkbox.value;
      const isSelected = selectedParticipants.some(
        (participant) => participant.username === friendUsername
      );
      checkbox.checked = isSelected;
    });
    console.log(selectedParticipants);
  });

  backButton.addEventListener("click", () => {
    element.innerHTML = "";
    displayHome(element);
  });

  acceptButton.addEventListener("click", () => {
    element.innerHTML = "";
    displayHome(element);

    const homeParticipantsContainer = document.getElementById("participants");
    displayHomeParticipants(homeParticipantsContainer, selectedParticipants);
  });
}

function displayParticipantsList(element, selectedParticipants) {
  element.innerHTML = createParticipantsList(selectedParticipants);
}

function displayFriendsList(element, array) {
  element.innerHTML = createFriendsList(array);

  const participantsContainer = document.querySelector(
    ".participants-array-pictures"
  );
  const friendsCheckboxes = document.querySelectorAll(".friend-checkbox");

  friendsCheckboxes.forEach((checkbox) => {
    const friendUsername = checkbox.value;
    const isSelected = selectedParticipants.some(
      (participant) => participant.username === friendUsername
    );
    checkbox.checked = isSelected;

    checkbox.addEventListener("change", (event) => {
      if (event.target.checked) {
        const friendUsername = checkbox.value;
        const selectedFriend = friends.list.find(
          (friend) => friend.username === friendUsername
        );

        selectedParticipants.push({
          fullName: selectedFriend.fullName,
          profilePicture: selectedFriend.profilePicture,
          username: friendUsername,
        });

        displayParticipantsList(participantsContainer, selectedParticipants);
      } else {
        const friendUsername = checkbox.value;
        selectedParticipants = selectedParticipants.filter(
          (participant) => participant.username !== friendUsername
        );

        displayParticipantsList(participantsContainer, selectedParticipants);
      }
      console.log(selectedParticipants);
    });
  });
}

function filterFriends(input) {
  const filteredList = friends.list.filter(
    (friend) =>
      friend.fullName.toLowerCase().includes(input.toLowerCase()) ||
      friend.username.toLowerCase().includes(input.toLowerCase())
  );
  return filteredList;
}
