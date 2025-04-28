<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manager Registration</n></title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/registration.css">
    <script src="<%= request.getContextPath() %>/js/registration.js" defer></script>
</head>
<body>
    <div class="container">
        <div class="back">
            <a href="<%= request.getContextPath() %>/login">Back to Login</a>
        </div>
        <h2>Manager Registration</h2>
        <form id="vendorForm" onsubmit="event.preventDefault(); if (validateForm()) { submitVendor(); }">
            <!-- Name -->
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>

            <!-- Department -->
            <label for="department">Department:</label>
            <select id="department" name="department" required>
                <option value="">Select Department</option>
                <option value="1">HR</option>
                <option value="2">IT</option>
                <option value="3">Finance</option>
                <option value="4">Marketing</option>
                <option value="5">Sales</option>
                <option value="6">Human Resources</option>
                <option value="7">IT Department</option>
                <option value="8">Marketing</option>
                <option value="9">Sales</option>
                <option value="10">Research and Development</option>
                <option value="11">Finance</option>
            </select>

            <!-- Date of Birth -->
            <label for="dob">Date of Birth:</label>
            <input type="date" id="dob" name="dob" required>

            <!-- Joining Date -->
            <label for="joiningDate">Joining Date:</label>
            <input type="date" id="joiningDate" name="joiningDate" required>

            <!-- Email -->
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>

            <!-- Phone Number -->
            <label for="phone">Phone Number:</label>
            <input type="tel" id="phone" name="phone" pattern="[0-9]{10}" required>

            <!-- Password -->
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <button type="submit">Register</button>
        </form>
        <p id="responseMessage"></p>

        <center><a href="<%= request.getContextPath() %>/home">Home</a></center>
    </div>
</body>
</html>
