// Sample data representing products added by users (you can replace this with dynamic data from a database)
const productsData = [
    {
        id: 1,
        name: "Product 1",
        description: "Description for Product 1",
        price: 19.99,
        imageUrl: "https://via.placeholder.com/150"
    },
    {
        id: 2,
        name: "Product 2",
        description: "Description for Product 2",
        price: 29.99,
        imageUrl: "https://via.placeholder.com/150"
    },
    {
        id: 3,
        name: "Product 3",
        description: "Description for Product 3",
        price: 14.99,
        imageUrl: "https://via.placeholder.com/150"
    }
];

// Function to display products on the webpage
function displayProducts() {
    const productsList = document.getElementById('products-list');

    productsData.forEach(product => {
        const productCard = document.createElement('div');
        productCard.classList.add('product-card');

        productCard.innerHTML = `
            <img src="${product.imageUrl}" alt="${product.name}">
            <h2>${product.name}</h2>
            <p>${product.description}</p>
            <p>Price: $${product.price}</p>
        `;

        productsList.appendChild(productCard);
    });
}

// Call the function to display products when the page loads
window.onload = displayProducts;