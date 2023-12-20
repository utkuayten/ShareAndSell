document.getElementById("login-form").addEventListener("submit", function(event) {
    event.preventDefault(); // Prevent default form submission

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    // Construct the request body with username and password
    const data = { username, password };
    console.log(data);
    // Make a POST request to the login API
    fetch('http://localhost:3000/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Invalid username or password');
            }
            // Redirect to the main page upon successful login
            window.location.href = "/profilePage.html";
        })
        .catch(error => {
            console.error('Login failed:', error);
            // Handle login failure or display error message to the user
        });
});
