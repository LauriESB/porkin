import axios from "axios";
import defaultProfilePicture from "../../public/images/default-profile-picture.png";

axios;

export async function getUserData(username) {
  try {
    const response = await axios.get(
      `https://porkin.onrender.com/person/${username}`
    );
    return response.data;
  } catch (error) {
    console.error("Error fetching data:", error);
    throw error;
  }
}

export async function createUser(newUserData) {
  const url = "https://porkin.onrender.com/person";
  try {
    const response = await axios.post(url, newUserData);
    return response.data;
  } catch (error) {
    console.error("Error creating user:", error);
    throw error;
  }
}

export async function createExpense(expenseData) {
  const url = "https://porkin.onrender.com/expense";

  try {
    const response = await axios.post(url, expenseData);
    return response.data;
  } catch (error) {
    console.error("Error creating expense:", error);
    throw error;
  }
}

export async function getAllExpenses(username) {
  try {
    const response = await axios.get(
      `https://porkin.onrender.com/expense/${username}`
    );
    return response.data;
  } catch (error) {
    console.error("Error fetching data:", error);
    throw error;
  }
}

export async function getAllUsers() {
  try {
    const response = await axios.get("https://porkin.onrender.com/person");
    return response.data;
  } catch (error) {
    console.error("Error fetching data:", error);
    throw error;
  }
}

export async function sendFriendRequest(personRequester, personReceiver) {
  const url = "https://porkin.onrender.com/friendRequest";

  try {
    const response = await axios.post(url, {
      personRequester,
      personReceiver,
    });

    console.log("Friend request sent successfully:", response.data);
    return response.data;
  } catch (error) {
    console.error(
      "Error sending friend request:",
      error.response?.data || error.message
    );
    throw error;
  }
}

export async function getFriendRequests() {
  try {
    const response = await axios.get(
      "https://porkin.onrender.com/friendRequest"
    );
    return response.data;
  } catch (error) {
    console.error("Error fetching data:", error);
    throw error;
  }
}

export async function acceptFriendRequest(user, friend) {
  const url = `https://porkin.onrender.com/friendRequest/accept/${user}/${friend}`;
  try {
    const response = await axios.post(url);

    console.log("Friend Added:", response.data);
    return response.data;
  } catch (error) {
    console.error(
      "Error sending friend request:",
      error.response?.data || error.message
    );
    throw error;
  }
}

export async function rejectFriendRequest(user, friend) {
  const url = `https://porkin.onrender.com/friendRequest/reject/${user}/${friend}`;
  try {
    const response = await axios.post(url);

    console.log("Friend Added:", response.data);
    return response.data;
  } catch (error) {
    console.error(
      "Error sending friend request:",
      error.response?.data || error.message
    );
    throw error;
  }
}

export async function uploadProfilePicture(user, fileInput) {
  const formData = new FormData();
  formData.append("file", fileInput.files[0]);

  try {
    const response = await axios.post(
      `https://porkin.onrender.com/person/${user}/upload-profile-picture`,
      formData,
      {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      }
    );
    console.log(response.data);
  } catch (error) {
    console.error("Error:", error);
  }
}

export async function getProfilePicture(username) {
  try {
    const response = await fetch(
      `https://porkin.onrender.com/person/${username}/profile-picture`
    );
    if (!response.ok) {
      throw new Error(
        `Failed to fetch profile picture: ${response.statusText}`
      );
    }
    const base64Image = await response.text();
    return `data:image/jpeg;base64,${base64Image}`;
  } catch (error) {
    console.error("Error fetching profile picture:", error);
    return defaultProfilePicture;
  }
}

export async function getFriendsList(username) {
  try {
    const response = await axios.get(
      `https://porkin.onrender.com/friendship/${username}`
    );
    return response.data;
  } catch (error) {
    console.error("Error fetching data:", error);
    throw error;
  }
}
