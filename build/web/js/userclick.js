/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this lichSuAllCategorylate
 */



var lichSuAllCategory = true;
var lichSuAllColor = true;
var lichSuAllSize = true;


function userClickedCategory() {
    // xu ly trong hop click category
    var allCategory = document.getElementsByClassName("allCategory");
    var category = document.getElementsByClassName("category");

    if (allCategory[0].checked === true && lichSuAllCategory === false) {
        lichSuAllCategory = true;
        for (let i = 0; i < category.length; i++) {
            category[i].checked = false;
        }
    } else {
        allCategory[0].checked = false;
        lichSuAllCategory = false;
    }

    var countCategoryChecked = 0;
    for (let i = 0; i < category.length; i++) {
        if (category[i].checked === true) {
            countCategoryChecked++;
        }
    }
    if (countCategoryChecked === category.length) {
        allCategory[0].checked = true;
        lichSuAllCategory = true;
        for (let i = 0; i < category.length; i++) {
            category[i].checked = false;
        }
    }
}


function userClickedSize() {
    // xu ly truong hop click size
    var allSize = document.getElementsByClassName("allSize");
    var size = document.getElementsByClassName("size");

    if (lichSuAllSize === false && allSize[0].checked === true) {
        lichSuAllSize = true;
        for (let i = 0; i < size.length; i++) {
            size[i].checked = false;
        }
    } else {
        lichSuAllSize = false;
        allSize[0].checked = false;
    }

    var countSizeChecked = 0;
    for (let i = 0; i < size.length; i++) {
        if (size[i].checked === true) {
            countSizeChecked++;
        }
    }
    if (countSizeChecked == size.length) {
        allSize[0].checked = true;
        lichSuAllSize = true;
        for (let i = 0; i < size.length; i++) {
            size[i].checked = false;
        }
    }
}


function userClickedColor() {
    // xu ly truong hop click Color
    var allColor = document.getElementsByClassName("allColor");
    var color = document.getElementsByClassName("color");
    if (lichSuAllColor === false && allColor[0].checked === true) {
        lichSuAllColor = true;
        for (let i = 0; i < color.length; i++) {
            color[i].checked = false;
        }
    } else {
        allColor[0].checked = false;
        lichSuAllColor = false;
    }

    var countColorChecked = 0;
    for (let i = 0; i < color.length; i++) {
        if (color[i].checked === true) {
            countColorChecked++;
        }
    }
    if (countColorChecked == color.length) {
        allColor[0].checked = true;
        lichSuAllColor = true;
        for (let i = 0; i < color.length; i++) {
            color[i].checked = false;
        }
    }
}

