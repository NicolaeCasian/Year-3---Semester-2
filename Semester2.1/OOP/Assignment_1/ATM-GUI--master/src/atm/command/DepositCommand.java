package atm.command;

import atm.Bank;

// DepositCommand.java
public class DepositCommand implements Command {
    private Bank bank;
    private double amount;
    private int accId;
    private String type;

    public DepositCommand(Bank bank, double amount, int accId, String type) {
        this.bank = bank;
        this.amount = amount;
        this.accId = accId;
        this.type = type;
    }

    @Override
    public void execute() {
        bank.deposit(amount, accId, type);
    }
}
