package by.javaguru;

import java.util.Map;
import java.util.Optional;

public class UserService {
    private static final UserService INSTANCE = new UserService();
    private final UserDao userDao = UserDao.getInstance();

    private UserService() {
    }

    public static UserService genInstance() {
        return INSTANCE;
    }

    public Optional<UserDto> getUser(long id) {
        return userDao.findById(id).map(user -> new UserDto(user.getName()));
    }

    public Map<Long, User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public boolean changeUserNameById(long id, String newName) {
        return userDao.changeUserNameById(id, newName);
    }
}
