package caldeiraVapor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author eliezer
 */
public class CaldeiraVaporGui extends javax.swing.JFrame {
    /**
     * Creates new form NewJFrame
     */
   
    public CaldeiraVaporGui() {
        initComponents();
        Thread caldeiraSimulada = new CaldeiraVapor ();
        caldeiraSimulada.start();
    }
    
    public static void alteraGui(int litros ){
        System.out.println("SETANDO");
        capacidadeGui.setText(litros+"");
    }
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton4 = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton5 = new javax.swing.JToggleButton();
        capacidadeGui = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(960, 590));
        getContentPane().setLayout(null);

        jToggleButton2.setText("Aberto/Fechado");
        getContentPane().add(jToggleButton2);
        jToggleButton2.setBounds(90, 260, 140, 23);

        jToggleButton4.setText("Aberto/Fechado");
        getContentPane().add(jToggleButton4);
        jToggleButton4.setBounds(90, 420, 140, 23);

        jToggleButton3.setText("Aberto/Fechado");
        getContentPane().add(jToggleButton3);
        jToggleButton3.setBounds(90, 340, 140, 23);

        jToggleButton1.setText("Aberto/Fechado");
        getContentPane().add(jToggleButton1);
        jToggleButton1.setBounds(90, 170, 140, 23);

        jToggleButton5.setText("Aberto/Fechado");
        getContentPane().add(jToggleButton5);
        jToggleButton5.setBounds(770, 420, 140, 23);

        capacidadeGui.setText("1000L");
        getContentPane().add(capacidadeGui);
        capacidadeGui.setBounds(450, 270, 49, 14);

        jLabel1.setText("500 ml/s");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(460, 80, 120, 14);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caldeiraVapor.png"))); // NOI18N
        jLabel2.setText("NivelCaldeira");
        jLabel2.setMinimumSize(new java.awt.Dimension(950, 550));
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 950, 550);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JLabel capacidadeGui;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JToggleButton jToggleButton4;
    private javax.swing.JToggleButton jToggleButton5;
    // End of variables declaration//GEN-END:variables
}
