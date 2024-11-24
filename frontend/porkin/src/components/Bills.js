import { displayBillsNavBar } from "./NavigationBar";
import plus from "../../public/svg/plus.svg";
import minus from "../../public/svg/minus.svg";
import { bills, registeredUsers, userData } from "./UserData";
import {
  adjustParentHeight,
  formatDate,
  getFirstName,
  insertComma,
} from "../utils/nameHelper";
import gsap from "gsap";

function createBillsScreen() {
  return `
    <div>
      <header id="friends-header">
        <p>Despesas</p>
      </header>
      <button id="history-button">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="24"
          height="24"
          fill="#000000"
          viewBox="0 0 256 256"
          data-darkreader-inline-fill=""
          style="--darkreader-inline-fill: #000000"
        >
          <path
            d="M134,80v44.6l37.09,22.25a6,6,0,0,1-6.18,10.3l-40-24A6,6,0,0,1,122,128V80a6,6,0,0,1,12,0Zm-6-46A93.4,93.4,0,0,0,61.51,61.56c-8.58,8.68-16,17-23.51,25.8V64a6,6,0,0,0-12,0v40a6,6,0,0,0,6,6H72a6,6,0,0,0,0-12H44.73C52.86,88.29,60.79,79.35,70,70a82,82,0,1,1,1.7,117.62,6,6,0,1,0-8.24,8.72A94,94,0,1,0,128,34Z"
          ></path>
        </svg>
        Ver histórico de despesas
      </button>
      <div id="bills-container">
        
      </div>
    </div>
  `;
}

export function displayBillsScreen(element) {
  element.innerHTML = createBillsScreen();
  displayBillsNavBar(element);

  const billsContainer = document.getElementById("bills-container");

  displayBillsList(billsContainer);
}

function displayBillsList(element) {
  bills.list.forEach((bill, index) => {
    const admin = registeredUsers.list.find(
      (user) => user.username === bill.admin
    );
    const parentContainer = document.getElementById("content");

    const participantsPictures = [];

    bill.participants.forEach((participant) => {
      const user = registeredUsers.list.find(
        (user) => user.username === participant.username
      );
      participantsPictures.push(`
        <img src="${user.profilePicture}" />  
      `);
    });

    element.innerHTML += `
      <div class="bill">
        <div class="admin-bill-name-value">
          <div>
            <img
              src="${admin.profilePicture}"
              alt=""
            />
            <p class="bill-name">${bill.billName}</p>
          </div>
          <div class="participant-value-container">
            <p class="participant-value-currency">R$</p>
            <p class="participant-value-quantity">${insertComma(
              bill.totalValue
            )}</p>
          </div>
        </div>
        <p class="pay-date">Pagar até ${formatDate(bill.payUntil)} via ${
      bill.method
    }</p>
        <div class="pay-button-and-participants">
          <button class="pay-button" data-username="${userData.username}">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="20"
              height="20"
              fill="#000000"
              viewBox="0 0 256 256"
              data-darkreader-inline-fill=""
              style="--darkreader-inline-fill: #000000"
            >
              <path
                d="M190,116a10,10,0,1,1-10-10A10,10,0,0,1,190,116ZM152,66H112a6,6,0,0,0,0,12h40a6,6,0,0,0,0-12Zm94,46v32a22,22,0,0,1-22,22h-3.77l-16.68,46.71A14,14,0,0,1,190.36,222H177.64a14,14,0,0,1-13.19-9.29L162.06,206H101.94l-2.39,6.71A14,14,0,0,1,86.36,222H73.64a14,14,0,0,1-13.19-9.29L47.76,177.18a85.72,85.72,0,0,1-21.47-50.24A18,18,0,0,0,14,144a6,6,0,0,1-12,0,30,30,0,0,1,24.19-29.43A86.1,86.1,0,0,1,112,34H216a6,6,0,0,1,0,12H187.82a85.92,85.92,0,0,1,35.12,39.83c.6,1.38,1.16,2.77,1.68,4.18A22,22,0,0,1,246,112Zm-12,0a10,10,0,0,0-10-10h-3.66a6,6,0,0,1-5.73-4.2,71.4,71.4,0,0,0-2.68-7.19A74,74,0,0,0,144,46H112A74,74,0,0,0,57.44,170a5.81,5.81,0,0,1,1.22,2l13.09,36.64A2,2,0,0,0,73.64,210H86.36a2,2,0,0,0,1.89-1.33L92.06,198a6,6,0,0,1,5.65-4h68.58a6,6,0,0,1,5.65,4l3.81,10.69a2,2,0,0,0,1.89,1.33h12.72a2,2,0,0,0,1.89-1.33L210.35,158a6,6,0,0,1,5.65-4h8a10,10,0,0,0,10-10Z"
              ></path>
            </svg>
            Quitar dívida
          </button>
          <div class="bill-participant-pictures">
            
          </div>
        </div>
        <div class="details">
          <div class="bill-line"></div>
          <div data-value="${admin.pix}" class="details-payment">
            <p><span>Pix:</span> ${admin.pix}</p>
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="20"
              height="20"
              fill="#02061740"
              viewBox="0 0 256 256"
              data-darkreader-inline-fill=""
              style="--darkreader-inline-fill: #000000"
            >
              <path
                d="M216,34H88a6,6,0,0,0-6,6V82H40a6,6,0,0,0-6,6V216a6,6,0,0,0,6,6H168a6,6,0,0,0,6-6V174h42a6,6,0,0,0,6-6V40A6,6,0,0,0,216,34ZM162,210H46V94H162Zm48-48H174V88a6,6,0,0,0-6-6H94V46H210Z"
              ></path>
            </svg>
          </div>
          <div class="people-who-payed">
            
          </div>
        </div>
        <div class="bill-line"></div>

        <button class="see-details">
          <img src="${plus}" alt="" /><p>Ver mais detalhes</p>
        </button>
      </div>
    `;

    const billParticipantPictures = document.querySelectorAll(
      ".bill-participant-pictures"
    );
    const details = document.querySelectorAll(".details");
    const seeDetailsButtons = document.querySelectorAll(".see-details");
    const seeDetailsButtonImages =
      document.querySelectorAll(".see-details > img");
    const seeDetailsButtonTexts = document.querySelectorAll(".see-details > p");
    const peopleWhoPayedContainers =
      document.querySelectorAll(".people-who-payed");
    const payBillButtons = document.querySelectorAll(".pay-button");
    const paymentDetails = document.querySelectorAll(".details-payment");

    paymentDetails.forEach((pix, index) => {
      pix.addEventListener("click", () => {
        const pixToCopy = pix.dataset.value;

        navigator.clipboard
          .writeText(pixToCopy)
          .then(() => {
            console.log("Pix copied to clipboard:", pixToCopy);
          })
          .catch((err) => {
            console.error("Failed to copy Pix:", err);
          });
      });
    });

    payBillButtons.forEach((button) => {});

    participantsPictures.forEach((picture) => {
      billParticipantPictures[index].innerHTML += picture;
    });

    bill.participants.forEach((participant) => {
      const user = registeredUsers.list.find(
        (user) => user.username === participant.username
      );

      peopleWhoPayedContainers[index].innerHTML += `
      <div class="person">
        <div class="person-div-1">
          <img
            src="${user.profilePicture}"
            alt=""
          />
          <p>${getFirstName(user.fullName)} ${
        participant.status === "paid" ? "pagou" : "deve"
      }</p>
        </div>
        <div class="person-div-2">
          <p class="person-currency">R$</p>
          <p class="person-value">${insertComma(participant.valueToPay)}</p>
        </div>
      </div>
      `;
    });

    seeDetailsButtons.forEach((button, index) => {
      button.addEventListener("click", () => {
        if (details[index].style.display === "block") {
          gsap.to(details[index], {
            height: 0,
            opacity: 0,
            duration: 0.3,
            ease: "power2.out",
            onComplete: () => {
              details[index].style.display = "none";
            },
          });

          seeDetailsButtonImages[index].src = plus;
          seeDetailsButtonTexts[index].textContent = "Ver mais detalhes";
        } else {
          details[index].style.display = "block";
          gsap.fromTo(
            details[index],
            { height: 0, opacity: 0 },
            {
              height: "auto",
              opacity: 1,
              duration: 0.3,
              ease: "power2.out",
              onComplete: () => {
                details[index].style.display = "block";
              },
            }
          );
          seeDetailsButtonImages[index].src = minus;
          seeDetailsButtonTexts[index].textContent = "Ver menos detalhes";
        }
      });
    });
  });
}
