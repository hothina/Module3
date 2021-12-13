package vn.na.ho.coffee.repository;



import vn.na.ho.coffee.exception.OperationException;
import vn.na.ho.coffee.model.Drink;
import vn.na.ho.coffee.model.Order;
import vn.na.ho.coffee.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository implements IOrderRepository {
    private static final String INSERT_ORDERS_SQL = "INSERT INTO orders (nameCustomer, phoneNumber, address, createdAt, idUser) VALUES (?, ?, ?, ?, ?);";
    private static final String SELECT_ORDERS_BY_ID = "select id from orders order By id desc limit 1";
    private static final String SELECT_ALL_ORDERS = "select * from orders";
    private static final String SELECT_ORDER_BY_CREATEDAT= "select * from orders where createdAt =\"?\"";


    public OrderRepository() {
    }

    @Override
    public int getIdOrder() {
        try (Connection connection = MysqlConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDERS_BY_ID);) {
//            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Order> getOrder() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = MysqlConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ORDERS)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                orders.add(getOrder(rs));
            }
        } catch (SQLException e) {
            throw new OperationException(e);
        }
        return orders;
    }



    @Override
    public void add(Order newOrder) {
        System.out.println(INSERT_ORDERS_SQL);
        try (Connection connection = MysqlConnection.getInstance().getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDERS_SQL)) {

            preparedStatement.setString(1, newOrder.getNameCustomer());
            preparedStatement.setString(2, newOrder.getPhoneNumber());
            preparedStatement.setString(3, newOrder.getAddress());
            preparedStatement.setString(4, newOrder.getCreatedAt());
            preparedStatement.setInt(5, newOrder.getIdUser());

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new OperationException(e);
        }
    }

    @Override
    public void update(Order order) {
        List<Order> orderList = getOrder();

        for (Order or : orderList) {
            if (or.getId() == order.getId()) {
                Order.transferFields(or, order);
            }
        }

    }

    private Order getOrder(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nameCustomer = rs.getString("nameCustomer");
        String phoneNumber = rs.getString("phoneNumber");
        String address = rs.getString("address");
        String createdAt = rs.getString("createdAt");
        int idUser = rs.getInt("idUser");

        return new Order(id, nameCustomer, phoneNumber, address, createdAt, idUser);
    }

    @Override
    public List<Order> selectAllOrders() {
        List<Order> orders = new ArrayList<>();

        try (Connection connection = MysqlConnection.getInstance().getConnection();


             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ORDERS);) {
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();


            while (rs.next()) {
                orders.add(getOrder(rs));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return orders;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }


}
