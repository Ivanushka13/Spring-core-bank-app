package ru.davydoff.operations.processors;

import org.springframework.stereotype.Component;
import ru.davydoff.account.AccountService;
import ru.davydoff.operations.ConsoleOperationType;
import ru.davydoff.operations.OperationCommandProcessor;

import java.util.Scanner;

@Component
public class WithdrawAccountProcessor implements OperationCommandProcessor {

    private final Scanner scanner;

    private final AccountService accountService;

    public WithdrawAccountProcessor(Scanner scanner, AccountService accountService) {
        this.scanner = scanner;
        this.accountService = accountService;
    }

    @Override
    public void processOperation() {
        System.out.println("Enter account id:");
        int accountId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter amount to withdraw:");
        int amountToWithdraw = Integer.parseInt(scanner.nextLine());
        accountService.withdrawFromAccount(accountId, amountToWithdraw);
        System.out.println("Successfully withdrawn amount=%s from accountId=%s"
                .formatted(amountToWithdraw, accountId));
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_WITHDRAW;
    }
}
