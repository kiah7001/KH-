  document.getElementById('passwordInput').addEventListener('click', function() {
  document.getElementById('passwordGuidelines').style.display = 'block';
});

document.getElementById('loginForm').addEventListener('submit', function(event) {
  event.preventDefault();
  
  let idInput = document.getElementById('idInput');
  let passwordInput = document.getElementById('passwordInput');
  let passwordConfirmInput = document.getElementById('passwordConfirmInput');

  if (idInput.value.trim() === '') {
      document.getElementById('idError').style.display = 'block';
  } else {
      document.getElementById('idError').style.display = 'none';
  }

  if (passwordInput.value.trim() === '') {
      document.getElementById('passwordError').style.display = 'block';
  } else {
      document.getElementById('passwordError').style.display = 'none';
  }

  if (passwordConfirmInput.value.trim() === '') {
      document.getElementById('passwordConfirmError').style.display = 'block';
  } else {
      document.getElementById('passwordConfirmError').style.display = 'none';
  }
});