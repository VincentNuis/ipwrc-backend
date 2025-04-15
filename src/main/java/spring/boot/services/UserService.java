package spring.boot.services;

import spring.boot.model.UserModel;

public interface UserService {
    UserModel getUser(String userName);
    void addUser(UserModel user);
    void deleteUser(String userName);
}
