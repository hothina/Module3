package vn.na.ho.coffee.services;


import vn.na.ho.coffee.exception.ExitsException;
import vn.na.ho.coffee.exception.NotFoundException;
import vn.na.ho.coffee.model.Role;
import vn.na.ho.coffee.model.User;
import vn.na.ho.coffee.model.UserStatus;
import vn.na.ho.coffee.repository.IUserRepository;
import vn.na.ho.coffee.repository.UserRepository;

import java.util.List;

public class UserServices implements IUserService {

    private IUserRepository userRepository;

    public UserServices() {
        userRepository = new UserRepository();
    }


    @Override
    public void login(String username, String password) {
        userRepository.getUserByUsernameAndPassword(username, password);
    }

    @Override
    public User getById(int id) {
        User user = userRepository.getById(id);
        if (user == null)
            throw new NotFoundException("User not found");
        return user;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    @Override
    public void addUser(User newUser) {
        if (userRepository.existByPhoneNumber(newUser.getPhoneNumber()))
            throw new ExitsException("mobile already exist");
        newUser.setStatus(UserStatus.AVAILABLE);
        newUser.setRole(Role.USER);
        userRepository.add(newUser);

    }

    @Override
    public User updateUser(User user) {
        if (userRepository.exist(user.getId())) {
            if (userRepository.existByPhoneNumber(user.getPhoneNumber()))
                throw new ExitsException("mobile  already exists");
            if (userRepository.existByUsername(user.getUsername()))
                throw new ExitsException("username  already exists");
            return userRepository.update(user);
        } else
            throw new NotFoundException("user not already exists");

    }

    @Override
    public List<User> selectAllUsers() {
        List<User> userList = userRepository.selectAllUsers();

        if (userList == null)
            throw new NotFoundException("user not already exists");
        userRepository.selectAllUsers();


        return userList;
    }


}
