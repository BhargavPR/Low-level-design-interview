package Splitwise.repsitory;

import Splitwise.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private List<User> users = new ArrayList<>();

    private static UserRepository INSTANCE = null;

    public static UserRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserRepository();
        }
        return INSTANCE;
    }

    public void insertUser(String name, String email) {
        users.add(new User(name, email));
    }

    public void insertUser(User user) {
        users.add(user);
    }

    public User getUserByName(String name) {
        return users.stream()
                .filter(user -> user.getName().equals(name))
                .findAny()
                .orElse(null);
    }

    public List<User> getUsers() {
        return users;
    }
}
