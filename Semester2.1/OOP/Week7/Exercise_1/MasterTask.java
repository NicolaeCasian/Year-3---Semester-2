package Exercise_1;
import java.util.*;

import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.Vector;


public class MasterTask extends Task{
    Vector leafs;
    public MasterTask(String initName, long initTime) {
        super(initName, initTime);
        leaf = false;
        leafs = new Vector();
    }
    public MasterTask(Task initParent, String initName, long initTime) {
        super(initParent, initName, initTime);
        leaf = false;
        leafs = new Vector();
    }
    public MasterTask(Task emp) {
        //promotes an employee position to a Boss
        //and thus allows it to have employees
        super(emp.getName (), emp.getTime());
        leafs = new Vector();
        leaf = false;
    }
    public boolean add(Task e) throws NoSuchElementException {
        leafs.add(e);
        return true;
    }
    public void remove(Task e) throws NoSuchElementException {
        leafs.removeElement(e);
    }
    public Enumeration subtasks () {
        return leafs.elements ();
    }
    public float getTime() {
        float sum = time;
        for (int i = 0; i < leafs.size(); i++) {
            sum += ((Task)leafs.elementAt(i)).getTime();
        }
        return sum;
    }
    public Task getChild(String s) throws NoSuchElementException {
        Task newEmp = null;
        if (getName().equals(s))
            return this;
        else {
            boolean found = false;
            Enumeration e = subtasks();
            while (e.hasMoreElements() && (! found)) {
                newEmp = (Task)e.nextElement();
                found = newEmp.getName().equals(s);
                if (! found) {
                    if (! newEmp.isLeaf ()) {
                        newEmp = newEmp.getChild(s);
                    } else
                        newEmp = null;
                    found =(newEmp != null);
                }
            }
            if (found)
                return newEmp;
            else
                return null;
        }
    }
}
