package vn.na.ho.coffee.model;

import java.io.Serializable;


public class Order implements Serializable {
    private int id;
    private String nameCustomer;
    private String phoneNumber;
    private String address;
    private String createdAt;
    private int idUser;

    public Order(int id, String nameCustomer, String phoneNumber, String address, String createdAt) {
        this.id = id;
        this.nameCustomer = nameCustomer;
        this.phoneNumber = phoneNumber;
        this.address = address;

        this.createdAt = createdAt;

    }
    public Order( String nameCustomer, String phoneNumber, String address, String createdAt,int idUser) {

        this.nameCustomer = nameCustomer;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.createdAt = createdAt;
        this.idUser = idUser;

    }

    public Order(int id, String nameCustomer, String phoneNumber, String address, String createdAt, int idUser) {
        this.id = id;
        this.nameCustomer = nameCustomer;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.createdAt = createdAt;
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public static void  transferFields(Order oldOrder, Order newOrder){
        oldOrder.id = newOrder.id;
        oldOrder.nameCustomer = newOrder.nameCustomer;
        oldOrder.phoneNumber = newOrder.phoneNumber;
        oldOrder.address = newOrder.address;
        oldOrder.idUser = newOrder.idUser;

        oldOrder.createdAt = newOrder.createdAt;

    }

    @Override
    public String toString() {
        return String.format("%d,%s,%s,%s,%s,%s",id, nameCustomer,phoneNumber,address,createdAt,idUser) ;
    }

    public Order(String raw){
        String[] fileds = raw.split(",");
        id = Integer.parseInt(fileds[0]);
        nameCustomer = fileds[1];
        phoneNumber = fileds[2];
        address = fileds[3];
        createdAt =fileds[4];
        idUser = Integer.parseInt(fileds[5]);
    }
}
