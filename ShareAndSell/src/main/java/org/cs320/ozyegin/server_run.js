const express = require('express');
const app = express();
const path = require('path');
const absolutePathToAssets = path.join(__dirname, 'UserInterfaceLayer', 'LoginManager', 'Pages');

// Serve static files from the 'public' directory
app.use(express.static(absolutePathToAssets));

// Route to serve the index.html file
app.get('/login', (req, res) => {
    res.sendFile(__dirname + '/UserInterfaceLayer/LoginManager/Pages/loginPage.html');
});
/*
app.post('/login', (req, res) => {
    const {username, password} = req.body; // Accessing form data sent via POST

    // Check credentials, perform authentication, etc.
    // Usually involves database interaction and authentication logic
    // For example, validating the user's credentials against a database

    // Respond based on authentication status
    if (validCredentials(username, password)) {
        // Successful login
        res.send('Login successful');
    } else {
        // Invalid credentials
        res.status(401).send('Invalid username or password');
    }
});
*/
// Start the server
const PORT = 1234;
app.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`);
});
