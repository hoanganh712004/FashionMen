/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function optionProduct() {
    var colors = document.getElementsByName("color");
    var sizes = document.getElementsByName("size");
    var quantity = document.getElementById("quantity");
    var tongHopOption = document.getElementById("tongHopOption");
    var giaTriCu = tongHopOption.value;

    var luaChonColor = '';
    var luaChonSize = '';
    var luaChonQuantity = '';
    for (let i = 0; i < colors.length; i++) {
        if (colors[i].checked === true) {
            luaChonColor = colors[i];
        }
    }

    for (let i = 0; i < sizes.length; i++) {
        if (sizes[i].checked === true) {
            luaChonSize = sizes[i];
        }
    }
    luaChonQuantity = quantity.value;
    if (luaChonColor != '' && luaChonSize != '' && luaChonQuantity != '') {
        if (tongHopOption.value != '') {
            tongHopOption.value += "\n" + luaChonColor.value + "-" + luaChonSize.value + "-" + '' + luaChonQuantity;
        } else {
            tongHopOption.value += luaChonColor.value + "-" + luaChonSize.value + "-" + '' + luaChonQuantity;
        }
        luaChonColor.checked = false;
        luaChonSize.checked = false;
        quantity.value = '';
    } else {
        window.alert("Vui lòng lựa chọn đầy đủ các trường thông tin: Color, Size, Quantity!");
    }
}

function submitForm() {
    var form = document.getElementById("f");
    var price = '';
    var discount = '';
    var option = '';
    var productName = '';
    productName = document.getElementById("productName").value;
    price = document.getElementById("price").value;
    discount = document.getElementById("discount").value;
    option = document.getElementById("tongHopOption").value;

    if (productName != '' && price != '' && discount != '' && option != '') {
        f.submit();
    } else {
        if (productName == '') {
            window.alert('Vui lòng nhập tên sản phẩm!');
        } else if (price == '') {
            window.alert('Vui lòng nhập giá tiền!');
        } else if (discount == '') {
            window.alert('Vui lòng nhập khuyến mãi!');
        } else if (option == '') {
            window.alert('Vui lòng nhập phân loại sản phẩm!');
        }
    }
}


function sttEdit(stt) {
    var anh = document.getElementById('anh' + stt);
    anh.value = stt;
}

