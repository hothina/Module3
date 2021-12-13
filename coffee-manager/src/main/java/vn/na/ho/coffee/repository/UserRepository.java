package vn.na.ho.coffee.repository;


import vn.na.ho.coffee.exception.AuthorizationException;
import vn.na.ho.coffee.exception.OperationException;
import vn.na.ho.coffee.model.Role;
import vn.na.ho.coffee.model.User;
import vn.na.ho.coffee.model.UserStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {

    private static final String INSERT_USERS_SQL = "INSERT INTO users (fullName, status, role, username, birthDay, phoneNumber, address, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String SELECT_USER_BY_ID = "select * from users where id =?";
    private static final String SELECT_USER_BY_PHONE_NUMBER = "select * from users where phoneNumber =\"?\"";
    private static final String SELECT_USER_BY_USERNAME = "select * from users where username =\"?\"";
    private static final String SELECT_USER_BY_USERNAME_PASSWORD = "select * from users where username =? and password=?";
    private static final String SELECT_ALL_USERS = "select * from users";
    private static final String UPDATE_USERS_SQL = "update users set fullName = ?,username = ?,status = ?, birthDay = ?, phoneNumber = ?, address = ? where id = ?;";
    private static final String UPDATE_USER_STATUS_SQL = "update users set status = ? where id = ?;";

    public UserRepository() {
    }

    @Override
    public User changeStatus(int id, UserStatus status) {
        try (Connection connection = MysqlConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_STATUS_SQL)) {
            statement.setInt(1, status.getValue());
            statement.setInt(2, id);
            return getById(id);
        } catch (SQLException e) {
            throw new OperationException(e);
        }
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        try (Connection connection = MysqlConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_USERNAME_PASSWORD)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            while (rs.next())
                return getUser(rs);
        } catch (SQLException e) {
            throw new OperationException(e);
        }
        throw new AuthorizationException("infor user invalid");
    }

    @Override
    public User getById(int id) {
        try (Connection connection = MysqlConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                return getUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean existByPhoneNumber(String phoneNumber) {
        try (Connection connection = MysqlConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_PHONE_NUMBER);) {
            preparedStatement.setString(1, phoneNumber);
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
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = MysqlConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                users.add(getUser(rs));
            }
        } catch (SQLException e) {
            throw new OperationException(e);
        }
        return users;
    }

    @Override
    public boolean exist(int id) {
        return getById(id) != null;
    }

    @Override
    public void add(User newUser) {
        System.out.println(INSERT_USERS_SQL);
        try (Connection connection = MysqlConnection.getInstance().getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            setUser(preparedStatement, newUser);
            preparedStatement.setInt(2, newUser.getStatus().getValue());
            preparedStatement.setString(3, newUser.getRole().getValue());
            preparedStatement.setString(8, newUser.getPassword());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new OperationException(e);
        }
    }

    @Override
    public User update(User user) {
        try (Connection connection = MysqlConnection.getInstance().getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
            statement.setString(1, user.getFullName());
            statement.setString(2, user.getUsername());
            statement.setInt(3, user.getStatus().getValue());
            statement.setString(4, user.getBirthDay());
            statement.setString(5, user.getPhoneNumber());
            statement.setString(6, user.getAddress());
            statement.setInt(7, user.getId());
            statement.executeUpdate();
            return getById(user.getId());
        } catch (SQLException e) {
            throw new OperationException(e);
        }
    }

    private void setUser(PreparedStatement preparedStatement, User user) throws SQLException {
        preparedStatement.setString(1, user.getFullName());
        preparedStatement.setString(4, user.getUsername());
        preparedStatement.setString(5, user.getBirthDay());
        preparedStatement.setString(6, user.getPhoneNumber());
        preparedStatement.setString(7, user.getAddress());
    }

    private User getUser(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String fullName = rs.getString("fullName");
        String username = rs.getString("username");
        UserStatus status = UserStatus.fromValue(rs.getInt("status"));
        Role role = Role.fromValue(rs.getString("role"));
        String birthDay = rs.getString("birthDay");
        String phoneNumber = rs.getString("phoneNumber");
        String address = rs.getString("address");
        return new User(id, username, fullName, status, role, birthDay, phoneNumber, address);
    }


    @Override
    public List<User> selectAllUsers() {


        List<User> users = new ArrayList<>();

        try (Connection connection = MysqlConnection.getInstance().getConnection();


             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();


            while (rs.next()) {
                users.add(getUser(rs));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }

    @Override
    public boolean existByUsername(String username) {
        try (Connection connection = MysqlConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_USERNAME);) {
            preparedStatement.setString(1, username);
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
