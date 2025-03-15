package Exercise_1;
import java.util.*;

public  abstract class  AbstractProcess {
    protected String name;
    protected float time;
    protected Task parent = null;
    protected boolean leaf = true;

    public abstract float getTime();
    public abstract String getName();
    public abstract boolean add(Task e)
	    throws NoSuchElementException;
    public abstract void remove(Task e)
	    throws NoSuchElementException;
    public abstract Enumeration subtasks();
    public abstract Task getChild(String s);
    public abstract float getSalaries();
    public Boolean isLeaf(){
	    return leaf;
    }
}
