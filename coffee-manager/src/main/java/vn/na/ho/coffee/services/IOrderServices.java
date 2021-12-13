package vn.na.ho.coffee.services;


import vn.na.ho.coffee.model.Order;

import java.util.List;

public interface IOrderServices {

    int getIdOrder();

    List<Order> getOrder();

    void addOrder(Order newOrder);


    public List<Order> selectAllOrders();
}
