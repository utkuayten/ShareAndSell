const express = require('express');
const app = express();
const path = require('path');
const absolutePathToAssets = path.join(__dirname, 'UserInterfaceLayer', 'LoginManager', 'Pages');

// Serve static files from the 'public' directory
app.use(express.static(absolutePathToAssets));
app.use(express.json());
// Route to serve the index.html file
app.get('/login', (req, res) => {
    res.sendFile(__dirname + '/UserInterfaceLayer/LoginManager/Pages/loginPage.html');
});
// ... (other code remains the same)

// Login endpoint
app.post('/login', (req, res) => {
    const { username, password } = req.body;

    // Check if username and password are provided in the request
    if (!username || !password) {
        return res.status(400).json({ message: 'Username and password are required.' });
    }

    // Check if the provided username and password are 'a'
    if (username === 'a' && password === 'a') {
        // Successful login
        return res.status(200).json({ message: 'Login successful', username });
    } else {
        // Invalid credentials
        return res.status(401).json({ message: 'Invalid username or password' });
    }
});

// ... (rest of the code remains the same)

app.get('/signUp', (req, res) => {
    res.sendFile(__dirname + '/UserInterfaceLayer/LoginManager/Pages/signUpPage.html');
});


app.get('/', (req, res) => {
    res.sendFile(__dirname + '/UserInterfaceLayer/LoginManager/Pages/mainPage.html');
});

// Start the server
const PORT = 3000;
app.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`);
});
