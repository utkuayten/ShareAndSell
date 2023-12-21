document.getElementById('login-form').addEventListener('submit', function(event) {
    event.preventDefault();

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    fetch('/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ name: username, password: password }),
    })
        .then(response => response.text())
        .then(result => {
            console.log(result);
            if (result === 'success') {
                // Redirect to a new page upon successful login
                window.location.href = '/mainPage'; // Replace with your success page URL
            } else {
                // Handle failure, such as displaying an error message
                console.log('Login failed');
                console.log(result);
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
});
