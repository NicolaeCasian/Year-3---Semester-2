package Exercise_1;

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

    JScrollPane sp;
    JPanel treePanel;
    JTree tree;
    DefaultMutableTreeNode troot;
    JLabel timeTaken;
    
    public Client() {
        super("Task Composite");
        makeTasks();
        createGUI();
    }

    private void createGUI() {
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

        //All the nodes under the Master Node SAP are added here
        sap = new MasterTask(projects, "SAP", 0);
        analysisSap = new MasterTask(sap, "Analysis", 0);
        codingSap = new MasterTask(sap, "Coding", 0);
        requirementsSap = new MasterTask(sap, "Requirements", 0);

        //All Leaf nodes under the master Node Analysis are added here
        uml = new Task(analysisSap, "UML", rand_time(10));
        dataAnalysis = new Task(analysisSap, "Data Analysis", rand_time(10));
        screenDesign = new Task(analysisSap, "Screen Design", rand_time(10));

        //All the nodes under website 
        website = new MasterTask(projects, "Website", 0);
        designWebsite = new MasterTask(website, "Design", 0);
        codingWebsite = new MasterTask(website, "Coding", 0);
        analysisWebsite = new MasterTask(website, "Analysis", 0);

        //All the leaf nodes under the master node Coding are added here
        prog = new Task(codingWebsite, "Programming", rand_time(10));
        screens = new Task(codingWebsite, "Screens", rand_time(10));

        //All the leaf nodes under master node Payroll are added here
        payroll = new MasterTask(projects, "Payroll", 0);
        designPayroll = new Task(payroll, "Design", rand_time(10));
        codingPayroll = new Task(payroll, "Coding", rand_time(10));
        testingPayroll = new Task(payroll, "Testing", rand_time(10));
    }

    private long rand_time(long time) {
        return time + (long)(Math.random() * 1000);
    }

    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
        if (node == null) return;
        String nodeName = (String)node.getUserObject();
        Task t = projects.getChild(nodeName);
        if (t != null) {
            timeTaken.setText("Time taken for " + nodeName + " is " + t.getTime());
        }
    }

    static public void main(String argv[]) {
        new Client();
    }


}
