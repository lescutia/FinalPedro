package graphreduction;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author Luis Escutia, Yolanda Alvarez 3/31/2017
 */
public class CNode {

    public enum eLabels {
        USE, DEF, RETURN, IF, ENDIF, WHILE, ENDWHILE, CONTINUE, BREAK, FOR, ENDFOR, ELSE, ENDELSE, START, END
    };

    /*< Variable with unique value to identify nodes. */
    int m_iID;

    boolean m_isId = false;
    /**
     * esta variable le sirve a gamma para su algoritmo
     */
    public boolean gammaExplored=false;
    /*< Enum to identify the instruction type. */
    ArrayList<eLabels> m_eLstInstructionsTypes;
    /*< string array of the defined variables. */
    public ArrayList<String> m_lstDefs;
    /*< string array of the used variables. */
    public ArrayList<String> m_lstUses;
    /*< string array of code lines. */
    ArrayList<String> m_lstCodeLines;
    /*< Pointer to the next node/default. */
    public CNode m_pLeftNode;
    /*< In conditional case the next node. */
    public CNode m_pRightNode;
    /*< variable to know if a node was already visited. */
    boolean m_bVisited;
    /*is used for make the graph, only team 1 use this variables*/
    public CNode m_lastLeft;
    /*is used for make the graph, only team 1 use this variables*/
    public CNode m_lastRight;
    /*Used to identify in the parser the type of node, only is used for make the graph*/
    int m_nType;
    /*
    boolean for make the graph
     */
    public boolean m_GExplored = false;
    /*if the node is breakNode, i need this*/
    public boolean breakRedirect = false;

    /**
     * constructor
     */

    public void mergeUses(CNode n1, CNode n2) {
        HashSet<String> set = new HashSet<String>();
        set.addAll(n1.m_lstUses);
        set.addAll(n2.m_lstUses);
        m_lstUses.addAll(set);
    }

    public void mergeUses(CNode n1) {
        HashSet<String> set = new HashSet<String>();
        set.addAll(n1.m_lstUses);
        m_lstUses.addAll(set);
    }

    public CNode() {
        m_bVisited = false;
        m_lstDefs = new ArrayList<>();
        m_lstUses = new ArrayList<>();
        m_lstCodeLines = new ArrayList<>();
        m_eLstInstructionsTypes=new ArrayList<>();
    }

    public boolean isId() {
        return m_isId;
    }

    public void idVar() {
        m_isId = true;
    }

    /**
     * constructor used in the parser
     *
     * @param id
     * @param codeLine
     */
    public CNode(int id, String codeLine) {
        m_bVisited = false;
        m_lstDefs = new ArrayList<>();
        m_lstUses = new ArrayList<>();
        m_lstCodeLines = new ArrayList<>();
        m_eLstInstructionsTypes=new ArrayList<>();
        m_iID = id;
        m_lstCodeLines.add(codeLine);
    }

    /**
     * constructor without ID
     *
     * @param codeLine
     */
    public CNode(String codeLine) {
        m_bVisited = false;
        m_lstDefs = new ArrayList<>();
        m_lstUses = new ArrayList<>();
        m_lstCodeLines = new ArrayList<>();
        m_lstCodeLines.add(codeLine);
        m_eLstInstructionsTypes=new ArrayList<>();
    }

    /**
     * @Param in_Node is the element wanted to conccatenate with the actual
     * node.
     */
    public void concatenateDU(CNode in_Node) {
        if (in_Node.m_lstDefs.size() > 0) {
            for (int i = 0; i < in_Node.m_lstDefs.size(); i++) {
                m_lstDefs.add(in_Node.m_lstDefs.get(i));
            }
        }

        if (in_Node.m_lstUses.size() > 0) {
            for (int i = 0; i < in_Node.m_lstUses.size(); i++) {
                m_lstUses.add(in_Node.m_lstUses.get(i));
            }
        }
    }

    /*
    *   @Param in_eLabelToCheck is the Label that you'r looking for.
    *   @Return true if the label exist, otherwhise return false.
     */
    public boolean instructionTypeExist(eLabels in_eLabelToCheck) {
        for (int i = 0; i < m_eLstInstructionsTypes.size(); i++) {
            if (m_eLstInstructionsTypes.get(i) == in_eLabelToCheck) {
                return true;
            }
        }
        return false;
    }

    /**
     * set the left node
     *
     * @param left
     */
    public void setLeft(CNode left) {
        m_pLeftNode = left;
    }

    /**
     * set the right node
     *
     * @param right
     */
    public void setRight(CNode right) {
        m_pRightNode = right;
    }

    /*set lastLeft node*/
    public void setLastLeft(CNode lastLeft) {
        m_lastLeft = lastLeft;
    }

    public int getType() {
        return m_nType;
    }

    /*set lastRight node*/
    /**
     * for making the graph i need this don´t touch, i see you
     *
     * @param m_nType
     */
    public void setType(int m_nType) {
        this.m_nType = m_nType;
    }

    public void setLastRight(CNode lastRight) {
        m_lastRight = lastRight;
    }

    /**
     * Add code line from the Parser
     *
     * @param codeLine
     */
    public void addCodeLine(String codeLine) {
        m_lstCodeLines.add(codeLine);
    }

    public void addDef(String codeLine) {
        m_lstDefs.add(codeLine);
    }

    public void addDefList(CNode node) {
        m_lstDefs.addAll(node.m_lstDefs);
    }

    public void addUse(String codeLine) {
        m_lstUses.add(codeLine);
    }

    public String getSingleCodeLine() {
        if (m_lstCodeLines.size() > 0) {
            return m_lstCodeLines.get(0);
        } else {
            return "";
        }
    }

    public int getId() {
        return m_iID;
    }
    
    public void addLabel(eLabels lb){
        m_eLstInstructionsTypes.add(lb);
    }
}
