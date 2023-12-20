// Mock data representing items in the basket
const basketItems = [
    { id: 1, name: "Product 1", price: 19.99 },
    { id: 2, name: "Product 2", price: 29.99 },
    { id: 3, name: "Product 3", price: 14.99 }
];

// Function to display basket items on the webpage
function displayBasketItems() {
    const basketList = document.getElementById('basket-items');

    basketItems.forEach(item => {
        const li = document.createElement('li');
        li.textContent = `${item.name} - $${item.price}`;
        basketList.appendChild(li);
    });
}

// Call the function to display basket items when the page loads
window.onload = displayBasketItems;