package atm.decorator;

import atm.factory.AbstractAccount;

public class FeeAccountDecorator extends AccountDecorator {

    private static final double FEE = 2.0;

    public FeeAccountDecorator(AbstractAccount account) {
        super(account);  // Wrap the original account
    }

    @Override
    public void withdraw(double amount, String txnType) {
        double amountWithFee = amount + FEE;
        System.out.println("Fee Applied: " + FEE + " for withdrawal.");
        super.withdraw(amountWithFee, txnType);  // Apply the fee before delegating
    }

    @Override
    public void transfer(double amount, AbstractAccount toAccount) {
        double amountWithFee = amount + FEE;
        System.out.println("Fee Applied: " + FEE + " for transfer.");
        super.transfer(amountWithFee, toAccount);  // Apply the fee before delegating
    }
}
