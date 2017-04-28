/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphreduction;

import java.util.ArrayList;
import scanner.BooleanExpresion;

/**
 *
 * @author Humanoide
 */
public class BooleanExpressionsManager {
    static ArrayList<BooleanExpresion> boolList;
    
    public static void setList(ArrayList<BooleanExpresion> boolList){
        BooleanExpressionsManager.boolList=boolList;
    }
    
    public static ArrayList<BooleanExpresion> getList(){
        return BooleanExpressionsManager.boolList;
    }
}
