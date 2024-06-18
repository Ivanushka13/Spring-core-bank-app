package ru.davydoff.user;

import org.springframework.stereotype.Service;
import ru.davydoff.account.AccountService;

import java.util.*;

@Service
public class UserService {
    private final Map<Integer, User> userMap;
    private final Set<String> takenLogins;
    private final AccountService accountService;
    private int idCounter;

    public UserService(AccountService accountService) {
        this.idCounter = 0;
        this.accountService = accountService;
        userMap = new HashMap<>();
        takenLogins = new HashSet<>();
    }

    public User createUser(String login) {
        if (takenLogins.contains(login)) {
            throw new IllegalArgumentException("User already exists with login=%s"
                    .formatted(login));
        }

        takenLogins.add(login);
        idCounter++;
        User newUser = new User(idCounter, login, new ArrayList<>());

        var newAccount = accountService.createAccount(newUser);
        newUser.getAccountList().add(newAccount);

        userMap.put(newUser.getId(), newUser);

        return newUser;
    }

    public Optional<User> findUserById(int id) {
        return Optional.ofNullable(userMap.get(id));
    }

    public List<User> getAllUsers() {
        return userMap.values().stream().toList();
    }
}
