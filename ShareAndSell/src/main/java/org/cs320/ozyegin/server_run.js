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

// Start the server
const PORT = 3000;
app.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`);
});
