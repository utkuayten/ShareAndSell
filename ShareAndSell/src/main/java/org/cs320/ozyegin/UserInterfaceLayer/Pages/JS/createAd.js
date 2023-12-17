// Function to handle form submission
function handleFormSubmission(event) {
    event.preventDefault();

    // Fetch form values
    const productName = document.getElementById('product-name').value;
    const productDescription = document.getElementById('product-description').value;
    const productPrice = parseFloat(document.getElementById('product-price').value);
    const productImage = document.getElementById('product-image').value;

    // Perform further actions (e.g., send data to a server, store in database)
    // For this example, you can log the data to the console
    console.log("Product Name:", productName);
    console.log("Product Description:", productDescription);
    console.log("Product Price:", productPrice);
    console.log("Product Image URL:", productImage);

    // Reset form after submission
    document.getElementById('ad-form').reset();
}

// Event listener for form submission
document.getElementById('ad-form').addEventListener('submit', handleFormSubmission);