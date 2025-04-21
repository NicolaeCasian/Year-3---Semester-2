package atm.command;

import atm.Bank;

// WithdrawCommand.java
public class WithdrawCommand implements Command {
    private final Bank bank;
    private final double amount;
    private final int accId;
    private final String type;

    public WithdrawCommand(Bank bank, double amount, int accId, String type) {
        this.bank   = bank;
        this.amount = amount;
        this.accId  = accId;
        this.type   = type;
    }

    @Override
    public void execute() {
        bank.withDraw(amount, accId, type);
    }
}
