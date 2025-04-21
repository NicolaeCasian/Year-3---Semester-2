package atm.command;

public class TransferCommand implements Command {
    private double amount;
    private atm.factory.AbstractAccount fromAccount;
    private atm.factory.AbstractAccount toAccount;

    public TransferCommand(double amount, atm.factory.AbstractAccount fromAccount, atm.factory.AbstractAccount toAccount) {
        this.amount = amount;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
    }

    @Override
    public void execute() {
        fromAccount.transfer(amount, toAccount);
        
}}
