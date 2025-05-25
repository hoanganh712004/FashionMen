/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function deleteCategory(address, name) {
    if (confirm('Are you sure you want to delete Category: ' + name + '?')) {
        console.log(window.location.host + address);
        window.location.href = address;
    }
}



function deleteComment(address) {
    if (confirm("Bạn có chắc chắn muốn xóa comment này không!")) {
        window.location.href = address;
    }
}

//function deleteProduct(address, name) {
//    if (confirm("Bạn có chắc chắn muốn xóa Product: " + name + "  không!")) {
//        window.location.href = address;
//    }
//}

function deleteUser(address, name) {
    if (confirm("Bạn có chắc chắn muốn xóa User: " + name + "  không!")) {
        window.location.href = address;
    }
}