package atm.command;

import atm.Bank;

// TransferCommand.java
public class TransferCommand implements Command {
    private final Bank bank;
    private final double amount;
    private final int fromAccId;
    private final int toAccId;

    public TransferCommand(Bank bank, double amount, int fromAccId, int toAccId) {
        this.bank      = bank;
        this.amount    = amount;
        this.fromAccId = fromAccId;
        this.toAccId   = toAccId;
    }

    @Override
    public void execute() {
        // re‑use your existing withdraw/deposit logic on Bank
        bank.withDraw(amount, fromAccId, "Transfer");
        bank.deposit(amount, toAccId,   "Transfer");
    }
}
