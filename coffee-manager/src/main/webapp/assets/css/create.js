
function addRow() {
    var select = document.getElementById("drink");
    var option = select.options[select.selectedIndex];

    var dPrice = Number(option.getAttribute("price"));
    var dQuantity=Number(document.getElementById("quantity").value);
    var dName = option.text;
    var drink = {
        id: select.value,
        name: dName,
        price: dPrice,
        quantity:dQuantity,
        total:dPrice*dQuantity
    };

    var html=`<tr>
                                    <td class="no"><input hidden  name="id" type="text"  value="${drink.id}"/>${drink.id}</td>
                                    <td class="text-left">
                                         <h3>${drink.name}</h3>
                                    </td>
                                    <td class="unit">${drink.price}</td>
                                    <td class="qty"><input hidden   name="qty" type="number"  value="${drink.quantity}"/>${drink.quantity}</td>
                                    <td class="total">${drink.price * drink.quantity}</td>
                                </tr>`;
    document.getElementById("order-detail").innerHTML+=html;
    document.getElementById("quantity").value=null;
    select.value=-1;
}