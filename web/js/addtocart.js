/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function selectQuantityTru() {
    let quantity = document.getElementById("quantity");
    var currentValue = parseInt(quantity.value, 10);
    quantity.value = currentValue - 1;
    console.log(quantity.value);
}


function selectQuantityCong() {
    let quantity = document.getElementById("quantity");
    var currentValue = parseInt(quantity.value, 10);
    quantity.value = currentValue + 1;
    console.log(quantity.value);
}

function addToCart() {
    var addCart = document.getElementById("formAddToCart");
    addCart.submit();
}

