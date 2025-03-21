

import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.Vector;

public class Task extends AbstractProcess{
     public Task(String initName, float initTime) {
        name = initName;  time = initTime; leaf = true;
    }
    public Task(Task initParent, String initName, float initTime) {
        name = initName; time = initTime;
        parent = initParent; leaf = true;
    }
    public float getTime() {return time;}
    public String getName(){return name;}

    public boolean add(Task e) throws NoSuchElementException {
        throw new NoSuchElementException("No subordinates");
    }
    public void remove(Task e) throws NoSuchElementException {
        throw new NoSuchElementException("No subordinates");
    }
    public Enumeration subtasks () {
        Vector v = new Vector();
        return v.elements ();
    }
    public Task getChild(String s) throws NoSuchElementException {
        throw new NoSuchElementException("No children");
    }
    public float getSalaries() {return time;}
    public Task getParent() {return parent;}
}
