function validateForm() {
    var email = document.getElementById("email").value;
    // Check if email is valid
    var emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    if (!emailPattern.test(email)) {
        alert("Please enter a valid email address.");
        return false;
    }

    var phone = document.getElementById("phone").value;
    var phonePattern = /^[0-9]{10}$/; // Simple validation for 10 digits
    if (!phonePattern.test(phone)) {
        alert("Please enter a valid 10-digit phone number.");
        return false;
    }

    return true; // If validation passes
}

function submitVendor() {
    // Validate form before submission
    if (!validateForm()) {
        return;
    }

    const formData = {
        name: document.getElementById("name").value,
        email: document.getElementById("email").value,
        phone: document.getElementById("phone").value,
        address: document.getElementById("address").value,
        password: document.getElementById("password").value,
    };

    // Clear any previous response message
    document.getElementById("responseMessage").innerText = "";

    // Basic form validation (if not done in validateForm())
    if (!formData.name || !formData.email || !formData.phone || !formData.address || !formData.password) {
        document.getElementById("responseMessage").innerText = "All fields are required.";
        return;
    }

    // Show loading message
    document.getElementById("responseMessage").innerText = "Submitting registration...";

    fetch('http://localhost:8085/registration/vendors', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
    .then(response => {
        const contentType = response.headers.get('content-type');
        if (contentType && contentType.includes('application/json')) {
            return response.json();
        } else {
            return response.text().then(text => { throw new Error(text); });
        }
    })
    .then(data => {
        if (data.success) {
            document.getElementById("responseMessage").innerText = "Registration successful!";
        } else {
            document.getElementById("responseMessage").innerText = data.message || "Registration failed.";
        }
    })
    .catch(error => {
        console.error('Error:', error);
        document.getElementById("responseMessage").innerText = error.message || "An error occurred. Please try again.";
    });
}