<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Settings</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/settings.css">
</head>
<body>
    <div class="container">
        <h1>Settings</h1>
        <form action="<%=request.getContextPath() %>/registration/settings/update" method="POST">
            <input type="hidden" name="email" value="${vendor.email}">
            <div class="input_field">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" value="${vendor.name}" required>
            </div>
            <div class="input_field">
                <label for="phone">Phone:</label>
                <input type="text" id="phone" name="phone" value="${vendor.phone}" required>
            </div>
            <div class="input_field">
                <label for="address">Address:</label>
                <input type="text" id="address" name="address" value="${vendor.address}" required>
            </div>
            <button type="submit" class="btn">Update Details</button>
        </form>

        <form action="<%=request.getContextPath() %>/registration/settings/changePassword" method="POST">
            <input type="hidden" name="email" value="${vendor.email}">
            <div class="input_field">
                <label for="oldPassword">Old Password:</label>
                <input type="password" id="oldPassword" name="oldPassword" required>
            </div>
            <div class="input_field">
                <label for="newPassword">New Password:</label>
                <input type="password" id="newPassword" name="newPassword" required>
            </div>
            <button type="submit" class="btn">Change Password</button>
        </form>

        <div class="messages">
            <c:if test="${not empty successMessage}">
                <p class="success">${successMessage}</p>
            </c:if>
            <c:if test="${not empty errorMessage}">
                <p class="error">${errorMessage}</p>
            </c:if>
        </div>
    </div>
</body>
</html>