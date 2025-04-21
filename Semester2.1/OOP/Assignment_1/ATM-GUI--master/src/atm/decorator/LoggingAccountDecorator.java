package atm.decorator;

import atm.factory.AbstractAccount;

public class LoggingAccountDecorator extends AccountDecorator {

    public LoggingAccountDecorator(AbstractAccount account) {
        super(account);  // Wrap the original account
    }

    @Override
    public void withdraw(double amount, String txnType) {
        System.out.println("Logging: Withdrawing " + amount + " from Account ID " + account.getAccountID());
        super.withdraw(amount, txnType);  // Add logging before delegating
    }

    @Override
    public void transfer(double amount, AbstractAccount toAccount) {
        System.out.println("Logging: Transferring " + amount + " from Account ID " + account.getAccountID() + " to Account ID " + toAccount.getAccountID());
        super.transfer(amount, toAccount);  // Add logging before delegating
    }
}
