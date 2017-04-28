/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanner;

/**
 *
 * @author Humanoide
 */
public class BooleanExpresion {
    
    String expresion;

    public BooleanExpresion(String expresion, int line) {
        this.expresion = expresion;
        this.line = line;
    }
    int line;

    public String getExpresion() {
        return expresion;
    }

    public void setExpresion(String expresion) {
        this.expresion = expresion;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }
    
    public String toString(){
        return expresion+" : "+line;
    }
    
}
