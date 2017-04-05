package graphreduction;
import java.util.ArrayList;

/**
 *  @author Luis Escutia, Yolanda Alvarez
 *  3/31/2017
 */
public class CNode 
{
    public enum eLabels{USE,DEF,RETURN,IF,ENDIF,WHILE,ENDWHILE,FOR,ENDFOR,ELSE,ENDELSE};
    
    /*< Variable with unique value to identify nodes. */
    int m_iID;
    /*< Enum to identify the instruction type. */
    ArrayList<eLabels> m_eLstInstructionsTypes;
    /*< string array of the defined variables. */
    ArrayList<String> m_lstDefs;
    /*< string array of the used variables. */
    ArrayList<String> m_lstUses;
    /*< string array of code lines. */
    ArrayList<String> m_lstCodeLines;
    /*< Pointer to the next node/default. */
    CNode m_pLeftNode;
    /*< In conditional case the next node. */
    CNode m_pRightNode;
    /*< variable to know if a node was already visited. */
    boolean m_bVisited;
    
    public CNode()
    {
        m_bVisited = false;
        m_lstDefs = new ArrayList<>();
        m_lstUses = new ArrayList<>();
        m_lstCodeLines = new ArrayList<>();
    }
    
    /*
    *   @Param in_Node is the element wanted to conccatenate with the actual node.
    */
    public void concatenateDU(CNode in_Node)
    {
        if( in_Node.m_lstDefs.size() > 0 )
        {
            for( int i = 0; i < in_Node.m_lstDefs.size(); i++ )
                m_lstDefs.add(in_Node.m_lstDefs.get(i) );
        }
        
        if( in_Node.m_lstUses.size() > 0 )
        {
            for( int i = 0; i < in_Node.m_lstUses.size(); i++ )
                m_lstUses.add(in_Node.m_lstUses.get(i) );
        }
    }
    
    /*
    *   @Param in_eLabelToCheck is the Label that you'r looking for.
    *   @Return true if the label exist, otherwhise return false.
    */
    public boolean instructionTypeExist( eLabels in_eLabelToCheck )
    {
        for( int i = 0; i < m_eLstInstructionsTypes.size(); i++ )
            if( m_eLstInstructionsTypes.get(i) == in_eLabelToCheck )
                return true;
        return false;
    }
}
