package application.service;

import application.db.entity.User;
import application.db.repository.UsersRepository;
import application.exception.UserNotFoundException;
import application.model.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;

    private final RedisTemplate redisTemplate;

    private final String HASH_KEY = "Email";

    public UserServiceImpl(UsersRepository usersRepository, RedisTemplate redisTemplate) {
        this.usersRepository = usersRepository;
        this.redisTemplate = redisTemplate;
    }

    @Override
    @Transactional
    @CacheEvict(value = "user", key = "#userObj.email")
    public UserDto createUser(UserDto userDto) {
        User request = new User();
        BeanUtils.copyProperties(userDto, request);
        UserDto cacheUser = isUserExistsInCache(userDto.getEmail());
        if (cacheUser == null) {
            User user = this.usersRepository.saveAndFlush(request);
            BeanUtils.copyProperties(user, userDto);
            redisTemplate.opsForHash().put(HASH_KEY, userDto.getEmail(), userDto);
        } else {
            BeanUtils.copyProperties(cacheUser, userDto);
        }
        return userDto;
    }

    @Override
    @Cacheable(value = "user", key = "#email")
    public UserDto findUserByEmail(String email) {
        UserDto userDto = new UserDto();
        UserDto cacheUser = isUserExistsInCache(email);
        if (cacheUser == null) {
            User user = this.usersRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User Not Found"));
            BeanUtils.copyProperties(user, userDto);
            return userDto;
        }
        BeanUtils.copyProperties(cacheUser, userDto);
        return userDto;
    }

    private UserDto isUserExistsInCache(String email) {
        return (UserDto) redisTemplate.opsForHash().get(HASH_KEY, email);
    }
}
