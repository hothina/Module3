package vn.na.ho.coffee.services;


import vn.na.ho.coffee.model.OrderDetail;

import java.util.List;

public interface IOrderDetailServices {


    List<OrderDetail> selectAllOrderDetail();
    void addOrderDetail(OrderDetail newOrderDetail);

}
