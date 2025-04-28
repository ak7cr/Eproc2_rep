function submitVendor() {
    const formData = {
        name: document.getElementById("name").value,
        email: document.getElementById("email").value,
        phone: document.getElementById("phone").value,
        address: document.getElementById("address").value,
        password: document.getElementById("password").value,
    };

    // Clear any previous response message
    document.getElementById("responseMessage").innerText = "";

    // Basic form validation
    if (!formData.name || !formData.email || !formData.phone || !formData.address || !formData.password) {
        document.getElementById("responseMessage").innerText = "All fields are required.";
        return;
    }

    // Show loading message
    document.getElementById("responseMessage").innerText = "Submitting registration...";

    fetch('http://localhost:8085/registration/vendors', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
    })
    .then(response => {
        // Check if the response is JSON
        const contentType = response.headers.get("Content-Type");

        if (contentType && contentType.includes("application/json")) {
            // If it's JSON, parse it
            return response.json();
        } else {
            // If it's not JSON, return the raw text
            return response.text().then(text => {
                throw new Error(text); // throw plain text error message
            });
        }
    })
    .then(data => {
        // Handle success: show response message
        document.getElementById("responseMessage").innerText = data.message || "Vendor registered successfully!";
    })
    .catch(error => {
        console.error("Error:", error);
        // If the error is plain text, display it directly
        document.getElementById("responseMessage").innerText = error.message || "Registration failed. Please try again.";
    });
}
