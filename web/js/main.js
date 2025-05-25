/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

var colorId = null;
var sizeId = null;
function userOptionColor(element, id, pid) {
    // Lấy danh sách tất cả các phần tử li trong cùng danh sách
    var listItems = element.parentNode.getElementsByTagName('li');
    var form = document.getElementById("f");
    for (var i = 0; i < listItems.length; i++) {
        listItems[i].classList.remove('selected');
    }

    element.classList.add('selected');
    colorId = id;
    console.log(sizeId);
    console.log(colorId);
    if (sizeId != null && colorId != null) {
        f.action = "productdetail?colorid=" + colorId + "&sizeid=" + sizeId + "&productid=" + pid;
        colorId = null;
        sizeId = null;
        f.submit();
    }
}


function userOptionSize(element, id, pid) {
// Lấy danh sách tất cả các phần tử li trong cùng danh sách
    var listItems = element.parentNode.getElementsByTagName('li');
    var form = document.getElementById("f");
    for (var i = 0; i < listItems.length; i++) {
        listItems[i].classList.remove('selected');
    }

// Thêm lớp 'selected' cho phần tử được click
    element.classList.add('selected');
    sizeId = id;
    console.log(sizeId);
    console.log(colorId);
    if (sizeId != null && colorId != null) {
        f.action = "productdetail?colorid=" + colorId + "&sizeid=" + sizeId + "&productid=" + pid;
        colorId = null;
        sizeId = null;
        f.submit();
    }
}












