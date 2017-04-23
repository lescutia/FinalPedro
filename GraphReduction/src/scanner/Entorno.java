/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanner;

import graphreduction.CGraph;
import graphreduction.CGraphManager;
import graphreduction.CNode;
import highlight.CTokenMarker;
import highlight.JEditTextArea;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import logic.TruthTable;

/**
 *
 * @author Humanoide
 */
public class Entorno extends javax.swing.JFrame {

    /**
     * Creates new form Entorno
     */
    JEditTextArea je = new JEditTextArea();
    parser p;

    public Entorno() {
        initComponents();
        je.setTokenMarker(new CTokenMarker());
        je.setVisible(true);
        je.setSize(800, 500);
        je.setCaretBlinkEnabled(true);
        je.setElectricScroll(ERROR);
        je.setHorizontalOffset(10);
        je.setOverwriteEnabled(true);
        jPanel1.add(je);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Pegar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Abrir");

        jButton2.setText("Analizar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(874, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(419, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        je.paste();
    }//GEN-LAST:event_jButton1ActionPerformed
    String msj = "";
   // CGraphManager manager=new CGraphManager();
    String mm;
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            msj = "";
            // TODO add your handling code here:
            String Cadena = je.getText();
            /*ESTA CHINGADERILLA DE CÓDIGO ME SIRVE PARA JALAR EL TEXTO Y QUE LO ANALIZE EL PARSER*/
            p = new parser(new Yylex(new BufferedReader(new StringReader(Cadena))));
            p.parse();
            // CGraph tmpGrpah = CGraphManager.getGraph("funcion");
            /*MORRO, BOOLEXP ES UNA LISTA CON LAS EXPRESIONES BOOLEANAS PERO DE TODO EL PROGRAMA*/
            
            String exps = p.action_obj.boolexp.toString();
            CNode nod = p.action_obj.program;
            msj = msj + "digraph G {\nnode [style=filled];\n";
            //explore(nod);

            LinkedList<functionIndex> fn = p.action_obj.fnList;
            for (functionIndex c : fn) {
                CGraph graph=new CGraph();
                graph.addBeginNode(c.getStart());
                graph.addEndNode(c.getEnd());
                //graph.compressNodes();
                mm=graph.getBeginNode().getId()+"\n";
                mm=mm+graph.getEndNode().getId()+"\n";
                
                explore(graph.getBeginNode());
                mm=mm+labels.size()+"\n";
                labels.clear();
                JOptionPane.showMessageDialog(null, mm);
                added.clear();
                mm="";
                CGraphManager.addGraph(c.getName(), graph);

            }
            msj = msj + "}";
            /*
            StringSelection stringSelection = new StringSelection(msj);
            Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
            clpbrd.setContents(stringSelection, null);
            JOptionPane.showMessageDialog(null, exps);
            */
            

        } catch (Exception ex) {
            Logger.getLogger(Entorno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    ArrayList<String> added = new ArrayList<String>();
    HashSet<Integer> labels=new HashSet<Integer>();
/*
    public void explore(CNode n) {
        if (n.m_pLeftNode != null && !n.m_GExplored) {
            if (n.getSingleCodeLine().contains("function")) {
                n.setType(7);
            }
            if (n.m_pLeftNode.getSingleCodeLine().contains("end function")) {
                n.m_pLeftNode.setType(9);
            }
            // n.m_pLeftNode.addParent();
            String m;
            if (n.getType() != 0) {
                m = "\"" + n.getId() + " .. " + n.getSingleCodeLine().replaceAll("\"", "") + " uses :" + n.m_lstUses.toString() + "  defs :" + n.m_lstDefs.toString() + "\" -> \"" + n.m_pLeftNode.getId() + " .. " + n.m_pLeftNode.getSingleCodeLine().replaceAll("\"", "") + " uses :" + n.m_pLeftNode.m_lstUses.toString() + "  defs :" + n.m_pLeftNode.m_lstDefs.toString() + "\"[ label = \"SI\" ] \n";
                String color = "[color=goldenrod3]";
                //[color=\"0.650 0.200 1.000\"]
                if (n.getType() == 7) {
                    color = "[color=lemonchiffon2]";
                }
                if (n.getType() == 6) {
                    color = "[color=lightsteelblue1]";
                }
                if (n.getType() == 9) {
                    color = "[color=cadetblue1]";
                }
                msj = msj + "\"" + n.getId() + " .. " + n.getSingleCodeLine().replaceAll("\"", "") + " uses :" + n.m_lstUses.toString() + "  defs :" + n.m_lstDefs.toString() + "\"[shape=box]" + color + "\n";

            } else {
                m = "\"" + n.getId() + " .. " + n.getSingleCodeLine().replaceAll("\"", "") + " uses :" + n.m_lstUses.toString() + "  defs :" + n.m_lstDefs.toString() + "\" -> \"" + n.m_pLeftNode.getId() + " .. " + n.m_pLeftNode.getSingleCodeLine().replaceAll("\"", "") + " uses :" + n.m_pLeftNode.m_lstUses.toString() + "  defs :" + n.m_pLeftNode.m_lstDefs.toString() + "\" \n";
            }
            boolean band = false;
            for (int i = 0; i < added.size(); i++) {
                if (m.equals(added.get(i))) {
                    band = true;
                    break;
                }
            }
            added.add(m);
            if (!band) {
                msj = msj + m;
            }
            n.m_GExplored = true;
            explore(n.m_pLeftNode);
        }
        if (n.m_pRightNode != null) {
            String m = "\"" + n.getId() + " .. " + n.getSingleCodeLine().replaceAll("\"", "") + " uses :" + n.m_lstUses.toString() + "  defs :" + n.m_lstDefs.toString() + "\" -> \"" + n.m_pRightNode.getId() + " .. " + n.m_pRightNode.getSingleCodeLine().replaceAll("\"", "") + " uses :" + n.m_pRightNode.m_lstUses.toString() + "  defs :" + n.m_pRightNode.m_lstDefs.toString() + "\"[ label = \"NO\" ] \n";

            boolean band = false;
            for (int i = 0; i < added.size(); i++) {
                if (m.equals(added.get(i))) {
                    band = true;
                    break;
                }
            }
            added.add(m);
            if (!band) {
                msj = msj + m;
            }
            n.m_GExplored = true;
            explore(n.m_pRightNode);
        }

    }*/
 
    public void explore(CNode n) {
        if (n.m_pLeftNode != null && n.m_pRightNode== null&& !n.m_GExplored) {
          
            
            String m="["+n.getId()+"]"+
                    "["+n.m_pLeftNode.getId()+"]\n";
          labels.add(n.getId());
          labels.add(n.m_pLeftNode.getId());
            boolean band = false;
            for (int i = 0; i < added.size(); i++) {
                if (m.equals(added.get(i))) {
                    band = true;
                    break;
                }
            }
            added.add(m);
            if (!band) {
                mm = mm + m;
            }
            n.m_GExplored = true;
            explore(n.m_pLeftNode);
        }
        if (n.m_pRightNode != null) {
           String m=" "+n.getId()+" "+
                    " "+n.m_pLeftNode.getId()+" "+n.m_pRightNode.getId()+"\n";
            labels.add(n.getId());
           labels.add(n.m_pRightNode.getId());
            boolean band = false;
            for (int i = 0; i < added.size(); i++) {
                if (m.equals(added.get(i))) {
                    band = true;
                    break;
                }
            }
            added.add(m);
            if (!band) {
                mm = mm + m;
            }
            n.m_GExplored = true;
            explore(n.m_pRightNode);
        }

    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Entorno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Entorno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Entorno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Entorno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Entorno().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
