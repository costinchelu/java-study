package demo.service;

import demo.entity.User;

import java.util.List;

public interface UserService {

    User getUserById(Integer id);

    List<User> getAllUsers();

    User saveUser(User user);
}
