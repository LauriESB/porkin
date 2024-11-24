import { displayFriendsNavBar } from "./NavigationBar.js";
import {
  friendRequests,
  friends,
  registeredUsers,
  userData,
} from "./UserData.js";
import transparentIcon from "../../public/images/porkin-transparent.png";
import gsap from "gsap";
import { getFirstAndLastName } from "../utils/nameHelper.js";

function createFriendsScreen() {
  return `
    <div>
      <header id="friends-header">
        <p>Amigos</p>
      </header>
      <div id="friends-main-buttons-container">
        <button id="friend-requests-button">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            fill="#020617"
            viewBox="0 0 256 256"
            data-darkreader-inline-fill=""
            style="--darkreader-inline-fill: #000000"
          >
            <path
              d="M224,50H32a6,6,0,0,0-6,6V192a14,14,0,0,0,14,14H216a14,14,0,0,0,14-14V56A6,6,0,0,0,224,50Zm-96,85.86L47.42,62H208.58ZM101.67,128,38,186.36V69.64Zm8.88,8.14L124,148.42a6,6,0,0,0,8.1,0l13.4-12.28L208.58,194H47.43ZM154.33,128,218,69.64V186.36Z"
            ></path>
          </svg>
          Solicitações de amizade
        </button>
        <button id="add-friends-button">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 24 24"
            width="20"
            height="20"
            fill="#F8FAFC"
          >
            <path
              d="M14 14.252V16.3414C13.3744 16.1203 12.7013 16 12 16C8.68629 16 6 18.6863 6 22H4C4 17.5817 7.58172 14 12 14C12.6906 14 13.3608 14.0875 14 14.252ZM12 13C8.685 13 6 10.315 6 7C6 3.685 8.685 1 12 1C15.315 1 18 3.685 18 7C18 10.315 15.315 13 12 13ZM12 11C14.21 11 16 9.21 16 7C16 4.79 14.21 3 12 3C9.79 3 8 4.79 8 7C8 9.21 9.79 11 12 11ZM18 17V14H20V17H23V19H20V22H18V19H15V17H18Z"
            ></path>
          </svg>
          Adicionar amigos
        </button>
        <div class="buttons-line"></div>
      </div>
      <div class="no-friends-added">
        <img src="${transparentIcon}" alt="" />
        <p>Ops! Parece que sua lista de amigos está vazia :(</p>
      </div>
      <div id="friends-screen-container"></div>
    </div>
  `;
}

export function displayFriendsScreen(element) {
  element.innerHTML = createFriendsScreen();
  displayFriendsNavBar(element);
  const friendsContainer = document.getElementById("friends-screen-container");
  const noFriendsWarning = document.querySelector(".no-friends-added");
  const addFriendButton = document.getElementById("add-friends-button");
  const friendRequestsButton = document.getElementById(
    "friend-requests-button"
  );

  if (friends.list.length === 0) {
    noFriendsWarning.style.display = "flex";
  } else {
    noFriendsWarning.style.display = "";
    displayFriendsScreenList(friendsContainer);
  }

  addFriendButton.addEventListener("click", () => {
    element.innerHTML = "";
    displayAddFriendScreen(element);
  });

  friendRequestsButton.addEventListener("click", () => {
    element.innerHTML = "";
    displayFriendRequests(element);
  });
}

function displayFriendsScreenList(element) {
  friends.list.forEach((friend) => {
    element.innerHTML += `
      <div class="friends-screen-friend">
        <div>
          <img
            src="${friend.profilePicture}"
            alt="${friend.fullName}'s picture"
          />
          <div class="name-and-username">
            <p class="friend-name">${friend.fullName}</p>
            <p class="friend-username">@${friend.username}</p>
          </div>
        </div>
        <button class="delete-friend-button" data-username="${friend.username}">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 24 24"
            fill="#02061740"
            width="24"
            height="24"
          >
            <path
              d="M14 14.252V16.3414C13.3744 16.1203 12.7013 16 12 16C8.68629 16 6 18.6863 6 22H4C4 17.5817 7.58172 14 12 14C12.6906 14 13.3608 14.0875 14 14.252ZM12 13C8.685 13 6 10.315 6 7C6 3.685 8.685 1 12 1C15.315 1 18 3.685 18 7C18 10.315 15.315 13 12 13ZM12 11C14.21 11 16 9.21 16 7C16 4.79 14.21 3 12 3C9.79 3 8 4.79 8 7C8 9.21 9.79 11 12 11ZM19 17.5858L21.1213 15.4645L22.5355 16.8787L20.4142 19L22.5355 21.1213L21.1213 22.5355L19 20.4142L16.8787 22.5355L15.4645 21.1213L17.5858 19L15.4645 16.8787L16.8787 15.4645L19 17.5858Z"
            ></path>
          </svg>
        </button>
      </div>
    `;
  });

  const deleteFriendButtons = document.querySelectorAll(
    ".delete-friend-button"
  );

  deleteFriendButtons.forEach((button) => {
    button.addEventListener("click", () => {
      const friendToRemove = button.dataset.username;
      const removalRequest = {
        user: userData.username,
        friendToRemove: friendToRemove,
      };
      console.log(removalRequest);
      element.innerHTML = "";
      displayFriendsScreenList(element);
    });
  });
}

function createAddFriendScreen() {
  return `
  <div>
    <header id="add-friend-header">
      <div class="add-friend-screen-label">
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
        <p>Adicionar amigos</p>
      </div>
      <div class="search-users-input-container">
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
          placeholder="Procurar usuários"
          class="search-user-input"
        />
      </div>
      <div class="add-friend-line"></div>
    </header>
    <div class="no-users-searched">
      <img src="${transparentIcon}" alt="" />
      <p>Pesquise por seus amigos e aumente sua rede :)</p>
    </div>
    <div class="no-users-found">
      <img src="${transparentIcon}" alt="" />
      <p>Não encontramos nenhum usuário com esse nome :(</p>
    </div>
    <div id="users-result">
      
    </div>
  </div>
  `;
}

function displayAddFriendScreen(element) {
  element.innerHTML = createAddFriendScreen();

  const searchedUsersContainer = document.getElementById("users-result");
  const searchUserInput = document.querySelector(".search-user-input");
  const noUsersSearched = document.querySelector(".no-users-searched");
  const noUsersFound = document.querySelector(".no-users-found");
  const addFriendBackButton = document.querySelector(".add-friend-back-button");
  const mainContainer = document.querySelector("#content > div");

  addFriendBackButton.addEventListener("click", () => {
    gsap.fromTo(
      mainContainer,
      { left: 0 },
      {
        left: window.innerWidth,
        duration: 0.2,
        ease: "power1.inOut",
      }
    );
    setTimeout(() => displayFriendsScreen(element), 200);
  });

  gsap.fromTo(
    mainContainer,
    { left: window.innerWidth },
    {
      left: 0,
      duration: 0.2,
      ease: "power2.inOut",
    }
  );

  if (searchUserInput.value === "") {
    searchedUsersContainer.innerHTML = "";
    noUsersSearched.style.display = "flex";
  }

  searchUserInput.addEventListener("input", () => {
    if (searchUserInput.value === "") {
      searchedUsersContainer.innerHTML = "";
      noUsersFound.style.display = "none";
      noUsersSearched.style.display = "flex";
      return;
    }

    searchedUsersContainer.innerHTML = "";
    noUsersSearched.style.display = "none";
    const usersFound = registeredUsers.list.filter(
      (user) =>
        user.fullName
          .toLowerCase()
          .includes(searchUserInput.value.toLowerCase()) ||
        user.username
          .toLowerCase()
          .includes(searchUserInput.value.toLowerCase())
    );

    if (usersFound.length === 0) {
      searchedUsersContainer.innerHTML = "";
      noUsersFound.style.display = "flex";
      return;
    }

    noUsersFound.style.display = "none";

    usersFound.forEach((user) => {
      searchedUsersContainer.innerHTML += `
        <div class="user-searched">
          <div>
            <img
              src="${user.profilePicture}"
              alt=""
            />
            <div class="name-and-username">
              <p class="user-full-name">${user.fullName}</p>
              <p class="user-username">@${user.username}</p>
            </div>
          </div>
          <button class="invite-button" data-username="${user.username}">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              viewBox="0 0 24 24"
              fill="#02061740"
              width="24"
              height="24"
            >
              <path d="M14 14.252V16.3414C13.3744 16.1203 12.7013 16 12 16C8.68629 16 6 18.6863 6 22H4C4 17.5817 7.58172 14 12 14C12.6906 14 13.3608 14.0875 14 14.252ZM12 13C8.685 13 6 10.315 6 7C6 3.685 8.685 1 12 1C15.315 1 18 3.685 18 7C18 10.315 15.315 13 12 13ZM12 11C14.21 11 16 9.21 16 7C16 4.79 14.21 3 12 3C9.79 3 8 4.79 8 7C8 9.21 9.79 11 12 11ZM18 17V14H20V17H23V19H20V22H18V19H15V17H18Z"></path>
            </svg>
          </button>
        </div>
      `;
    });
  });
}

function createFriendRequestsScreen() {
  return `
    <div>
      <header id="friend-requests-header">
        <div class="friend-requests-screen-label">
          <button class="friend-requests-back-button">
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
          <p>Solicitações de amizade</p>
        </div>
      </header>
      <div class="no-requests">
        <img src="${transparentIcon}" alt="" />
        <p>Você não tem nenhuma solicitação de amizade pendente no momento</p>
      </div>
      <div id="friend-requests">
        
      </div>
    </div>
  `;
}

function displayFriendRequests(element) {
  element.innerHTML = createFriendRequestsScreen();

  const mainContainer = document.querySelector("#content > div");
  const friendRequestsBackButton = document.querySelector(
    ".friend-requests-back-button"
  );
  const friendRequestsContainer = document.getElementById("friend-requests");

  displayFriendRequestsList(friendRequestsContainer);

  gsap.fromTo(
    mainContainer,
    { left: window.innerWidth },
    {
      left: 0,
      duration: 0.2,
      ease: "power2.inOut",
    }
  );

  friendRequestsBackButton.addEventListener("click", () => {
    gsap.fromTo(
      mainContainer,
      { left: 0 },
      {
        left: window.innerWidth,
        duration: 0.2,
        ease: "power1.inOut",
      }
    );
    setTimeout(() => displayFriendsScreen(element), 200);
  });
}

function displayFriendRequestsList(element) {
  const noRequestsWarning = document.querySelector(".no-requests");

  if (friendRequests.list.length === 0) {
    noRequestsWarning.style.display = "flex";
    return;
  }

  noRequestsWarning.style.display = "none";

  friendRequests.list.forEach((request) => {
    const user = registeredUsers.list.find(
      (user) => user.username === request.sentBy
    );
    const currentDate = new Date();
    const requestDate = new Date(request.date);
    const timeDiff = Math.floor(
      (currentDate - requestDate) / (1000 * 60 * 60 * 24)
    );
    const timeAgo = `${timeDiff}d`;

    element.innerHTML += `
      <div class="friend-request">
        <div>
          <img
            src="${user.profilePicture}"
            alt="${user.fullName}'s picture"
          />
          <div class="name-and-username">
            <div class="full-name-and-date">
              <p class="user-full-name">${getFirstAndLastName(
                user.fullName
              )}</p>
              <p class="request-date">${timeAgo}</p>
            </div>
            <p class="user-username">@${user.username}</p>
          </div>
        </div>
        <div class="buttons-div">
          <button class="accept-request" data-username="${user.username}">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              viewBox="0 0 24 24"
              fill="#02061740"
              width="24"
              height="24"
            >
              <path
                d="M4 3H20C20.5523 3 21 3.44772 21 4V20C21 20.5523 20.5523 21 20 21H4C3.44772 21 3 20.5523 3 20V4C3 3.44772 3.44772 3 4 3ZM5 5V19H19V5H5ZM11.0026 16L6.75999 11.7574L8.17421 10.3431L11.0026 13.1716L16.6595 7.51472L18.0737 8.92893L11.0026 16Z"
              ></path>
            </svg>
          </button>
          <button class="refuse-request" data-username="${user.username}">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              viewBox="0 0 24 24"
              fill="#02061740"
              width="24"
              height="24"
            >
              <path
                d="M4 3H20C20.5523 3 21 3.44772 21 4V20C21 20.5523 20.5523 21 20 21H4C3.44772 21 3 20.5523 3 20V4C3 3.44772 3.44772 3 4 3ZM5 5V19H19V5H5ZM7 11H17V13H7V11Z"
              ></path>
            </svg>
          </button>
        </div>
      </div>
    `;
  });
}
