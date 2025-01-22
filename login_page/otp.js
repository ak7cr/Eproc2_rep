const API_URL = 'http://localhost:8080';

// DOM Elements
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

let timeLeft = 300; // 5 minutes in seconds
let timerInterval;

// Format time from seconds to MM:SS
function formatTime(seconds) {
  const mins = Math.floor(seconds / 60);
  const secs = seconds % 60;
  return `${mins}:${secs.toString().padStart(2, '0')}`;
}

// Start countdown timer
function startTimer() {
  timer.classList.remove('hidden');
  clearInterval(timerInterval);
  timeLeft = 300;
  timer.textContent = formatTime(timeLeft);
  
  timerInterval = setInterval(() => {
    timeLeft--;
    timer.textContent = formatTime(timeLeft);
    
    if (timeLeft <= 60) {
      timer.classList.add('warning');
    }
    
    if (timeLeft <= 0) {
      clearInterval(timerInterval);
      verifyBtn.disabled = true;
      error.textContent = 'OTP expired. Please request a new one.';
    }
  }, 1000);
}

// Handle email form submission
emailForm.addEventListener('submit', async (e) => {
  e.preventDefault();
  error.textContent = '';
  submitBtn.disabled = true;
  submitBtn.textContent = 'Sending...';
  
  try {
    const response = await fetch(`${API_URL}/sendOtp`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        recipient: emailInput.value,
      }),
    });
    
    if (!response.ok) throw new Error('Failed to send OTP');
    
    // Show OTP form
    emailForm.classList.add('hidden');
    otpForm.classList.remove('hidden');
    mailIcon.classList.add('hidden');
    keyIcon.classList.remove('hidden');
    title.textContent = 'Enter Verification Code';
    subtitle.textContent = `We've sent a code to ${emailInput.value}`;
    startTimer();
    
  } catch (err) {
    error.textContent = 'Failed to send OTP. Please try again.';
  } finally {
    submitBtn.disabled = false;
    submitBtn.textContent = 'Continue';
  }
});

// Handle OTP input
const otpInputs = document.querySelectorAll('.otp-input');
otpInputs.forEach((input, index) => {
  input.addEventListener('input', (e) => {
    if (e.target.value.length === 1) {
      if (index < otpInputs.length - 1) {
        otpInputs[index + 1].focus();
      }
    }
  });
  
  input.addEventListener('keydown', (e) => {
    if (e.key === 'Backspace' && !e.target.value && index > 0) {
      otpInputs[index - 1].focus();
    }
  });
});

// Handle OTP form submission
otpForm.addEventListener('submit', async (e) => {
  e.preventDefault();
  error.textContent = '';
  verifyBtn.disabled = true;
  verifyBtn.textContent = 'Verifying...';
  
  const otp = Array.from(otpInputs).map(input => input.value).join('');
  
  try {
    const response = await fetch(`${API_URL}/verifyOtp`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        email: emailInput.value,
        otp: otp,
      }),
    });
    
    if (!response.ok) throw new Error('Invalid OTP');
    
    const result = await response.text();
    if (result === 'OTP Verified Successfully!') {
      title.textContent = 'Verification Successful';
      subtitle.textContent = 'You can now proceed';
      otpForm.innerHTML = '<p class="success">✓ Verified</p>';
      clearInterval(timerInterval);
    } else {
      throw new Error('Invalid OTP');
    }
    
  } catch (err) {
    error.textContent = 'Invalid OTP. Please try again.';
    verifyBtn.disabled = false;
    verifyBtn.textContent = 'Verify Code';
  }
});

// Handle change email button
changeEmailBtn.addEventListener('click', () => {
  otpForm.classList.add('hidden');
  emailForm.classList.remove('hidden');
  keyIcon.classList.add('hidden');
  mailIcon.classList.remove('hidden');
  title.textContent = 'Verify Your Email';
  subtitle.textContent = 'Please enter your email address to receive a verification code';
  error.textContent = '';
  timer.classList.add('hidden');
  clearInterval(timerInterval);
  otpInputs.forEach(input => input.value = '');
  emailInput.value = '';
});