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
    return response.data;
  } catch (error) {
    console.error(
      "Error updating user data:",
      error.response?.data || error.message
    );
    throw error;
  }
}

export async function addPix(username, pix) {
  const url = "https://porkin.onrender.com/pix";
  const payload = { username: username, pix: pix };

  try {
    const response = await axios.post(url, payload);
    console.log("Pix added successfully:", response.data);
    return response.data;
  } catch (error) {
    console.error("Error adding Pix:", error.response?.data || error.message);
    throw error;
  }
}

export async function updatePix(username, pix) {
  const url = "https://porkin.onrender.com/pix";
  const payload = { username: username, pix: pix };

  try {
    const response = await axios.put(url, payload);
    console.log("Pix updated successfully:", response.data);
    return response.data;
  } catch (error) {
    console.error("Error updating Pix:", error.response?.data || error.message);
    throw error;
  }
}

export async function payBill(bill, id) {
  const url = `https://porkin.onrender.com/expense/${id}`;
  try {
    const response = await axios.put(url, bill);
    console.log(`Bill ${id} updated:`, response.data);
    return response.data;
  } catch (error) {
    console.error(
      "Error updating bill:",
      error.response?.data || error.message
    );
    throw error;
  }
}

export async function deleteFriend(username, friend) {
  const url = `https://porkin.onrender.com/friendship/${username}/${friend}`;
  try {
    const response = await axios.delete(url);
    console.log(`Deleted ${friend} from your friends`);
    return response.data;
  } catch (error) {
    console.error(`Error deleting ${friend} from your friends`);
  }
}

export async function deleteBill(billId) {
  const url = `https://porkin.onrender.com/expense/${billId}`;
  try {
    const response = await axios.delete(url);
    console.log(`Deleted bill ${billId}`);
    return response.data;
  } catch (error) {
    console.error(`Error deleting bill ${billId}`);
  }
}

export async function sendEmailToBack(email) {
  const url = `https://porkin.onrender.com/person/recovery`;

  try {
    const response = await axios.post(
      url,
      { email }, // Wrap the email in an object
      {
        headers: {
          "Content-Type": "application/json", // Ensure Content-Type is JSON
        },
      }
    );
    console.log(`sent ${email} to backend`);
    return response.data;
  } catch (error) {
    console.error(`Error sending ${email} to backend:`, error);
  }
}

export async function changePassword(email, recoveryCode, newPassword) {
  const url = `https://porkin.onrender.com/person/reset`;

  try {
    const response = await axios.post(
      url,
      { email, recoveryCode, newPassword },
      {
        headers: {
          "Content-Type": "application/json",
        },
      }
    );
    console.log(`sent ${email} to backend`);
    return response.data;
  } catch (error) {
    console.error(`Error sending ${email} to backend:`, error);
  }
}
