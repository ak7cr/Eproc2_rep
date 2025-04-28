<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vendor Registration</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/registration.css">
    <script src="<%= request.getContextPath() %>/js/registration.js" defer></script>
</head>
<body>
    <div class="container">
        <div class="back">
            <a href="<%= request.getContextPath() %>/login">Back to Login</a>
        </div>
        <h2>Vendor Registration</h2>
        <form id="vendorForm" onsubmit="event.preventDefault(); if (validateForm()) { submitVendor(); }">
            <label for="name">Vendor Name:</label>
            <input type="text" id="name" name="name" required>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>

            <label for="phone">Phone Number:</label>
            <input type="text" id="phone" name="phone" required>

            <label for="address">Address:</label>
            <textarea id="address" name="address" required></textarea>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <button type="submit">Register</button>
        </form>
        <p id="responseMessage"></p>

        <center><a href="<%= request.getContextPath() %>/home">Home</a></center>
    </div>
</body>
</html>