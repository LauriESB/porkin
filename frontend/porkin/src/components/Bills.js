import { displayBillsNavBar } from "./NavigationBar";
import plus from "../../public/svg/plus.svg";
import minus from "../../public/svg/minus.svg";
import { bills, profilePictures, registeredUsers, userData } from "./UserData";
import {
  adjustParentHeight,
  formatDate,
  getFirstName,
  insertComma,
} from "../utils/nameHelper";
import gsap from "gsap";
import {
  getAllExpenses,
  getAllUsers,
  getProfilePicture,
  payBill,
  deleteBill,
} from "../utils/requests";
import { all } from "axios";

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

export async function displayBillsScreen(element, currentUserData) {
  element.innerHTML = createBillsScreen();
  displayBillsNavBar(element, currentUserData);

  const billsContainer = document.getElementById("bills-container");
  const historyButton = document.getElementById("history-button");

  historyButton.addEventListener("click", () => {
    displayBillsHistory(element, currentUserData);
  });

  await displayBillsList(billsContainer, currentUserData);
}

async function displayBillsList(element, currentUserData) {
  const allBills = await getAllExpenses(currentUserData.username);
  const allUsers = await getAllUsers();

  // Filter bills to only include those where completed is false
  const incompleteBills = allBills.filter((bill) => !bill.completed);
  console.log(incompleteBills);

  for (let index = 0; index < incompleteBills.length; index++) {
    const bill = incompleteBills[index];
    const admin = allUsers.find(
      (user) => user.username === bill.idExpenseCreator
    );
    const parentContainer = document.getElementById("content");

    const participantsPictures = await Promise.all(
      bill.expenseDetails.map(async (participant) => {
        const user = allUsers.find(
          (user) => user.username === participant.username
        );
        const userProfilePicture = await getProfilePicture(user.username);
        return `<img src="${userProfilePicture}" />`;
      })
    );

    const isCurrentUserAdmin = admin.username === currentUserData.username;

    const adminProfilePicture = await getProfilePicture(admin.username);

    element.innerHTML += `
    <div class="delete-container">
          <button class="delete-bill-button" style="${
            isCurrentUserAdmin ? "color: var(--slate-950);" : ""
          }">
        <p class="hide-name" style="${
          isCurrentUserAdmin ? "color: var(--slate-950);" : ""
        }">Deletar ${bill.title}</p>
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="24"
          height="24"
          fill="${isCurrentUserAdmin ? "var(--slate-950)" : "#02061726"}"
          viewBox="0 0 256 256"
        >
          <path
            d="M204.24,195.76a6,6,0,1,1-8.48,8.48L128,136.49,60.24,204.24a6,6,0,0,1-8.48-8.48L119.51,128,51.76,60.24a6,6,0,0,1,8.48-8.48L128,119.51l67.76-67.75a6,6,0,0,1,8.48,8.48L136.49,128Z"
          ></path>
        </svg>
      </button>
        </div>
      <div class="bill">
        
        <div class="admin-bill-name-value">
          <div>
            <img
              src="${adminProfilePicture}"
              alt=""
            />
            <p class="bill-name">${bill.title}</p>
          </div>
          <div class="participant-value-container">
            <p class="participant-value-currency">R$</p>
            <p class="participant-value-quantity">${insertComma(
              bill.totalCost
            )}</p>
          </div>
        </div>
        <p class="pay-date">Pagar até ${formatDate(bill.dueDate)}
    </p>
        <div class="pay-button-and-participants">
          <button class="pay-button" data-username="${
            currentUserData.username
          }" data-id="${bill.id}" data-index="${index}">
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
          <button class="pay-button-finished hidden" data-username="${
            currentUserData.username
          }" data-id="${bill.id}" data-index="${index}">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="20"
              height="20"
              fill="#0ea5e9"
              viewBox="0 0 256 256"
            >
              <path
                d="M228.24,76.24l-128,128a6,6,0,0,1-8.48,0l-56-56a6,6,0,0,1,8.48-8.48L96,191.51,219.76,67.76a6,6,0,0,1,8.48,8.48Z"
              ></path>
            </svg>
            Dívida Paga
          </button>
          <div class="bill-participant-pictures">
            
          </div>
        </div>
        <div class="details">
          <div class="bill-line"></div>
          <div data-value="${admin.pix}" class="details-payment">
            <p><span>Pix:</span> ${
              admin.pix ? admin.pix : "Nenhuma chave cadastrada"
            }</p>
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
      </>
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
    const paidBillButtons = document.querySelectorAll(".pay-button-finished");
    const paymentDetails = document.querySelectorAll(".details-payment");

    const deleteBillButtons = document.querySelectorAll(".delete-bill-button");

    // Add event listeners to each button
    deleteBillButtons.forEach((button, index) => {
      const bill = allBills[index];
      const hideNameElement = button
        .closest(".delete-container")
        .querySelector(".hide-name"); // Adjust selector if necessary

      button.addEventListener("mouseenter", () => {
        hideNameElement.style.display = "block";
      });

      button.addEventListener("mouseleave", () => {
        hideNameElement.style.display = "none";
      });

      button.addEventListener("click", async () => {
        if (bill.idExpenseCreator === currentUserData.username) {
          const confirmation = confirm(
            `Are you sure you want to delete ${bill.title}?`
          );
          if (confirmation) {
            try {
              await deleteBill(bill.id);
              alert(`${bill.title} has been successfully deleted.`);
              button.closest(".bill").remove();
            } catch (error) {
              console.error("Error deleting bill:", error);
              alert("Failed to delete the bill. Please try again.");
            }
          }
        } else {
          alert("You do not have permission to delete this bill.");
        }
      });
    });

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

    const participant = bill.expenseDetails.find(
      (participant) => participant.username === currentUserData.username
    );

    if (participant.paid) {
      payBillButtons[index].classList.add("hidden");
      paidBillButtons[index].classList.remove("hidden");
    }

    participantsPictures.forEach((picture) => {
      billParticipantPictures[index].innerHTML += picture;
    });
    console.log(bill.expenseDetails);

    bill.expenseDetails.forEach(async (participant) => {
      const user = allUsers.find(
        (user) => user.username === participant.username
      );
      const userProfilePicture = await getProfilePicture(user.username);
      console.log(user);

      peopleWhoPayedContainers[index].innerHTML += `
      <div class="person">
        <div class="person-div-1">
          <img
            src="${userProfilePicture}"
            alt=""
          />
          <p>${getFirstName(user.name)} ${
        participant.paid ? "pagou" : "deve"
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
  }

  element.addEventListener("click", async (event) => {
    if (event.target.classList.contains("pay-button")) {
      const buttonIndex = event.target.dataset.index;
      const billId = event.target.dataset.id;

      const updatedBill = allBills[buttonIndex];
      const participant = updatedBill.expenseDetails.find(
        (participant) => participant.username === currentUserData.username
      );

      participant.paid = true;

      await payBill(updatedBill, billId);
    }
  });
}

function createBillsHistory() {
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
            <p>Histórico de despesas</p>
          </div>
        </header>
        <div id="bills-history-screen">
          <select name="bill-date-select" id="bill-date-select">
          </select>
          <div class="select-line"></div>
          <div id="total-spent">
            <p>Total gasto:</p>
            <div>
              <p class="total-spent-currency">R$</p>
              <p class="total-spent-value">00,00</p>
            </div>
          </div>
          <div id="history-container">
            
          </div>
        </div>
      </div>
  `;
}

async function displayBillsHistory(element, currentUserData) {
  element.innerHTML = createBillsHistory();
  const bills = await getAllExpenses(currentUserData.username);

  const backToBillsButton = document.querySelector(".add-friend-back-button");

  backToBillsButton.addEventListener("click", () => {
    displayBillsScreen(element, currentUserData);
  });

  displayMonthsOptions(bills, currentUserData);
}

async function displayMonthsOptions(bills, currentUserData) {
  const monthSelect = document.getElementById("bill-date-select");
  const totalSpent = document.querySelector(".total-spent-value");
  const historyContainer = document.getElementById("history-container");

  historyContainer.innerHTML = "";
  monthSelect.innerHTML = '<option value="0">Desde sempre</option>';

  const uniqueMonths = new Set();

  bills.forEach((bill) => {
    const creationDate = new Date(bill.creationDate);
    const monthYear = creationDate.toLocaleString("pt-BR", {
      month: "long",
      year: "numeric",
    });
    uniqueMonths.add(monthYear);
  });

  const sortedMonths = [...uniqueMonths].sort(
    (a, b) => new Date(`1 ${a}`) - new Date(`1 ${b}`)
  );

  sortedMonths.forEach((monthYear) => {
    const option = document.createElement("option");
    option.value = monthYear;
    option.textContent = monthYear.charAt(0).toUpperCase() + monthYear.slice(1); // Capitalize first letter
    monthSelect.appendChild(option);
  });

  function updateTotalSpent() {
    const selectedMonth = monthSelect.value;

    historyContainer.innerHTML = "";

    let total = 0;

    bills.forEach(async (bill) => {
      const billDate = new Date(bill.creationDate);
      const billMonthYear = billDate.toLocaleString("pt-BR", {
        month: "long",
        year: "numeric",
      });

      if (selectedMonth === "0" || billMonthYear === selectedMonth) {
        const userPortion = bill.expenseDetails
          .filter((detail) => detail.username === currentUserData.username)
          .reduce((subSum, detail) => subSum + detail.valueToPay, 0);

        total += userPortion;

        const billElement = document.createElement("div");
        billElement.classList.add("history-bill");

        const allUsers = await getAllUsers();
        console.log(allUsers);
        const admin = allUsers.find(
          (user) => user.username === bill.idExpenseCreator
        );
        console.log(admin);

        const adminProfilePicture = await getProfilePicture(admin.username);

        billElement.innerHTML = `
                  <div class="history-bill-left">
                      <img src="${adminProfilePicture}" alt=""/>
                      <div>
                          <p class="history-bill-title">${bill.title}</p>
                          <p class="history-bill-date">Criada em ${billDate.toLocaleDateString(
                            "pt-BR"
                          )}</p>
                      </div>
                  </div>
                  <div class="history-bill-right">
                      <p class="history-bill-currency">R$</p>
                      <p class="history-bill-value">${userPortion
                        .toFixed(2)
                        .replace(".", ",")}</p>
                  </div>
              `;

        historyContainer.appendChild(billElement);
      }
    });

    totalSpent.textContent = `${total.toFixed(2).replace(".", ",")}`;
  }

  monthSelect.addEventListener("change", updateTotalSpent);

  updateTotalSpent();
}
