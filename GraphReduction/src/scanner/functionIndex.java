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
    LinkedList<CNode> nodes=new LinkedList<CNode>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<CNode> getNodes() {
        return nodes;
    }

    public functionIndex() {
    }

    public void setNodes(LinkedList<CNode> nodes) {
        this.nodes = nodes;
    }
    
    public void addNode(CNode c){
        nodes.addLast(c);
    }
    
}
