async function validateForm() {
  // grab elements
  const name        = document.getElementById("name").value.trim();
  const dept        = document.getElementById("department").value;
  const dob         = document.getElementById("dob").value;
  const joinDate    = document.getElementById("joiningDate").value;
  const email       = document.getElementById("email").value.trim();
  const phone       = document.getElementById("phone").value.trim();
  const password    = document.getElementById("password").value;

  // clear previous messages
  const msgEl = document.getElementById("responseMessage");
  msgEl.innerText = "";

  // Name
  if (!name) {
    msgEl.innerText = "Please enter your name.";
    return false;
  }

  // Department
  if (!dept) {
    msgEl.innerText = "Please select a department.";
    return false;
  }

  // Date of Birth
  if (!dob) {
    msgEl.innerText = "Please select your date of birth.";
    return false;
  }
  const dobDate = new Date(dob);
  if (dobDate > new Date()) {
    msgEl.innerText = "Date of birth cannot be in the future.";
    return false;
  }

  // Joining Date
  if (!joinDate) {
    msgEl.innerText = "Please select your joining date.";
    return false;
  }
  const joinDateObj = new Date(joinDate);
  if (joinDateObj > new Date()) {
    msgEl.innerText = "Joining date cannot be in the future.";
    return false;
  }
  if (joinDateObj < dobDate) {
    msgEl.innerText = "Joining date must be after your date of birth.";
    return false;
  }

  // Email
  const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
  if (!emailPattern.test(email)) {
    msgEl.innerText = "Please enter a valid email address.";
    return false;
  }

  // Phone
  const phonePattern = /^[0-9]{10}$/;
  if (!phonePattern.test(phone)) {
    msgEl.innerText = "Please enter a valid 10-digit phone number.";
    return false;
  }

  // Password
  if (password.length < 6) {
    msgEl.innerText = "Password must be at least 6 characters long.";
    return false;
  }

  return true;
}

async function submitVendor() {
  // validate first
  if (!await validateForm()) return;

  // disable form
  const btn       = document.querySelector("#vendorForm button[type=submit]");
  btn.disabled    = true;
  btn.innerText   = "Registering…";

  // collect payload
  const payload = {
    name:        document.getElementById("name").value.trim(),
    department:  document.getElementById("department").value,
    dob:         document.getElementById("dob").value,
    joiningDate: document.getElementById("joiningDate").value,
    email:       document.getElementById("email").value.trim(),
    phone:       document.getElementById("phone").value.trim(),
    password:    document.getElementById("password").value
  };

  const msgEl = document.getElementById("responseMessage");
  msgEl.innerText = "";

  try {
    const res = await fetch(`${request.getContextPath()}/registration/managers`, {
      method:  "POST",
      headers: { "Content-Type": "application/json" },
      body:    JSON.stringify(payload)
    });

    let result;
    const contentType = res.headers.get("content-type") || "";
    if (contentType.includes("application/json")) {
      result = await res.json();
    } else {
      throw new Error(await res.text());
    }

    if (res.ok && result.success) {
      msgEl.innerText = "Registration successful!";
      btn.innerText = "Registered";
    } else {
      msgEl.innerText = result.message || "Registration failed.";
      btn.disabled = false;
      btn.innerText = "Register";
    }
  } catch (err) {
    console.error(err);
    msgEl.innerText = err.message || "An unexpected error occurred.";
    btn.disabled   = false;
    btn.innerText  = "Register";
  }
}
