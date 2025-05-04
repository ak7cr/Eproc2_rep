// employee.js

// Validate all fields before submitting
async function validateForm() {
  const name       = document.getElementById("name").value.trim();
  const email      = document.getElementById("email").value.trim();
  const phone      = document.getElementById("phone").value.trim();
  const city       = document.getElementById("city").value.trim();
  const state      = document.getElementById("state").value.trim();
  const dept       = document.getElementById("department").value.trim();
  const dob        = document.getElementById("dob").value;

  const msgEl = document.getElementById("responseMessage");
  msgEl.style.color = "red";
  msgEl.innerText = "";

  if (!name)    { msgEl.innerText = "Please enter your name.";        return false; }
  if (!email)   { msgEl.innerText = "Please enter your email.";       return false; }
  if (!phone)   { msgEl.innerText = "Please enter your phone.";       return false; }
  if (!city)    { msgEl.innerText = "Please enter your city.";        return false; }
  if (!state)   { msgEl.innerText = "Please enter your state.";       return false; }
  if (!dept)    { msgEl.innerText = "Please enter your department.";  return false; }
  if (!dob)     { msgEl.innerText = "Please select your date of birth."; return false; }

  // simple patterns
  const emailPattern = /^[^@\s]+@[^@\s]+\.[^@\s]+$/;
  if (!emailPattern.test(email))  { msgEl.innerText = "Invalid email format.";    return false; }

  if (!/^[0-9]{10}$/.test(phone)) { msgEl.innerText = "Phone must be 10 digits.";  return false; }

  // DOB cannot be in the future
  if (new Date(dob) > new Date()) { msgEl.innerText = "DOB cannot be in the future."; return false; }

  return true;
}

// Submit via AJAX and show result
async function submitEmployee() {
  if (!await validateForm()) return;

  const btn = document.querySelector("#employeeForm button[type=submit]");
  btn.disabled = true;
  btn.innerText = "Submitting…";

  // collect form data
  const payload = {
    name:       document.getElementById("name").value.trim(),
    email:      document.getElementById("email").value.trim(),
    phone:      document.getElementById("phone").value.trim(),
    city:       document.getElementById("city").value.trim(),
    state:      document.getElementById("state").value.trim(),
    department: document.getElementById("department").value.trim(),
    dob:        document.getElementById("dob").value
  };

  const msgEl = document.getElementById("responseMessage");
  msgEl.innerText = "";

  try {
    const res = await fetch("/registration/employees", {
      method:  "POST",
      headers: { "Content-Type": "application/json" },
      body:    JSON.stringify(payload)
    });
    const result = await res.json();

    if (res.ok && result.success) {
      msgEl.style.color = "green";
      msgEl.innerText = result.message || "Saved successfully!";
      document.getElementById("employeeForm").reset();
      btn.innerText = "Submitted";
    } else {
      msgEl.style.color = "red";
      msgEl.innerText = result.message || "Failed to save.";
      btn.disabled = false;
      btn.innerText = "Submit Now";
    }
  } catch (err) {
    console.error(err);
    msgEl.style.color = "red";
    msgEl.innerText = err.message || "An unexpected error occurred.";
    btn.disabled = false;
    btn.innerText = "Submit Now";
  }
}
