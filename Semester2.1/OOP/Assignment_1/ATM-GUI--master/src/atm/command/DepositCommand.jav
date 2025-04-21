package atm.command;

import atm.factory.AbstractAccount;

public class DepositCommand implements Command {
    private final AbstractAccount account;
    private final double amount;

    public DepositCommand(AbstractAccount account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void execute() {
        account.deposit(amount);
    }
}