import eyeOpen from "../../public/svg/eye-open.svg";
import eyeClosed from "../../public/svg/eye-closed.svg";
import appleIcon from "../../public/images/apple-icon.png";
import facebookIcon from "../../public/images/facebook-icon.png";
import googleIcon from "../../public/images/google-icon.png";
import porkinIcon from "../../public/images/porkin-icon.svg";
import gsap from "gsap";
import { displayHome } from "./Home";
import { displayResetPasswordStepOne } from "./ResetPassword";
import { getUserData, login } from "../utils/requests";
import { createUser } from "../utils/requests";

function createLoginScreen() {
  return `
    <div id="login-page">
      <div id="hero">
        <img id="porkin-icon" src="${porkinIcon}" alt="porkin! icon" />
        <p id="hero-line-1">Bem-vindo ao porkin!</p>
        <p id="hero-line-2">Organize os gastos do rolê sem medo!</p>
      </div>
      <div class="socials-buttons">
        <button id="google-button">
          <img
            class="social-icon"
            src="${googleIcon}"
            alt="Google icon"
          />
        </button>
        <button id="apple-button">
          <img
            class="social-icon"
            src="${appleIcon}"
            alt="Apple icon"
          />
        </button>
        <button id="facebook-button">
          <img
            class="social-icon"
            src="${facebookIcon}"
            alt="Facebook icon"
          />
        </button>
      </div>
      <div class="or-lines">
        <div class="or-line"></div>
        <p>ou</p>
        <div class="or-line"></div>
      </div>
      <div id="login-info-container">
        <label for="email-input"> Nome de usuário </label>
        <div class="email-input-container">
          <input
            id="email-input"
            type="text"
            placeholder="Insira seu nome de usuário"
          />
        </div>
        <label for="password-input"> Senha </label>
        <div class="password-input-container">
          <input
            id="password-input"
            type="password"
            placeholder="Insira sua senha"
          />
          <button id="show-password-button"><img src="${eyeClosed}" alt="" /></button>
        </div>
        <button id="forgot-password-button">Esqueceu a senha?</button>
      </div>
      <div class="final-line"></div>
      <button id="login-button">Entrar</button>
      <button id="go-to-signup-button">
        Não tem uma conta?<span>Criar conta</span>
      </button>
    </div>
  `;
}

function createSignupScreen() {
  return `
    <div id="signup-page">
      <div id="hero">
        <img
          id="porkin-icon"
          src="${porkinIcon}"
          alt="porkin! icon"
        />
        <p id="hero-line-1">Bem-vindo ao porkin!</p>
        <p id="hero-line-2">Organize os gastos do rolê sem medo!</p>
      </div>
      <div class="socials-buttons">
        <button id="google-button">
          <img
            class="social-icon"
            src="${googleIcon}"
            alt="Google icon"
          />
        </button>
        <button id="apple-button">
          <img
            class="social-icon"
            src="${appleIcon}"
            alt="Apple icon"
          />
        </button>
        <button id="facebook-button">
          <img
            class="social-icon"
            src="${facebookIcon}"
            alt="Facebook icon"
          />
        </button>
      </div>
      <div class="or-lines">
        <div class="or-line"></div>
        <p>ou</p>
        <div class="or-line"></div>
      </div>
      <div id="signup-info-container">
        <label for="full-name-input"> Nome completo </label>
        <div class="full-name-input-container">
          <input
            id="full-name-input"
            type="text"
            placeholder="Insira seu nome completo"
          />
        </div>
        <label for="email-input"> Endereço de e-mail </label>
        <div class="email-input-container">
          <input
            id="email-input"
            type="email"
            placeholder="Insira seu e-mail"
          />
        </div>
        <label for="username-input"> Nome de usuário </label>
        <div class="username-input-container">
          <input
            id="username-input"
            type="text"
            placeholder="Insira um nome de usuário"
          />
        </div>
        <label for="password-input"> Senha </label>
        <div
          class="password-input-container password-input-container-from-signup-page"
        >
          <input
            id="password-input"
            type="password"
            placeholder="Insira sua senha"
          />
          <button id="show-password-button">
            <img src="${eyeClosed}" alt="" />
          </button>
        </div>
      </div>
      <div class="final-line"></div>
      <button id="signup-button">Criar conta</button>
      <button id="go-to-login-button">
        Já possui uma conta?<span>Entrar</span>
      </button>
    </div>
  `;
}

export function displayLoginScreen(element) {
  element.innerHTML = createLoginScreen();

  const showPasswordButton = document.getElementById("show-password-button");
  const showPasswordIcon = document.querySelector(
    "#show-password-button > img"
  );
  const passwordInput = document.getElementById("password-input");
  const goToSignUpButton = document.getElementById("go-to-signup-button");
  const mainContainer = document.getElementById("login-page");
  const loginButton = document.getElementById("login-button");
  const forgotPasswordButton = document.getElementById(
    "forgot-password-button"
  );
  const emailInput = document.getElementById("email-input");

  forgotPasswordButton.addEventListener("click", () => {
    displayResetPasswordStepOne(element);
  });

  showPasswordButton.addEventListener("click", () => {
    if (passwordInput.type === "password") {
      passwordInput.type = "text";
      showPasswordIcon.src = eyeOpen;
      return;
    }

    if (passwordInput.type === "text") {
      passwordInput.type = "password";
      showPasswordIcon.src = eyeClosed;
      return;
    }
  });

  goToSignUpButton.addEventListener("click", () => {
    displaySignupScreen(element);
  });

  loginButton.addEventListener("click", async () => {
    if (emailInput.value === "") {
      return;
    }
    if (passwordInput.value === "") {
      return;
    }

    const userObject = {
      username: emailInput.value,
      password: passwordInput.value,
    };

    try {
      const response = await login(userObject); // Assuming `login` returns a response with a token
      const token = response?.token; // Adjust this if the token key differs

      if (token) {
        localStorage.setItem("authToken", token); // Store the token in localStorage
        console.log("Token saved to localStorage:", token);
        const currentUserData = await getUserData(emailInput.value);
        gsap.fromTo(
          mainContainer,
          { right: 0 },
          {
            right: window.innerWidth,
            duration: 0.2,
            ease: "power1.inOut",
          }
        );
        setTimeout(() => {
          element.innerHTML = "";
          displayHome(element, currentUserData);
        }, 200);
      } else {
        console.error("Token not found in login response");
      }
    } catch (error) {
      console.error("Login failed:", error);
    }
  });
}
async function displaySignupScreen(element) {
  element.innerHTML = createSignupScreen();

  const goToLoginButton = document.getElementById("go-to-login-button");
  const mainContainer = document.getElementById("signup-page");
  const showPasswordButton = document.getElementById("show-password-button");
  const showPasswordIcon = document.querySelector(
    "#show-password-button > img"
  );
  const usernameInput = document.getElementById("username-input");
  const fullNameInput = document.getElementById("full-name-input");
  const passwordInput = document.getElementById("password-input");
  const emailInput = document.getElementById("email-input");
  const signupButton = document.getElementById("signup-button");

  goToLoginButton.addEventListener("click", () => {
    displayLoginScreen(element);
  });

  showPasswordButton.addEventListener("click", () => {
    if (passwordInput.type === "password") {
      passwordInput.type = "text";
      showPasswordIcon.src = eyeOpen;
      return;
    }

    if (passwordInput.type === "text") {
      passwordInput.type = "password";
      showPasswordIcon.src = eyeClosed;
      return;
    }
  });

  signupButton.addEventListener("click", async () => {
    const newUserFullName = fullNameInput.value;
    const newUserUsername = usernameInput.value;
    const newUserEmail = emailInput.value;
    const newUserPassword = passwordInput.value;

    const newUserData = {
      name: newUserFullName,
      username: newUserUsername,
      email: newUserEmail,
      password: newUserPassword,
    };

    try {
      await createUser(newUserData);

      const currentUserData = await getUserData(newUserUsername);
      localStorage.setItem("currentUser", JSON.stringify(currentUserData));

      gsap.fromTo(
        mainContainer,
        { right: 0 },
        {
          right: window.innerWidth,
          duration: 0.2,
          ease: "power1.inOut",
        }
      );

      setTimeout(() => {
        element.innerHTML = "";
        displayHome(element, currentUserData);
      }, 200);
    } catch (error) {
      console.error("Error during signup process:", error);
    }
  });
}
