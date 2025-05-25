/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function addWallet(walletId, address) {
    console.log(walletId);
    console.log(address);
    var balance = prompt('Vui lòng nhập số tiền muốn nạp');
    if(balance !== ''){
        window.location.href = address + balance;
    }
}
