package ru.davydoff.operations.processors;

import org.springframework.stereotype.Component;
import ru.davydoff.account.Account;
import ru.davydoff.account.AccountService;
import ru.davydoff.operations.ConsoleOperationType;
import ru.davydoff.operations.OperationCommandProcessor;
import ru.davydoff.user.User;
import ru.davydoff.user.UserService;

import java.util.Scanner;

@Component
public class CloseAccountProcessor implements OperationCommandProcessor {

    private final Scanner scanner;
    private final AccountService accountService;
    private final UserService userService;

    public CloseAccountProcessor(
            Scanner scanner,
            AccountService accountService,
            UserService userService
    ) {
        this.scanner = scanner;
        this.accountService = accountService;
        this.userService = userService;
    }

    @Override
    public void processOperation() {
        System.out.println("Enter account id to close:");
        int accountId = Integer.parseInt(scanner.nextLine());
        Account account = accountService.closeAccount(accountId);
        User user = userService.findUserById(account.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("No such user with id=%s"
                        .formatted(account.getUserId())));
        user.getAccountList().remove(account);

        System.out.println("Account successfully closed with id=%s"
                .formatted(accountId));
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_CLOSE;
    }
}
