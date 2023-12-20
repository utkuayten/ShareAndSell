
document.getElementById("login-form").addEventListener("submit", function(event) {
    event.preventDefault(); // Prevent default form submission
    // Perform login logic here (authentication, etc.)
    // For demonstration purposes, you can add your logic here or link it to your backend
    // Example:
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    // Here, you might perform validation and/or send a request to the server for authentication


    // For demonstration, simply logging the entered credentials to the console
    console.log("Username:", username);
    console.log("Password:", password);

    // After validation/authentication, you can redirect the user to the appropriate page
    // For example:
    // window.location.href = "dashboard.html";
});

