
import java.awt.*;
import java.awt.event.*;
import java.util.*;

//swing classes
import javax.swing.text.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import javax.swing.tree.*;

public class Client extends JFrame implements TreeSelectionListener {
    //ALl the nodes under the Master Node Projects are added here
    Task projects , sap , website , payroll ;

    //All the nodes under the Master Node SAP are added here
    Task analysisSap , codingSap , requirementsSap;

    //All Leaf nodes under the master Node Analysis are added here
    Task uml , dataAnalysis , screenDesign;

    //All the nodes under website 
    Task designWebsite , codingWebsite , analysisWebsite;

    //All the leaf nodes under the master node Coding are added here
    Task prog , screens;

    //All the leaf nodes under master node Payroll are added here
    Task designPayroll , codingPayroll , testingPayroll;

    
    //Java Swing components added here
    JScrollPane sp;
    JPanel treePanel;
    JTree tree;
    DefaultMutableTreeNode troot;
    JLabel timeTaken;
    
    //Client Constructor which creates GUI and makes tasks
    public Client() {
        super("Task Composite");
        makeTasks();
        createGUI();
    }


    //Create GUI method which creates GUI for the client 
    private void createGUI() {
        //Creates a panel for the tree
        treePanel = new JPanel();
        getContentPane().add(treePanel);
        treePanel.setLayout(new BorderLayout());
        sp = new JScrollPane();
        treePanel.add("Center", sp);
        treePanel.add("South", timeTaken = new JLabel("          "));
        treePanel.setBorder(new BevelBorder(BevelBorder.RAISED));
        troot = new DefaultMutableTreeNode(projects.getName());
        tree= new JTree(troot);
        tree.setBackground(Color.lightGray);
        loadTree(projects);
        /* Put the Tree in a scroller. */
        sp.getViewport().add(tree);
        setSize(new Dimension(200, 300));
        setVisible(true);
    }

    public void loadTree(Task topTask) {
        DefaultMutableTreeNode troot;
        troot = new DefaultMutableTreeNode(topTask.getName());
        treePanel.remove(tree);
        tree= new JTree(troot);
        tree.addTreeSelectionListener(this);
        sp.getViewport().add(tree);
        addNodes(troot, topTask);
        tree.expandRow(0);
        repaint();
    }

    private void addNodes(DefaultMutableTreeNode pnode, Task task) {
        Enumeration e = task.subtasks();
        if (e != null) {
            while (e.hasMoreElements()) {
                Task t = (Task)e.nextElement();
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(t.getName());
                pnode.add(node);
                if (!t.isLeaf()) {
                    addNodes(node, t);
                }
            }
        }
    }

    private void makeTasks() {
       //All the nodes under the Master Node Projects are added here
       projects = new MasterTask("Projects", 0);
       projects.add(sap = new MasterTask("SAP", 0));
       projects.add(website = new MasterTask("Website", 0));
        projects.add(payroll = new MasterTask("Payroll", 0));

        //All the nodes under the Master Node SAP are added here
        sap.add(analysisSap = new MasterTask("Analysis", 0));
        sap.add(codingSap = new MasterTask("Coding", 20));
        sap.add(requirementsSap = new MasterTask("Requirements", 10));


        //All Leaf nodes under the master Node Analysis are added here
        analysisSap.add(uml = new Task("UML", rand_time(10)));
        analysisSap.add(dataAnalysis = new Task("Data Analysis", rand_time(10)));
        analysisSap.add(screenDesign = new Task("Screen Design", rand_time(10)));


        //All the nodes under website 
        website.add(designWebsite = new MasterTask("Design", 20));
        website.add(codingWebsite = new MasterTask("Coding", 20));
        website.add(analysisWebsite = new MasterTask("Analysis", 10));

        //All the leaf nodes under the master node Coding are added here
        codingWebsite.add(prog = new Task("Programming", rand_time(10)));
        codingWebsite.add(screens = new Task("Screen Design", rand_time(10)));

        //All the leaf nodes under master node Payroll are added here
        payroll.add(designPayroll = new Task("Design", rand_time(10)));
        payroll.add(codingPayroll = new Task("Coding", rand_time(10)));
        payroll.add(testingPayroll = new Task("Testing", rand_time(10)));
    }

    //Method to calculate random time
    private long rand_time(long time) {
        return time +(long)(Math.random () -0.5)*time/5;
    }

    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
        if (node == null) return;
        String nodeName = (String)node.getUserObject();
        Task t = projects.getChild(nodeName);
        if (t != null) {
            timeTaken.setText("Time taken for " + nodeName + " is " + t.getTime() + " hours");
        }
    }

    static public void main(String argv[]) {
        new Client();
    }


}
