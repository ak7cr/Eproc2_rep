<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Email Verification</title>
  <link rel="stylesheet" type="text/css"        href="<%=request.getContextPath() %>/css/otp.css">
  <script src="<%=request.getContextPath() %>/js/otp.js"></script>

</head>
<body>
  <div class="container">
    <div class="card">
      <div class="icon-container">
        <div id="iconWrapper" class="icon-wrapper">
          <svg id="mailIcon" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><rect width="20" height="16" x="2" y="4" rx="2"/><path d="m22 7-8.97 5.7a1.94 1.94 0 0 1-2.06 0L2 7"/></svg>
          <svg id="keyIcon" class="hidden" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M2 18v3c0 .6.4 1 1 1h4v-3h3v-3h2l1.4-1.4a6.5 6.5 0 1 0-4-4Z"/><circle cx="16.5" cy="7.5" r=".5"/></svg>
        </div>
      </div>
      
      <h2 id="title">Verify Your OTP</h2>
      <p id="subtitle">Please enter your verification code</p>
      
      <div id="timer" class="timer hidden"></div>
      <p id="error" class="error"></p>

      <form id="otpForm">
        <div class="input-group">
          <input type="number" id="otp" required placeholder="Enter your OTP">
        </div>
		
		
        <button type="submit" id="submitBtn">
          Continue
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M5 12h14"/><path d="m12 5 7 7-7 7"/></svg>
        </button>
      </form>

      <form id="otpForm" class="hidden">
        <div class="otp-container">
          <input type="text" maxlength="1" class="otp-input">
          <input type="text" maxlength="1" class="otp-input">
          <input type="text" maxlength="1" class="otp-input">
          <input type="text" maxlength="1" class="otp-input">
          <input type="text" maxlength="1" class="otp-input">
          <input type="text" maxlength="1" class="otp-input">
        </div>
        <button type="submit" id="verifyBtn">
          Verify Code
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M5 12h14"/><path d="m12 5 7 7-7 7"/></svg>
        </button>
        <button type="button" id="changeEmailBtn" class="text-button">
          Use different email
        </button>
      </form>
    </div>
  </div>
  <script src="otp.js"></script>
</body>
</html>