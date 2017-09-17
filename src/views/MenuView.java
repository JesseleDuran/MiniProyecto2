/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.gui.TableFormat;
import ca.odell.glazedlists.swing.EventTableModel;
import connections.DB4OConnection;
import controlador.ReportesController;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.table.DefaultTableModel;
import miniproyecto2.dao.AdapterDB4O;
import miniproyecto2.dao.DañoDAO;
import miniproyecto2.dao.DispositivoDAO;
import models.Daño;
import models.Dispositivo;
import utils.PDFGenerator;

/**
 *
 * @author Jessele Durán
 */
public class MenuView extends javax.swing.JFrame {

    /**
     * Creates new form MenuView
     */
    public MenuView(Object ci) throws Exception {
        super("Menú Principal");
        initComponents();
        this.ci = ci;
        initButtons();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        dispositivosTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        dañosTable = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        actualizar = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        registrarMenu = new javax.swing.JMenu();
        verMenu = new javax.swing.JMenu();
        asignarMenu = new javax.swing.JMenu();
        cerrrarSesionMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        dispositivosTable.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        dispositivosTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        dispositivosTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dispositivosTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(dispositivosTable);

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel3.setText("Dispositivos");

        dañosTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(dañosTable);

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel4.setText("Daños");

        actualizar.setText("Actualizar Tablas");

        menuBar.setAlignmentX(0.9F);

        registrarMenu.setText("Registrar");
        registrarMenu.setActionCommand("3");
        registrarMenu.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        menuBar.add(registrarMenu);

        verMenu.setText("Ver");
        verMenu.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        menuBar.add(verMenu);

        asignarMenu.setText("Asignar");
        asignarMenu.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        menuBar.add(asignarMenu);

        cerrrarSesionMenu.setText("Cerrar Sesión");
        cerrrarSesionMenu.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        cerrrarSesionMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cerrrarSesionMenuMouseClicked(evt);
            }
        });
        cerrrarSesionMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrrarSesionMenuActionPerformed(evt);
            }
        });
        menuBar.add(cerrrarSesionMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE))
                        .addGap(18, 18, 18))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(actualizar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dispositivosTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dispositivosTableMouseClicked

    }//GEN-LAST:event_dispositivosTableMouseClicked

    private void cerrrarSesionMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrrarSesionMenuActionPerformed
        System.exit(0);
    }//GEN-LAST:event_cerrrarSesionMenuActionPerformed

    private void cerrrarSesionMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cerrrarSesionMenuMouseClicked
        System.exit(0);
    }//GEN-LAST:event_cerrrarSesionMenuMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton actualizar;
    public javax.swing.JMenu asignarMenu;
    private javax.swing.JMenu cerrrarSesionMenu;
    private javax.swing.JTable dañosTable;
    private javax.swing.JTable dispositivosTable;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuBar menuBar;
    public javax.swing.JMenu registrarMenu;
    public javax.swing.JMenu verMenu;
    // End of variables declaration//GEN-END:variables
    public Object ci;
    private void initButtons()
    {
        addEdit();
       
    }


    private void addEdit()
    {



    }

    public void actualizarDispositivos(DB4OConnection db) {
     String [] properties = new String[] {"id", "nombre", "marca","descripcion","cantidad"};
     String [] columns = new String[] {"ID","Nombre","Marca","Descripcion","Cantidad"};
     EventList list = new BasicEventList();
     db.open();
     list.addAll(DispositivoDAO.getInstance().getAll(db));
     db.close(); 
     TableFormat personTableFormat = GlazedLists.tableFormat(Dispositivo.class,properties, columns);
     EventTableModel tableModel = new EventTableModel(list, personTableFormat);
     dispositivosTable.setModel(tableModel);
    }

    public void actualizarDaños(DB4OConnection db) {
     String [] properties = new String[] {"descripcion","costo","fecha","resuelto","dispositivo"}; //TODO
     String [] columns = new String[] {"Descripcion","Costo","Fecha","Resuelto","ID Disp"};
     EventList list = new BasicEventList();
     db.open();
     list.addAll(DañoDAO.getInstance().getAll(db));
     db.close(); 
     TableFormat personTableFormat = GlazedLists.tableFormat(Daño.class,properties, columns);
     EventTableModel tableModel = new EventTableModel(list, personTableFormat);
     dañosTable.setModel(tableModel);  
    }

}
