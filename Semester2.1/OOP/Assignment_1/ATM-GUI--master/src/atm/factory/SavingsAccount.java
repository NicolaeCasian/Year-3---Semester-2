package atm.factory;

public class SavingsAccount extends AbstractAccount {

    public SavingsAccount(double balance, String accountType, int accountID) {
        super(balance, accountType, accountID);
    }

    @Override
    public void withdraw(double amount, String txnType) {
        balance -= amount;
        System.out.println("Withdrawn " + amount + " from Account ID " + accountID);
    }

    @Override
    public void transfer(double amount, AbstractAccount toAccount) {
        this.withdraw(amount, "Transfer");
        toAccount.setBalance(toAccount.getBalance() + amount);
        System.out.println("Transferred " + amount + " to Account ID " + toAccount.getAccountID());
    }
}
