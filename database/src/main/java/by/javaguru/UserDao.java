package by.javaguru;

import java.util.List;
import java.util.Optional;

public class UserDao {
    private List<User> users = List.of(
            new User("Ivan"),
            new User("Nikolay"),
            new User("Sergey")
    );

    public Optional<User> findById(Long id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }
}
