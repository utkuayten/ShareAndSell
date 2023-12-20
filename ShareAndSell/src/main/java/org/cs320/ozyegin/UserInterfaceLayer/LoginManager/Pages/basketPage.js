// Sepet öğelerini temsil eden bir dizi
const basketItems = [
    { name: 'Product 1', quantity: 2, price: 50, imageUrl: 'path/to/product1.jpg' },
    { name: 'Product 2', quantity: 1, price: 30, imageUrl: 'path/to/product2.jpg' },
    // Diğer ürünleri ekleyin
];

// Önerilen ürünleri temsil eden bir dizi
const recommendedItems = [
    { name: 'Recommended Product 1',price: 50, imageUrl: 'path/to/recommended1.jpg' },
    { name: 'Recommended Product 2',price: 50, imageUrl: 'path/to/recommended2.jpg' },
    // Diğer önerilen ürünleri ekleyin
];

// Sayfa yüklendiğinde sepet öğelerini ve önerilen ürünleri göster
document.addEventListener('DOMContentLoaded', function () {
    renderBasketItems();
    renderRecommendedItems();
});

// Sepet öğelerini HTML'e dönüştüren fonksiyon
function renderBasketItems() {
    const basketList = document.getElementById('basket-items');
    basketList.innerHTML = '';

    basketItems.forEach(item => {
        const listItem = createListItem(item, 'basket-item');
        basketList.appendChild(listItem);
    });
}

// Önerilen ürünleri HTML'e dönüştüren fonksiyon
function renderRecommendedItems() {
    const recommendedList = document.getElementById('recommended-items');
    recommendedList.innerHTML = '';

    recommendedItems.forEach(item => {
        const listItem = createListItem(item, 'recommended-item');
        recommendedList.appendChild(listItem);
    });
}

// Ortak fonksiyon: Ürün bilgilerini içeren bir öğeyi oluşturur
function createListItem(item, className) {
    const listItem = document.createElement('li');
    listItem.className = className;

    const productDetails = document.createElement('div');
    productDetails.className = 'product-details';

    const productImage = document.createElement('img');
    productImage.className = 'product-image';
    productImage.src = item.imageUrl;
    productImage.alt = item.name;

    const productName = document.createElement('span');
    productName.className = 'product-name';
    productName.textContent = item.name;

    productDetails.appendChild(productImage);
    productDetails.appendChild(productName);

    if (className === 'basket-item') {
        const quantity = document.createElement('span');
        quantity.className = 'quantity';
        quantity.textContent = `Quantity: ${item.quantity}`;

        const price = document.createElement('span');
        price.className = 'price';
        price.textContent = `Price: ${item.price} TL`;

        listItem.appendChild(productDetails);
        listItem.appendChild(quantity);
        listItem.appendChild(price);
    } else if (className === 'recommended-item') {
  const quantity = document.createElement('span');

         quantity.textContent = `Quantity: ${item.quantity}`;

         const price = document.createElement('span');
         price.className = 'price';
         price.textContent = `Price: ${item.price} TL`;

         listItem.appendChild(productDetails);

         listItem.appendChild(price);
    }

    return listItem;
}

// Ödeme Yap butonu işlevselliği
const paymentButton = document.getElementById('payment-button');
paymentButton.addEventListener('click', function () {
    alert('Ödeme Yap tıklandı!');
});
