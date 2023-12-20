
document.getElementById("signup-form").addEventListener("submit", function(event) {
    event.preventDefault(); // Prevent default form submission
    // Fetch user inputs
    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    // Here, you can perform validation of the inputs and further actions (e.g., sending data to a server)
    const sqlite3 = require('sqlite3');

    // For demonstration purposes, logging the entered data to the console
    console.log("Name:", name);
    console.log("Email:", email);
    console.log("Password:", password);


// Open a database connection
    const db = new sqlite3.Database('Users.db', sqlite3.OPEN_READWRITE, (err) => {
        if (err) {
            console.error(err.message);
        } else {
            console.log('Connected to the SQLite database.');
        }
    });

// Define your insert query
    const insertQuery = `INSERT INTO Users (id, name, mail , password, rate) VALUES (?,?, ?, ?)`;

// Data to be inserted
    const dataToInsert = [name, email, password, 0]; // Replace with actual values

// Execute the insertion
    db.run(insertQuery, dataToInsert, function (err) {
        if (err) {
            console.error(err.message);
        } else {
            console.log(`Rows inserted: ${"ok"}`);
        }
    });

// Close the database connection
    db.close((err) => {
        if (err) {
            console.error(err.message);
        } else {
            console.log('Closed the database connection.');
        }
    });

    // After validation, you can redirect the user or perform other necessary actions
    // For example:
    // window.location.href = "login.html"; // Redirect to login page after successful signup
});