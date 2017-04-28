/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphreduction;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *Esta clase sirve para tomar un mapa con los nombres de las funciones
 * asociados al nodo inicial de la función
 * @author Humanoide
 */
public class CGraphManager {
    static Map<String, CGraph> m_GraphsMaps = new LinkedHashMap();
    
    public static CGraph getGraph( String in_strGraphName )
    {
        CGraph result = null;
        if( !m_GraphsMaps.containsKey( in_strGraphName ) )
        {
            result = new CGraph();
            m_GraphsMaps.put(in_strGraphName, result);
        }
        else
            result = m_GraphsMaps.get( in_strGraphName );
        
         return result;
    }
    
    public static void addGraph(String in_strGraphName, CGraph m_graph){
        if( !m_GraphsMaps.containsKey( in_strGraphName ) )
        {
            m_GraphsMaps.put(in_strGraphName, m_graph);
        }
    }
    
    public static Map<String, CGraph> getGraphs()
    {
        return m_GraphsMaps;
    }
}
