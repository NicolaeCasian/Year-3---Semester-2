package atm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

// Builder for sign‑up
import atm.builder.SignUpRequest;
// Factory for account creation
import atm.factory.AccountFactory;
import atm.factory.AbstractAccount;
// Decorators
import atm.decorator.FeeAccountDecorator;
import atm.decorator.LoggingAccountDecorator;
// Command Pattern
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

    @Override public void actionPerformed(ActionEvent e) { /* not used */ }

    public void showLoginMenu() {
        jSignPage = new JFrame();
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
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
        mainPanel.add(signIn);

        jSignPage.setTitle("ATM Machine");
        jSignPage.setSize(500,250);
        jSignPage.setLocationRelativeTo(null);
        jSignPage.setVisible(true);
    }

    // === Builder+Factory+Decorator for account creation ===
    public void showCreateAccMenu() {
        jCreatAcc = new JFrame();
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
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
        JCreateUser.addActionListener(e -> {
            if (tfUserName.getText().isEmpty() || tfUserPass.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter username and password");
                return;
            }
            // new user
            loggedInUser = new User(tfUserName.getText(), tfUserPass.getText());
            users.put(loggedInUser, new ArrayList<>());

            // then choose initial account
            jCreateAcc = new JFrame();
            JPanel inner = new JPanel();
            inner.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
            inner.setLayout(new BoxLayout(inner, BoxLayout.Y_AXIS));
            jCreateAcc.add(inner, BorderLayout.CENTER);

            inner.add(new Label(" Create your Account :  "+tfUserName.getText()));
            inner.add(new Label(" Select your account type : "));
            JRadioButton checking = new JRadioButton("Checking");
            JRadioButton saving   = new JRadioButton("Saving");
            ButtonGroup group = new ButtonGroup();
            group.add(checking); group.add(saving);
            checking.addActionListener(a->accType="checking");
            saving  .addActionListener(a->accType="savings");
            inner.add(checking); inner.add(saving);

            inner.add(new Label("Amount to be added: "));
            tfBal = new TextField(10);
            inner.add(tfBal);

            inner.add(new Label("AccountNumber (opt): "));
            tfAccId = new TextField(10);
            inner.add(tfAccId);

            JButton create = new JButton("Create Account");
            create.addActionListener(ae -> {
                try {
                    double initBal = Double.parseDouble(tfBal.getText());
                    // Builder + Factory
                    SignUpRequest req = new SignUpRequest.Builder(
                        tfUserName.getText(), tfUserPass.getText())
                        .setAccountType(accType, initBal)
                        .build();
                    AbstractAccount acct = req.getAccount();
                    // optional override of ID
                    if (!tfAccId.getText().trim().isEmpty())
                        acct.setAccountID(Integer.parseInt(tfAccId.getText()));
                    // Decorators
                    AbstractAccount wrapped =
                        new LoggingAccountDecorator(new FeeAccountDecorator(acct));
                    // to your concrete Account
                    Account newAcct = new Account(
                        wrapped.getBalance(),
                        wrapped.getAccountType(),
                        wrapped.getAccountID()
                    );
                    users.get(loggedInUser).add(newAcct);

                    JOptionPane.showMessageDialog(null,
                        "Account Created: ID="+newAcct.getAccId()
                        +", Bal=$"+newAcct.getBalance()
                        +", Type="+newAcct.getType()
                    );
                    jCreateAcc.dispose();
                    jCreatAcc.dispose();
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(null, "User Account not Created !!!");
                    jCreateAcc.dispose();
                }
            });
            inner.add(create);

            jCreateAcc.setTitle("Creating Account");
            jCreateAcc.setSize(500,500);
            jCreateAcc.setLocationRelativeTo(null);
            inner.setBackground(Color.CYAN);
            jCreateAcc.setVisible(true);
        });
        mainPanel.add(JCreateUser);

        jCreatAcc.setTitle("Creating Account");
        jCreatAcc.setSize(300,250);
        jCreatAcc.setLocationRelativeTo(null);
        jCreatAcc.setBackground(Color.GREEN);
        jCreatAcc.setVisible(true);
    }

    public void showMainMenu() {
        jMenu = new JFrame();
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        jMenu.add(mainPanel, BorderLayout.CENTER);

        JLabel heading = new JLabel(" Hi  "+tfUserName.getText()+" what would you like to do:  ");
        heading.setFont(new Font("Serif",Font.PLAIN,50));
        mainPanel.add(heading);

        // 1. Transfer (Command)
        transfer = new JButton(" 1.  Transfer ");
        transfer.setFont(new Font("Serif",Font.PLAIN,30));
        transfer.addActionListener(e->{
            j4 = new JFrame();
            JPanel p = new JPanel();
            p.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
            p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
            j4.add(p,BorderLayout.CENTER);

            p.add(new JLabel("Account ID to transfer from :")); transFromAccId=new TextField(); p.add(transFromAccId);
            p.add(new JLabel("Account ID to Transfer into: "));    transInAccId=new TextField();   p.add(transInAccId);
            p.add(new JLabel("Amount: "));                        transAmount=new TextField();    p.add(transAmount);

            transTranfer = new JButton("Transfer");
            transTranfer.addActionListener(ae->{
                try {
                    int fromId = Integer.parseInt(transFromAccId.getText());
                    int toId   = Integer.parseInt(transInAccId.getText());
                    double amt = Double.parseDouble(transAmount.getText());
                    // Command
                    Command cmd = new TransferCommand(this, amt, fromId, toId);
                    cmd.execute();
                    JOptionPane.showMessageDialog(null,"Transfer successful");
                    j4.dispose();
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(null,"Transfer failed: "+ex.getMessage());
                }
            });
            p.add(transTranfer);

            j4.setTitle("Transfer");
            j4.setSize(500,500);
            j4.setLocationRelativeTo(null);
            j4.setVisible(true);
            j4.pack();
        });
        mainPanel.add(transfer);

        // 2. createAccount
        createAccount = new JButton(" 2.  createAccount");
        createAccount.setFont(new Font("Serif",Font.PLAIN,30));
        createAccount.addActionListener(e->showCreateAccMenu());
        mainPanel.add(createAccount);

        // 3. deleteAccount
        deleteAccount = new JButton(" 3.  deleteAccount");
        deleteAccount.setFont(new Font("Serif",Font.PLAIN,30));
        deleteAccount.addActionListener(e->{
            j1 = new JFrame();
            JPanel p = new JPanel();
            p.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
            p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
            j1.add(p,BorderLayout.CENTER);

            p.add(new JLabel("Add Account ID  to be deleted : "));
            delAccId=new TextField(); p.add(delAccId);
            JButton del=new JButton(" Confirm ");
            del.addActionListener(ae->{
                try {
                    int id=Integer.parseInt(delAccId.getText());
                    deleteAccount(id);
                    j1.dispose();
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(null,"Invalid AccId");
                }
            });
            p.add(del);

            j1.setTitle("ATM Machine");
            j1.setSize(500,500);
            j1.setLocationRelativeTo(null);
            p.setBackground(Color.blue);
            j1.setVisible(true);
            j1.pack();
        });
        mainPanel.add(deleteAccount);

        // 4. deposit (Command)
        deposit = new JButton("4.   deposit ");
        deposit.setFont(new Font("Serif",Font.PLAIN,30));
        deposit.addActionListener(e->{
            depositF=new JFrame();
            JPanel p=new JPanel();
            p.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
            p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
            depositF.add(p,BorderLayout.CENTER);

            p.add(new JLabel("Account number: ")); depAccId=new TextField(); p.add(depAccId);
            p.add(new JLabel("Amount: "));        depAmount=new TextField(); p.add(depAmount);
            p.add(new JLabel("Deposit Type: "));  depType=new TextField();   p.add(depType);

            depBut=new JButton("DEPOSIT");
            depBut.addActionListener(ae->{
                try {
                    double amt = Double.parseDouble(depAmount.getText());
                    int id    = Integer.parseInt(depAccId.getText());
                    String t  = depType.getText();
                    Command cmd = new DepositCommand(this, amt, id, t);
                    cmd.execute();
                    depositF.dispose();
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(null,"Invalid Input");
                }
            });
            p.add(depBut);

            depositF.setTitle("ATM Machine");
            depositF.setSize(500,500);
            depositF.setLocationRelativeTo(null);
            p.setBackground(Color.GREEN);
            depositF.setVisible(true);
            depositF.pack();
        });
        mainPanel.add(deposit);

        // 5. withDraw (Command)
        withDraw = new JButton("5.   withDraw ");
        withDraw.setFont(new Font("Serif",Font.PLAIN,30));
        withDraw.addActionListener(e->{
            j2=new JFrame();
            JPanel p=new JPanel();
            p.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
            p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
            j2.add(p,BorderLayout.CENTER);

            p.add(new JLabel("Enter AccID "));    withAccId=new TextField(10); p.add(withAccId);
            p.add(new JLabel("Enter Amount"));    withAmount=new TextField(10); p.add(withAmount);
            p.add(new JLabel("WithDrawal type : ")); withType=new TextField(10); p.add(withType);

            withWithDraw=new JButton(" WithDraw ");
            withWithDraw.addActionListener(ae->{
                try {
                    double amt = Double.parseDouble(withAmount.getText());
                    int id    = Integer.parseInt(withAccId.getText());
                    String t  = withType.getText();
                    Command cmd = new WithdrawCommand(this, amt, id, t);
                    cmd.execute();
                    j2.dispose();
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(null,"Invalid Input");
                }
            });
            p.add(withWithDraw);

            j2.setTitle("ATM Machine");
            j2.setSize(500,500);
            j2.setLocationRelativeTo(null);
            p.setBackground(Color.ORANGE);
            j2.setVisible(true);
            j2.pack();
        });
        mainPanel.add(withDraw);

        // 6. showAccount
        showAcc=new JButton("6. showAccount ");
        showAcc.setFont(new Font("Serif",Font.PLAIN,30));
        showAcc.addActionListener(e->{
            j3=new JFrame();
            JPanel p=new JPanel();
            p.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
            p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
            j3.add(p,BorderLayout.CENTER);

            p.add(new JLabel("Enter AccID: "));
            showAccId=new TextField(); p.add(showAccId);

            show=new JButton(" Show TRANS LOG ");
            show.addActionListener(ae->{
                try {
                    int id=Integer.parseInt(showAccId.getText());
                    showAccount(id);
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(null,"Invalid Input");
                }
            });
            p.add(show);

            j3.setTitle("ATM Machine");
            j3.setSize(500,500);
            j3.setLocationRelativeTo(null);
            p.setBackground(Color.blue);
            j3.setVisible(true);
            j3.pack();
        });
        mainPanel.add(showAcc);

        // 7. showAllAccount (Iterator)
        showAllAcc=new JButton("7.  showAllAccount ");
        showAllAcc.setFont(new Font("Serif",Font.PLAIN,30));
        showAllAcc.addActionListener(e->{
            Iterator<Account> it = users.get(loggedInUser).iterator();
            while(it.hasNext()) {
                Account a = it.next();
                System.out.println("Account ID: "+a.getAccId()
                 + " Balance: $"+a.getBalance()
                 + " Type: "+a.getType());
            }
            JOptionPane.showMessageDialog(null,"Accounts data printed to the Console!!");
        });
        mainPanel.add(showAllAcc);

        // 8. deleteUser
        delUser=new JButton("8.  deleteUser ");
        delUser.setFont(new Font("Serif",Font.PLAIN,30));
        delUser.addActionListener(e->{
            users.remove(loggedInUser);
            JOptionPane.showMessageDialog(null,"User Deleted !!");
            jMenu.dispose();
        });
        mainPanel.add(delUser);

        // 9. LogOut
        logOut=new JButton("9.  LogOut ");
        logOut.setFont(new Font("Serif",Font.PLAIN,30));
        logOut.addActionListener(e->{
            tfName.setText(""); tfPass.setText("");
            jMenu.dispose();
        });
        mainPanel.add(logOut);

        // 10. Quit
        quit=new JButton("10.  Quit ");
        quit.setFont(new Font("Serif",Font.PLAIN,30));
        quit.setBackground(Color.red);
        quit.addActionListener(e->System.exit(0));
        mainPanel.add(quit);

        jMenu.setTitle("ATM Machine");
        jMenu.setSize(300,600);
        jMenu.setLocationRelativeTo(null);
        jMenu.setVisible(true);
        jMenu.pack();
    }

    public void createAccount(double balance, String type, int accId) {
		if (users.get(loggedInUser).size() <= 5) {
			boolean check = true;
			for (ArrayList<Account> array : users.values()) {
				for (Account i : array) {
					if (accId == i.getAccId()) {
						JOptionPane.showMessageDialog(null, " Account ID Exists !!");
						check = false;
						break;
					}
				}
			}
			if (check) {
				Account acc = new Account(balance, type, accId);
				users.get(loggedInUser).add(acc);
				JOptionPane.showMessageDialog(null, "Account Created !! || Account ID : " + accId + ", Balance : $" + balance + ", Type : " + type);
			}
		} else {
			JOptionPane.showMessageDialog(null, " No more accounts can be created");
		}

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


}
