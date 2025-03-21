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



public class Bank extends Frame implements ActionListener {

	private JFrame jSignPage;
	private Label lblInput;
	private Label lblOutput;
	private TextField tfName;
	private TextField tfPass;
	private JButton signUp;
	private JButton signIn;

	private JFrame jMenu;
	private JButton transfer;
	private JFrame j4;
	private TextField transFromAccId;
	private TextField transAmount;
	private TextField transInAccId;
	private JButton transTranfer;
	private Account accWith;
	private JButton createAccount;
	private JFrame newCreateAcc;
	private JButton deleteAccount;
	private TextField delAccId;
	private JFrame j1;
	private JButton delete;
	private JButton deposit;
	private JFrame depositF;
	private TextField depAccId;
	private TextField depAmount;
	private TextField depType;
	private JButton depBut;
	private JButton withDraw;
	private JFrame j2;
	private TextField withAccId;
	private TextField withAmount;
	private TextField withType;
	private JButton withWithDraw;
	private JButton showAcc;
	private JFrame j3;
	private TextField showAccId;
	private JButton show;
	private JButton showAllAcc;
	private JButton delUser;
	private JButton logOut;
	private JButton quit;

	private JFrame jCreatAcc;
	private Label lblName; // Declare input Label
	private Label lblPass; // Declare output Label
	private TextField tfUserName; // Declare input TextField
	private TextField tfUserPass;
	private JButton JCreateUser;
	private TextField tfBal;
	private TextField tfAccId;
	private String accType;
	private double balance;
	private int accId;
	private JFrame jCreateAcc;

	private HashMap<User, ArrayList<Account>> users;
	private User loggedInUser;
	private Calendar rightNow;
	private int hour;

	public Bank() {
		users = new HashMap<User, ArrayList<Account>>();
		loggedInUser = null;
		rightNow = Calendar.getInstance();

	}

	public void showMainMenu() {

		jMenu = new JFrame();
		JPanel mainPanel = new JPanel();

        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        jMenu.add(mainPanel,BorderLayout.CENTER);
        

		JLabel heading = new JLabel();
		heading.setText(" Hi  " + tfUserName.getText()
				+ " what would you like to do:  ");
		heading.setFont(new Font("Serif", Font.PLAIN, 50));
		mainPanel.add(heading);

		transfer = new JButton();
		transfer.setFont(new Font("Serif", Font.PLAIN, 30));
		transfer.setText(" 1.  Transfer ");
		transfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				j4 = new JFrame();
				JPanel mainPanel = new JPanel();

		        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		        j4.add(mainPanel,BorderLayout.CENTER);

		        mainPanel.add(new JLabel("Account ID to tranfer from :"));
				transFromAccId = new TextField();
				mainPanel.add(transFromAccId);

				mainPanel.add(new JLabel("Account ID to Tranfer into: "));
				transInAccId = new TextField();
				mainPanel.add(transInAccId);

				mainPanel.add(new JLabel("Amount: "));
				transAmount = new TextField();
				mainPanel.add(transAmount);

				transTranfer = new JButton("Transfer");
				transTranfer.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							int acc1 = Integer.parseInt(transFromAccId
									.getText());
							int acc2 = Integer.parseInt(transInAccId.getText());
							int amount = Integer.parseInt(transAmount.getText());
							boolean check1 = false;
							boolean check2 = false;
							for (Account i : users.get(loggedInUser)) {
								if (acc1 == i.getAccId()) {
									check1 = true;
									accWith = i;
								}
							}
							for (ArrayList<Account> array : users.values()) {
								for (Account i : array) {
									if (acc2 == i.getAccId()) {
										check2 = true;
									}
								}
							}

							if (check1 && check2) {
								if (amount <= accWith.getBalance()) {
									withDraw(amount, acc1, "Transfer");
									deposit(amount, acc2, "Transfer");
									JOptionPane.showMessageDialog(null,
											"Tranfer successful");
									j4.dispose();

								} else {
									JOptionPane
											.showMessageDialog(null,
													"Tranfer failed : in suffiecent balance");
								}
							} else {
								JOptionPane.showMessageDialog(null,
										"Tranfer failed : Account not found ");
							}
						} catch (Exception e5) {
							JOptionPane.showMessageDialog(null,
									"Tranfer failed : Invalid Input");
						}

					}
				});
				mainPanel.add(transTranfer);
				j4.setTitle("Transfer");
				j4.setSize(500, 500);
				j4.setLocationRelativeTo(null);
				j4.setVisible(true);
				j4.pack();

			}
		});
		mainPanel.add(transfer);

		createAccount = new JButton();
		createAccount.setFont(new Font("Serif", Font.PLAIN, 30));
		createAccount.setText(" 2.  createAccount");
		createAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newCreateAcc = new JFrame();
				JPanel mainPanel = new JPanel();

		        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		        newCreateAcc.add(mainPanel,BorderLayout.CENTER);

				JRadioButton checking = new JRadioButton("Checking");
				JRadioButton saving = new JRadioButton("Saving");

				ButtonGroup group = new ButtonGroup();
				group.add(checking);
				group.add(saving);

				mainPanel.add(new Label(" Create your Account :  "
						+ loggedInUser.getName()));

				mainPanel.add(new Label(" Select your account type : "));
				checking.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						accType = "Checking";
					}
				});
				mainPanel.add(checking);

				saving.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						accType = "Saving";
					}
				});
				mainPanel.add(saving);

				mainPanel.add(new Label("Amount to be added: "));
				tfBal = new TextField(10);
				mainPanel.add(tfBal);

				mainPanel.add(new Label("AccountNumber: "));
				tfAccId = new TextField(10);
				mainPanel.add(tfAccId);

				JButton create = new JButton("Create Account");

				create.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						try {
							double d1 = Double.parseDouble(tfBal.getText());
							int i1 = Integer.parseInt(tfAccId.getText());
							createAccount(d1, accType, i1);
							newCreateAcc.dispose();
						} catch (Exception E) {
							JOptionPane.showMessageDialog(null,
									" User Account not Created !!!");
							newCreateAcc.dispose();
						}
					}
				});

				mainPanel.add(create);

				newCreateAcc.setTitle("Creating Account"); // "super" Frame sets
															// title
				newCreateAcc.setSize(500, 500);
				newCreateAcc.setLocationRelativeTo(null);// "super" Frame sets
															// initial
															// window size
				newCreateAcc.setVisible(true);// "super" Frame shows
				newCreateAcc.pack();

			}
		});
		mainPanel.add(createAccount);

		deleteAccount = new JButton();
		deleteAccount.setFont(new Font("Serif", Font.PLAIN, 30));
		deleteAccount.setText(" 3.  deleteAccount");
		deleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				j1 = new JFrame();
				JPanel mainPanel = new JPanel();

		        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		        j1.add(mainPanel,BorderLayout.CENTER);

		        mainPanel.add(new JLabel("Add Account ID  to be deleted : "));
				delAccId = new TextField();
				mainPanel.add(delAccId);

				JButton del = new JButton();
				del.setFont(new Font("Serif", Font.PLAIN, 15));
				del.setText(" Confirm ");
				del.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							int accId = Integer.parseInt(delAccId.getText());
							System.out.println((users.get(loggedInUser)).get(0)
									.getAccId());
							deleteAccount(accId);
							j1.dispose();
						} catch (Exception e1) {
							JOptionPane
									.showMessageDialog(null, "Invalid AccId");
						}
					}
				});
				mainPanel.add(del);

				j1.setTitle("ATM Machine");
				j1.setSize(500, 500);
				j1.setLocationRelativeTo(null);
				mainPanel.setBackground(Color.blue);
				j1.setVisible(true);
				j1.pack();

			}
		});
		mainPanel.add(deleteAccount);

		deposit = new JButton();
		deposit.setFont(new Font("Serif", Font.PLAIN, 30));
		deposit.setText("4.   deposit ");
		deposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				depositF = new JFrame();
				JPanel mainPanel = new JPanel();

		        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		        depositF.add(mainPanel,BorderLayout.CENTER);

		        mainPanel.add(new JLabel("Account number: "));
				depAccId = new TextField();
				mainPanel.add(depAccId);

				mainPanel.add(new JLabel("Amount: "));
				depAmount = new TextField();
				mainPanel.add(depAmount);

				mainPanel.add(new JLabel("Deposit Type: "));
				depType = new TextField();
				mainPanel.add(depType);

				depBut = new JButton("DEPOSIT");
				depBut.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							double d1 = Double.parseDouble(depAmount.getText());
							int i1 = Integer.parseInt(depAccId.getText());
							deposit(d1, i1, depType.getText());
							depositF.dispose();
						} catch (NumberFormatException e2) {
							JOptionPane
									.showMessageDialog(null, "Invalid Input");
						}
					}
				});
				mainPanel.add(depBut);

				depositF.setTitle("ATM Machine");
				depositF.setSize(500, 500);
				depositF.setLocationRelativeTo(null);
				mainPanel.setBackground(Color.GREEN);
				depositF.setVisible(true);
				depositF.pack();

			}
		});
		mainPanel.add(deposit);

		withDraw = new JButton();
		withDraw.setFont(new Font("Serif", Font.PLAIN, 30));
		withDraw.setText("5.   withDraw ");
		withDraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				j2 = new JFrame();
				JPanel mainPanel = new JPanel();

		        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		        j2.add(mainPanel,BorderLayout.CENTER);

		        mainPanel.add(new JLabel("Enter AccID "));
				withAccId = new TextField(10);
				mainPanel.add(withAccId);

				mainPanel.add(new JLabel("Enter Amount"));
				withAmount = new TextField(10);
				mainPanel.add(withAmount);

				mainPanel.add(new JLabel("WithDrawal type : "));
				withType = new TextField(10);
				mainPanel.add(withType);

				withWithDraw = new JButton();
				withWithDraw.setFont(new Font("Serif", Font.PLAIN, 30));
				withWithDraw.setText(" WithDraw ");
				withWithDraw.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							int accId = Integer.parseInt(withAccId.getText());
							double amount = Double.parseDouble(withAmount
									.getText());
							withDraw(amount, accId, withType.getText());
							j2.dispose();

						} catch (Exception e4) {
							JOptionPane.showMessageDialog(null,
									"Transaction Failed : Invalid Input");
						}
					}

				});
				mainPanel.add(withWithDraw);

				j2.setTitle("ATM Machine");
				j2.setSize(500, 500);
				j2.setLocationRelativeTo(null);
				mainPanel.setBackground(Color.ORANGE);
				j2.setVisible(true);
				j2.pack();

			}

		});
		mainPanel.add(withDraw);

		showAcc = new JButton();
		showAcc.setFont(new Font("Serif", Font.PLAIN, 30));
		showAcc.setText("6. showAccount ");
		showAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				j3 = new JFrame();
				JPanel mainPanel = new JPanel();

		        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		        j3.add(mainPanel,BorderLayout.CENTER);

		        mainPanel.add(new JLabel("Enter AccID: "));
				showAccId = new TextField();
				mainPanel.add(showAccId);

				show = new JButton();
				show.setFont(new Font("Serif", Font.PLAIN, 30));
				show.setText(" Show TRANS LOG ");
				show.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							int accId = Integer.parseInt(showAccId.getText());
							showAccount(accId);
						} catch (Exception e5) {
							JOptionPane
									.showMessageDialog(null, "Invalid Input");
						}
					}
				});
				mainPanel.add(show);
				j3.setTitle("ATM Machine");
				j3.setSize(500, 500);
				j3.setLocationRelativeTo(null);
				mainPanel.setBackground(Color.blue);
				j3.setVisible(true);
				j3.pack();

			}
		});
		mainPanel.add(showAcc);

		showAllAcc = new JButton();
		showAllAcc.setFont(new Font("Serif", Font.PLAIN, 30));
		showAllAcc.setText("7.  showAllAccount ");
		showAllAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Account i : users.get(loggedInUser)) {
					System.out.println("Account ID: " + i.getAccId() + " "
							+ "Balance : $" + i.getBalance() + " " + "Type: "
							+ i.getType());
				}
				JOptionPane.showMessageDialog(null,"Accounts data printed to the Console!!");
			}
		});
		mainPanel.add(showAllAcc);

		delUser = new JButton();
		delUser.setFont(new Font("Serif", Font.PLAIN, 30));
		delUser.setText("8.  deleteUser ");
		delUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				users.remove(loggedInUser);
				JOptionPane.showMessageDialog(null, "User Deleted !!");
				jMenu.dispose();
			}
		});
		mainPanel.add(delUser);

		logOut = new JButton();
		logOut.setFont(new Font("Serif", Font.PLAIN, 30));
		logOut.setText("9.  LogOut ");
		logOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfName.setText("");
				tfPass.setText("");
				jMenu.dispose();
			}
		});
		mainPanel.add(logOut);

		quit = new JButton();
		quit.setFont(new Font("Serif", Font.PLAIN, 30));
		quit.setText("10.  Quit ");
		quit.setBackground(Color.red);
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mainPanel.add(quit);

		jMenu.setTitle("ATM Machine"); // "super" Frame sets title
		jMenu.setSize(300, 600);
		jMenu.setLocationRelativeTo(null);// "super" Frame sets initial window
											// size
		
		jMenu.setVisible(true);// "super" Frame shows
		jMenu.pack();
	}

	public void showLoginMenu() {
		

		jSignPage = new JFrame();
		JPanel mainPanel = new JPanel();

        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        jSignPage.add(mainPanel,BorderLayout.CENTER);
        
       
   
		JLabel heading = new JLabel();
		heading.setText(" Sign up / Login ");
		heading.setFont(new Font("Serif", Font.PLAIN, 25));
		mainPanel.add(heading);

		lblInput = new Label(" ***  Enter UserName: ");
		mainPanel.add(lblInput);

		tfName = new TextField(5);
		mainPanel.add(tfName);

		lblOutput = new Label(" ***  Password");
		mainPanel.add(lblOutput);

		tfPass = new TextField(5);
		mainPanel.add(tfPass);

		signUp = new JButton("Sign Up");
		signUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showCreateAccMenu();
			}
		});
		mainPanel.add(signUp);

		signIn = new JButton("Sign In");
		signIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean succ = false;
				for (User key : users.keySet()) {
					if ((key.getName()).equals(tfName.getText())
							&& (key.getPassword()).equals(tfPass.getText())) {
						loggedInUser = key;
						showMainMenu();
						succ = true;
						break;
					}
				}
				if (!succ) {
					JOptionPane.showMessageDialog(null,
							"Incorrect Username or Password");
				}

			}
		});
		mainPanel.add(signIn);
		

		jSignPage.setTitle("ATM Machine"); // "super" Frame sets title
		jSignPage.setSize(500, 250);
		jSignPage.setLocationRelativeTo(null);// "super" Frame sets initial
												// window size
		jSignPage.setVisible(true);// "super" Frame shows
	}

	// creates first user and account
	public void showCreateAccMenu() {

		jCreatAcc = new JFrame();
		JPanel mainPanel = new JPanel();

        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        jCreatAcc.add(mainPanel,BorderLayout.CENTER);

		JLabel heading = new JLabel();
		heading.setText(" Create Your User Account ");
		heading.setFont(new Font("Serif", Font.PLAIN, 25));
		mainPanel.add(heading);

		lblName = new Label(" Enter UserName: "); // Construct Label
		mainPanel.add(lblName); // "super" Frame container adds Label component

		tfUserName = new TextField(10);
		mainPanel.add(tfUserName);

		lblPass = new Label(" Password");
		mainPanel.add(lblPass);

		tfUserPass = new TextField(10);
		mainPanel.add(tfUserPass);

		JCreateUser = new JButton("Create User");
		mainPanel.add(JCreateUser);
		JCreateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfUserName.getText().length() != 0
						&& tfUserPass.getText().length() != 0) {
					jCreateAcc = new JFrame();
					JPanel mainPanel = new JPanel();

			        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
			        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
			        jCreateAcc.add(mainPanel,BorderLayout.CENTER);

					JRadioButton checking = new JRadioButton("Checking");
					JRadioButton saving = new JRadioButton("Saving");

					ButtonGroup group = new ButtonGroup();
					group.add(checking);
					group.add(saving);

					mainPanel.add(new Label(" Create your Account :  "
							+ tfUserName.getText()));

					mainPanel.add(new Label(" Select your account type : "));
					checking.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							accType = "Checking";
						}
					});
					mainPanel.add(checking);

					saving.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							accType = "Saving";
						}
					});
					mainPanel.add(saving);

					mainPanel.add(new Label("Amount to be added: "));
					tfBal = new TextField(10);
					mainPanel.add(tfBal);

					mainPanel.add(new Label("AccountNumber: "));
					tfAccId = new TextField(10);
					mainPanel.add(tfAccId);

					JButton create = new JButton("Create Account");

					create.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {

							User newUser = new User(tfUserName.getText(),
									tfUserPass.getText());
							try {
								double d1 = Double.parseDouble(tfBal.getText());
								int i1 = Integer.parseInt(tfAccId.getText());
								Account newAccount = new Account(d1, accType,
										i1);
								boolean check = true;
								for (ArrayList<Account> array : users.values()) {
									for (Account i : array) {
										if (i1 == i.getAccId()) {
											JOptionPane.showMessageDialog(null, " Account exists");
											check = false;
											break;
										}
									}
								}
								if(check)
								{
								ArrayList<Account> newList = new ArrayList<Account>();
								newList.add(newAccount);
								users.put(newUser, newList);
								JOptionPane.showMessageDialog(null,
										"Account Created || UserName : " + tfUserName.getText() + ", Account ID : " + tfAccId.getText() + ", Balance : $" + tfBal.getText() + ", Type : " + accType);
								}
								jCreateAcc.dispose();
								jCreatAcc.dispose();
							} catch (Exception E) {
								JOptionPane.showMessageDialog(null,
										" User Account not Created !!!");
								jCreateAcc.dispose();
							}
						}
					});
  				    mainPanel.add(create);

					jCreateAcc.setTitle("Creating Account"); // "super" Frame
																// sets title
					jCreateAcc.setSize(500, 500);
					jCreateAcc.setLocationRelativeTo(null);// "super" Frame sets
															// initial
															// window size
					mainPanel.setBackground(Color.CYAN);
					jCreateAcc.setVisible(true);// "super" Frame shows
					jCreateAcc.pack();

				} else {
					JOptionPane.showMessageDialog(null,
							" Enter password and username");
				}

			}

		});

		jCreatAcc.setTitle("Creating Account"); // "super" Frame sets title
		jCreatAcc.setSize(300, 250);
		jCreatAcc.setLocationRelativeTo(null);// "super" Frame sets initial
												// window size
		jCreatAcc.setBackground(Color.GREEN);
		jCreatAcc.setVisible(true);
		jCreatAcc.pack();
	}

	public void createUser(String name, String password) {
		User user = new User(name, password);
		ArrayList<Account> list = new ArrayList<Account>();
		users.put(user, list);
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

	public static void main(String args[]) {
		Bank b1 = new Bank();
		b1.showLoginMenu();
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
