package atm.factory;

public class CheckingAccount extends AbstractAccount {
    // Constructor for initializing properties specific to CheckingAccount
    public CheckingAccount(double balance, String accountType, int accountID) {
        super(balance, accountType, accountID);
    }

    // Implementing the abstract methods from AbstractAccount
    @Override
    public void withdraw(double amount, String txnType) {
        balance -= amount;
        System.out.println("Withdrawn " + amount + " from Account ID " + accountID);
    }

    // Implementing the transfer method for CheckingAccount
    @Override
    public void transfer(double amount, AbstractAccount toAccount) {
        this.withdraw(amount, "Transfer");
        toAccount.setBalance(toAccount.getBalance() + amount);
        System.out.println("Transferred " + amount + " to Account ID " + toAccount.getAccountID());
    }
}
