function editUserInfo() {
    document.querySelector('.user-info').style.display = 'none';
    document.querySelector('.edit-form').style.display = 'block';

    // Get user info and populate edit form
    const userName = document.getElementById('user-name').innerText;
    const userEmail = document.getElementById('user-email').innerText;
    const userAddress = document.getElementById('user-address').innerText;

    document.getElementById('edit-name').value = userName;
    document.getElementById('edit-email').value = userEmail;
    document.getElementById('edit-address').value = userAddress;
}

// Function to handle form submission
document.getElementById('user-edit-form').addEventListener('submit', function(event) {
    event.preventDefault();

    // Get edited values
    const editedName = document.getElementById('edit-name').value;
    const editedEmail = document.getElementById('edit-email').value;
    const editedAddress = document.getElementById('edit-address').value;

    // Update user info in the UI
    document.getElementById('user-name').innerText = editedName;
    document.getElementById('user-email').innerText = editedEmail;
    document.getElementById('user-address').innerText = editedAddress;

    // Hide edit form and show user info section
    document.querySelector('.user-info').style.display = 'block';
    document.querySelector('.edit-form').style.display = 'none';
});