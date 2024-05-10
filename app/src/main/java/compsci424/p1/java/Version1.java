/* COMPSCI 424 Program 1
 * Name: Jack Lemm
 */
package compsci424.p1.java;

import java.util.LinkedList;

/**
 * Implements the process creation hierarchy for Version 1, which uses
 * linked lists.
 */
public class Version1 {
    private Version1PCB[] pcbs;

    public Version1(int n) {
        pcbs = new Version1PCB[n];
        // initialize PCB for process 0
        pcbs[0] = new Version1PCB(-1); // parent PID of process 0 is -1
    }

    public int create(int parentPid) {
        if (parentPid < 0 || parentPid >= pcbs.length || pcbs[parentPid] == null) {
        	System.out.println("Error: Parent process does not exist");
            return -1; // parent process does not exist
        }

        // allocate and initialize a free PCB object
        for (int i = 0; i < pcbs.length; i++) {
            if (pcbs[i] == null) {
                pcbs[i] = new Version1PCB(parentPid);
                // insert the newly allocated PCB object into parentPid's list of children
                pcbs[parentPid].addChildPid(i);
                return 0; // success!
            }
        }
        return -2; // no free PCB available
    }

    public int destroy(int targetPid) {
        if (targetPid < 0 || targetPid >= pcbs.length || pcbs[targetPid] == null) {
            return -1; // target process does not exist
        }

        // recursively destroy all descendants of targetPid
        LinkedList<Integer> children = pcbs[targetPid].getChildrenPids();
        for (int childPid : children) {
            destroy(childPid);
        }

        // remove targetPid from its parent's list of children
        int parentPid = pcbs[targetPid].getParentPid();
        pcbs[parentPid].removeChildPid(targetPid);

        // deallocate targetPid's PCB
        pcbs[targetPid] = null;

        return 0; // success!
    }

    public void showProcessInfo() {
        for (int i = 0; i < pcbs.length; i++) {
            if (pcbs[i] != null) {
                int parentPid = pcbs[i].getParentPid();
                LinkedList<Integer> children = pcbs[i].getChildrenPids();

                System.out.print("Process " + i + ": parent is " + parentPid);
                if (children.isEmpty()) {
                    System.out.println(" and has no children");
                } else {
                    System.out.print(" and children are ");
                    for (int childPid : children) {
                        System.out.print(childPid + " ");
                    }
                    System.out.println();
                }
            }
        }
    }
}

 
