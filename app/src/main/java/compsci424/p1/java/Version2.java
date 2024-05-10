/* COMPSCI 424 Program 1
 * Name: Jack Lemm
 */
package compsci424.p1.java;

/**
 * Implements the process creation hierarchy for Version 2, which does
 * not use linked lists.
 */
public class Version2 {
    private Version2PCB[] pcbs;

    public Version2(int n) {
        pcbs = new Version2PCB[n];
        // initialize PCB for process 0
        pcbs[0] = new Version2PCB(-1); // parent PID of process 0 is -1
    }

    public int create(int parentPid) {
        if (parentPid < 0 || parentPid >= pcbs.length || pcbs[parentPid] == null) {
        	System.out.println("Error: Parent process does not exist");
            return -1; // parent process does not exist
        }

        // allocate and initialize a free PCB object
        for (int i = 0; i < pcbs.length; i++) {
            if (pcbs[i] == null) {
                pcbs[i] = new Version2PCB(parentPid);
                // connect the new PCB object to its parent
                pcbs[i].setParentPid(parentPid);
                // connect to siblings if they exist
                int youngestSibling = pcbs[parentPid].getFirstChildPid();
                if (youngestSibling == -1) {
                    // if this is the first child, set as the first child
                    pcbs[parentPid].setFirstChildPid(i);
                } else {
                	// find the youngest sibling and set the new process as its younger sibling
                	while (pcbs[youngestSibling].getYoungerSiblingPid() != -1) {
                	    youngestSibling = pcbs[youngestSibling].getYoungerSiblingPid();
                	}
                	// connect the new process as the younger sibling of its older sibling
                	pcbs[i].setOlderSiblingPid(youngestSibling);
                	pcbs[youngestSibling].setYoungerSiblingPid(i);
                }
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
        int childPid = pcbs[targetPid].getFirstChildPid();
        while (childPid != -1) {
            destroy(childPid);
            childPid = pcbs[targetPid].getFirstChildPid();
        }

        // adjust connections within the hierarchy graph
        int olderSibling = pcbs[targetPid].getOlderSiblingPid();
        int youngerSibling = pcbs[targetPid].getYoungerSiblingPid();
        if (olderSibling != -1) {
            pcbs[olderSibling].setYoungerSiblingPid(youngerSibling);
        } else {
            // no older sibling, set parent's first child to the younger sibling
            pcbs[pcbs[targetPid].getParentPid()].setFirstChildPid(youngerSibling);
        }
        if (youngerSibling != -1) {
            pcbs[youngerSibling].setOlderSiblingPid(olderSibling);
        }

        // deallocate targetPid's PCB
        pcbs[targetPid] = null;

        return 0; // success!
    }

    public void showProcessInfo() {
        for (int i = 0; i < pcbs.length; i++) {
            if (pcbs[i] != null) {
                int parentPid = pcbs[i].getParentPid();
                int firstChildPid = pcbs[i].getFirstChildPid();
                int olderSiblingPid = pcbs[i].getOlderSiblingPid();
                int youngerSiblingPid = pcbs[i].getYoungerSiblingPid();

                System.out.print("Process " + i + ": parent is " + parentPid);
                if (firstChildPid == -1) {
                    System.out.println(" and has no children");
                } else {
                    System.out.print(" and children are " + firstChildPid);
                    int siblingPid = youngerSiblingPid;
                    while (siblingPid != -1) {
                        if (pcbs[siblingPid] != null) { // skip if the PCB is null
                            System.out.print(" " + siblingPid);
                        }
                        siblingPid = pcbs[siblingPid].getYoungerSiblingPid();
                    }
                    System.out.println();
                }
            }
        }
    }
}