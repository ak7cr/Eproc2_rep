const API_URL = 'http://localhost:8085/registration';

// DOM Elements
document.addEventListener('DOMContentLoaded', () => {
  const emailForm = document.getElementById('emailForm');
  const otpForm = document.getElementById('otpForm');
  const emailInput = document.getElementById('email');
  const title = document.getElementById('title');
  const subtitle = document.getElementById('subtitle');
  const timer = document.getElementById('timer');
  const error = document.getElementById('error');
  const submitBtn = document.getElementById('submitBtn');
  const verifyBtn = document.getElementById('verifyBtn');
  const changeEmailBtn = document.getElementById('changeEmailBtn');
  const mailIcon = document.getElementById('mailIcon');
  const keyIcon = document.getElementById('keyIcon');
  const otpInputs = document.querySelectorAll('.otp-input');

  // Handle email form submission
  if (emailForm) {
    emailForm.addEventListener('submit', async (e) => {
      e.preventDefault();
      const email = emailInput.value;
      try {
        const response = await fetch(`${API_URL}/sendOtp`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({ recipient: email })
        });
        const data = await response.json();
        console.log('Response from /sendOtp:', data); // Log the response
        if (data.success) {
          // Show OTP form and hide email form
          emailForm.style.display = 'none';
          otpForm.style.display = 'block';
          // Start OTP timer
          startOtpTimer();
        } else {
          error.textContent = data.message || 'Failed to send OTP.';
        }
      } catch (err) {
        console.error('Error during /sendOtp:', err); // Log the error
        error.textContent = 'An error occurred. Please try again.';
      }
    });
  }

  // Handle OTP form submission
  if (otpForm) {
    otpForm.addEventListener('submit', async (e) => {
      e.preventDefault();
      const otp = Array.from(otpInputs).map(input => input.value).join('');
      const email = emailInput.value;
      try {
        const response = await fetch(`${API_URL}/verifyOtp`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({ email, otp })
        });
        const data = await response.json();
        console.log('Response from /verifyOtp:', data); // Log the response
        if (data.success) {
          // OTP verified successfully
          subtitle.textContent = 'OTP verified successfully!';
          // Redirect to homepage
          window.location.href = '/registration/home';
        } else {
          error.textContent = data.message || 'Invalid OTP.';
        }
      } catch (err) {
        console.error('Error during /verifyOtp:', err); // Log the error
        error.textContent = 'An error occurred. Please try again.';
      }
    });
  }

  // Handle OTP input navigation
  otpInputs.forEach((input, index) => {
    input.addEventListener('input', (e) => {
      if (e.target.value.length === 1 && index < otpInputs.length - 1) {
        otpInputs[index + 1].focus();
      }
    });

    input.addEventListener('keydown', (e) => {
      if (e.key === 'Backspace' && !e.target.value && index > 0) {
        otpInputs[index - 1].focus();
      }
    });
  });

  // Function to start OTP timer
  function startOtpTimer() {
    let timeLeft = 60; // 60 seconds
    const timerInterval = setInterval(() => {
      if (timeLeft <= 0) {
        clearInterval(timerInterval);
        timer.textContent = 'OTP expired. Please request a new one.';
      } else {
        timer.textContent = `Time left: ${timeLeft--}s`;
      }
    }, 1000);
  }
});