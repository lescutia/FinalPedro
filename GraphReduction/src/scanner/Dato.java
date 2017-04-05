/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scanner;

/**
 *
 * @author SirHumanoide
 */
public class Dato
{
final int T_INT = 1; 
final int T_FLOAT = 2;

int tipo; // En

int clase; // Var. loca

public Dato()
{
//...
}
public void setTipo(int valorTipo)
{
this.tipo=valorTipo;
}
public int getTipo()
{
return this.tipo;
}

}