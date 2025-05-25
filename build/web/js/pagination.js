/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function pagination(page) {
    var orderBy = document.getElementById("orderBy").value;
    var form = document.getElementById("f");
    var addressOld = window.location.href;
    var address = addressOld.split("&page");
    var adderssNew = address[0];
    window.location.href = adderssNew + "&page=" + page;
}