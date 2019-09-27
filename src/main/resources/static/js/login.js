/**
 * Capture DOM elements for later use.
 */

const loginInput = document.querySelector('#username');
const submitButton = document.querySelector('#submitButton');
const errorMessage = document.querySelector('#errorMessage');

/**
 * Add event listeners.
 */

loginInput.addEventListener('keydown', e =>
  e.keyCode === 13 ? performLogin(e) : null
);

submitButton.addEventListener('click', e => performLogin(e));

/**
 * Logs in to chat application, using the supplied username.
 * @param {event} e - The event triggering the process.
 * @returns new window or error message;
 */
const performLogin = e => {
  errorMessage.classList.add('hidden');
  e.preventDefault();
  const username = loginInput.value;
  if (username != '') {
    loginInput.value = '';
    return (window.location.href = `http://localhost:8080/chat?username=${username}`);
  }
  return errorMessage.classList.remove('hidden');
};
