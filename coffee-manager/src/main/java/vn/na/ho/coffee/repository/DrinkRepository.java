package vn.na.ho.coffee.repository;

import vn.na.ho.coffee.exception.OperationException;
import vn.na.ho.coffee.model.Drink;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DrinkRepository implements IDrinkRepository {
    private static final String INSERT_DRINKS_SQL = "INSERT INTO drinks (name, quantity, price) VALUES (?, ?, ?);";
    private static final String SELECT_DRINK_BY_ID= "select * from drinks where id =?";
    private static final String SELECT_DRINK_BY_NAME= "select * from drinks where name =\"?\"";

    private static final String SELECT_ALL_DRINK = "select * from drinks";
    private static final String UPDATE_DRINKS_SQL = "update drinks set quantity = ?,price = ? where id = ?;";



    public DrinkRepository() {
    }

    @Override
    public Drink getById(int id) {

        try (Connection connection = MysqlConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DRINK_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                return getDrink(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;


    }

    @Override
    public List<Drink> getDrinks() {
       List<Drink> drinks = new ArrayList<>();
        try (Connection connection = MysqlConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DRINK)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                drinks.add(getDrink(rs));
            }
        } catch (SQLException e) {
            throw new OperationException(e);
        }
        return drinks;


    }

    @Override
    public boolean exist(int id) {
        return getById(id) != null;
    }

    @Override
    public void add(Drink newDrink) {

        System.out.println(INSERT_DRINKS_SQL);
        try (Connection connection = MysqlConnection.getInstance().getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DRINKS_SQL)) {

            preparedStatement.setString(1, newDrink.getName());
            preparedStatement.setInt(2, newDrink.getQuantity());
            preparedStatement.setLong(3, newDrink.getPrice());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new OperationException(e);
        }

    }

    @Override
    public Drink update(Drink drink) {
        try (Connection connection = MysqlConnection.getInstance().getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_DRINKS_SQL);) {
            statement.setInt(3, drink.getId());
            statement.setInt(1, drink.getQuantity());
            statement.setLong(2, drink.getPrice());

            statement.executeUpdate();
            return getById(drink.getId());
        } catch (SQLException e) {
            throw new OperationException(e);
        }
        }

    @Override
    public boolean existByName(String name) {

            try (Connection connection = MysqlConnection.getInstance().getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DRINK_BY_NAME);) {
                preparedStatement.setString(1, name);
                System.out.println(preparedStatement);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }

    @Override
    public List<Drink> selectAllDrinks() {
        List<Drink> drinks = new ArrayList<>();

        try (Connection connection = MysqlConnection.getInstance().getConnection();


             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DRINK);) {
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();


            while (rs.next()) {
                drinks.add(getDrink(rs));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return drinks;
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


    private Drink getDrink(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        int quantity = rs.getInt("quantity");
        long price = rs.getLong("price");
        return new Drink(id, name, quantity,price);

    }





}
