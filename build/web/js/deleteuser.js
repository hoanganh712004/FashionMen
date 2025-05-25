/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function deleteUser(userId) {
        console.log(window.location.origin)
    if(confirm("Are you sure you want to delete this product?")){
        window.location.href = window.location.origin + '/360-fashion-men-vn/' + 'deleteuser?userId=' + userId;
    }
}
