export function getFirstName(fullName) {
  const nameArray = fullName.trim().split(" ");
  return nameArray[0];
}

export function insertComma(number) {
  number = parseInt(number).toFixed(2).replace(".", ",");
  return number;
}

export function removeComma(number) {
  number = parseFloat(number.replace(",", "."));
  return number;
}

export function getInitialPercentages(selectedParticipants) {
  const initialPercentage = 100 / selectedParticipants.length;
  return initialPercentage;
}

export function calculatePercentage(totalValue, splitValue) {
  if (totalValue === 0) {
    return 0;
  }
  return (splitValue / totalValue) * 100;
}

export function calculateValueFromPercentage(totalValue, percentage) {
  return totalValue * (percentage / 100);
}

export function parsePercentage(percentage) {
  return parseInt(percentage);
}
