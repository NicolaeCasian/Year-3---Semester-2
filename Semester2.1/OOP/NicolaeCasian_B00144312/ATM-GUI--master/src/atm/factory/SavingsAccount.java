package atm.factory;

public class SavingsAccount extends AbstractAccount {
    // Constructor for initializing properties specific to SavingsAccount
    public SavingsAccount(double balance, String accountType, int accountID) {
        super(balance, accountType, accountID);
    }

    // Method to withdraw money from the account
    // It overrides the abstract method from AbstractAccount class
    @Override
    public void withdraw(double amount, String txnType) {
        balance -= amount;
        System.out.println("Withdrawn " + amount + " from Account ID " + accountID);
    }

    //  Method to transfer money to another account
    // It overrides the abstract method from AbstractAccount class
    @Override
    public void transfer(double amount, AbstractAccount toAccount) {
        this.withdraw(amount, "Transfer");
        toAccount.setBalance(toAccount.getBalance() + amount);
        System.out.println("Transferred " + amount + " to Account ID " + toAccount.getAccountID());
    }
}
