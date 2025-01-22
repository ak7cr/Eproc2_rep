function login() {
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    
};

document.GetElementById("responseMessage").innerText = "";

if (!username || !password) {
    document.getElementById("responseMessage").innerText = "All fields are required.";
    return;
}

document.getElementById("responseMessage").innerText = "Logging in...";

fetch('http://localhost:8085/registration/sendOtp', {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json',
    },
    body: JSON.stringify({
        username: username,
        password: password,
    }),
})

.then(response => {
    const contentType = response.headers.get("Content-Type");

    if (contentType && contentType.includes("application/json")) {
        return response.json();
    } else {
        return response.text().then(text => {
            throw new Error(text);
        });
    }
})

.then(data => {
    document.getElementById("responseMessage").innerText = data.message || "Login successful!";
})

.catch(error => {
    console.error("Error:", error);
    document.getElementById("responseMessage").innerText = error.message || "Login failed. Please try again.";
})
