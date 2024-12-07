import { displaySettingsNavBar } from "./NavigationBar";
import {
  getProfilePicture,
  getUserData,
  updateUserData,
  uploadProfilePicture,
} from "../utils/requests";
import { displayLoginScreen } from "./AuthScreen";
import { profilePictures } from "./UserData";
import editIcon from "../../public/svg/edit.svg";
import saveIcon from "../../public/svg/save.svg";

async function createSettingsScreen(currentUserData) {
  const currentUserProfilePicture = await getProfilePicture(
    currentUserData.username
  );
  return `
    <div>
      <header id="friends-header">
        <p>Configurações</p>
      </header>
      <div class="photo-section">
        <img
          src="${currentUserProfilePicture}"
          alt=""
        />
        <div>
          <input id="picture-input" type="file" accept="image/*">
          <button class="change-picture-button">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="20"
              height="20"
              fill="#f8fafc"
              viewBox="0 0 256 256"
            >
              <path
                d="M208,34H80A14,14,0,0,0,66,48V66H48A14,14,0,0,0,34,80V208a14,14,0,0,0,14,14H176a14,14,0,0,0,14-14V190h18a14,14,0,0,0,14-14V48A14,14,0,0,0,208,34ZM78,48a2,2,0,0,1,2-2H208a2,2,0,0,1,2,2v74.2l-20.1-20.1a14,14,0,0,0-19.8,0L94.2,178H80a2,2,0,0,1-2-2ZM178,208a2,2,0,0,1-2,2H48a2,2,0,0,1-2-2V80a2,2,0,0,1,2-2H66v98a14,14,0,0,0,14,14h98Zm30-30H111.17l67.41-67.41a2,2,0,0,1,2.83,0L210,139.17V176A2,2,0,0,1,208,178Zm-88-68A22,22,0,1,0,98,88,22,22,0,0,0,120,110Zm0-32a10,10,0,1,1-10,10A10,10,0,0,1,120,78Z"
              ></path>
            </svg>
            <p>Salvar</p>
            </button>
          </input>
        </div>
      </div>
      <div class="separation-line"></div>
      <div id="signup-info-container">
        <label for="username-input"> Nome de usuário </label>
        <div class="username-input-container transparent">
          <input
            id="username-input"
            type="text"
            value="${currentUserData.username}"
            class="transparent"
            disabled
          />
          <button id="edit-username-button">
            <img src="${editIcon}">
          </button>
          
        </div>
        <label for="full-name-input"> Nome completo </label>
        <div class="full-name-input-container transparent">
          <input
            id="full-name-input"
            type="text"
            value="${currentUserData.name}"
            class="transparent"
            disabled
          />
          <button id="edit-full-name-button">
            <img src="${editIcon}">
          </button>
        </div>
        <label for="email-input"> Endereço de e-mail </label>
        <div class="email-input-container transparent">
          <input
            id="email-input"
            type="email"
            value="${currentUserData.email}"
            class="transparent"
            disabled
          />
          <button id="edit-email-button">
            <img src="${editIcon}">
          </button>
        </div>
        <label for="email-input"> Chave Pix </label>
        <div class="pix-input-container transparent">
          <input
            id="pix-input"
            type="text"
            placeholder="Insira uma chave Pix"
            value="${currentUserData.pix ? currentUserData.pix : ""}"
            class="transparent"
            disabled
          />
          <button id="edit-pix-button">
            <img src="${editIcon}">
          </button>
        </div>
        <label for="email-input"> PayPal </label>
        <div class="paypal-input-container transparent">
          <input
            id="paypal-input"
            type="text"
            placeholder="Insira uma chave PayPal"
            value="${currentUserData.paypal ? currentUserData.paypal : ""}"
            class="transparent"
            disabled
          />
          <button id="edit-paypal-button">
            <img src="${editIcon}">
          </button>
        </div>
      </div>
      <button id="change-password-button">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="24"
          height="24"
          fill="#000000"
          viewBox="0 0 256 256"
        >
          <path
            d="M215.15,40.85A78,78,0,0,0,86.2,121.31l-56.1,56.1a13.94,13.94,0,0,0-4.1,9.9V216a14,14,0,0,0,14,14H72a6,6,0,0,0,6-6V206H96a6,6,0,0,0,6-6V182h18a6,6,0,0,0,4.24-1.76l10.45-10.44A77.59,77.59,0,0,0,160,174h.1A78,78,0,0,0,215.15,40.85ZM226,98.16c-1.12,35.16-30.67,63.8-65.88,63.84a65.93,65.93,0,0,1-24.51-4.67,6,6,0,0,0-6.64,1.26L117.51,170H96a6,6,0,0,0-6,6v18H72a6,6,0,0,0-6,6v18H40a2,2,0,0,1-2-2V187.31a2,2,0,0,1,.58-1.41l58.83-58.83a6,6,0,0,0,1.26-6.64A65.61,65.61,0,0,1,94,95.92C94,60.71,122.68,31.16,157.83,30A66,66,0,0,1,226,98.16ZM190,76a10,10,0,1,1-10-10A10,10,0,0,1,190,76Z"
          ></path>
        </svg>
        <p>Alterar senha</p>
      </button>
      <div class="separation-line"></div>
      <button id="logout-button">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="24"
          height="24"
          fill="#F8FAFC"
          viewBox="0 0 256 256"
        >
          <path
            d="M118,216a6,6,0,0,1-6,6H48a6,6,0,0,1-6-6V40a6,6,0,0,1,6-6h64a6,6,0,0,1,0,12H54V210h58A6,6,0,0,1,118,216Zm110.24-92.24-40-40a6,6,0,0,0-8.48,8.48L209.51,122H112a6,6,0,0,0,0,12h97.51l-29.75,29.76a6,6,0,1,0,8.48,8.48l40-40A6,6,0,0,0,228.24,123.76Z"
          ></path>
        </svg>
        <p>Fazer logout</p>
      </button>
      <button id="delete-account-button">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="24"
          height="24"
          fill="#EF4444"
          viewBox="0 0 256 256"
        >
          <path
            d="M236.8,188.09,149.35,36.22h0a24.76,24.76,0,0,0-42.7,0L19.2,188.09a23.51,23.51,0,0,0,0,23.72A24.35,24.35,0,0,0,40.55,224h174.9a24.35,24.35,0,0,0,21.33-12.19A23.51,23.51,0,0,0,236.8,188.09ZM222.93,203.8a8.5,8.5,0,0,1-7.48,4.2H40.55a8.5,8.5,0,0,1-7.48-4.2,7.59,7.59,0,0,1,0-7.72L120.52,44.21a8.75,8.75,0,0,1,15,0l87.45,151.87A7.59,7.59,0,0,1,222.93,203.8ZM120,144V104a8,8,0,0,1,16,0v40a8,8,0,0,1-16,0Zm20,36a12,12,0,1,1-12-12A12,12,0,0,1,140,180Z"
          ></path>
        </svg>
        <p>Excluir conta</p>
      </button>
    </div>
  `;
}

export async function displaySettingsScreen(element, currentUserData) {
  element.innerHTML = await createSettingsScreen(currentUserData);
  displaySettingsNavBar(element, currentUserData);

  const changePictureButton = document.querySelector(".change-picture-button");
  const imageInput = document.getElementById("picture-input");
  const logoutButton = document.getElementById("logout-button");

  const editUsernameButton = document.getElementById("edit-username-button");
  const editFullNameButton = document.getElementById("edit-full-name-button");
  const editEmailButton = document.getElementById("edit-email-button");
  const editPixButton = document.getElementById("edit-pix-button");
  const editPaypalButton = document.getElementById("edit-paypal-button");
  const editUsernameButtonImage = document.querySelector(
    "#edit-username-button > img"
  );
  const editFullNameButtonImage = document.querySelector(
    "#edit-full-name-button > img"
  );
  const editEmailButtonImage = document.querySelector(
    "#edit-email-button > img"
  );
  const editPixButtonImage = document.querySelector("#edit-pix-button > img");
  const editPaypalButtonImage = document.querySelector(
    "#edit-paypal-button > img"
  );
  const editUsernameInput = document.getElementById("username-input");
  const editFullNameInput = document.getElementById("full-name-input");
  const editEmailInput = document.getElementById("email-input");
  const editPixInput = document.getElementById("pix-input");
  const editPaypalInput = document.getElementById("paypal-input");

  editUsernameButton.addEventListener("click", async () => {
    if (editUsernameInput.disabled) {
      editUsernameInput.disabled = false;
      editUsernameButtonImage.src = saveIcon;
    } else {
      const newUsername = editUsernameInput.value;
      const userObject = currentUserData;

      userObject.username = newUsername;
      updateUserData(currentUserData.username, userObject);
      currentUserData = await getUserData(newUsername);

      editUsernameInput.disabled = true;
      editUsernameButtonImage.src = editIcon;
    }
  });

  editFullNameButton.addEventListener("click", async () => {
    if (editFullNameInput.disabled) {
      editFullNameInput.disabled = false;
      editFullNameButtonImage.src = saveIcon;
    } else {
      const newFullname = editFullNameInput.value;
      const userObject = currentUserData;

      userObject.name = newFullname;
      updateUserData(currentUserData.username, userObject);
      currentUserData = await getUserData(currentUserData.username);

      editFullNameInput.disabled = true;
      editFullNameButtonImage.src = editIcon;
    }
  });

  editEmailButton.addEventListener("click", async () => {
    if (editEmailInput.disabled) {
      editEmailInput.disabled = false;
      editEmailButtonImage.src = saveIcon;
    } else {
      const newEmail = editEmailInput.value;
      const userObject = currentUserData;

      userObject.email = newEmail;
      updateUserData(currentUserData.username, userObject);
      currentUserData = await getUserData(currentUserData.username);

      editEmailInput.disabled = true;
      editEmailButtonImage.src = editIcon;
    }
  });

  editPixButton.addEventListener("click", async () => {
    if (editPixInput.disabled) {
      editPixInput.disabled = false;
      editPixButtonImage.src = saveIcon;
    } else {
      const newPix = editPixInput.value;
      const userObject = currentUserData;

      userObject.pix = newPix;
      updateUserData(currentUserData.username, userObject);
      currentUserData = await getUserData(currentUserData.username);

      editPixInput.disabled = true;
      editPixButtonImage.src = editIcon;
    }
  });

  changePictureButton.addEventListener("click", () => {
    if (imageInput.files.length === 0) {
      return;
    }

    uploadProfilePicture(currentUserData.username, imageInput);
  });

  logoutButton.addEventListener("click", () => {
    const localStorageKey = "currentUser";

    localStorage.removeItem(localStorageKey);

    displayLoginScreen(element);
  });
}
