package vn.na.ho.coffee.model;


import java.io.Serializable;



public class OrderDetail implements Serializable {
    private int id;
    private int quantity;
    private long price;
    private int idDrink;
    private long total ;
    private int idOrder;

    public OrderDetail() {
    }

    public OrderDetail(int id, int idDrink, String drinkName, long price, int quantity, long total, int idOrder) {
        this.id = id;
        this.idDrink = idDrink;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
        this.idOrder = idOrder;

    }

    public OrderDetail(int id, int idDrink, int idOrder, int quantity, long price) {
        this.id = id;
        this.idDrink = idDrink;
        this.price = price;
        this.quantity = quantity;
        this.idOrder = idOrder;
    }

    public OrderDetail(int idDrink, int idOrder, int quantity, long price) {
        this.idDrink = idDrink;
        this.price = price;
        this.quantity = quantity;
        this.idOrder = idOrder;
    }

    public OrderDetail(int idDrink, int idOrder, int quantity) {
        this.idDrink = idDrink;

        this.quantity = quantity;
        this.idOrder = idOrder;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getIdDrink() {
        return idDrink;
    }

    public void setIdDrink(int idDrink) {
        this.idDrink = idDrink;
    }


    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    @Override
    public String toString() {
        return String.format("%d,%d,%s,%d,%d,%d",id, idDrink,price,quantity,total,idOrder);
    }

    public static void  transferFields(OrderDetail oldOrderDetail, OrderDetail newOrderDetail){
        oldOrderDetail.id = newOrderDetail.id;
        oldOrderDetail.quantity = newOrderDetail.quantity;
        oldOrderDetail.price = newOrderDetail.price;
        oldOrderDetail.idDrink = newOrderDetail.idDrink;
        oldOrderDetail.total = newOrderDetail.total;
        oldOrderDetail.idOrder = newOrderDetail.idOrder;
    }

}
