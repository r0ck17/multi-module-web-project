package by.javaguru;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserDao {
    private static final UserDao INSTANCE = new UserDao();
    private static final String pathJson = "database.json";
    private static final File file = new File(pathJson).getAbsoluteFile();


    private UserDao() {
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }

    public Map<Long, User> getAllUsers() {
        return getUserFromJson();
    }

    public Optional<User> findById(long id) {
        Map<Long, User> users = getUserFromJson();
        return Optional.ofNullable(users.get(id));
    }

    public boolean changeUserNameById(long id, String newName) {
        Map<Long, User> users = getUserFromJson();
        User updatedUser = users.computeIfPresent(id, (userId, user) -> {
            user.setName(newName);
            return user;
        });

        writeUsersToJson(users);
        return Objects.nonNull(updatedUser);
    }

    public static void generateDataToJson() {
        if (file.exists()) {
            return;
        }

        final String[] names = {"Nikolay", "Olga", "Dmitriy", "Svyatoslav", "Igor"};
        Supplier<User> userGenerator =
                () -> new User(names[(int) (Math.random() * names.length)]);
        Map<Long, User> users = Stream.generate(userGenerator)
                .limit(15)
                .collect(Collectors.toMap(User::getId, Function.identity()));

        writeUsersToJson(users);
    }

    private Map<Long, User> getUserFromJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<Long, User> users = objectMapper.readValue(file,
                    new TypeReference<Map<Long, User>>() {});
            return users;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeUsersToJson(Map<Long, User> users) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();

        try {
            objectWriter.writeValue(file, users);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
