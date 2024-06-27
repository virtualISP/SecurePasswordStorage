
document.getElementById('enter-app').addEventListener('click', function () {
    document.getElementById('cover-page').classList.add('d-none');
    document.getElementById('main-content').classList.remove('d-none');
});


async function handleFormSubmit(event, endpoint, messageElement) {
    event.preventDefault();

    const formData = new FormData(event.target);
    const data = Object.fromEntries(formData.entries());

    messageElement.textContent = 'Processing...';
    messageElement.style.color = 'blue';

    try {
        const response = await fetch(endpoint, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        const result = await response.json();

        if (response.ok) {
            messageElement.textContent = result.message;
            messageElement.style.color = 'green';
        } else {
            messageElement.textContent = 'Error: ' + result.message;
            messageElement.style.color = 'red';
        }
    } catch (error) {
        messageElement.textContent = 'Error: Unable to connect to the server';
        messageElement.style.color = 'red';
    }
}


document.getElementById('signupForm').addEventListener('submit', (event) => handleFormSubmit(event, 'http://localhost:8080/user/create', document.getElementById('signup-message')));
document.getElementById('loginForm').addEventListener('submit', (event) => handleFormSubmit(event, 'http://localhost:8080/user/login', document.getElementById('signin-message')));
document.getElementById('changePasswordForm').addEventListener('submit', (event) => handleFormSubmit(event, 'http://localhost:8080/user/changePassword', document.getElementById('change-message')));
