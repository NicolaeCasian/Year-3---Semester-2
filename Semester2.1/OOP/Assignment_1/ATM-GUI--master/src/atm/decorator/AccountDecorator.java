package atm.decorator;

import atm.factory.AbstractAccount;

// Abstract decorator that extends Account and delegates to the wrapped Account
public abstract class AccountDecorator extends AbstractAccount {
    protected AbstractAccount account; // The Account object being wrapped

    // Constructor takes an Account object to wrap and decorate
    public AccountDecorator(AbstractAccount account) {
        super(account.getBalance(), account.getAccountType(), account.getAccountID());
        this.account = account;
    }

    @Override
    public void withdraw(double amount, String txnType) {
        account.withdraw(amount, txnType);  // Delegate to the wrapped account
    }

    @Override
    public void transfer(double amount, AbstractAccount toAccount) {
        account.transfer(amount, toAccount);  // Delegate to the wrapped account
    }
}
