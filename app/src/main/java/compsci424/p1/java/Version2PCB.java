/* COMPSCI 424 Program 1
 * Name: Jack Lemm
 */
package compsci424.p1.java;

/**
 * The process control block structure that is used to track a
 * process's parent, first child, younger sibling, and older sibling
 * (if they exist) in Version 2.
 */
public class Version2PCB {
    private int parentPid;
    private int firstChildPid;
    private int youngerSiblingPid;
    private int olderSiblingPid;

    public Version2PCB(int parentPid) {
        this.parentPid = parentPid;
        this.firstChildPid = -1; // No child initially
        this.youngerSiblingPid = -1; // No younger sibling initially
        this.olderSiblingPid = -1; // No older sibling initially
    }

    public int getParentPid() {
        return parentPid;
    }

    public void setParentPid(int parentPid) {
        this.parentPid = parentPid;
    }

    public int getFirstChildPid() {
        return firstChildPid;
    }

    public void setFirstChildPid(int firstChildPid) {
        this.firstChildPid = firstChildPid;
    }

    public int getYoungerSiblingPid() {
        return youngerSiblingPid;
    }

    public void setYoungerSiblingPid(int youngerSiblingPid) {
        this.youngerSiblingPid = youngerSiblingPid;
    }

    public int getOlderSiblingPid() {
        return olderSiblingPid;
    }

    public void setOlderSiblingPid(int olderSiblingPid) {
        this.olderSiblingPid = olderSiblingPid;
    }
}