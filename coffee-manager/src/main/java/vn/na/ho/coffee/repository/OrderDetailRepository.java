package vn.na.ho.coffee.repository;


import vn.na.ho.coffee.exception.OperationException;
import vn.na.ho.coffee.model.Drink;
import vn.na.ho.coffee.model.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailRepository implements IOrderDetailRepository {

    private static final String INSERT_ORDERDETAIL_SQL = "INSERT INTO orderdetail (idDrink, idOrder, quantity) VALUES (?, ?, ?);";
    private static final String SELECT_ALL_ORDERDETAIL = "select * from orderdetail";


    public OrderDetailRepository() {
    }




    @Override
    public List<OrderDetail> selectAllOrderDetail() {
        List<OrderDetail> orderDetails = new ArrayList<>();

        try (Connection connection = MysqlConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ORDERDETAIL)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                orderDetails.add(getOrderDetail(rs));
            }
        } catch (SQLException e) {
            throw new OperationException(e);
        }
        return orderDetails;
    }




    @Override
    public void add(OrderDetail newOrderDetail) {
        System.out.println(INSERT_ORDERDETAIL_SQL);
        try (Connection connection = MysqlConnection.getInstance().getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDERDETAIL_SQL)) {

            preparedStatement.setInt(1, newOrderDetail.getIdDrink());
            preparedStatement.setInt(2, newOrderDetail.getIdOrder());
            preparedStatement.setInt(3, newOrderDetail.getQuantity());

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new OperationException(e);
        }


    }



    private OrderDetail getOrderDetail(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int idDrink = rs.getInt("idDrink");
        int idOrder = rs.getInt("idOrder");
        int quantity = rs.getInt("quantity");

        return new OrderDetail(id, idDrink, idOrder, quantity);
    }

}
