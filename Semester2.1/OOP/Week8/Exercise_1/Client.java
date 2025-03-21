import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class Client extends JFrame implements MouseMotionListener {
   
   private Vector<String> names;
   // Vector of buttons
   private Vector<Button> buttons;
   private ButtonFactory fact;
   
   //Constants for the buttons position and size 
   private final int Top = 30, Left = 30;
   private final int W = 50, H = 30;
   private final int VSpace = 80, HSpace = 70, HCount = 3;
   private String selectedName = "";
   
   //Constructor to create the client 
   public Client() {
      //Setting up the JFrame
      super("Exercise 1");
      //Instantiate the ButtonFactory
      fact = new ButtonFactory();
      //Create the names and the folders
      makeNames();
      makeButtons();
      
      // Adding an empty JPanel so the JFrame has a content pane
      JPanel jp = new JPanel();
      getContentPane().add(jp);
      
      setSize(new Dimension(300,300));
      addMouseMotionListener(this);
      setVisible(true);
      repaint();
   }
   
   //Method to create the buttons
   private void makeButtons() {
      buttons = new Vector<Button>();
      for (int i = 0; i < names.size(); i++) {
         // Initially, add unselected buttons.
         buttons.add(fact.getButton(false));
      }
   }
   
   //Method to create the names of the buttons
   private void makeNames() {
      names = new Vector<String>();
      names.addElement("Add");
      names.addElement("Update");
      names.addElement("Delete");
      names.addElement("Find");
      selectedName = "";
   }
   
   //Paint method to draw and display the buttons 
   @Override
   public void paint(Graphics g) {
      super.paint(g);  
      
      Button f;
      String name;
      int j = 0;      // count number in row
      int row = Top;  // start in upper left
      int x = Left;
      
      // Draw all buttons with labels
      for (int i = 0; i < names.size(); i++) {
         name = names.elementAt(i);
         // Use the appropriate button instance based on selection
         if (name.equals(selectedName))
            f = fact.getButton(true);
         else
            f = fact.getButton(false);
         
         // Draw the button
         f.draw(g, x, row, name);
         
         x = x + HSpace; // Move to next horizontal position
         j++;
         if (j >= HCount) { // Reset for next row
            j = 0;
            row += VSpace;
            x = Left;
         }
      }
   }
   
   @Override
   public void mouseMoved(MouseEvent e) {
      int j = 0;      // count number in row
      int row = Top;  // start in upper left
      int x = Left;
      
      // Loop through button positions and check if the mouse is over one
      for (int i = 0; i < buttons.size(); i++) {
         Rectangle r = new Rectangle(x, row, W, H);
         if (r.contains(e.getX(), e.getY())) {
            // Set selectedName from the names vector
            selectedName = names.elementAt(i);
            repaint();
            return; // Exit after the first match
         }
         x = x + HSpace;
         j++;
         if (j >= HCount) {
            j = 0;
            row += VSpace;
            x = Left;
         }
      }
      // If the mouse is not over any button, clear selection.
      selectedName = "";
      repaint();
   }
   
   //No action when the mouse is dragged
   @Override
   public void mouseDragged(MouseEvent e) {
      
   }
   
   //Main method to create the client 
   public static void main(String[] argv) {
      new Client();
   }
}
