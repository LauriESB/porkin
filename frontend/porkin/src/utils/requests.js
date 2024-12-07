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
  formData.append("image", fileInput.files[0]); // Use "image" as the key

  try {
    const response = await axios.post(
      `https://porkin.onrender.com/image/${user}`,
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
      `https://porkin.onrender.com/image/${username}`
    );
    if (!response.ok) {
      throw new Error(
        `Failed to fetch profile picture: ${response.statusText}`
      );
    }

    // Convert response to a Blob for image rendering
    const blob = await response.blob();
    const imageUrl = URL.createObjectURL(blob);
    return imageUrl; // This can be used as the `src` for an image element
  } catch (error) {
    console.error("Error fetching profile picture:", error);

    // Provide a default image URL or fallback
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

export async function updateUserData(username, userObject) {
  const url = `https://porkin.onrender.com/person/${username}`;
  try {
    const response = await axios.put(url, userObject);
    console.log("User data updated successfully:", response.data);
    return response.data; // Return response data if needed
  } catch (error) {
    console.error(
      "Error updating user data:",
      error.response?.data || error.message
    );
    throw error; // Re-throw error to handle it in the calling code
  }
}
