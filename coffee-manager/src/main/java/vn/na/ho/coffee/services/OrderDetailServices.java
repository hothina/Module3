package vn.na.ho.coffee.services;


import vn.na.ho.coffee.exception.NotFoundException;
import vn.na.ho.coffee.model.Drink;
import vn.na.ho.coffee.model.OrderDetail;
import vn.na.ho.coffee.repository.IOrderDetailRepository;
import vn.na.ho.coffee.repository.OrderDetailRepository;

import java.util.List;

public class OrderDetailServices implements IOrderDetailServices {
    private IOrderDetailRepository orderDetailRepository;

    public OrderDetailServices() {
        orderDetailRepository = new OrderDetailRepository();
    }




    @Override
    public List<OrderDetail> selectAllOrderDetail() {
        List<OrderDetail> orderDetailList = orderDetailRepository.selectAllOrderDetail();

        if (orderDetailList == null)
            throw new NotFoundException("orderDetail not already exists");
        orderDetailRepository.selectAllOrderDetail();


        return orderDetailList;
    }

    @Override
    public void addOrderDetail(OrderDetail newOrderDetail) {

        orderDetailRepository.add(newOrderDetail);
    }
}
