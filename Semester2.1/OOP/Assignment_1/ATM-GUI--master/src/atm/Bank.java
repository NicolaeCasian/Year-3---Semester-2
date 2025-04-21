package atm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;

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
    private TextField transFromAccId, transAmount, transInAccId;
    private TextField delAccId, depAccId, depAmount, depType, withAccId, withAmount, withType, showAccId;
    private JButton signUp, signIn, JCreateUser;
    private JButton transfer, createAccount, deleteAccount, deposit, withDraw, showAcc, showAllAcc, delUser, logOut, quit;
    private JButton transTranfer, depBut, withWithDraw, show;
    private String accType;
    private Calendar rightNow;

    private HashMap<User, ArrayList<Account>> users;
    private User loggedInUser;
    private int hour;

    public Bank() {
        users = new HashMap<>();
        rightNow = Calendar.getInstance();
    }

    public static void main(String[] args) {
        new Bank().showLoginMenu();
    }

    @Override public void actionPerformed(ActionEvent e) { /* Not used */ }

    public void showLoginMenu() {
        jSignPage = new JFrame();
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        jSignPage.add(mainPanel, BorderLayout.CENTER);

        JLabel heading = new JLabel(" Sign up / Login ");
        heading.setFont(new Font("Serif", Font.PLAIN, 25));
        mainPanel.add(heading);

        lblInput = new Label(" ***  Enter UserName: ");
        mainPanel.add(lblInput);
        tfName = new TextField(10);
        mainPanel.add(tfName);

        lblOutput = new Label(" ***  Password");
        mainPanel.add(lblOutput);
        tfPass = new TextField(10);
        mainPanel.add(tfPass);

        signUp = new JButton("Sign Up");
        signUp.addActionListener(e -> showCreateAccMenu());
        mainPanel.add(signUp);

        signIn = new JButton("Sign In");
        signIn.addActionListener(e -> {
            boolean succ = false;
            for (User u : users.keySet()) {
                if (u.getName().equals(tfName.getText()) &&
                    u.getPassword().equals(tfPass.getText())) {
                    loggedInUser = u;
                    jSignPage.dispose();
                    showMainMenu();
                    succ = true;
                    break;
                }
            }
            if (!succ) {
                JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
            }
        });
        mainPanel.add(signIn);

        jSignPage.setTitle("ATM Machine");
        jSignPage.setSize(500, 250);
        jSignPage.setLocationRelativeTo(null);
        jSignPage.setVisible(true);
    }

    // === Builder + Factory + Decorator for ACCOUNT CREATION ===
    public void showCreateAccMenu() {
        jCreatAcc = new JFrame();
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        jCreatAcc.add(mainPanel, BorderLayout.CENTER);

        JLabel heading = new JLabel(" Create Your User Account ");
        heading.setFont(new Font("Serif", Font.PLAIN, 25));
        mainPanel.add(heading);

        lblName = new Label(" Enter UserName: ");
        mainPanel.add(lblName);
        tfUserName = new TextField(10);
        mainPanel.add(tfUserName);

        lblPass = new Label(" Password");
        mainPanel.add(lblPass);
        tfUserPass = new TextField(10);
        mainPanel.add(tfUserPass);

        JCreateUser = new JButton("Create User");
        mainPanel.add(JCreateUser);
        JCreateUser.addActionListener(e -> {
            if (tfUserName.getText().isEmpty() || tfUserPass.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter username and password");
                return;
            }
            // initialize user map entry
            loggedInUser = new User(tfUserName.getText(), tfUserPass.getText());
            users.put(loggedInUser, new ArrayList<>());

            // now choose account type & deposit
            jCreateAcc = new JFrame();
            JPanel inner = new JPanel();
            inner.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            inner.setLayout(new BoxLayout(inner, BoxLayout.Y_AXIS));
            jCreateAcc.add(inner, BorderLayout.CENTER);

            inner.add(new Label(" Create your Account :  " + tfUserName.getText()));
            inner.add(new Label(" Select your account type : "));
            JRadioButton checking = new JRadioButton("Checking");
            JRadioButton saving   = new JRadioButton("Saving");
            ButtonGroup group = new ButtonGroup();
            group.add(checking); group.add(saving);
            checking.addActionListener(ev -> accType = "savings".equalsIgnoreCase("checking") ? "Checking" : "Checking");
            saving  .addActionListener(ev -> accType = "Saving");
            inner.add(checking);
            inner.add(saving);

            inner.add(new Label("Amount to be added: "));
            tfBal = new TextField(10);
            inner.add(tfBal);

            inner.add(new Label("AccountNumber: "));
            tfAccId = new TextField(10);
            inner.add(tfAccId);

            JButton create = new JButton("Create Account");
            inner.add(create);
            create.addActionListener(ev -> {
                try {
                    // Builder + Factory
                    SignUpRequest req = new SignUpRequest.Builder(
                            tfUserName.getText(), tfUserPass.getText())
                        .setAccountType(accType, Double.parseDouble(tfBal.getText()))
                        .build();
                    AbstractAccount acct = req.getAccount();

                    // override ID if custom
                    if (!tfAccId.getText().trim().isEmpty()) {
                        acct.setAccountID(Integer.parseInt(tfAccId.getText()));
                    }

                    // Decorators
                    AbstractAccount wrapped = new LoggingAccountDecorator(
                                                new FeeAccountDecorator(acct)
                                            );

                    // wrap back into your concrete Account for the rest of your code
                    Account newAccount = new Account(
                        wrapped.getBalance(),
                        wrapped.getAccountType(),
                        wrapped.getAccountID()
                    );
                    users.get(loggedInUser).add(newAccount);

                    JOptionPane.showMessageDialog(null,
                        "Account Created || UserName : " + tfUserName.getText()
                        + ", Account ID : " + newAccount.getAccId()
                        + ", Balance : $" + newAccount.getBalance()
                        + ", Type : " + newAccount.getType()
                    );
                    jCreateAcc.dispose();
                    jCreatAcc.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                        "User Account not Created !!!");
                    jCreateAcc.dispose();
                }
            });

            jCreateAcc.setTitle("Creating Account");
            jCreateAcc.setSize(500, 500);
            jCreateAcc.setLocationRelativeTo(null);
            inner.setBackground(Color.CYAN);
            jCreateAcc.setVisible(true);
            jCreateAcc.pack();
        });

        jCreatAcc.setTitle("Creating Account");
        jCreatAcc.setSize(300, 250);
        jCreatAcc.setLocationRelativeTo(null);
        jCreatAcc.setBackground(Color.GREEN);
        jCreatAcc.setVisible(true);
        jCreatAcc.pack();
    }

    public void showMainMenu() {
        jMenu = new JFrame();
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        jMenu.add(mainPanel, BorderLayout.CENTER);

        JLabel heading = new JLabel(" Hi  " + tfUserName.getText()
                + " what would you like to do:  ");
        heading.setFont(new Font("Serif", Font.PLAIN, 50));
        mainPanel.add(heading);

        // --- Transfer: now uses Command ---
        transfer = new JButton();
        transfer.setFont(new Font("Serif", Font.PLAIN, 30));
        transfer.setText(" 1.  Transfer ");
        transfer.addActionListener(e -> {
            j4 = new JFrame();
            JPanel p = new JPanel();
            p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
            j4.add(p, BorderLayout.CENTER);

            p.add(new JLabel("Account ID to transfer from :"));
            transFromAccId = new TextField();
            p.add(transFromAccId);

            p.add(new JLabel("Account ID to Transfer into: "));
            transInAccId = new TextField();
            p.add(transInAccId);

            p.add(new JLabel("Amount: "));
            transAmount = new TextField();
            p.add(transAmount);

            transTranfer = new JButton("Transfer");
            transTranfer.addActionListener(ev -> {
                try {
                    int acc1 = Integer.parseInt(transFromAccId.getText());
                    int acc2 = Integer.parseInt(transInAccId.getText());
                    double amount = Double.parseDouble(transAmount.getText());

                    Account from = null, to = null;
                    for (Account a : users.get(loggedInUser)) {
                        if (a.getAccId() == acc1) from = a;
                        if (a.getAccId() == acc2) to   = a;
                    }
                    if (from == null || to == null) throw new Exception("Account not found");
                    Command cmd = new TransferCommand(amount, from, to);
                    cmd.execute();

                    JOptionPane.showMessageDialog(null,"Transfer successful");
                    j4.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,"Transfer failed: " + ex.getMessage());
                }
            });
            p.add(transTranfer);

            j4.setTitle("Transfer");
            j4.setSize(500, 500);
            j4.setLocationRelativeTo(null);
            j4.setVisible(true);
            j4.pack();
        });
        mainPanel.add(transfer);

        // --- Create Account ---
        createAccount = new JButton();
        createAccount.setFont(new Font("Serif", Font.PLAIN, 30));
        createAccount.setText(" 2.  createAccount");
        createAccount.addActionListener(e -> showCreateAccMenu());
        mainPanel.add(createAccount);

        // --- Delete Account ---
        deleteAccount = new JButton();
        deleteAccount.setFont(new Font("Serif", Font.PLAIN, 30));
        deleteAccount.setText(" 3.  deleteAccount");
        deleteAccount.addActionListener(e -> {
            j1 = new JFrame();
            JPanel p = new JPanel();
            p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
            j1.add(p, BorderLayout.CENTER);

            p.add(new JLabel("Add Account ID  to be deleted : "));
            delAccId = new TextField();
            p.add(delAccId);

            JButton del = new JButton(" Confirm ");
            del.addActionListener(ev -> {
                try {
                    int id = Integer.parseInt(delAccId.getText());
                    deleteAccount(id);
                    j1.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid AccId");
                }
            });
            p.add(del);

            j1.setTitle("ATM Machine");
            j1.setSize(500, 500);
            j1.setLocationRelativeTo(null);
            p.setBackground(Color.blue);
            j1.setVisible(true);
            j1.pack();
        });
        mainPanel.add(deleteAccount);

        // --- Deposit: now uses Command ---
        deposit = new JButton();
        deposit.setFont(new Font("Serif", Font.PLAIN, 30));
        deposit.setText("4.   deposit ");
        deposit.addActionListener(e -> {
            depositF = new JFrame();
            JPanel p = new JPanel();
            p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
            depositF.add(p, BorderLayout.CENTER);

            p.add(new JLabel("Account number: "));
            depAccId = new TextField(); p.add(depAccId);
            p.add(new JLabel("Amount: "));
            depAmount = new TextField(); p.add(depAmount);
            p.add(new JLabel("Deposit Type: "));
            depType = new TextField(); p.add(depType);

            depBut = new JButton("DEPOSIT");
            depBut.addActionListener(ev -> {
                try {
                    int id = Integer.parseInt(depAccId.getText());
                    double amt = Double.parseDouble(depAmount.getText());
                    Account acc = null;
                    for (Account a : users.get(loggedInUser)) {
                        if (a.getAccId() == id) { acc = a; break; }
                    }
                    if (acc == null) throw new Exception("Account not found");
                    Command cmd = new DepositCommand(acc, amt);
                    cmd.execute();
                    JOptionPane.showMessageDialog(null, "Deposit successful");
                    depositF.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Input");
                }
            });
            p.add(depBut);

            depositF.setTitle("ATM Machine");
            depositF.setSize(500, 500);
            depositF.setLocationRelativeTo(null);
            p.setBackground(Color.GREEN);
            depositF.setVisible(true);
            depositF.pack();
        });
        mainPanel.add(deposit);

        // --- Withdraw: now uses Command ---
        withDraw = new JButton();
        withDraw.setFont(new Font("Serif", Font.PLAIN, 30));
        withDraw.setText("5.   withDraw ");
        withDraw.addActionListener(e -> {
            j2 = new JFrame();
            JPanel p = new JPanel();
            p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
            j2.add(p, BorderLayout.CENTER);

            p.add(new JLabel("Enter AccID "));
            withAccId = new TextField(10); p.add(withAccId);
            p.add(new JLabel("Enter Amount"));
            withAmount = new TextField(10); p.add(withAmount);
            p.add(new JLabel("WithDrawal type : "));
            withType = new TextField(10); p.add(withType);

            withWithDraw = new JButton(" WithDraw ");
            withWithDraw.addActionListener(ev -> {
                try {
                    int id = Integer.parseInt(withAccId.getText());
                    double amt = Double.parseDouble(withAmount.getText());
                    Account acc = null;
                    for (Account a : users.get(loggedInUser)) {
                        if (a.getAccId() == id) { acc = a; break; }
                    }
                    if (acc == null) throw new Exception("Account not found");
                    Command cmd = new WithdrawCommand(acc, amt);
                    cmd.execute();
                    JOptionPane.showMessageDialog(null, "Withdrawal successful");
                    j2.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Input");
                }
            });
            p.add(withWithDraw);

            j2.setTitle("ATM Machine");
            j2.setSize(500, 500);
            j2.setLocationRelativeTo(null);
            p.setBackground(Color.ORANGE);
            j2.setVisible(true);
            j2.pack();
        });
        mainPanel.add(withDraw);

        // --- Show Account ---
        showAcc = new JButton();
        showAcc.setFont(new Font("Serif", Font.PLAIN, 30));
        showAcc.setText("6. showAccount ");
        showAcc.addActionListener(e -> {
            j3 = new JFrame();
            JPanel p = new JPanel();
            p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
            j3.add(p, BorderLayout.CENTER);

            p.add(new JLabel("Enter AccID: "));
            showAccId = new TextField(); p.add(showAccId);

            show = new JButton(" Show TRANS LOG ");
            show.addActionListener(ev -> {
                try {
                    int id = Integer.parseInt(showAccId.getText());
                    showAccount(id);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Input");
                }
            });
            p.add(show);

            j3.setTitle("ATM Machine");
            j3.setSize(500, 500);
            j3.setLocationRelativeTo(null);
            p.setBackground(Color.blue);
            j3.setVisible(true);
            j3.pack();
        });
        mainPanel.add(showAcc);

        // --- Show All Accounts ---
        showAllAcc = new JButton();
        showAllAcc.setFont(new Font("Serif", Font.PLAIN, 30));
        showAllAcc.setText("7.  showAllAccount ");
        showAllAcc.addActionListener(e -> {
            for (Account a : users.get(loggedInUser)) {
                System.out.println("Account ID: " + a.getAccId()
                    + " Balance : $" + a.getBalance()
                    + " Type: " + a.getType());
            }
            JOptionPane.showMessageDialog(null,"Accounts data printed to the Console!!");
        });
        mainPanel.add(showAllAcc);

        // --- Delete User ---
        delUser = new JButton();
        delUser.setFont(new Font("Serif", Font.PLAIN, 30));
        delUser.setText("8.  deleteUser ");
        delUser.addActionListener(e -> {
            users.remove(loggedInUser);
            JOptionPane.showMessageDialog(null, "User Deleted !!");
            jMenu.dispose();
        });
        mainPanel.add(delUser);

        // --- Logout ---
        logOut = new JButton();
        logOut.setFont(new Font("Serif", Font.PLAIN, 30));
        logOut.setText("9.  LogOut ");
        logOut.addActionListener(e -> {
            tfName.setText("");
            tfPass.setText("");
            jMenu.dispose();
        });
        mainPanel.add(logOut);

        // --- Quit ---
        quit = new JButton();
        quit.setFont(new Font("Serif", Font.PLAIN, 30));
        quit.setText("10.  Quit ");
        quit.setBackground(Color.red);
        quit.addActionListener(e -> System.exit(0));
        mainPanel.add(quit);

        jMenu.setTitle("ATM Machine");
        jMenu.setSize(300, 600);
        jMenu.setLocationRelativeTo(null);
        jMenu.setVisible(true);
        jMenu.pack();
    }

    public void deleteAccount(int accId)

	{
		boolean check = true;
		for (Account i : users.get(loggedInUser)) {
			if (accId == i.getAccId()) {
				users.get(loggedInUser).remove(i);
				check = false;
				JOptionPane.showMessageDialog(null, "Account deleted !! || Account Id : " + accId );
				break;
			}

		}
		if (check) {
			JOptionPane.showMessageDialog(null, "Account does not exist!!");
		}
		return;
	}

	public void deposit(double amount, int accId, String type) {
		Account obj = null;
		boolean check = false;
		for (ArrayList <Account> array : users.values()) {
			for(Account i : array){
			if (accId == i.getAccId()) {
				check = true;
				obj = i;
			}
		  }
		}
		if (check) {
			obj.setBalance(obj.getBalance() + amount);
			int time1 = rightNow.get(Calendar.MINUTE);
			obj.doTransaction(amount, time1, type);
			JOptionPane.showMessageDialog(null, "Deposit SuccessFull || Account ID : " + accId + ", Amount deposited : $" + amount +", NewBalance : $ " + obj.getBalance() + ", Type : " + type);
			loggedInUser.Spend(accId, amount, type);
		} else {
			JOptionPane.showMessageDialog(null, "Deposit failed!!");
		}
	}

	public void withDraw(double amount, int accId, String type) {
		boolean check = true;
		Account found = null;
		for (Account i : users.get(loggedInUser)) {
			if (i.getAccId() == accId) {
				check = false;
				found = i;
				break;
			}
		}
		if (check) {
			JOptionPane.showMessageDialog(null, "Account not found !!");
		} else {
			if (found.getBalance() < amount) {
				JOptionPane.showMessageDialog(null, "In Sufficint balance");
			} else {
				hour = rightNow.get(Calendar.MINUTE);
				found.setBalance(found.getBalance() - amount);
				found.doTransaction(amount, hour, type);
				JOptionPane
						.showMessageDialog(
								null,
								"Acc ID: "
										+ found.getAccId()
										+ " . Withdrawal complete. Amount withDrawn : $" + amount + ". Remaining balance : $"
										+ found.getBalance());
				loggedInUser.Spend(accId, amount, type);
			}
		}
	}

	public void showAccount(int accId) {
		boolean check = true;
		Account obj = null;
		for (Account i : users.get(loggedInUser)) {
			if (i.getAccId() == accId) {
				check = false;
				obj = i;
				break;
			}
		}
		if (check) {
			JOptionPane.showMessageDialog(null, "Account ID not found !!");
		} else {
			obj.printTransLog();
			JOptionPane.showMessageDialog(null, "Account ID " + accId + ". Transcaction written to the text file (TrabsLog.txt)");
			j3.dispose();

		}
	}
    public void showAllAccount() {
        for (Account i : users.get(loggedInUser)) {
            System.out.println("Account ID: " + i.getAccId() + " Balance : $" + i.getBalance() + " Type: " + i.getType());
        }
        JOptionPane.showMessageDialog(null, "Accounts data printed to the Console!!");
        jMenu.dispose();
    }
}
