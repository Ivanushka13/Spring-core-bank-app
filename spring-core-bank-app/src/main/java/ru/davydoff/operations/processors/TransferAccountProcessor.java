package ru.davydoff.operations.processors;

import org.springframework.stereotype.Component;
import ru.davydoff.account.AccountService;
import ru.davydoff.operations.ConsoleOperationType;
import ru.davydoff.operations.OperationCommandProcessor;

import java.util.Scanner;

@Component
public class TransferAccountProcessor implements OperationCommandProcessor {

    private final Scanner scanner;

    private final AccountService accountService;

    public TransferAccountProcessor(Scanner scanner, AccountService accountService) {
        this.scanner = scanner;
        this.accountService = accountService;
    }

    @Override
    public void processOperation() {
        System.out.println("Enter source account id:");
        int fromAccountId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter destination account id:");
        int toAccountId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter amount to transfer");
        int amountToTransfer = Integer.parseInt(scanner.nextLine());
        accountService.transfer(fromAccountId, toAccountId, amountToTransfer);
        System.out.println("Successfully transferred %s from accountId=%s to accountId=%s"
                .formatted(amountToTransfer, fromAccountId, toAccountId));
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_TRANSFER;
    }
}
