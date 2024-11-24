import porkinIcon from "../../public/images/porkin-icon.svg";
import { displayLoginScreen } from "./AuthScreen";

function createResetPasswordStepOne() {
  return `
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
          <p>Alterar senha</p>
        </div>
      </header>
      <div id="full-height">
        <div class="change-password-fields">
          <img src="${porkinIcon}" alt="" />
          <h1>Esqueceu sua senha?</h1>
          <label for="email-input"
            >Informe o seu e-mail cadastrado e enviaremos as instruções para
            você redefinir sua senha.</label
          >
          <div class="email-input-container reset-password-container">
            <input
              id="email-input"
              type="email"
              placeholder="Insira seu e-mail"
            />
          </div>
        </div>
        <button class="reset-password">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            fill="#F8FAFC"
            viewBox="0 0 256 256"
          >
            <path
              d="M222,48V96a6,6,0,0,1-6,6H168a6,6,0,0,1,0-12h33.52L183.47,72a81.51,81.51,0,0,0-57.53-24h-.46A81.5,81.5,0,0,0,68.19,71.28a6,6,0,1,1-8.38-8.58,93.38,93.38,0,0,1,65.67-26.76H126a93.45,93.45,0,0,1,66,27.53l18,18V48a6,6,0,0,1,12,0ZM187.81,184.72a81.5,81.5,0,0,1-57.29,23.34h-.46a81.51,81.51,0,0,1-57.53-24L54.48,166H88a6,6,0,0,0,0-12H40a6,6,0,0,0-6,6v48a6,6,0,0,0,12,0V174.48l18,18.05a93.45,93.45,0,0,0,66,27.53h.52a93.38,93.38,0,0,0,65.67-26.76,6,6,0,1,0-8.38-8.58Z"
            ></path>
          </svg>
          Redefinir senha
        </button>
        <div class="steps">
          <div class="step first-step selected"></div>
          <div class="step second-step"></div>
          <div class="step third-step"></div>
          <div class="step fourth-step"></div>
        </div>
      </div>
  `;
}

function createResetPasswordStepTwo() {
  return `
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
          <p>Alterar senha</p>
        </div>
      </header>
      <div id="full-height">
        <div class="change-password-fields">
          <img src="${porkinIcon}" alt="" />
          <h1>Cheque seu e-mail</h1>
          <label for="email-input"
            >Te enviamos um código de verificação para confirmar sua
            identidade.</label
          >
          <div class="email-input-container reset-password-container">
            <input id="email-input" type="text" placeholder="Insira o código" />
          </div>
          <p class="send-again">Enviar novamente</p>
        </div>
        <button class="reset-password">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            fill="#F8FAFC"
            viewBox="0 0 256 256"
          >
            <path
              d="M220.24,132.24l-72,72a6,6,0,0,1-8.48-8.48L201.51,134H40a6,6,0,0,1,0-12H201.51L139.76,60.24a6,6,0,0,1,8.48-8.48l72,72A6,6,0,0,1,220.24,132.24Z"
            ></path>
          </svg>
          Verificar
        </button>
        <div class="steps">
          <div class="step first-step"></div>
          <div class="step second-step selected"></div>
          <div class="step third-step"></div>
          <div class="step fourth-step"></div>
        </div>
      </div>
  `;
}

function createResetPasswordStepThree() {
  return `
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
          <p>Alterar senha</p>
        </div>
      </header>
      <div id="full-height">
        <div class="change-password-fields">
          <img src="${porkinIcon}" alt="" />
          <h1>Digite uma nova senha</h1>
          <label for="password-input"
            >Insira uma nova senha abaixo para voltar a ter acesso a sua
            conta.</label
          >
          <div class="password-input-container">
            <input
              id="password-input"
              type="password"
              placeholder="Insira uma nova senha"
            />
            <button id="show-password-button">
              <img src="../public/svg/eye-closed.svg" alt="" />
            </button>
          </div>
        </div>
        <button class="reset-password">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            fill="#F8FAFC"
            viewBox="0 0 256 256"
          >
            <path
              d="M228.24,76.24l-128,128a6,6,0,0,1-8.48,0l-56-56a6,6,0,0,1,8.48-8.48L96,191.51,219.76,67.76a6,6,0,0,1,8.48,8.48Z"
            ></path>
          </svg>
          Trocar senha
        </button>
        <div class="steps">
          <div class="step first-step"></div>
          <div class="step second-step"></div>
          <div class="step third-step selected"></div>
          <div class="step fourth-step"></div>
        </div>
      </div>
  `;
}

function createResetPasswordStepFour() {
  return `
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
          <p>Alterar senha</p>
        </div>
      </header>
      <div id="full-height">
        <div class="change-password-fields">
          <img src="${porkinIcon}" alt="" />
          <h1>Tudo certo!</h1>
          <p class="success-message">
            Sua senha foi alterada com sucesso! Volte para a tela de login para
            se conectar.
          </p>
        </div>
        <button class="reset-password">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            fill="#F8FAFC"
            viewBox="0 0 256 256"
          >
            <path
              d="M140.24,132.24l-40,40a6,6,0,0,1-8.48-8.48L121.51,134H24a6,6,0,0,1,0-12h97.51L91.76,92.24a6,6,0,0,1,8.48-8.48l40,40A6,6,0,0,1,140.24,132.24ZM200,34H136a6,6,0,0,0,0,12h58V210H136a6,6,0,0,0,0,12h64a6,6,0,0,0,6-6V40A6,6,0,0,0,200,34Z"
            ></path>
          </svg>
          Voltar para o login
        </button>
        <div class="steps">
          <div class="step first-step"></div>
          <div class="step second-step"></div>
          <div class="step third-step"></div>
          <div class="step fourth-step selected"></div>
        </div>
      </div>
  `;
}

export function displayResetPasswordStepOne(element) {
  element.innerHTML = createResetPasswordStepOne();

  const resetButton = document.querySelector(".reset-password");

  resetButton.addEventListener("click", () => {
    displayResetPasswordStepTwo(element);
  });
}

function displayResetPasswordStepTwo(element) {
  element.innerHTML = "";
  element.innerHTML = createResetPasswordStepTwo();

  const verifyButton = document.querySelector(".reset-password");

  verifyButton.addEventListener("click", () => {
    displayResetPasswordStepThree(element);
  });
}

function displayResetPasswordStepThree(element) {
  element.innerHTML = "";
  element.innerHTML = createResetPasswordStepThree();

  const changePasswordButton = document.querySelector(".reset-password");

  changePasswordButton.addEventListener("click", () => {
    displayResetPasswordStepFour(element);
  });
}

function displayResetPasswordStepFour(element) {
  element.innerHTML = "";
  element.innerHTML = createResetPasswordStepFour();

  const backToLoginButton = document.querySelector(".reset-password");

  backToLoginButton.addEventListener("click", () => {
    displayLoginScreen(element);
  });
}
