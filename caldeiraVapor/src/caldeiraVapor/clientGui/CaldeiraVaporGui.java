package caldeiraVapor.clientGui;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import caldeiraVapor.clientGui.ClienteThread;


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
    }
    
    public static void setCapacidade(String litros) {
        capacidadeGui.setText(litros+"L");
        
        int capacidade = Integer.parseInt(litros);     
        maximoLimiteGui.setText((int)(capacidade*0.8)+"");
        maximoNormalGui.setText((int)(capacidade*0.6)+"");
        minimoNormalGui.setText((int)(capacidade*0.1)+"");
        minimoLimiteGui.setText((int)(capacidade*0.05)+"");
    }
    public static void setLevel(String litros ){
        levelGui.setText("Sensor Level: "+litros+"L");
    }
    
    public static void setVapor(String vapor) {
        vaporGui.setText(vapor+"L/s");
    }
    
    public static void setBombaAberta(int quantidade) {
        //SUPER FEIO MAS NAO PENSEI EM NADA MELHOR :B
        if (quantidade > 0) {
            bomba1Gui.setSelected(true);
            bomba1Gui.setText("Aberta");
        } else {
            bomba1Gui.setSelected(false);
            bomba1Gui.setText("Fechada");
        }
        
        if (quantidade > 1) {
            bomba2Gui.setSelected(true);
            bomba2Gui.setText("Aberta");
        } else {
            bomba2Gui.setSelected(false);
            bomba2Gui.setText("Fechada");
        }
        
        if (quantidade > 2) {
            bomba3Gui.setSelected(true);
             bomba3Gui.setText("Aberta");
        } else {
            bomba3Gui.setSelected(false);
            bomba3Gui.setText("Fechada");
        }
        
        if (quantidade > 3) {
            bomba4Gui.setSelected(true);
            bomba4Gui.setText("Aberta");
        } else {
            bomba4Gui.setSelected(false);
            bomba4Gui.setText("Fechada");
        }
    }
    
    public static void setValvula(String vapor) {
        if(vapor.equals("true")) {
            valvulaGui.setSelected(true);
            valvulaGui.setText("Aberta");
        }
        if(vapor.equals("false")) {
            valvulaGui.setSelected(false);
            valvulaGui.setText("Fechada");
        }
    }
    
    public static void setModo(String modo) {
        modoGui.setText("Modo: "+ modo);
    }
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bomba1Gui = new javax.swing.JToggleButton();
        bomba2Gui = new javax.swing.JToggleButton();
        bomba3Gui = new javax.swing.JToggleButton();
        bomba4Gui = new javax.swing.JToggleButton();
        valvulaGui = new javax.swing.JToggleButton();
        capacidadeGui = new javax.swing.JLabel();
        vaporGui = new javax.swing.JLabel();
        levelGui = new javax.swing.JLabel();
        modoGui = new javax.swing.JLabel();
        maximoLimiteGui = new javax.swing.JLabel();
        maximoNormalGui = new javax.swing.JLabel();
        minimoNormalGui = new javax.swing.JLabel();
        minimoLimiteGui = new javax.swing.JLabel();
        fundoGui = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(960, 590));
        getContentPane().setLayout(null);

        bomba1Gui.setText("Aberto/Fechado");
        bomba1Gui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bomba1GuiActionPerformed(evt);
            }
        });
        getContentPane().add(bomba1Gui);
        bomba1Gui.setBounds(90, 180, 140, 30);

        bomba2Gui.setText("Aberto/Fechado");
        bomba2Gui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bomba2GuiActionPerformed(evt);
            }
        });
        getContentPane().add(bomba2Gui);
        bomba2Gui.setBounds(90, 260, 140, 30);

        bomba3Gui.setText("Aberto/Fechado");
        bomba3Gui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bomba3GuiActionPerformed(evt);
            }
        });
        getContentPane().add(bomba3Gui);
        bomba3Gui.setBounds(90, 340, 140, 30);

        bomba4Gui.setText("Aberto/Fechado");
        bomba4Gui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bomba4GuiActionPerformed(evt);
            }
        });
        getContentPane().add(bomba4Gui);
        bomba4Gui.setBounds(90, 420, 140, 30);

        valvulaGui.setText("Aberto/Fechado");
        valvulaGui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valvulaGuiActionPerformed(evt);
            }
        });
        getContentPane().add(valvulaGui);
        valvulaGui.setBounds(770, 420, 140, 30);

        capacidadeGui.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        capacidadeGui.setForeground(new java.awt.Color(0, 0, 0));
        capacidadeGui.setText("Capacity: 1000L");
        getContentPane().add(capacidadeGui);
        capacidadeGui.setBounds(400, 100, 170, 22);

        vaporGui.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        vaporGui.setForeground(new java.awt.Color(192, 0, 0));
        vaporGui.setText("0 ml/s");
        getContentPane().add(vaporGui);
        vaporGui.setBounds(630, 110, 120, 18);

        levelGui.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        levelGui.setForeground(new java.awt.Color(0, 0, 0));
        levelGui.setText("Sensor Level");
        getContentPane().add(levelGui);
        levelGui.setBounds(520, 210, 190, 18);

        modoGui.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        modoGui.setForeground(new java.awt.Color(0, 0, 0));
        modoGui.setText("MODO: ");
        getContentPane().add(modoGui);
        modoGui.setBounds(70, 50, 170, 22);

        maximoLimiteGui.setText("0");
        getContentPane().add(maximoLimiteGui);
        maximoLimiteGui.setBounds(830, 150, 70, 20);

        maximoNormalGui.setText("0");
        getContentPane().add(maximoNormalGui);
        maximoNormalGui.setBounds(850, 228, 70, 20);

        minimoNormalGui.setText("0");
        getContentPane().add(minimoNormalGui);
        minimoNormalGui.setBounds(850, 270, 70, 50);

        minimoLimiteGui.setText("0");
        getContentPane().add(minimoLimiteGui);
        minimoLimiteGui.setBounds(820, 350, 80, 18);

        fundoGui.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caldeiraVapor.png"))); // NOI18N
        fundoGui.setText("NivelCaldeira");
        fundoGui.setMinimumSize(new java.awt.Dimension(950, 550));
        getContentPane().add(fundoGui);
        fundoGui.setBounds(0, 0, 950, 550);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bomba1GuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bomba1GuiActionPerformed
        try {
            if(bomba1Gui.isSelected()) {     
                bomba1Gui.setSelected(true);
                bomba1Gui.setText("Aberta");
                enviarParaServidor("BOMBA,1,true");
            } else {
                bomba1Gui.setSelected(false);
                bomba1Gui.setText("Fechada");
                enviarParaServidor("BOMBA,1,false");
            }
        } catch (IOException ex) {
            Logger.getLogger(CaldeiraVaporGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bomba1GuiActionPerformed
    
    private void enviarParaServidor(String msg) throws IOException {
        PrintWriter out;
        Socket s = new Socket("127.0.0.1", 31314);
        out = new PrintWriter(s.getOutputStream(), true);
        out.println(msg);
    }
    
    
    private void bomba2GuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bomba2GuiActionPerformed
        try {
            if(bomba2Gui.isSelected()) {     
                bomba2Gui.setSelected(true);
                bomba2Gui.setText("Aberta");
                enviarParaServidor("BOMBA,1,true");
            } else {
                bomba2Gui.setSelected(false);
                bomba2Gui.setText("Fechada");
                enviarParaServidor("BOMBA,1,false");
            }
        } catch (IOException ex) {
            Logger.getLogger(CaldeiraVaporGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bomba2GuiActionPerformed

    private void bomba3GuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bomba3GuiActionPerformed
        try {
            if (bomba3Gui.isSelected()) {     
                bomba3Gui.setSelected(true);
                bomba3Gui.setText("Aberta");
                enviarParaServidor("BOMBA,1,true");
            } else {
                bomba3Gui.setSelected(false);
                bomba3Gui.setText("Fechada");
                enviarParaServidor("BOMBA,1,false");
            }
         } catch (IOException ex) {
            Logger.getLogger(CaldeiraVaporGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bomba3GuiActionPerformed

    private void bomba4GuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bomba4GuiActionPerformed
        try {
            if(bomba4Gui.isSelected()) {
                bomba4Gui.setSelected(true);
                bomba4Gui.setText("Aberta");
                enviarParaServidor("BOMBA,1,true");
            } else {
                bomba4Gui.setSelected(false);
                bomba4Gui.setText("Fechada");
                enviarParaServidor("BOMBA,1,false");
            }
         } catch (IOException ex) {
            Logger.getLogger(CaldeiraVaporGui.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }//GEN-LAST:event_bomba4GuiActionPerformed

    private void valvulaGuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valvulaGuiActionPerformed
        
        try { 
            if(valvulaGui.isSelected()) {     
                valvulaGui.setSelected(true);
                valvulaGui.setText("Aberta");
                enviarParaServidor("VALVULA,true");
            } else {
                valvulaGui.setText("Fechada");
                valvulaGui.setSelected(false);
                enviarParaServidor("VALVULA,false");
            }
        } catch (IOException ex) {
            Logger.getLogger(CaldeiraVaporGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_valvulaGuiActionPerformed
    
    public static void main(String args[]) throws IOException {
        CaldeiraVaporGui plantaGui = new CaldeiraVaporGui();
        plantaGui.setVisible(true);
        ClienteThread cliente = new ClienteThread(plantaGui, 31313);
        cliente.start();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JToggleButton bomba1Gui;
    private static javax.swing.JToggleButton bomba2Gui;
    private static javax.swing.JToggleButton bomba3Gui;
    private static javax.swing.JToggleButton bomba4Gui;
    private static javax.swing.JLabel capacidadeGui;
    private javax.swing.JLabel fundoGui;
    private static javax.swing.JLabel levelGui;
    private static javax.swing.JLabel maximoLimiteGui;
    private static javax.swing.JLabel maximoNormalGui;
    private static javax.swing.JLabel minimoLimiteGui;
    private static javax.swing.JLabel minimoNormalGui;
    private static javax.swing.JLabel modoGui;
    private static javax.swing.JToggleButton valvulaGui;
    private static javax.swing.JLabel vaporGui;
    // End of variables declaration//GEN-END:variables
}