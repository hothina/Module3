package vn.na.ho.coffee.repository;


import vn.na.ho.coffee.model.User;
import vn.na.ho.coffee.model.UserStatus;

import java.sql.SQLException;
import java.util.List;


public interface IUserRepository {

    User changeStatus(int id, UserStatus status) throws SQLException;

    User getUserByUsernameAndPassword(String username, String password);

    User getById(int id);

    boolean existByPhoneNumber(String phoneNumber);

    List<User> getUsers();

    boolean exist(int id);

    void add(User newUser);

    User update(User user);

    List<User> selectAllUsers();

    boolean existByUsername(String phoneNumber);
}
