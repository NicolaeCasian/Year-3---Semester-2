import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//In this version, the Command objects are external classes
//and we pass them copies of the Frame instance
public class Client extends JFrame {
   // Declare menu components and buttons
   private Menu mnuFile;
   private MenuItem mnuOpen;
   private MenuItem mnuExit;
   private JButton btnOpen;
   private JButton btnExit;

   public Client(){
      // Call JFrame constructor with title
      super("Frame with external commands");
   
      // Create and set the menu bar for the frame
      MenuBar mbar = new MenuBar();
      setMenuBar(mbar);
      
     //Add menu items to handle basic menu operations
      mnuFile = new Menu("File", true);
      mbar.add(mnuFile);
      mnuOpen = new MenuItem("Open...");
      mnuFile.add(mnuOpen);
      mnuExit = new MenuItem("Exit");
      mnuFile.add(mnuExit);
      
      // Create buttons that opens the menu and exist the GUI
      btnOpen = new JButton("Open");
      btnExit = new JButton("Exit");

      // Reference to the current frame to pass to command objects
      JFrame frm = this;

      // Action Listener for the menu open command
      mnuOpen.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               // Executes the OpenCommand 
               executeCommand(new OpenCommand(frm));
            }
        });

      //Action Listener for the menu exit command
      mnuExit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               // Executes the exit command
               executeCommand(new ExitCommand(frm));
            }
        });

      //Action Listener for the Open button
      btnOpen.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               // Execute the Open command when button is pressed
               executeCommand(new OpenCommand(frm));
            }
        });
      
      //Action Listener for the Exit button
      btnExit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               //Executes the Exit command
               executeCommand(new ExitCommand(frm));
            }
        });

      // Jpanel with  Open and Exit buttons
      JPanel p = new JPanel();
      add(p);
      p.add(btnOpen);
      p.add(btnExit);

      //Setting size and visibility of the frame
      setSize(400,450);
      show();
   }


   private void executeCommand(Command command){
      // We could keep a command history in a stack here
      command.Execute();
   }

   //Main method to create the frame
   static public void main(String argv[]){
      new Client();
   }
}

// interface command with a method to execute 
interface Command {
   void Execute();
}

// OpenCommand implementing the command interface
class OpenCommand implements Command{
   private JFrame parent;
   // Constructor 
   OpenCommand(JFrame parent){
      this.parent = parent;
   }
   
   //Method to open a file
   public void Execute(){
      // Create a FileDialog to open a file and display it
      FileDialog fDlg = new FileDialog(parent, "Open file");
      fDlg.show();
   }
}

// ExitCommand implementing the Command interface
class ExitCommand implements Command{
   private JFrame parent;
   
   // Exit Command Constructor 
   ExitCommand(JFrame parent){
      this.parent = parent;
   }
   
   //Method to exit apllication
   public void Execute(){
      //Close the frame
      System.exit(0);
   }
}

// Window adapter to handle window closing events
class ExitGUI extends WindowAdapter{
  //Method to close the GUI
   public void windowClosing(WindowEvent e){
      System.exit(0);
   }
}
