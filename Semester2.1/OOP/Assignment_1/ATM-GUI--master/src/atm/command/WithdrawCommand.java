package atm.command;
import atm.factory.AbstractAccount;
public class WithdrawCommand implements Command {
    private AbstractAccount account;
    private double amount;

    // New constructor to match the parameters
    public WithdrawCommand(AbstractAccount account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void execute() {
        account.withdraw(amount, "Withdrawal");
    }
}