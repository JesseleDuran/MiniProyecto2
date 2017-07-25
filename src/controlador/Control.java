/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import connections.DB4OConnection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import miniproyecto2.dao.AdminDAO;
import miniproyecto2.dao.AplicacionDAO;
import models.Admin;
import models.Aplicacion;
import views.AppRegisterView;
import views.LoginView;
import views.MenuView;

/**
 *
 * @author Mota
 */
public class Control implements ActionListener {
    
    LoginView ventana_login  = new LoginView();
    MenuView ventana_principal;
    AppRegisterView app_register = new AppRegisterView();
    Admin adm;
    DB4OConnection db;
    
    public Control(Integer Ventana, DB4OConnection db ){
        this.db = db;
        switch(Ventana){
            case 1: this.ventana_login.setVisible(true);
                    this.ventana_login.cancelarButton.addActionListener(this);
                    this.ventana_login.ingresarButton.addActionListener(this); 
                    break;
        }
    }
    
    public void actionPerformed(ActionEvent e){
        switch (e.getActionCommand()){           
            case "Ingresar": ingresarButtonActionPerform(e);
            break;
            case "exit": cancelarButtonActionPerformed(e);
            break;
            case "cancelar": this.app_register.dispose();
            break;
            case "RegistrarApp": registrarAppButtonActionPerformed(e);
        }
    }
    
    private void registrarAppButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                   

        if (validaciones() != true) {
            
            Aplicacion app = new Aplicacion(this.app_register.nameAppField.getText().toUpperCase(),this.app_register.marcaAppField.getText().toUpperCase(), this.app_register.versionField.getText().toUpperCase() );
            if(AplicacionDAO.getInstance().)
            if (AplicacionDAO.getInstance().insert(db, app) == true) {
                this.app_register.dispose();
                JOptionPane.showMessageDialog(null, "La aplicación se ha registrado correctamente", "Registro con éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error al registrar Aplicación", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void ingresarButtonActionPerform(ActionEvent e){
       
        if(!this.ventana_login.ciField.getText().trim().equals("") &&
           !this.ventana_login.passwordField.getText().trim().equals("")){
            
           this.adm = new Admin(Integer.parseInt(this.ventana_login.ciField.getText()),this.ventana_login.passwordField.getText()); 
           
           HashMap<String, Object> adm = new HashMap<String,Object>();
           adm.put("cedula", Integer.parseInt(this.ventana_login.ciField.getText()));
           List<Admin> loggeado =  AdminDAO.getInstance().queryByAllProperties(db, adm);
           System.out.println(loggeado);
            if(loggeado.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "El nombre de usuario y contraseña no coinciden"
                            + ", por favor vuelva a intentarlo"
                            , "Error al Iniciar Sesión",
                            JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                ventana_login.dispose();
               try {
                   this.ventana_principal = new MenuView(loggeado.get(0).getCedula());
                   this.ventana_principal.setVisible(true);
                   addRegistrar();
               } catch (Exception ex) {
                   Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
               }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Por favor, llenar todos los campos"
                            , "Error al Iniciar Sesión",
                            JOptionPane.ERROR_MESSAGE);
        }
    
    }
    
    public void cancelarButtonActionPerformed(ActionEvent e){
       System.exit(0);
    }
    
    private void addRegistrar()
    {
        JMenuItem participante = new JMenuItem("Aplicación");
        participante.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                app_register.setVisible(true);
            }
        });
        this.ventana_principal.registrarMenu.add(participante);

    }
    
    public boolean validaciones() {
        if (this.app_register.nameAppField.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Error:Debe ingresar un nombre", "ERROR", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if (this.app_register.marcaAppField.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Error:Debe ingresar una marca", "ERROR", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if (this.app_register.versionField.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar una versión", "ERROR", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        return false;
    }
    
}
