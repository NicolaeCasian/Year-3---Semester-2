package atm.factory;

public abstract class AbstractAccount {
    protected double balance;
    protected String accountType;
    protected int accountID;

    // Constructor for initializing common properties
    public AbstractAccount(double balance, String accountType, int accountID) {
        this.balance = balance;
        this.accountType = accountType;
        this.accountID = accountID;
    }

    // Abstract methods that concrete Account classes must implement
    public abstract void withdraw(double amount, String txnType);
    public abstract void transfer(double amount, AbstractAccount toAccount);

    // Getter and Setter methods
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public int getAccountID() {
        return accountID;
    }
    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }
    public void deposit(double amount) {
        this.balance += amount;
    }
}
