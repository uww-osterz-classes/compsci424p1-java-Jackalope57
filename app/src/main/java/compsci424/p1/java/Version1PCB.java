/* COMPSCI 424 Program 1
 * Name: Jack Lemm
 */
import java.util.LinkedList;

/**
 * The process control block structure that is used to track a
 * process's parent and children (if any) in Version 1.
 */
public class Version1PCB {
    private int parentPid;
    private LinkedList<Integer> childrenPids;

    public Version1PCB(int parentPid) {
        this.parentPid = parentPid;
        this.childrenPids = new LinkedList<>();
    }

    public int getParentPid() {
        return parentPid;
    }

    public void setParentPid(int parentPid) {
        this.parentPid = parentPid;
    }

    public LinkedList<Integer> getChildrenPids() {
        return childrenPids;
    }

    public void addChildPid(int childPid) {
        childrenPids.add(childPid);
    }

    public void removeChildPid(int childPid) {
        childrenPids.remove(Integer.valueOf(childPid));
    }
}