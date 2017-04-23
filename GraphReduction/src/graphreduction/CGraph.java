package graphreduction;
import java.util.ArrayList;

/**
 * @author Luis Escutia, Yolanda Alvarez
 * 3/31/2017
 */
public class CGraph 
{
    CNode m_BeginNode;
    ArrayList<CNode> m_EndNodes = new ArrayList<>();
    int m_iNumNodes;
    
    public CGraph()
    {
        
    }

    public CNode getBeginNode() {
        return m_BeginNode;
    }
    
    public void increaseNumNodes(){ m_iNumNodes++; }
    
    public void addBeginNode( CNode in_BeginNode )
    {
        m_BeginNode = in_BeginNode;
    }
    
    public void addEndNode( CNode in_EndNode )
    {
        m_EndNodes.add(in_EndNode);
    }
    
    public void compressNodes()
    {
        boolean bActualhasDU = getDU( m_BeginNode );
        m_BeginNode.m_bVisited = true;
        compressNodes( m_BeginNode, m_BeginNode.m_pLeftNode, bActualhasDU );
        compressNodes( m_BeginNode, m_BeginNode.m_pRightNode, bActualhasDU );
    }
    
    /*
    *   @Param in_prev is the previous node of the graph (Parent)
    *   @Param in_actual is the actual node of the graph
    *   @Param in_bPrevHasDU is a flag to identify if the parent have a def/use
    */
    void compressNodes( CNode in_prev, CNode in_actual, boolean in_bPrevHasDU )
    {
        if( in_actual != null && !in_actual.m_bVisited && in_prev != null  )
        {
            in_actual.m_bVisited = true;
            boolean bActualhasDU = getDU( in_actual );
            //In case of DU node.
            if( in_bPrevHasDU && bActualhasDU )
            {
                in_prev.concatenateDU(in_actual);
                in_prev.m_pLeftNode = in_actual.m_pLeftNode;
                in_actual = in_prev;
                m_iNumNodes--;
            }
            else if ( !bActualhasDU && doesSectionCodeEnds(in_actual) )
            {
                in_prev.m_pLeftNode = in_actual.m_pLeftNode;
                in_actual = in_prev;
            }
            compressNodes( in_actual, in_actual.m_pLeftNode, bActualhasDU );
            compressNodes( in_actual, in_actual.m_pRightNode, bActualhasDU );
        }
    }
    
    /*
    *   @Param in_Node is the node that you're looking for a def or use.
    *   @Return true if the node has a def or use.
    */
    boolean getDU( CNode in_Node )
    {/*
        return in_Node.instructionTypeExist( CNode.eLabels.DEF ) ? true : 
                in_Node.instructionTypeExist( CNode.eLabels.USE );*/
        return in_Node.m_lstUses.size()>0 || in_Node.m_lstDefs.size()>0; 
    }
    
    /*
    *   @Param in_Node is the node that you're looking to identify if it's a dummy node.
    *   @Return true if the node is just to identify where does a section of code ends, otherwhise false.
    */
    boolean doesSectionCodeEnds( CNode in_Node )
    {
        return in_Node.instructionTypeExist( CNode.eLabels.RETURN ) ? true:
                in_Node.instructionTypeExist( CNode.eLabels.ENDFOR ) ? true:
                    in_Node.instructionTypeExist( CNode.eLabels.ENDIF )? true:
                        in_Node.instructionTypeExist( CNode.eLabels.ENDWHILE ) ? true:
                            in_Node.instructionTypeExist( CNode.eLabels.ENDELSE )? true:
                                in_Node.instructionTypeExist( CNode.eLabels.END);
    }
    
    public void generateCoverageGraph()
    {
        //Generar txt
    }
}
