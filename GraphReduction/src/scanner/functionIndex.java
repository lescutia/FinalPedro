/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanner;

import graphreduction.CNode;
import java.util.LinkedList;

/**
 *
 * @author Humanoide
 */
public class functionIndex {
    
    String name;
    CNode start;
    CNode end;

    public functionIndex(String name, CNode start, CNode end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CNode getStart() {
        return start;
    }

    public void setStart(CNode start) {
        this.start = start;
    }

    public CNode getEnd() {
        return end;
    }

    public void setEnd(CNode end) {
        this.end = end;
    }
}