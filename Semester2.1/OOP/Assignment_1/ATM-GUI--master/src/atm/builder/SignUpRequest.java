package atm.builder;

import atm.factory.AbstractAccount;
import atm.factory.SavingsAccount;
import atm.factory.CheckingAccount;

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
        private String username;
        private String password;
        private AbstractAccount account;

        public Builder(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public Builder setAccountType(String type, double balance) {
            String t = type.toLowerCase();
            if (t.equals("savings") || t.equals("saving")) {
                this.account = new SavingsAccount(balance, type, generateAccountID());
            } else if (t.equals("checking")) {
                this.account = new CheckingAccount(balance, type, generateAccountID());
            } else {
                throw new IllegalArgumentException("Invalid account type");
            }
            return this;
        }
        

        private int generateAccountID() {
            // In production you’d use a proper sequence or UUID, not random
            return (int)(Math.random() * 10000);
        }

        public SignUpRequest build() {
            return new SignUpRequest(this);
        }
    }
}
