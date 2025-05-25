/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function optionSort() {
    var form = document.getElementById("f");
    var selected = document.getElementById("orderBy");

    var query = document.getElementById("keyword").value;
    
    // Kiểm tra nếu có từ khóa tìm kiếm (query/keyword)
    if (query !== "") {
        // Nếu query không rỗng, thêm query vào URL
        var url = form.action + "?keyword=" + encodeURIComponent(query) + "&orderBy=" + selected.value;
        window.location.href = url; // Chuyển hướng đến URL mới
    } else {
        // Nếu query rỗng, gửi form
        form.submit();
    }
}




