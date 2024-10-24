export function getFirstName(fullName) {
  const nameArray = fullName.trim().split(" ");
  return nameArray[0];
}