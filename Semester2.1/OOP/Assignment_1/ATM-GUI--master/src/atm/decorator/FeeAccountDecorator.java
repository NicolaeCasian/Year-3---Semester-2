package atm.decorator;

import atm.factory.AbstractAccount;

public class FeeAccountDecorator extends AccountDecorator {
      // Fixed fee for each transaction
    private static final double FEE = 2.0;
  
    // This decorator adds a fee to the withdrawal and transfer operations
    public FeeAccountDecorator(AbstractAccount account) {
        super(account);  
    }

    // Override the withdraw method to apply a fee
    @Override
    public void withdraw(double amount, String txnType) {
        double amountWithFee = amount + FEE;
        System.out.println("Fee Applied: " + FEE + " for withdrawal.");
        super.withdraw(amountWithFee, txnType);  // Apply the fee before delegating
    }
    // Override the transfer method to apply a fee
    @Override
    public void transfer(double amount, AbstractAccount toAccount) {
        double amountWithFee = amount + FEE;
        System.out.println("Fee Applied: " + FEE + " for transfer.");
        super.transfer(amountWithFee, toAccount);  // Apply the fee before delegating
    }
}
