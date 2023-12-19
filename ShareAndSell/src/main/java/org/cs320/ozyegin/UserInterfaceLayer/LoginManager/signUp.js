document.getElementById("signup-form").addEventListener("submit", function(event) {
    event.preventDefault(); // Prevent default form submission
    // Fetch user inputs
    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    // Here, you can perform validation of the inputs and further actions (e.g., sending data to a server)

    // For demonstration purposes, logging the entered data to the console
    console.log("Name:", name);
    console.log("Email:", email);
    console.log("Password:", password);

    // After validation, you can redirect the user or perform other necessary actions
    // For example:
    // window.location.href = "login.html"; // Redirect to login page after successful signup
});