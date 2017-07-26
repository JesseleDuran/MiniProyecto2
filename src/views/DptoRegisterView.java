/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Jessele Durán
 */
public class DptoRegisterView extends javax.swing.JFrame {

    /**
     * Creates new form AdminRegisterView
     */
    public DptoRegisterView() {
        super("Registrar Departamento");
        initComponents();
        restringirTeclas();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        registrarDptoButton = new javax.swing.JButton();
        cancelarButton = new javax.swing.JButton();
        nameDptoField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel1.setText("Nombre del Departamento:");

        registrarDptoButton.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        registrarDptoButton.setText("Registrar");
        registrarDptoButton.setActionCommand("RegistrarDpto");


        cancelarButton.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        cancelarButton.setText("Cancelar");
        cancelarButton.setActionCommand("CancelarDpto");


        nameDptoField.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
    

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel3.setText("Registrar Departamento");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(cancelarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(registrarDptoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(nameDptoField, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(28, 28, 28)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameDptoField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(registrarDptoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton cancelarButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    public javax.swing.JTextField nameDptoField;
    public javax.swing.JButton registrarDptoButton;
    // End of variables declaration//GEN-END:variables
    public KeyListener eventosDeTeclaOnlyLetters;

    private void restringirTeclas() {

        eventosDeTeclaOnlyLetters = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                char caracter = ke.getKeyChar();
                if ((Character.isLetter(caracter)) == false) {
                    ke.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent ke) {

            }

            @Override
            public void keyReleased(KeyEvent ke) {

            }
        };

        nameDptoField.addKeyListener(eventosDeTeclaOnlyLetters);
    }

}
