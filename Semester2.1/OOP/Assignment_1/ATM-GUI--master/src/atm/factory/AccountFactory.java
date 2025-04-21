package atm.factory;

public class AccountFactory {
    private static int accountIdCounter = 1; // Static counter for account IDs

    public static AbstractAccount createAccount(double balance, String type) {
        int accountId = accountIdCounter++; // Generate a new account ID
        
        // Create the appropriate type of Account based on the type
        switch(type.toLowerCase()) {
            case "savings":
                return new SavingsAccount(balance, type, accountId);  // Create a SavingsAccount
            case "checking":
                return new CheckingAccount(balance, type, accountId);  // Create a CheckingAccount
            default:
                throw new IllegalArgumentException("Invalid account type: " + type);
        }
    }
}
