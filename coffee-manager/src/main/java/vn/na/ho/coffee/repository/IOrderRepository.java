package vn.na.ho.coffee.repository;


import vn.na.ho.coffee.model.Order;
import java.util.List;

public interface IOrderRepository {

   List<Order> getOrder();

   void add(Order newOrder);
   void update(Order order);
    List<Order> selectAllOrders();

   int getIdOrder();


}
