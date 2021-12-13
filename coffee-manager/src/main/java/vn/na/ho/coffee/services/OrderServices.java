package vn.na.ho.coffee.services;


import vn.na.ho.coffee.exception.ExitsException;
import vn.na.ho.coffee.exception.NotFoundException;
import vn.na.ho.coffee.model.Drink;
import vn.na.ho.coffee.model.Order;
import vn.na.ho.coffee.repository.IOrderRepository;
import vn.na.ho.coffee.repository.OrderRepository;

import java.util.List;

public class OrderServices implements IOrderServices {

    private IOrderRepository orderRepository;

    public OrderServices(){
        orderRepository = new OrderRepository();
    }
    @Override
    public int getIdOrder() {
        int order = orderRepository.getIdOrder();
        if (order == 0)
            throw new NotFoundException("Order not found");
        return order;

    }

    @Override
    public List<Order> getOrder() {
        return orderRepository.getOrder();
    }

    @Override
    public void addOrder(Order newOrder) {

        orderRepository.add(newOrder);


    }




    @Override
    public List<Order> selectAllOrders() {
        List<Order> orderList = orderRepository.selectAllOrders();

        if (orderList == null)
            throw new NotFoundException("order not already exists");
        orderRepository.selectAllOrders();


        return orderList;
    }
}
