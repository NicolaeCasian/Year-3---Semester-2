 import java.awt.*;
import java.awt.event.*;
import javax.swing.text.*;
import javax.swing.*;
import javax.swing.event.*;


public class ClientWindow extends JFrame implements ActionListener{
    private JButton CButton, DButton, Quit;

    public ClientWindow(){
        super ("Client Window");
        JPanel jp = new JPanel();
        getContentPane().add(jp);

	    jp.add(new JButton("Normal Button"));
        jp.add( new CoolDecorator(DButton = new JButton("Cool Button")));

        jp.add(Quit = new JButton("Quit"));
        Quit.addActionListener(this);
        setSize(new Dimension(200,100));

        setVisible(true);
        Quit.requestFocus();
    }


   public void actionPerformed(ActionEvent e){
        System.exit(0);
   }
   static public void main(String argv[]){
        new ClientWindow();
   }
}
