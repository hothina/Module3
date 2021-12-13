package vn.na.ho.coffee.repository;


import vn.na.ho.coffee.model.OrderDetail;

import java.util.List;

public interface IOrderDetailRepository {

    List<OrderDetail> selectAllOrderDetail();

    void add(OrderDetail newOrderDetail) ;

}
