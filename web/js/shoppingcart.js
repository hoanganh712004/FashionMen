/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


//function xacNhanXoaKhiThayDoiSoLuongVe0(i) {
//    var form = document.getElementById("f");
//    var soLuong_raw = document.getElementById("quantity" + i).value;
//    var soLuong = parseInt(soLuong_raw, 10);
//
//    if (soLuong == 1) {
//
//    } else {
//        form.submit();
//    }
//}


function deleteProduct(productId) {
    
    // Tạo form gửi yêu cầu xóa sản phẩm
    var form = document.getElementById('f' + productId);  // Lấy form tương ứng với sản phẩm
    var hiddenInput = document.createElement('input');  // Tạo một input ẩn để gửi thông tin sản phẩm
    hiddenInput.type = 'hidden';
    hiddenInput.name = 'action';
    hiddenInput.value = 'delete';  // Xác định hành động xóa
    
    form.appendChild(hiddenInput);  // Thêm input vào form
    form.submit();  // Gửi form
}

function clearAll() {
    var form = document.getElementById("fclear");
    if (confirm("Bạn có chắc chắn muốn xóa sản phẩm này không!!!")) {
        form.submit();
    }
}