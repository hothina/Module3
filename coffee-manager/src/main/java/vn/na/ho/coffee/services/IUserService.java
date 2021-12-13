package vn.na.ho.coffee.services;


import vn.na.ho.coffee.model.User;

import java.util.List;

public interface IUserService {
    void login(String username, String password);

    User getById(int id);

    List<User> getUsers();

    void addUser(User user);

    User updateUser(User user);


    List<User> selectAllUsers();


}
