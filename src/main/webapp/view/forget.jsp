<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reset your Password</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/forget.css">
    <script src="<%=request.getContextPath() %>/js/forget.js"></script>
</head>
<body>
    <div class="back">
        <a href="<%= request.getContextPath() %>/login">Back to Login</a>
    </div>
    <div class="wrapper">
        
        <h2 class="center">Reset your Password</h2>
        <br>
        <p class="center">Enter your registered Email below</p>
    
        <form action="<%=request.getContextPath() %>/forget/generateOtp" method="POST">
            <div class="input_field">
                
                <input type="email" id="email" name="email" placeholder="Enter your email" required>
            </div>
            <div class="input_field">
                <input type="submit" value="Send OTP" class="btn">
            </div>
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