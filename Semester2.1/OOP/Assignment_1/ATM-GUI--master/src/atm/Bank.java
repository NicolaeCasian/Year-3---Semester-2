package atm;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import atm.builder.SignUpRequest;
import atm.factory.AbstractAccount;
import atm.factory.AccountFactory;
import atm.decorator.FeeAccountDecorator;
import atm.decorator.LoggingAccountDecorator;
import atm.command.Command;
import atm.command.TransferCommand;
import atm.command.DepositCommand;
import atm.command.WithdrawCommand;

public class Bank extends Frame implements ActionListener {

    private JFrame jSignPage, jMenu, j4, newCreateAcc, j1, depositF, j2, j3, jCreatAcc, jCreateAcc;
    private Label lblInput, lblOutput, lblName, lblPass;
    private TextField tfName, tfPass, tfUserName, tfUserPass, tfBal, tfAccId;
    private TextField transFromAccId, transInAccId, transAmount;
    private TextField delAccId;
    private TextField depAccId, depAmount, depType;
    private TextField withAccId, withAmount, withType;
    private TextField showAccId;

    private JButton signUp, signIn, JCreateUser;
    private JButton transfer, createAccount, deleteAccount, deposit,
            withDraw, showAcc, showAllAcc, delUser, logOut, quit;
    private JButton transTranfer, depBut, withWithDraw, show;

    private String accType;
    private Calendar rightNow;
    private User loggedInUser;

    // store AbstractAccount for Factory/Decorator
    private HashMap<User, ArrayList<AbstractAccount>> users;

    public Bank() {
        users = new HashMap<>();
        rightNow = Calendar.getInstance();
    }

    public static void main(String[] args) {
        new Bank().showLoginMenu();
    }

    @Override public void actionPerformed(ActionEvent e) { /* unused */ }

    public void showCreateAccMenu() {
        jCreateAcc = new JFrame();
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        jCreateAcc.add(panel, BorderLayout.CENTER);

        panel.add(new JLabel("Create Account"));
        panel.add(new JLabel("Enter Username:"));
        panel.add(tfUserName = new TextField(10));
        panel.add(new JLabel("Enter Password:"));
        panel.add(tfUserPass = new TextField(10));
        panel.add(new JLabel("Initial Balance:"));
        panel.add(tfBal = new TextField(10));

        JButton createButton = new JButton("Create");
        panel.add(createButton);
        createButton.addActionListener(e -> {
            try {
                String username = tfUserName.getText();
                String password = tfUserPass.getText();
                double balance = Double.parseDouble(tfBal.getText());
                User newUser = new User(username, password);
                users.put(newUser, new ArrayList<>());
                JOptionPane.showMessageDialog(null, "Account created successfully!");
                jCreateAcc.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error creating account: " + ex.getMessage());
            }
        });

        jCreateAcc.setTitle("Create Account");
        jCreateAcc.setSize(400, 300);
        jCreateAcc.setLocationRelativeTo(null);
        jCreateAcc.setVisible(true);
    }

    public void showLoginMenu() {
        jSignPage = new JFrame();
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        jSignPage.add(mainPanel, BorderLayout.CENTER);

        mainPanel.add(new JLabel(" Sign up / Login "));
        mainPanel.add(lblInput = new Label("Username:"));
        mainPanel.add(tfName = new TextField(10));
        mainPanel.add(lblOutput = new Label("Password:"));
        mainPanel.add(tfPass = new TextField(10));

        mainPanel.add(signUp = new JButton("Sign Up"));
        signUp.addActionListener(e -> showCreateAccMenu());

        mainPanel.add(signIn = new JButton("Sign In"));
        signIn.addActionListener(e -> {
            boolean succ = false;
            for (User u : users.keySet()) {
                if (u.getName().equals(tfName.getText())
                 && u.getPassword().equals(tfPass.getText())) {
                    loggedInUser = u;
                    jSignPage.dispose();
                    showMainMenu();
                    succ = true;
                    break;
                }
            }
            if (!succ) JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
        });

        jSignPage.setTitle("ATM Machine");
        jSignPage.setSize(500,250);
        jSignPage.setLocationRelativeTo(null);
        jSignPage.setVisible(true);
    }

    public void showMainMenu() {
        jMenu = new JFrame();
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        jMenu.add(mainPanel, BorderLayout.CENTER);

        mainPanel.add(new JLabel(" Hi " + loggedInUser.getName() + ", what next?"));

        // 1. Transfer
        mainPanel.add(transfer = new JButton("1. Transfer"));
        transfer.addActionListener(e -> {
            j4 = new JFrame();
            JPanel p = new JPanel();
            p.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
            p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
            j4.add(p, BorderLayout.CENTER);

            p.add(new JLabel("From Account ID:"));
            p.add(transFromAccId = new TextField());
            p.add(new JLabel("To Account ID:"));
            p.add(transInAccId = new TextField());
            p.add(new JLabel("Amount:"));
            p.add(transAmount = new TextField());

            p.add(transTranfer = new JButton("Transfer"));
            transTranfer.addActionListener(ev -> {
                try {
                    int from = Integer.parseInt(transFromAccId.getText());
                    int to   = Integer.parseInt(transInAccId.getText());
                    double amt = Double.parseDouble(transAmount.getText());
                    AbstractAccount aFrom = null, aTo = null;
                    for (AbstractAccount a: users.get(loggedInUser)) {
                        if (a.getAccountID()==from) aFrom=a;
                        if (a.getAccountID()==to)   aTo=a;
                    }
                    if (aFrom==null||aTo==null) throw new Exception("Acct not found");
                    Command cmd = new TransferCommand(amt, aFrom, aTo);
                    cmd.execute();
                    JOptionPane.showMessageDialog(null, "Transfer successful");
                    j4.dispose();
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(null, "Transfer failed: "+ex.getMessage());
                }
            });

            j4.setTitle("Transfer");
            j4.setSize(400,400);
            j4.setLocationRelativeTo(null);
            j4.setVisible(true);
            j4.pack();
        });

        // 2. Create Account
        mainPanel.add(createAccount = new JButton("2. Create Account"));
        createAccount.addActionListener(e -> showCreateAccMenu());

        // 3. Delete Account
        mainPanel.add(deleteAccount = new JButton("3. Delete Account"));
        deleteAccount.addActionListener(e -> {
            j1 = new JFrame();
            JPanel p = new JPanel();
            p.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
            p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
            j1.add(p, BorderLayout.CENTER);
            p.add(new JLabel("Account ID to delete:"));
            p.add(delAccId = new TextField());
            JButton c = new JButton("Confirm");
            p.add(c);
            c.addActionListener(ev -> {
                try {
                    int id = Integer.parseInt(delAccId.getText());
                    users.get(loggedInUser).removeIf(a->a.getAccountID()==id);
                    JOptionPane.showMessageDialog(null, "Deleted if existed");
                    j1.dispose();
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid ID");
                }
            });
            j1.setTitle("Delete"); j1.setSize(300,200);
            j1.setLocationRelativeTo(null); j1.setVisible(true); j1.pack();
        });

        // 4. Deposit
        mainPanel.add(deposit = new JButton("4. Deposit"));
        deposit.addActionListener(e -> {
            depositF = new JFrame();
            JPanel p = new JPanel();
            p.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
            p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
            depositF.add(p, BorderLayout.CENTER);
            p.add(new JLabel("Acct ID:")); p.add(depAccId = new TextField());
            p.add(new JLabel("Amount:")); p.add(depAmount = new TextField());
            p.add(depBut = new JButton("Deposit"));
            depBut.addActionListener(ev -> {
                try {
                    int id = Integer.parseInt(depAccId.getText());
                    double amt = Double.parseDouble(depAmount.getText());
                    AbstractAccount a=null;
                    for(AbstractAccount x: users.get(loggedInUser))
                        if(x.getAccountID()==id) a=x;
                    if(a==null) throw new Exception("Not found");
                    Command cmd = new DepositCommand(a, amt);
                    cmd.execute();
                    JOptionPane.showMessageDialog(null,"Deposit successful");
                    depositF.dispose();
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(null,"Deposit failed: "+ex.getMessage());
                }
            });
            depositF.setTitle("Deposit");
            depositF.setSize(300,200);
            depositF.setLocationRelativeTo(null);
            depositF.setVisible(true);
            depositF.pack();
        });

        // 5. Withdraw
        mainPanel.add(withDraw = new JButton("5. Withdraw"));
        withDraw.addActionListener(e -> {
            j2 = new JFrame();
            JPanel p = new JPanel();
            p.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
            p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
            j2.add(p, BorderLayout.CENTER);
            p.add(new JLabel("Acct ID:")); p.add(withAccId = new TextField());
            p.add(new JLabel("Amount:")); p.add(withAmount = new TextField());
            p.add(withWithDraw = new JButton("Withdraw"));
            withWithDraw.addActionListener(ev -> {
                try {
                    int id = Integer.parseInt(withAccId.getText());
                    double amt = Double.parseDouble(withAmount.getText());
                    AbstractAccount a=null;
                    for(AbstractAccount x: users.get(loggedInUser))
                        if(x.getAccountID()==id) a=x;
                    if(a==null) throw new Exception("Not found");
                    Command cmd = new WithdrawCommand(a, amt);
                    cmd.execute();
                    JOptionPane.showMessageDialog(null,"Withdrawal successful");
                    j2.dispose();
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(null,"Withdraw failed: "+ex.getMessage());
                }
            });
            j2.setTitle("Withdraw");
            j2.setSize(300,200);
            j2.setLocationRelativeTo(null);
            j2.setVisible(true);
            j2.pack();
        });

        // 6. Show all
        mainPanel.add(showAllAcc = new JButton("6. Show All Accounts"));
        showAllAcc.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            for(AbstractAccount a: users.get(loggedInUser)) {
                sb.append("ID:").append(a.getAccountID())
                  .append(" Type:").append(a.getAccountType())
                  .append(" Bal:").append(a.getBalance()).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        });

        // 7. Delete User
        mainPanel.add(delUser = new JButton("7. Delete User"));
        delUser.addActionListener(e -> {
            users.remove(loggedInUser);
            JOptionPane.showMessageDialog(null,"User deleted");
            jMenu.dispose();
            showLoginMenu();
        });

        // 8. Logout
        mainPanel.add(logOut = new JButton("8. Logout"));
        logOut.addActionListener(e -> { jMenu.dispose(); showLoginMenu(); });

        // 9. Quit
        mainPanel.add(quit = new JButton("9. Quit"));
        quit.setBackground(Color.RED);
        quit.addActionListener(e -> System.exit(0));

        jMenu.setTitle("ATM Machine");
        jMenu.setSize(350,600);
        jMenu.setLocationRelativeTo(null);
        jMenu.setVisible(true);
        jMenu.pack();
    }

    // Unused original methods left in place for reference
    public void createAccount(double b,String t,int i) { /* replaced by Factory+Builder+Decorator+Command */ }
    public void deposit(double a,int id,String t) { /* replaced by DepositCommand */ }
    public void withDraw(double a,int id,String t) { /* replaced by WithdrawCommand */ }
    public void showAccount(int id)    { /* you can still call printTransLog on an Account if you unwrap */ }
}
