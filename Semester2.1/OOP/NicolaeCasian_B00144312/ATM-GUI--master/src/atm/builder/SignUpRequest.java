package atm.builder;

import atm.factory.AbstractAccount;
import atm.factory.SavingsAccount;
import atm.factory.CheckingAccount;
import java.util.concurrent.atomic.AtomicInteger;

public class SignUpRequest {
    private String username;
    private String password;
    private AbstractAccount account;

    private SignUpRequest(Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.account  = builder.account;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    /** <-- This is what your Bank.java needs to call */
    public AbstractAccount getAccount() {
        return account;
    }

    public static class Builder {
        private static final AtomicInteger ACCOUNT_ID_SEQUENCE = new AtomicInteger(1000);

        private String username;
        private String password;
        private AbstractAccount account;

        public Builder(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public Builder setAccountType(String type, double balance) {
            int newId = generateAccountID();
            String t = type.toLowerCase();
            if (t.equals("savings") || t.equals("saving")) {
                this.account = new SavingsAccount(balance, type, newId);
            } else if (t.equals("checking")) {
                this.account = new CheckingAccount(balance, type, newId);
            } else {
                throw new IllegalArgumentException("Invalid account type: " + type);
            }
            return this;
        }

        private int generateAccountID() {
            // Sequential unique IDs starting from 1000
            return ACCOUNT_ID_SEQUENCE.getAndIncrement();
        }

        public SignUpRequest build() {
            if (account == null) {
                throw new IllegalStateException("Account type must be set before building");
            }
            return new SignUpRequest(this);
        }
    }
}
