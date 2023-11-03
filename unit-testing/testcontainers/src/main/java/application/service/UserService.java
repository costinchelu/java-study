package application.service;

import application.model.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto findUserByEmail(String email);
}
