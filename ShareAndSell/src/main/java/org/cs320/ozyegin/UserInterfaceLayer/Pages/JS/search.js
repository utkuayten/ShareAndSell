// Sample data representing products (you can replace this with actual data from a database)
const productsData = [
    { id: 1, name: "Product 1", year: 2023, price: 19.99 },
    { id: 2, name: "Product 2", year: 2022, price: 29.99 },
    { id: 3, name: "Product 3", year: 2023, price: 14.99 },
    // Add more product data as needed
];

// Function to display products based on search keyword
function searchProducts() {
    const searchInput = document.getElementById('search-input').value.toLowerCase();
    const filteredProducts = productsData.filter(product =>
        product.name.toLowerCase().includes(searchInput)
    );
    displayProducts(filteredProducts);
}

// Function to apply filters for year and price
function applyFilters() {
    const yearFilter = document.getElementById('year-filter').value;
    const priceFilter = parseFloat(document.getElementById('price-filter').value);

    let filteredProducts = productsData;

    if (yearFilter !== '') {
        filteredProducts = filteredProducts.filter(product => product.year.toString() === yearFilter);
    }

    if (!isNaN(priceFilter)) {
        filteredProducts = filteredProducts.filter(product => product.price <= priceFilter);
    }

    displayProducts(filteredProducts);
}

// Function to display products on the webpage
function displayProducts(products) {
    const productsList = document.getElementById('products-list');
    productsList.innerHTML = '';

    products.forEach(product => {
        const productCard = document.createElement('div');
        productCard.classList.add('product-card');

        productCard.innerHTML = `
            <h2>${product.name}</h2>
            <p>Year: ${product.year}</p>
            <p>Price: $${product.price}</p>
        `;

        productsList.appendChild(productCard);
    });
}