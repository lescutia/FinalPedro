/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanner;

import java.util.LinkedList;

/**
 *
 * @author Humanoide
 */
public class node {
    String cont;
    node left, right;
    node lastLeft, lastRight;
    boolean explored=false;
    /**
     * Type0=normal
     * 1 = IF
     * 2 = IF ELSE
     * 3 WHILE
     */
    int type=0;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public node(String cont, node left, node right) {
        this.cont = cont;
        this.left = left;
        this.right = right;
    }

    public node(String cont) {
        this.cont = cont;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }

    public node getLeft() {
        return left;
    }

    public void setLeft(node left) {
        this.left = left;
    }

    public node getRight() {
        return right;
    }

    public void setRight(node right) {
        this.right = right;
    }

    public node(String cont, node left) {
        this.cont = cont;
        this.left = left;
    }
    
    

    
}
