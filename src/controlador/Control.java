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
import miniproyecto2.dao.DepartamentoDAO;
import miniproyecto2.dao.DispositivoDAO;
import models.Admin;
import models.Aplicacion;
import models.Departamento;
import models.Dispositivo;
import views.AppRegisterView;
import views.ComponenteRegisterView;
import views.DispositivoRegisterView;
import views.DptoRegisterView;
import views.LoginView;
import views.MenuView;

/**
 *
 * @author Mota
 */
public class Control implements ActionListener {
    
    LoginView ventana_login  = new LoginView();
    MenuView ventana_principal;
    DptoRegisterView dpto_register = new DptoRegisterView();
    AppRegisterView app_register = new AppRegisterView();
    DispositivoRegisterView dispo_register = new DispositivoRegisterView();
    ComponenteRegisterView comp_register = new ComponenteRegisterView();
    Admin adm;
    DB4OConnection db;
    AplicacionDAO appDao = AplicacionDAO.getInstance();
    DispositivoDAO dispoDao = DispositivoDAO.getInstance();
    DepartamentoDAO dptoDao = DepartamentoDAO.getInstance();
    
    public Control(Integer Ventana, DB4OConnection db ){
        this.db = db;
        switch(Ventana){
            case 1: 
                this.ventana_login.setVisible(true);
                this.ventana_login.cancelarButton.addActionListener(this);
                this.ventana_login.ingresarButton.addActionListener(this); 
                break;
            case 2: 
                this.app_register.setVisible(true);
                this.app_register.cancelarButton.addActionListener(this);
                this.app_register.registrarAppButton.addActionListener(this);
                break;
            case 3:
                this.comp_register.setVisible(true);
                this.comp_register.cancelarButton.addActionListener(this);
                this.comp_register.registrarButton.addActionListener(this);             
                break;
            case 4: 
                this.dpto_register.setVisible(true);
                this.dpto_register.cancelarButton.addActionListener(this);
                this.dpto_register.registrarDptoButton.addActionListener(this);             
                break;
            case 5: 
                this.dispo_register.setVisible(true);
                this.dispo_register.cancelarButton.addActionListener(this);
                this.dispo_register.registrarButton.addActionListener(this);             
                break;    
        }
    }
    
    public void actionPerformed(ActionEvent e){
        System.out.println(e.getActionCommand());
        switch (e.getActionCommand()){           
            case "Ingresar": 
                ingresarButtonActionPerform(e);
                break;
            case "exit": 
                cancelarButtonActionPerformed(e);
                break;
            case "Cancelar": 
                this.app_register.dispose();
                break;
            case "RegistrarApp": 
                registrarAppButtonActionPerformed(e);
                break;
            case "RegistrarCompo":
                registrarCompoButtonActionPerformed(e);
                break;
            case "CancelarComp":
                this.comp_register.dispose();
                break;
            case "RegistrarDpto":
                registrarDptoButtonActionPerformed(e);
                break;
            case "CancelarDpto":
                this.dpto_register.dispose();
                break;
            case "RegistraDispo":  
                registrarDispoButtonActionPerformed(e);
                break;
            case "CancelarDispo":
                this.dispo_register.dispose();
                break;
           
        }
    }
    //registers
    
    private void registrarDispoButtonActionPerformed(ActionEvent evt) {
        if (validacionesCompo() != true) {

            HashMap<String, Object> dispoComparison = new HashMap<String, Object>();
            dispoComparison.put("nombre", this.dispo_register.nameField.getText().toUpperCase());
            dispoComparison.put("marca", this.dispo_register.marcaField.getText().toUpperCase());

            Dispositivo dispositivo = new Dispositivo(this.dispo_register.nameField.getText().toUpperCase(), this.dispo_register.marcaField.getText().toUpperCase(), this.dispo_register.descripcionArea.getText().toUpperCase(), false);

            List<Dispositivo> dispoExistente = dispoDao.queryByAllProperties(db, dispoComparison);

            if (dispoExistente.isEmpty()) {
                if (dispoDao.insert(db, dispositivo) == true) {
                    this.dispo_register.dispose();
                    JOptionPane.showMessageDialog(null, "El dispositivo se ha registrado correctamente", "Registro con éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al registrar dispositivo", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error:Ese dispositivo ya existe", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;

            }
        }
    }
    
    private void registrarCompoButtonActionPerformed(ActionEvent evt) {
        if (validacionesCompo() != true) {

            HashMap<String, Object> compComparison = new HashMap<String, Object>();
            compComparison.put("nombre", this.comp_register.nameField.getText().toUpperCase());
            compComparison.put("marca", this.comp_register.marcaField.getText().toUpperCase());

            Dispositivo componente = new Dispositivo(this.comp_register.nameField.getText().toUpperCase(), this.comp_register.marcaField.getText().toUpperCase(), this.comp_register.descripcionArea.getText().toUpperCase(), true);

            List<Dispositivo> compExistente = dispoDao.queryByAllProperties(db, compComparison);

            if (compExistente.isEmpty()) {
                if (dispoDao.insert(db, componente) == true) {
                    this.comp_register.dispose();
                    JOptionPane.showMessageDialog(null, "El componente se ha registrado correctamente", "Registro con éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al registrar Componente", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error:Ese componente ya existe", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;

            }
        }
    }
    
    private void registrarDptoButtonActionPerformed(ActionEvent evt) 
    {
        if (validacionesDpto() != true) {

            HashMap<String, Object> dptoComparison = new HashMap<String, Object>();
            dptoComparison.put("nombre", this.dpto_register.nameDptoField.getText().toUpperCase());

            Departamento departamento = new Departamento(this.dpto_register.nameDptoField.getText().toUpperCase());

            List<Departamento> dptoExistente = dptoDao.queryByAllProperties(db, dptoComparison);

            if (dptoExistente.isEmpty()) {
                if (dptoDao.insert(db, departamento) == true) {
                    this.dpto_register.dispose();
                    JOptionPane.showMessageDialog(null, "El departamento se ha registrado correctamente", "Registro con éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al registrar departamento", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error:Ese departamento ya existe", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;

            }
        }
    } 
    
    private void registrarAppButtonActionPerformed(ActionEvent evt) {                                                   

        if (validaciones() != true) {
           
            HashMap<String, Object> appComparison = new HashMap<String,Object>();
            appComparison.put("nombre", this.app_register.nameAppField.getText().toUpperCase());
            appComparison.put("version", this.app_register.versionField.getText().toUpperCase());
                   
            Aplicacion app = new Aplicacion(this.app_register.nameAppField.getText().toUpperCase(),this.app_register.marcaAppField.getText().toUpperCase(), this.app_register.versionField.getText().toUpperCase());
            
            List<Aplicacion> appExistente = appDao.queryByAllProperties(db, appComparison);
            
            if(appExistente.isEmpty())
            {
                if (appDao.insert(db, app) == true) {
                    this.app_register.dispose();
                    JOptionPane.showMessageDialog(null, "La aplicación se ha registrado correctamente", "Registro con éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al registrar Aplicación", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Error:Esa aplicación ya existe", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
             
            }
                      
        }
    }
    
    //login
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
                Control control1 = new Control(2, db);
            }
        });
        this.ventana_principal.registrarMenu.add(participante);

        JMenuItem componente = new JMenuItem("Componente");
        componente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Control control1 = new Control(3, db);
            }
        });
        this.ventana_principal.registrarMenu.add(componente);
        
        JMenuItem dpto = new JMenuItem("Departamento");
        dpto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Control control1 = new Control(4, db);
            }
        });
        this.ventana_principal.registrarMenu.add(dpto);
        
        JMenuItem dispo = new JMenuItem("Dispositivo");
        dispo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Control control1 = new Control(5, db);
            }
        });
        this.ventana_principal.registrarMenu.add(dispo);

    }
    
    //validaciones
    
    public boolean validaciones() {
        if (this.app_register.nameAppField.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar un nombre", "ERROR", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if (this.app_register.marcaAppField.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar una marca", "ERROR", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if (this.app_register.versionField.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar una versión", "ERROR", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        return false;
    }
    
    public boolean validacionesCompo() {
        if (this.comp_register.nameField.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar un nombre", "ERROR", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if (this.comp_register.marcaField.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar una marca", "ERROR", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if (this.comp_register.descripcionArea.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar una descripción", "ERROR", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        return false;
    }
    
    public boolean validacionesDpto() {
        if (this.dpto_register.nameDptoField.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar un nombre", "ERROR", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        return false;
    }
    
}
