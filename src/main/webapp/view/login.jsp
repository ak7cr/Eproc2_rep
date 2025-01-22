<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/login.css">
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
</head>
<body>
    <div class="wrapper">
		<form action="./stag" method="POST" id="loginForm">
		    <h1 class="center">Vendor Login</h1>

		    <div class="input_field">
		        <input type="text" name="email" id="email" placeholder="Email" required aria-label="Username">
		    </div>

		    <div class="input_field">
		        <input type="password" name="password" id="password" placeholder="Password" required aria-label="Password">
		    </div>

		    <div class="remember-forget">
		        <a href="./otp">Forget Password?</a>
		    </div>

		    <!-- Google reCAPTCHA widget -->
			<div class="g-recaptcha" data-sitekey="6LfSzLQqAAAAADSZtJPZ7uGcvyi4W7WXyJvqsC2v"></div>


		    <br><br>
		    <button type="submit" class="btn">Login</button>
		    <br><br>
		    <div class="signup_link">
		        <p> Don't have an Account? <a href="./reg">Register Here</a></p>
		    </div>

			<!-- Display error message if it exists -->
            <div class="error-message">
                <c:if test="${not empty errorMessage}">
                    <p style="color: red;">${errorMessage}</p>
                </c:if>
            </div>
		</form>


		<div class="side">
			<a href="./list">Click here to see Tender List</a>
		</div>


        <script>
            document.getElementById("loginForm").addEventListener("submit", function (event) {
                const recaptchaResponse = grecaptcha.getResponse();

                if (!recaptchaResponse) {
                    alert("Please complete the reCAPTCHA to proceed.");
                    event.preventDefault(); // Prevent form submission if reCAPTCHA isn't completed
                }
            });
			
			const sessionTimeoutWarning = 28 * 60 * 1000; // 28 minutes
			    const sessionTimeout = 30 * 60 * 1000; // 30 minutes

			    setTimeout(() => {
			        alert("Your session is about to expire. Please save your work or log in again.");
			    }, sessionTimeoutWarning);

			    setTimeout(() => {
			        window.location.href = "/login?timeout"; // Redirect to login page
			    }, sessionTimeout);
        </script>
		
    </div>
</body>
</html>
