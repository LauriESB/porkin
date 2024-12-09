import transparentIcon from "../../public/images/porkin-transparent.png";
import "../utils/nameHelper.js";
import {
  calculatePercentage,
  calculateValueFromPercentage,
  getFirstName,
  getInitialPercentages,
  insertComma,
  parsePercentage,
  removeComma,
} from "../utils/nameHelper.js";
import "./UserData.js";
import { bills, friends, paymentMethods, userData } from "./UserData.js";
import "./NavigationBar.js";
import { displayNavBar } from "./NavigationBar.js";
import { displayHomeNavBar } from "./NavigationBar.js";
import gsap from "gsap";
import closedLock from "../../public/svg/lock-closed.svg";
import openLock from "../../public/svg/lock-open.svg";
import {
  createExpense,
  getAllExpenses,
  getFriendsList,
  getProfilePicture,
  getUserData,
} from "../utils/requests.js";
import { profilePictures } from "./UserData.js";
import { getAllUsers } from "../utils/requests.js";
import defaultPicture from "../../public/images/default-profile-picture.png";

function createSelectedParticipants() {}

let selectedParticipants = [];
export const localStorageKey = "formattedValue";

function createHome(currentUserData) {
  return `<main id="home-main">
      <header id="home-header">
        <h3>Bem vindo(a), ${getFirstName(currentUserData.name)}</h3>
        <button id="notification-button">
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="32" height="32">
            <path d="M5 18H19V11.0314C19 7.14806 15.866 4 12 4C8.13401 4 5 7.14806 5 11.0314V18ZM12 2C16.9706 2 21 6.04348 21 11.0314V20H3V11.0314C3 6.04348 7.02944 2 12 2ZM9.5 21H14.5C14.5 22.3807 13.3807 23.5 12 23.5C10.6193 23.5 9.5 22.3807 9.5 21Z"></path>
          </svg>
          <div class="new-notification hidden"></div>
        </button>
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

export async function displayHome(element, currentUserData) {
  element.innerHTML += createHome(currentUserData);
  console.log(currentUserData);

  window.currentUser = currentUserData;

  if (selectedParticipants.length === 0) {
    selectedParticipants = [
      {
        fullName: "Você",
        profilePicture: profilePictures[currentUser.username],
        username: currentUserData.username,
      },
    ];
  }

  displayHomeNavBar(element, currentUserData);
  const addParticipantsButton = document.getElementById(
    "add-participants-button"
  );
  const valueInput = document.getElementById("new-value-input");
  const homeParticipantsContainer = document.getElementById("participants");
  const divideButton = document.getElementById("home-divide-button");
  const inputContainer = document.getElementById("input-value");
  const notificationButton = document.getElementById("notification-button");
  const newNotification = document.querySelector(".new-notification");

  const notificationBills = await getExpireTodayBills(currentUserData);

  const savedValue = localStorage.getItem(localStorageKey);
  if (savedValue) {
    valueInput.value = savedValue;
  }

  notificationButton.addEventListener("click", () => {
    displayNotificationScreen(element, currentUserData, notificationBills);
  });

  if (notificationBills.length > 0) {
    newNotification.classList.remove("hidden");
  }

  valueInput.addEventListener("input", (event) => {
    let value = event.target.value.replace(/\D/g, "");

    if (value.length === 0) {
      value = "00";
    } else if (value.length === 1) {
      value = "0" + value;
    }

    value = (parseInt(value) / 100).toFixed(2).replace(".", ",");

    valueInput.value = value;

    localStorage.setItem(localStorageKey, value);
  });

  divideButton.addEventListener("click", () => {
    const insertedValue = parseFloat(valueInput.value.replace(",", "."));
    const insertedParticipants = selectedParticipants;
    if (!insertedValue) {
      console.log("Insert a value");

      gsap.fromTo(
        inputContainer,
        { x: -5 },
        {
          x: 5,
          duration: 0.1,
          repeat: 5,
          yoyo: true,
          ease: "power1.inOut",
          onComplete: () => {
            gsap.set(inputContainer, { x: 0 });
          },
        }
      );
      return;
    }

    displaySplitBill(
      element,
      insertedParticipants,
      insertedValue,
      currentUserData
    );
    const billSplitingPage = document.getElementById("bill-spliting-page");
    const completeSplitButton = document.getElementById("create-bill-button");

    gsap.fromTo(
      billSplitingPage,
      { top: window.innerHeight },
      {
        top: 0,
        duration: 0.5,
        ease: "power2.inOut",
      }
    );

    gsap.fromTo(
      completeSplitButton,
      { top: 400 },
      {
        top: window.innerHeight - 6 * 16,
        duration: 0.5,
        ease: "power2.inOut",
      }
    );
  });

  addParticipantsButton.addEventListener("click", () => {
    displayAddParticipants(element, currentUserData);
    resetParticipants(currentUserData);
  });

  homeParticipantsContainer.addEventListener("click", () => {
    displayAddParticipants(element, currentUserData);
  });
}

async function createFriendsList(array) {
  let friendsList = "";
  const allUsers = await getAllUsers();

  for (const friend of array) {
    const currentFriend = allUsers.find((user) => user.username === friend);
    if (!currentFriend) continue; // Skip if the friend is not found

    const friendProfilePicture = await getProfilePicture(
      currentFriend.username
    );

    friendsList += `
      <div class="friend">
        <div>
          <img
            class="friend-picture"
            src="${friendProfilePicture}"
            alt="${currentFriend.name}'s picture"
          />
          <div class="friend-name-container">
            <p class="friend-name">${currentFriend.name}</p>
            <p class="friend-username">@${currentFriend.username}</p>
          </div>
        </div>
        <input type="checkbox" class="friend-checkbox" value="${currentFriend.username}" />
      </div>
    `;
  }

  return friendsList;
}

async function createParticipantsList(selectedParticipants) {
  let participantsList = "";
  console.log(selectedParticipants);

  for (const participant of selectedParticipants) {
    const participantProfilePicture = await getProfilePicture(
      participant.username
    );

    participantsList += `
      <div class="participant">
        <div class="participant-picture">
        <!--
        <button class="remove-participant-button" data-username="${
          participant.username
        }">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          viewBox="0 0 24 24"
          fill="currentColor"
        >
          <path
            d="M11.9997 10.5865L16.9495 5.63672L18.3637 7.05093L13.4139 12.0007L18.3637 16.9504L16.9495 18.3646L11.9997 13.4149L7.04996 18.3646L5.63574 16.9504L10.5855 12.0007L5.63574 7.05093L7.04996 5.63672L11.9997 10.5865Z"
          ></path>
        </svg>
        </button>
        -->
          <img
            src="${participantProfilePicture}"
            alt="${participant.fullName}'s profile picture"
          />
        </div>
        <p class="participant-name">${getFirstName(participant.fullName)}</p>
      </div>
    `;
  }

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
  const participantsWithoutUser = selectedParticipants.filter(
    (participant) => participant.username !== userData.username
  );
  let homeParticipantsIcons = "";
  participantsWithoutUser.forEach((participant) => {
    homeParticipantsIcons += `
      <img class="participant-photo" src="${participant.profilePicture}">
    `;
  });
  return homeParticipantsIcons;
}

function displayHomeParticipants(element, selectedParticipants) {
  element.innerHTML += createHomeParticipantsIcons(selectedParticipants);
}

async function displayAddParticipants(element, currentUserData) {
  element.innerHTML = createAddParticipants();

  const participantsContainer = document.querySelector(
    ".participants-array-pictures"
  );
  const friendsListContainer = document.getElementById("friends-list");
  const searchFriend = document.querySelector(".search-friend-input");
  const clearButton = document.querySelector(".clear-array-button");
  const backButton = document.querySelector(".add-participants-back-button");
  const acceptButton = document.getElementById("accept-participants");
  const addParticipantsScreen = document.getElementById(
    "add-participants-container"
  );

  gsap.fromTo(
    addParticipantsScreen,
    { left: window.innerWidth },
    {
      left: 0,
      duration: 0.2,
      ease: "power1.inOut",
    }
  );
  const friendsList = await getFriendsList(currentUserData.username);

  displayParticipantsList(participantsContainer, selectedParticipants);
  displayFriendsList(friendsListContainer, friendsList);

  searchFriend.addEventListener("input", (event) => {
    friendsListContainer.innerHTML = "";
    displayFriendsList(friendsListContainer, filterFriends(searchFriend.value));
  });

  clearButton.addEventListener("click", () =>
    clearParticipantsList(currentUserData)
  );
  backButton.addEventListener("click", () => {
    gsap.fromTo(
      addParticipantsScreen,
      { left: 0 },
      {
        left: window.innerWidth,
        duration: 0.2,
        ease: "power1.inOut",
      }
    );
    setTimeout(() => goBackToHome(element, currentUserData), 200);
  });
  acceptButton.addEventListener("click", () => {
    gsap.fromTo(
      addParticipantsScreen,
      { left: 0 },
      {
        left: window.innerWidth,
        duration: 0.2,
        ease: "power1.inOut",
      }
    );
    setTimeout(() => acceptParticipants(element, currentUserData), 200);
  });
}

async function displayParticipantsList(element, selectedParticipants) {
  element.innerHTML = await createParticipantsList(selectedParticipants);
}

async function displayFriendsList(element, array) {
  element.innerHTML = await createFriendsList(array);
  console.log(array);

  const participantsContainer = document.querySelector(
    ".participants-array-pictures"
  );
  const friendsCheckboxes = document.querySelectorAll(".friend-checkbox");
  const allUsers = await getAllUsers();

  friendsCheckboxes.forEach((checkbox) => {
    const friendUsername = checkbox.value;
    const isSelected = selectedParticipants.some(
      (participant) => participant.username === friendUsername
    );
    checkbox.checked = isSelected;

    checkbox.addEventListener("change", (event) => {
      if (event.target.checked) {
        const friendUsername = checkbox.value;
        const selectedFriend = allUsers.find(
          (friend) => friend.username === friendUsername
        );

        selectedParticipants.push({
          fullName: selectedFriend.name,
          profilePicture: profilePictures[selectedFriend.username],
          username: friendUsername,
        });

        displayParticipantsList(participantsContainer, selectedParticipants);
        console.log(selectedParticipants);
      } else {
        const friendUsername = checkbox.value;
        selectedParticipants = selectedParticipants.filter(
          (participant) => participant.username !== friendUsername
        );

        displayParticipantsList(participantsContainer, selectedParticipants);
      }
    });
  });
}

async function filterFriends(input) {
  const friendsList = await getFriendsList(currentUserData.username);
  const filteredList = friendsList.filter(
    (friend) =>
      friend.fullName.toLowerCase().includes(input.toLowerCase()) ||
      friend.username.toLowerCase().includes(input.toLowerCase())
  );
  return filteredList;
}

function clearParticipantsList(currentUserData) {
  const participantsContainer = document.querySelector(
    ".participants-array-pictures"
  );
  const friendsCheckboxes = document.querySelectorAll(".friend-checkbox");
  selectedParticipants = selectedParticipants.filter(
    (friend) => friend.username === currentUserData.username
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
}

function goBackToHome(element, currentUserData) {
  element.innerHTML = "";
  displayHome(element, currentUserData);
}

function acceptParticipants(element, currentUserData) {
  goBackToHome(element, currentUserData);

  const homeParticipantsContainer = document.getElementById("participants");
  displayHomeParticipants(homeParticipantsContainer, selectedParticipants);
}

function resetParticipants(currentUserData) {
  selectedParticipants = [
    {
      fullName: "Você",
      profilePicture: userData.profilePicture,
      username: currentUserData.username,
    },
  ];
}

function createSplitBill() {
  const currentDate = new Date();
  const formattedDate = currentDate.toISOString().split("T")[0];

  return `
    <form id="bill-spliting-page" action="">
        <header class="bill-spliting-header">
          <div class="left-container">
            <button class="bill-spliting-back-button">
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
              <p>Dividir</p>
              <p>Valor total</p>
            </div>
          </div>
          <div class="value-to-split">
            <p class="currency">R$</p>
            <p class="bill-total-value">140,00</p>
          </div>
        </header>
        <div class="bill-info">
          <div class="labels">
            <label for="name-input">Nome da despesa</label>
            <div id="bill-name-container" class="bill-input-container">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="24"
                height="24"
                fill="#020617"
                viewBox="0 0 256 256"
              >
                <path
                  d="M180,104a4,4,0,0,1-4,4H80a4,4,0,0,1,0-8h96A4,4,0,0,1,180,104Zm-4,28H80a4,4,0,0,0,0,8h96a4,4,0,0,0,0-8Zm52-76V208a4,4,0,0,1-4,4,4.05,4.05,0,0,1-1.79-.42L192,196.47l-30.21,15.11a4,4,0,0,1-3.58,0L128,196.47,97.79,211.58a4,4,0,0,1-3.58,0L64,196.47,33.79,211.58A4,4,0,0,1,28,208V56A12,12,0,0,1,40,44H216A12,12,0,0,1,228,56Zm-8,0a4,4,0,0,0-4-4H40a4,4,0,0,0-4,4V201.53l26.21-13.11a4,4,0,0,1,3.58,0L96,203.53l30.21-15.11a4,4,0,0,1,3.58,0L160,203.53l30.21-15.11a4,4,0,0,1,3.58,0L220,201.53Z"
                ></path>
              </svg>
              <input
                id="name-input"
                class="bill-input"
                type="text"
                placeholder="Churrasco, viagem..."
              />
            </div>
          </div>

          <div id="second-line">
            <div class="labels">
              <label for="date-input">Pagar Até</label>
              <div id="bill-date-container" class="bill-input-container">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  width="24"
                  height="24"
                  fill="#020617"
                  viewBox="0 0 256 256"
                >
                  <path
                    d="M208,36H180V24a4,4,0,0,0-8,0V36H84V24a4,4,0,0,0-8,0V36H48A12,12,0,0,0,36,48V208a12,12,0,0,0,12,12H208a12,12,0,0,0,12-12V48A12,12,0,0,0,208,36ZM48,44H76V56a4,4,0,0,0,8,0V44h88V56a4,4,0,0,0,8,0V44h28a4,4,0,0,1,4,4V84H44V48A4,4,0,0,1,48,44ZM208,212H48a4,4,0,0,1-4-4V92H212V208A4,4,0,0,1,208,212Zm-52-60a4,4,0,0,1-4,4H132v20a4,4,0,0,1-8,0V156H104a4,4,0,0,1,0-8h20V128a4,4,0,0,1,8,0v20h20A4,4,0,0,1,156,152Z"
                  ></path>
                </svg>
                <input
                  id="date-input"
                  class="bill-input"
                  type="date"
                  min="${formattedDate}"
                  value="${formattedDate}"
                />
              </div>
            </div>

            <div class="labels">
              <label for="payment-input">Como pagar</label>
              <div
                id="bill-payment-method-container"
                class="bill-input-container"
              >
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  width="24"
                  height="24"
                  fill="#020617"
                  viewBox="0 0 256 256"
                >
                  <path
                    d="M216,68H56a12,12,0,0,1,0-24H192a4,4,0,0,0,0-8H56A20,20,0,0,0,36,56V184a20,20,0,0,0,20,20H216a12,12,0,0,0,12-12V80A12,12,0,0,0,216,68Zm4,124a4,4,0,0,1-4,4H56a12,12,0,0,1-12-12V72a19.86,19.86,0,0,0,12,4H216a4,4,0,0,1,4,4Zm-32-60a8,8,0,1,1-8-8A8,8,0,0,1,188,132Z"
                  ></path>
                </svg>
                <select
                  class="bill-input"
                  name="payment-methods"
                  id="payment-select"
                ></select>
              </div>
            </div>
          </div>
          <div class="separation-line"></div>
          <button id="edit-participants-button">
            <div id="participants-pictures-split-page">
              <div class="circle">
                <svg
                  width="20"
                  height="20"
                  viewBox="0 0 25 25"
                  fill="none"
                  xmlns="http://www.w3.org/2000/svg"
                >
                  <path
                    d="M12.5 0C10.0277 0 7.61099 0.733112 5.55538 2.10663C3.49976 3.48015 1.89761 5.43238 0.951511 7.71646C0.00541586 10.0005 -0.242126 12.5139 0.24019 14.9386C0.722505 17.3634 1.91301 19.5907 3.66117 21.3388C5.40933 23.087 7.63661 24.2775 10.0614 24.7598C12.4861 25.2421 14.9995 24.9946 17.2835 24.0485C19.5676 23.1024 21.5199 21.5002 22.8934 19.4446C24.2669 17.389 25 14.9723 25 12.5C24.9964 9.18591 23.6782 6.0086 21.3348 3.66518C18.9914 1.32177 15.8141 0.0036395 12.5 0ZM12.5 24C10.2255 24 8.00211 23.3255 6.11095 22.0619C4.21978 20.7983 2.7458 19.0022 1.87539 16.9009C1.00498 14.7995 0.777245 12.4872 1.22097 10.2565C1.6647 8.02568 2.75997 5.97658 4.36828 4.36827C5.97658 2.75997 8.02569 1.6647 10.2565 1.22097C12.4872 0.777239 14.7995 1.00498 16.9009 1.87539C19.0022 2.74579 20.7983 4.21978 22.0619 6.11094C23.3255 8.00211 24 10.2255 24 12.5C23.9967 15.549 22.784 18.4721 20.6281 20.6281C18.4721 22.784 15.549 23.9967 12.5 24ZM18 12.5C18 12.6326 17.9473 12.7598 17.8536 12.8536C17.7598 12.9473 17.6326 13 17.5 13H13V17.5C13 17.6326 12.9473 17.7598 12.8536 17.8536C12.7598 17.9473 12.6326 18 12.5 18C12.3674 18 12.2402 17.9473 12.1465 17.8536C12.0527 17.7598 12 17.6326 12 17.5V13H7.50001C7.3674 13 7.24022 12.9473 7.14645 12.8536C7.05268 12.7598 7.00001 12.6326 7.00001 12.5C7.00001 12.3674 7.05268 12.2402 7.14645 12.1464C7.24022 12.0527 7.3674 12 7.50001 12H12V7.5C12 7.36739 12.0527 7.24021 12.1465 7.14645C12.2402 7.05268 12.3674 7 12.5 7C12.6326 7 12.7598 7.05268 12.8536 7.14645C12.9473 7.24021 13 7.36739 13 7.5V12H17.5C17.6326 12 17.7598 12.0527 17.8536 12.1464C17.9473 12.2402 18 12.3674 18 12.5Z"
                    fill="#020617"
                  />
                </svg>
              </div>
            </div>
            <p>Editar participantes</p>
          </button>
        </div>
        <div id="participants-parts"></div>
        <div id="split-error-message">
          <div>
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="32"
              height="32"
              fill="#000000"
              viewBox="0 0 256 256"
            >
              <path
                d="M128,24A104,104,0,1,0,232,128,104.11,104.11,0,0,0,128,24Zm0,192a88,88,0,1,1,88-88A88.1,88.1,0,0,1,128,216Zm-8-80V80a8,8,0,0,1,16,0v56a8,8,0,0,1-16,0Zm20,36a12,12,0,1,1-12-12A12,12,0,0,1,140,172Z"
              ></path>
            </svg>
            <p class="error-message-title">
              
            </p>
          </div>

          <p class="error-message">
            
          </p>
        </div>
        <button id="create-bill-button">
          Dividir Agora
          <svg
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 24 24"
            fill="currentColor"
            height="24"
            width="24"
          >
            <path
              d="M16.1716 10.9999L10.8076 5.63589L12.2218 4.22168L20 11.9999L12.2218 19.778L10.8076 18.3638L16.1716 12.9999H4V10.9999H16.1716Z"
            ></path>
          </svg>
        </button>
      </form>
  `;
}

function loadPaymentMethods(element) {
  Object.keys(paymentMethods).forEach((method, index) => {
    element.innerHTML += `
      <option value="${index}">${method}</option>
    `;
  });
}

async function displaySplitBill(
  element,
  selectedParticipantsArray,
  valueToSplit,
  currentUserData
) {
  element.innerHTML = createSplitBill();
  const paymentSelect = document.getElementById("payment-select");

  loadPaymentMethods(paymentSelect);

  let participantsPictures = document.getElementById("participants-pictures");
  const addIcon = document.querySelector(".circle");
  const totalValueToSplit = document.querySelector(".bill-total-value");
  const initialPercentage = getInitialPercentages(selectedParticipants);
  const initialSplitValue = ((valueToSplit * initialPercentage) / 100)
    .toFixed(2)
    .replace(".", ",");
  const participantsContainer = document.getElementById("participants-parts");
  const completeSplitButton = document.getElementById("create-bill-button");
  const billSplitingBackButton = document.querySelector(
    ".bill-spliting-back-button"
  );
  const billSplitingPage = document.getElementById("bill-spliting-page");
  const displayedTotalValue = document.querySelector(".value-to-split");
  const billNameInput = document.getElementById("name-input");
  const billDateInput = document.getElementById("date-input");
  const billPaymentInput = document.getElementById("payment-select");
  const billNameContainer = document.getElementById("bill-name-container");
  const editParticipantsButton = document.getElementById(
    "edit-participants-button"
  );

  editParticipantsButton.addEventListener("click", (event) => {
    event.preventDefault();
    const currentName = billNameInput.value;
    const currentDate = billDateInput.value;
    const currentPayment = billPaymentInput.value;

    displayAddParticipants(element, currentUserData);

    const backButton = document.querySelector(".add-participants-back-button");
    const acceptParticipantsButton = document.getElementById(
      "accept-participants"
    );

    const newBackButton = backButton.cloneNode(true);
    const newAcceptParticipantsButton =
      acceptParticipantsButton.cloneNode(true);

    backButton.parentNode.replaceChild(newBackButton, backButton);
    acceptParticipantsButton.parentNode.replaceChild(
      newAcceptParticipantsButton,
      acceptParticipantsButton
    );

    console.log(selectedParticipants);

    newBackButton.addEventListener("click", () => {
      const addParticipantsScreen = document.getElementById(
        "add-participants-container"
      );

      gsap.fromTo(
        addParticipantsScreen,
        { left: 0 },
        {
          left: window.innerWidth,
          duration: 0.2,
          ease: "power1.inOut",
        }
      );
      setTimeout(() => {
        displaySplitBill(
          element,
          selectedParticipantsArray,
          valueToSplit,
          currentUserData
        );
        const billNameInput = document.getElementById("name-input");
        const billDateInput = document.getElementById("date-input");
        const billPaymentInput = document.getElementById("payment-select");
        billNameInput.value = currentName;
        billDateInput.value = currentDate;
        billPaymentInput.value = currentPayment;
      }, 200);
    });

    newAcceptParticipantsButton.addEventListener("click", () => {
      const addParticipantsScreen = document.getElementById(
        "add-participants-container"
      );

      gsap.fromTo(
        addParticipantsScreen,
        { left: 0 },
        {
          left: window.innerWidth,
          duration: 0.2,
          ease: "power1.inOut",
        }
      );
      setTimeout(() => {
        displaySplitBill(
          element,
          selectedParticipantsArray,
          valueToSplit,
          currentUserData
        );
        const billNameInput = document.getElementById("name-input");
        const billDateInput = document.getElementById("date-input");
        const billPaymentInput = document.getElementById("payment-select");
        billNameInput.value = currentName;
        billDateInput.value = currentDate;
        billPaymentInput.value = currentPayment;
      }, 200);
    });
  });

  billSplitingBackButton.addEventListener("click", (event) => {
    event.preventDefault();

    gsap.fromTo(
      billSplitingPage,
      { top: 0 },
      {
        top: window.innerHeight,
        duration: 0.4,
        ease: "power2.inOut",
        onComplete: () => {
          element.innerHTML = "";
          displayHome(element, currentUserData);
        },
      }
    );

    gsap.fromTo(
      completeSplitButton,
      { top: window.innerHeight - 6 * 16 },
      {
        top: 400,
        duration: 0.5,
        ease: "power2.inOut",
      }
    );
  });

  selectedParticipants.forEach((participant) => {
    const participantPicture = `
      <img
        src="${participant.profilePicture}
      "
        alt="${participant.fullName}'s picture"
        class="participant-picture-split-page"
      />
    `;

    addIcon.insertAdjacentHTML("beforebegin", participantPicture);
  });

  totalValueToSplit.innerHTML = insertComma(valueToSplit);
  displayParticipantsSplits(
    participantsContainer,
    selectedParticipants,
    initialPercentage,
    initialSplitValue,
    valueToSplit
  );

  completeSplitButton.addEventListener("click", async (event) => {
    event.preventDefault();

    const errorContainer = document.getElementById("split-error-message");
    const errorTitle = document.querySelector(".error-message-title");
    const errorMessage = document.querySelector(".error-message");

    errorContainer.addEventListener("click", () => {
      gsap.to(errorContainer, {
        opacity: 0,
        duration: 1,
        ease: "power2.inOut",
        onComplete: () => {
          errorContainer.style.display = "none";
        },
      });
    });

    const valueInputs = document.querySelectorAll(".raw-value");
    const percentageInputs = document.querySelectorAll(".percentage-value");
    const participantNames = document.querySelectorAll(
      ".participant-percentage-value-container > p"
    );

    let distributedValue = 0;
    valueInputs.forEach((input) => {
      distributedValue += parseFloat(input.value.replace(",", "."));
    });

    if (distributedValue < valueToSplit) {
      gsap.fromTo(
        completeSplitButton,
        { x: -5 },
        {
          x: 5,
          duration: 0.1,
          repeat: 5,
          yoyo: true,
          ease: "power2.inOut",
          onComplete: () => {
            gsap.set(completeSplitButton, { x: 0 });
          },
        }
      );

      gsap.fromTo(
        displayedTotalValue,
        { x: -5 },
        {
          x: 5,
          duration: 0.1,
          repeat: 5,
          yoyo: true,
          ease: "power2.inOut",
          onComplete: () => {
            gsap.set(displayedTotalValue, { x: 0 });
          },
        }
      );

      errorTitle.innerHTML =
        "Ops! Parece que o valor não foi completamente distribuído.";

      errorMessage.innerHTML =
        "Certifique-se de que todos os participantes receberam sua parte.";

      gsap
        .timeline()
        .fromTo(
          errorContainer,
          { display: "none", opacity: 0 },
          {
            display: "block",
            opacity: 1,
            ease: "power2.inOut",
            duration: 1,
          }
        )
        .to(errorContainer, {
          opacity: 0,
          duration: 1,
          ease: "power2.inOut",
          delay: 3,
          onComplete: () => {
            errorContainer.style.display = "none";
          },
        });

      return;
    }

    if (billNameInput.value === "") {
      gsap.fromTo(
        billNameContainer,
        { x: -5 },
        {
          x: 5,
          duration: 0.1,
          repeat: 5,
          yoyo: true,
          ease: "power2.inOut",
          onComplete: () => {
            gsap.set(billNameContainer, { x: 0 });
          },
        }
      );

      errorTitle.innerHTML = "Ops! Parece que o nome da despesa está vazio.";

      errorMessage.innerHTML =
        "Por favor, insira um nome para a despesa antes de prosseguir.";

      gsap
        .timeline()
        .fromTo(
          errorContainer,
          { display: "none", opacity: 0 },
          {
            display: "block",
            opacity: 1,
            ease: "power2.inOut",
            duration: 1,
          }
        )
        .to(errorContainer, {
          opacity: 0,
          duration: 1,
          ease: "power2.inOut",
          delay: 3,
          onComplete: () => {
            errorContainer.style.display = "none";
          },
        });

      return;
    }

    const participants = [];

    selectedParticipants.forEach((participant, index) => {
      const valueToPay = valueInputs[index]?.value || 0;
      const percentageToPay = percentageInputs[index]?.value || 0;
      const username = participant.username;
      console.log(username);

      const participantData = {
        username: username,
        valueToPay: parseFloat(valueToPay.replace(",", ".")),
        percentage: parseFloat(percentageToPay.replace("%", "")),
      };

      participants.push(participantData);
    });

    participants[0].username = currentUserData.username;

    console.log(participants);

    const billData = {
      title: billNameInput.value,
      totalCost: valueToSplit,
      dueDate: billDateInput.value,
      idExpenseCreator: currentUserData.username,
      expenseDetails: participants,
    };

    createExpense(billData);

    gsap.fromTo(
      billSplitingPage,
      { top: 0 },
      {
        top: window.innerHeight,
        duration: 0.4,
        ease: "power2.inOut",
        onComplete: () => {
          element.innerHTML = "";
          displayHome(element, currentUserData);
        },
      }
    );

    gsap.fromTo(
      completeSplitButton,
      { top: window.innerHeight - 6 * 16 },
      {
        top: 400,
        duration: 0.5,
        ease: "power2.inOut",
      }
    );
  });
}

function displayParticipantsSplits(
  element,
  selectedParticipantsArray,
  initialPercentage,
  initialSplitValue,
  valueToSplit
) {
  element.innerHTML = createParticipantsSplits(
    selectedParticipants,
    initialPercentage,
    initialSplitValue
  );

  const valueInputs = document.querySelectorAll(".raw-value");
  const sliders = document.querySelectorAll(".participant-range");
  const percentageInputs = document.querySelectorAll(".percentage-value");
  const lockButtons = document.querySelectorAll(".lock-participant");

  lockButtons.forEach((button, index) => {
    button.addEventListener("click", (event) => {
      event.preventDefault();
      sliders[index].classList.toggle("disabled-slider");
      button.classList.toggle("disable");
      valueInputs[index].disabled = !valueInputs[index].disabled;
      sliders[index].disabled = !sliders[index].disabled;
      percentageInputs[index].disabled = !percentageInputs[index].disabled;

      const lockImg = button.querySelector("img");

      if (button.classList.contains("disable")) {
        lockImg.src = closedLock;
      } else {
        lockImg.src = openLock;
      }
    });
  });

  valueInputs.forEach((input, index) => {
    input.addEventListener("input", (event) => {
      let value = event.target.value.replace(/\D/g, "");

      if (value.length === 0) {
        value = "00";
      } else if (value.length === 1) {
        value = "0" + value;
      }

      value = (parseInt(value) / 100).toFixed(2).replace(".", ",");
      input.value = value;

      const newValue = removeComma(event.target.value);
      const newPercentage = calculatePercentage(valueToSplit, newValue);
      gsap.to(sliders[index], {
        value: parseInt(newPercentage),
        duration: 0.3,
        ease: "power2.out",
      });
      percentageInputs[index].value = `${parseInt(newPercentage)}%`;

      let lockedValueTotal = 0;
      valueInputs.forEach((input, i) => {
        if (input.disabled) {
          lockedValueTotal += removeComma(input.value);
        }
      });

      const remainingValue = valueToSplit - newValue - lockedValueTotal;
      const unlockedInputs = Array.from(valueInputs).filter(
        (input, i) => i !== index && !input.disabled
      );
      const unlockedSliders = Array.from(sliders).filter(
        (slider, i) => i !== index && !slider.disabled
      );
      const unlockedPercentageInputs = Array.from(percentageInputs).filter(
        (input, i) => i !== index && !input.disabled
      );
      const otherValue = remainingValue / unlockedInputs.length;

      unlockedInputs.forEach((otherInput, i) => {
        otherInput.value = otherValue.toFixed(2).replace(".", ",");
        const updatedValue = removeComma(otherInput.value) || 0;
        const updatedPercentage = calculatePercentage(
          valueToSplit,
          updatedValue
        );

        gsap.to(unlockedSliders[i], {
          value: parseInt(updatedPercentage),
          duration: 0.3,
          ease: "power2.out",
          onUpdate: () => {
            unlockedPercentageInputs[i].value = `${parseInt(
              updatedPercentage
            )}%`;
          },
        });
      });
    });
  });

  sliders.forEach((slider, index) => {
    slider.addEventListener("input", (event) => {
      let percentageValue = parseFloat(event.target.value);

      let lockedPercentage = 0;
      sliders.forEach((input, i) => {
        if (input.disabled) {
          lockedPercentage += parseInt(input.value);
        }
      });

      const remainingPercentage = 100 - lockedPercentage;
      let newValue = calculateValueFromPercentage(
        valueToSplit,
        percentageValue
      );

      if (percentageValue > remainingPercentage) {
        event.target.value = remainingPercentage;
        newValue = calculateValueFromPercentage(
          valueToSplit,
          remainingPercentage
        );
      }

      let lockedValueTotal = 0;
      valueInputs.forEach((input, i) => {
        if (input.disabled) {
          lockedValueTotal += removeComma(input.value);
        }
      });

      if (lockedValueTotal + newValue > valueToSplit) {
        const maxValue = valueToSplit - lockedValueTotal;
        valueInputs[index].value = maxValue.toFixed(2).replace(".", ",");
      } else {
        valueInputs[index].value = newValue.toFixed(2).replace(".", ",");
      }

      percentageInputs[index].value = `${event.target.value}%`;

      let remainingValue = valueToSplit - lockedValueTotal - newValue;
      remainingValue = Math.max(remainingValue, 0);

      const unlockedInputs = Array.from(valueInputs).filter(
        (input, i) => i !== index && !input.disabled
      );

      const notSelectedUnlockedSliders = Array.from(sliders).filter(
        (slider, i) => i !== index && !slider.disabled
      );
      const unlockedPercentageInputs = Array.from(percentageInputs).filter(
        (input, i) => i !== index && !input.disabled
      );

      const otherValue = remainingValue / unlockedInputs.length;

      unlockedInputs.forEach((otherInput, i) => {
        const exactValue = otherValue.toFixed(2);
        otherInput.value = exactValue.replace(".", ",");
        const updatedValue = removeComma(exactValue) || 0;
        const updatedPercentage = calculatePercentage(
          valueToSplit,
          updatedValue
        );

        const cappedPercentage = Math.max(updatedPercentage, 0);

        gsap.to(notSelectedUnlockedSliders[i], {
          value: parseInt(cappedPercentage),
          duration: 0.3,
          ease: "power2.out",
          onUpdate: () => {
            unlockedPercentageInputs[i].value = `${parseInt(
              cappedPercentage
            )}%`;
          },
        });
      });
    });
  });

  percentageInputs.forEach((input, index) => {
    input.addEventListener("input", (event) => {
      let percentageValue = event.target.value.replace(/\D/g, "");

      const newValue = calculateValueFromPercentage(
        valueToSplit,
        percentageValue
      );

      gsap.to(sliders[index], {
        value: parseInt(percentageValue),
        duration: 0.3,
        ease: "power2.out",
      });

      valueInputs[index].value = newValue.toFixed(2).replace(".", ",");

      let lockedValueTotal = 0;
      valueInputs.forEach((input, i) => {
        if (input.disabled) {
          lockedValueTotal += removeComma(input.value);
        }
      });

      const remainingValue = valueToSplit - newValue - lockedValueTotal;
      console.log(remainingValue);

      const unlockedInputs = Array.from(valueInputs).filter(
        (input, i) => i !== index && !input.disabled
      );
      const unlockedSliders = Array.from(sliders).filter(
        (slider, i) => i !== index && !slider.disabled
      );
      const unlockedPercentageInputs = Array.from(percentageInputs).filter(
        (input, i) => i !== index && !input.disabled
      );
      const otherValue = remainingValue / unlockedInputs.length;

      unlockedInputs.forEach((otherInput, i) => {
        otherInput.value = otherValue.toFixed(2).replace(".", ",");
        const updatedValue = removeComma(otherInput.value) || 0;
        const updatedPercentage = calculatePercentage(
          valueToSplit,
          updatedValue
        );

        gsap.to(unlockedSliders[i], {
          value: parseInt(updatedPercentage),
          duration: 0.3,
          ease: "power2.out",
          onUpdate: () => {
            unlockedPercentageInputs[i].value = `${parseInt(
              updatedPercentage
            )}%`;
          },
        });
      });
    });

    input.addEventListener("focus", (event) => {
      if (event.target.value.endsWith("%")) {
        event.target.value = event.target.value.slice(0, -1);
      }
    });

    input.addEventListener("blur", (event) => {
      if (!event.target.value.endsWith("%")) {
        event.target.value = event.target.value + "%";
      }
    });
  });
}

function createParticipantsSplits(
  selectedParticipantsArray,
  initialPercentage,
  initialSplitValue
) {
  let participantList = "";

  selectedParticipants.forEach((participant) => {
    participantList += `
      <div class="participant-split">
        <div class="upper">
          <div class="upper-left">
            <img
              class="participant-picture-split"
              src="${participant.profilePicture}"
              alt=""
            />
            <div class="participant-percentage-value-container">
              <p>${getFirstName(participant.fullName)} deve</p>
              <input class="percentage-value" type="text" value="${parseInt(
                initialPercentage
              )}%" />
            </div>
          </div>
          <div class="participant-raw-value-container">
            <input type="text" class="raw-value" value="${initialSplitValue}" />
          </div>
        </div>
        <div class="lower">
          <input
            class="participant-range"
            type="range"
            min="0"
            max="100"
            step="1"
            value="${parseInt(initialPercentage)}"
          />
          <button class="lock-participant">
            <img src="${openLock}" alt="" />
          </button>
        </div>
      </div>
    `;
  });

  return participantList;
}

function createNotificationScreen() {
  return `
    <div>
        <header id="bills-history-header">
          <div class="bills-history-screen-label">
            <button class="add-friend-back-button">
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
            <p>Notificações</p>
          </div>
        </header>
        <div id="notifications-container">
          
        </div>
      </div>
  `;
}

async function displayNotificationScreen(
  element,
  currentUserData,
  notificationBills
) {
  element.innerHTML = createNotificationScreen();

  const backToHomeButton = document.querySelector(".add-friend-back-button");
  const notificationsContainer = document.getElementById(
    "notifications-container"
  );

  backToHomeButton.addEventListener("click", () => {
    goBackToHome(element, currentUserData);
  });
  console.log(notificationBills);

  notificationBills.forEach((bill) => {
    notificationsContainer.innerHTML += `
      <div class="notification">
        <div class="notification-left">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="32"
            height="32"
            fill="#3b82f6"
            viewBox="0 0 256 256"
          >
            <path
              d="M128,24A104,104,0,1,0,232,128,104.11,104.11,0,0,0,128,24Zm0,192a88,88,0,1,1,88-88A88.1,88.1,0,0,1,128,216Zm-8-80V80a8,8,0,0,1,16,0v56a8,8,0,0,1-16,0Zm20,36a12,12,0,1,1-12-12A12,12,0,0,1,140,172Z"
            ></path>
          </svg>
          <div>
            <p class="notification-title">Lembrete</p>
            <p class="notification-message">
              A despesa
              <span class="notification-bill-name">${bill.title}</span> vence
              hoje!
            </p>
          </div>
        </div>
        <div class="notification-circle"></div>
      </div>
    `;
  });
}

async function getExpireTodayBills(currentUserData) {
  const userBills = await getAllExpenses(currentUserData.username);

  const today = new Date().toISOString().split("T")[0];

  const dueTodayBills = userBills.filter((bill) => bill.dueDate === today);

  return dueTodayBills;
}
