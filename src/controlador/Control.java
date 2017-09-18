/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.gui.TableFormat;
import ca.odell.glazedlists.swing.EventTableModel;
import connections.DB4OConnection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import miniproyecto2.dao.AdapterDB4O;
import miniproyecto2.dao.AdminDAO;
import miniproyecto2.dao.AplicacionDAO;
import miniproyecto2.dao.AppDispositivoDAO;
import miniproyecto2.dao.AutorizacionDAO;
import miniproyecto2.dao.CompuestoDAO;
import miniproyecto2.dao.DañoDAO;
import miniproyecto2.dao.DepartamentoDAO;
import miniproyecto2.dao.DispositivoDAO;
import miniproyecto2.dao.UserDAO;
import miniproyecto2.dao.UserDispositivoDAO;
import models.Admin;
import models.Aplicacion;
import models.AppDispositivo;
import models.Autorizacion;
import models.Compuesto;
import models.Daño;
import models.Departamento;
import models.Dispositivo;
import models.Transaccion;
import models.User;
import models.UserDispositivo;
import views.AppDeviceView;
import views.AppRegisterView;
import views.AuthorizationView;
import views.ComponenteRegisterView;
import views.CompoundDeviceView;
import views.DamageRegisterView;
import views.DeviceUserView;
import views.DispositivoRegisterView;
import views.DptoRegisterView;
import views.LoginView;
import views.MenuView;
import views.TableView;
import views.UserRegisterView;

/**
 *
 * @author Mota
 */
public class Control implements ActionListener {

    DB4OConnection db;

    LoginView ventana_login  = new LoginView();
    MenuView ventana_principal;
    DptoRegisterView dpto_register = new DptoRegisterView();
    AppRegisterView app_register = new AppRegisterView();
    DispositivoRegisterView dispo_register = new DispositivoRegisterView();
    ComponenteRegisterView comp_register = new ComponenteRegisterView();
    UserRegisterView user_register = new UserRegisterView();
    DamageRegisterView damage_register = new DamageRegisterView();
    AppDeviceView appdevice = new AppDeviceView();
    AuthorizationView authorizationView = new AuthorizationView();
    CompoundDeviceView compounView = new CompoundDeviceView();
    DeviceUserView deviceUserView = new DeviceUserView();
    

    Admin adm;
    AplicacionDAO appDao = AplicacionDAO.getInstance();
    UserDAO userDao = UserDAO.getInstance();
    DispositivoDAO dispoDao = DispositivoDAO.getInstance();
    DepartamentoDAO dptoDao = DepartamentoDAO.getInstance();
    private int lastWindow;
    private Class editClass;
    private String lastEditId;
    class Ventanas
    {
        public final static int login = 1;
        
        class Registrar
        {
            public final static int app = 2;
            public final static int componente = 3;
            public final static int dep = 4;
            public final static int device = 5;
            public final static int empleado = 6;
            public final static int admin = 7;
            public final static int damage = 8;
        }

        class Ver
        {
            public final static int app = 9;
            public final static int componente = 10;
            public final static int dep = 11;
            public final static int device = 12;
            public final static int empleado = 13;
            public final static int admin = 14;
            public final static int damage = 15;
            public final static int transaccion = 16;
            
            public final static int app_device = 21;
            public final static int authorization = 22;
            public final static int user_device = 23;
        }

        class Asignar
        {
            public final static int app_device = 17;
            public final static int authorization = 18;
            public final static int compound = 19;
            public final static int user_device = 20;
        }
        
    }

    private static Control instance;
    
    public final static int [] editables = new int []
    {
        Ventanas.Ver.app, Ventanas.Ver.dep, 
        Ventanas.Ver.device, Ventanas.Ver.empleado,
        Ventanas.Ver.damage
    };
    
    public void setDatabase(DB4OConnection db)
    {
        this.db = db;
    }

    public static Control getInstance()
    {
        if(instance == null)
            instance = new Control();
        return instance;
    }

    private Control()
    {
        ventana_login.cancelarButton.addActionListener(this);
        ventana_login.ingresarButton.addActionListener(this);

        app_register.cancelarButton.addActionListener(this);
        app_register.registrarAppButton.addActionListener(this);

        this.comp_register.cancelarButton.addActionListener(this);
        this.comp_register.registrarButton.addActionListener(this);
        this.dpto_register.cancelarButton.addActionListener(this);
        this.dpto_register.registrarDptoButton.addActionListener(this);

        this.dispo_register.cancelarButton.addActionListener(this);
        this.dispo_register.registrarButton.addActionListener(this);
        this.user_register.cancelarButton.addActionListener(this);
        this.user_register.registrarButton.addActionListener(this);
        this.damage_register.registrarButton.addActionListener(this);
        this.appdevice.registrarButton.addActionListener(this);
        this.authorizationView.registrarButton.addActionListener(this);
        this.compounView.registrarButton.addActionListener(this);
        this.deviceUserView.registrarButton.addActionListener(this);
    }

    public void abrirVentana(Integer Ventana){
        System.out.println("VENTANA:"+Ventana);
        this.lastWindow = Ventana;
        switch(Ventana){
            case Ventanas.login:
                this.ventana_login.setVisible(true);

                this.ventana_login.setButtonTitle("Ingresar");
                this.ventana_login.setTitle("Inicio de Sesion");
                break;
            case Ventanas.Registrar.app:
                this.app_register.cleanFields();
                this.app_register.setVisible(true);
                this.app_register.registrarAppButton.setText("Registrar");
                this.app_register.registrarAppButton.setActionCommand("RegistrarApp");
                break;
            case Ventanas.Registrar.componente:
                this.comp_register.setVisible(true);

                break;
            case Ventanas.Registrar.dep:
                this.dpto_register.setVisible(true);
                this.dpto_register.registrarDptoButton.setText("Registrar");
                this.dpto_register.registrarDptoButton.setActionCommand("RegistrarDpto");
                break;
            case Ventanas.Registrar.device:
                this.dispo_register.cleanFields();
                this.dispo_register.setVisible(true);
                this.dispo_register.registrarButton.setText("Registrar");
                this.dispo_register.registrarButton.setActionCommand("RegistrarDispo");
              
                break;
            case Ventanas.Registrar.empleado:
                this.user_register.cargarDepartamentos(db);
                this.user_register.setVisible(true);

                break;
            case Ventanas.Registrar.admin:
                this.ventana_login.setVisible(true);
                this.ventana_login.setButtonTitle("Agregar");
                this.ventana_login.setTitle("Agregar Administrador");
                this.ventana_login.cleanFields();
                break;
            case Ventanas.Registrar.damage:
                this.damage_register.setVisible(true);
                this.damage_register.cleanFields();
                this.damage_register.setTitle("Registrar daño");
                this.damage_register.cargarDispositivos(db);

                break;
            case Ventanas.Asignar.app_device:
                this.appdevice.setVisible(true);
                this.appdevice.cargarAplicaciones(db);
                this.appdevice.cargarDispositivos(db);

                break;
            case Ventanas.Asignar.authorization:
                this.authorizationView.setVisible(true);
                this.authorizationView.cargarAplicaciones(db);
                this.authorizationView.cargarDepartamentos(db);

                break;
            case Ventanas.Asignar.user_device:
                this.deviceUserView.setVisible(true);
                this.deviceUserView.cargarDispositivos(db);
                this.deviceUserView.cargarUsuarios(db);

                break;
            case Ventanas.Asignar.compound:
                this.compounView.setVisible(true);
                this.compounView.cargarDispositivos(db);
                break;
            default:
                makeTableView(Ventana);
                break;
        }
    }

    public void actionPerformed(ActionEvent e){
        System.out.println("Action Command:"+e.getActionCommand());
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
            case "RegistrarDispo":
                registrarDispoButtonActionPerformed(e);
                break;
            case "CancelarDispo":
                this.dispo_register.dispose();
                break;
            case "RegistrarUser":
                registrarUserButtonActionPerformed(e);
                break;

            case "Agregar":
                addModelByLastWindowOpen();
                break;
            case "Editar":
                editModelByLastEditClass();
                break;

        }
    }

    //registers
    private void registrarUserButtonActionPerformed(ActionEvent evt) {
        if (validacionesUser() != true) {

            HashMap<String, Object> userComparison = new HashMap<String, Object>();
            userComparison.put("ci", this.user_register.ciField.getText());

            User empleado = new User(this.user_register.ciField.getText(), this.user_register.nameField.getText().toUpperCase(), this.user_register.apellidoField.getText().toUpperCase(), this.user_register.getSelectedDepartment());

            List<User> userExistente = userDao.queryByAllProperties(db, userComparison);

            if (userExistente.isEmpty()) {
                if (userDao.insert(db, empleado) == true) {
                    this.user_register.dispose();
                    JOptionPane.showMessageDialog(null, "El empleado se ha registrado correctamente", "Registro con éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al registrar empleado", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error:Ese empleado ya existe", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;

            }
        }
    }

    private void registrarDispoButtonActionPerformed(ActionEvent evt) {
        if (validacionesDispo() != true) {

            HashMap<String, Object> dispoComparison = new HashMap<String, Object>();
            dispoComparison.put("nombre", this.dispo_register.nameField.getText().toUpperCase());
            dispoComparison.put("marca", this.dispo_register.marcaField.getText().toUpperCase());

            Dispositivo dispositivo = new Dispositivo(this.dispo_register.nameField.getText().toUpperCase(), this.dispo_register.marcaField.getText().toUpperCase(), this.dispo_register.descripcionArea.getText().toUpperCase(), false, Integer.parseInt(this.dispo_register.cantidadField.getText()));

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

            Dispositivo componente = new Dispositivo(this.comp_register.nameField.getText().toUpperCase(), this.comp_register.marcaField.getText().toUpperCase(), this.comp_register.descripcionArea.getText().toUpperCase(), true, Integer.parseInt(this.comp_register.cantidadField.getText()));

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
           //this.adm.setClave(this.ventana_login.passwordField.getText());
           HashMap<String, Object> adm = new HashMap<String,Object>();
           adm.put("cedula", Integer.parseInt(this.ventana_login.ciField.getText()));
           adm.put("clave", this.adm.getClave());
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
                   addVer();
                   addAsignar();
                   this.ventana_principal.actualizarDispositivos(db);
                   this.ventana_principal.actualizarDaños(db);
                   this.ventana_principal.actualizar.addActionListener(new ActionListener() {

                       @Override
                       public void actionPerformed(ActionEvent e) {
                           ventana_principal.actualizarDispositivos(db);
                           ventana_principal.actualizarDaños(db);
                       }
                   });
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

    //creacion de Administrador
    public void createAdmin()
    {
         if(!this.ventana_login.ciField.getText().trim().equals("") &&
           !this.ventana_login.passwordField.getText().trim().equals("")){

           Admin admin = new Admin(Integer.parseInt(this.ventana_login.ciField.getText()),this.ventana_login.passwordField.getText());

           HashMap<String, Object> adm = new HashMap<String,Object>();
           adm.put("cedula", Integer.parseInt(this.ventana_login.ciField.getText()));
           List<Admin> loggeado =  AdminDAO.getInstance().queryByAllProperties(db, adm);
           System.out.println(loggeado);
            if(loggeado.isEmpty())
            {
                AdminDAO.getInstance().insert(db, admin);
                ventana_login.dispose();
                  JOptionPane.showMessageDialog(null, "El Administrador se ha registrado correctamente", "Registro con éxito", JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
               JOptionPane.showMessageDialog(null, "Administrador existente"
                   + ", por favor vuelva a intentarlo"
                  , "Error al Crear",
                            JOptionPane.ERROR_MESSAGE);
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
                abrirVentana(Ventanas.Registrar.app);
            }
        });
        this.ventana_principal.registrarMenu.add(participante);

        JMenuItem componente = new JMenuItem("Componente");
        componente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentana(Ventanas.Registrar.componente);
            }
        });
        this.ventana_principal.registrarMenu.add(componente);

        JMenuItem dpto = new JMenuItem("Departamento");
        dpto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentana(Ventanas.Registrar.dep);
            }
        });
        this.ventana_principal.registrarMenu.add(dpto);

        JMenuItem dispo = new JMenuItem("Dispositivo");
        dispo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentana(Ventanas.Registrar.device);
            }
        });
        this.ventana_principal.registrarMenu.add(dispo);

        JMenuItem empleado = new JMenuItem("Empleado");
        empleado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentana(Ventanas.Registrar.empleado);
            }
        });
        this.ventana_principal.registrarMenu.add(empleado);

        JMenuItem admin = new JMenuItem("Administrador");
        admin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentana(Ventanas.Registrar.admin);
            }
        });
        this.ventana_principal.registrarMenu.add(admin);

        JMenuItem damage = new JMenuItem("Daño");
        damage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentana(Ventanas.Registrar.damage);
            }
        });
        this.ventana_principal.registrarMenu.add(damage);

    }

    private void addVer()
    {
        JMenuItem participante = new JMenuItem("Aplicaciónes");
        participante.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                abrirVentana(Ventanas.Ver.app);
            }
        });
        this.ventana_principal.verMenu.add(participante);

        JMenuItem componente = new JMenuItem("Componentes");
        componente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentana(Ventanas.Ver.componente);
            }
        });
        this.ventana_principal.verMenu.add(componente);

        JMenuItem dpto = new JMenuItem("Departamentos");
        dpto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentana(Ventanas.Ver.dep);
            }
        });
        this.ventana_principal.verMenu.add(dpto);

        JMenuItem dispo = new JMenuItem("Dispositivos");
        dispo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentana(Ventanas.Ver.device);
            }
        });
        this.ventana_principal.verMenu.add(dispo);

        JMenuItem empleado = new JMenuItem("Empleados");
        empleado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentana(Ventanas.Ver.empleado);
            }
        });
        this.ventana_principal.verMenu.add(empleado);

        JMenuItem admin = new JMenuItem("Administradores");
        admin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentana(Ventanas.Ver.admin);
            }
        });
        this.ventana_principal.verMenu.add(admin);

        JMenuItem damage = new JMenuItem("Daños");
        damage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentana(Ventanas.Ver.damage);
            }
        });
        this.ventana_principal.verMenu.add(damage);

        JMenuItem trans = new JMenuItem("Transacciones");
        trans.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentana(Ventanas.Ver.transaccion);
            }
        });
        this.ventana_principal.verMenu.add(trans);
        
        JMenuItem auth = new JMenuItem("Autorizaciones");
        auth.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentana(Ventanas.Ver.authorization);
            }
        });
        this.ventana_principal.verMenu.add(auth);

        JMenuItem user_device = new JMenuItem("Dispositivos Por Empleado");
        user_device.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentana(Ventanas.Ver.user_device);
            }
        });
        this.ventana_principal.verMenu.add(user_device);

        JMenuItem app_device = new JMenuItem("Aplicaciones por dispositivo");
        app_device.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentana(Ventanas.Ver.app_device);
            }
        });
        this.ventana_principal.verMenu.add(app_device);

    }

    private void addAsignar()
    {
        JMenuItem participante = new JMenuItem("Asignar Dispositivo a Empleado");
        participante.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                abrirVentana(Ventanas.Asignar.user_device);
            }
        });
        this.ventana_principal.asignarMenu.add(participante);

        JMenuItem componente = new JMenuItem("Asignar Componente a Dispositivo");
        componente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentana(Ventanas.Asignar.compound);
            }
        });
        this.ventana_principal.asignarMenu.add(componente);

        JMenuItem dpto = new JMenuItem("Asignar Aplicacion a Dispositivo");
        dpto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentana(Ventanas.Asignar.app_device);
            }
        });
        this.ventana_principal.asignarMenu.add(dpto);

        JMenuItem dispo = new JMenuItem("Asignar Autorizacion de Aplicacion");
        dispo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentana(Ventanas.Asignar.authorization);
            }
        });
        this.ventana_principal.asignarMenu.add(dispo);

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

    public boolean validacionesDispo() {
        if (this.dispo_register.nameField.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar un nombre", "ERROR", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if (this.dispo_register.marcaField.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar una marca", "ERROR", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if (this.dispo_register.descripcionArea.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar una descripción", "ERROR", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        return false;
    }

    public boolean validacionesUser() {
        if (this.user_register.nameField.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar un nombre", "ERROR", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if (this.user_register.apellidoField.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar un apellido", "ERROR", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if (this.user_register.ciField.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar una cédula", "ERROR", JOptionPane.ERROR_MESSAGE);
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

    private void addModelByLastWindowOpen() {
        switch(lastWindow)
        {
            case Ventanas.Registrar.admin:
                createAdmin();
            break;

            case Ventanas.Registrar.damage:
                createDamage();
            break;

            case Ventanas.Asignar.app_device:
                createAppDevice();
            break;

            case Ventanas.Asignar.authorization:
                createAuthorization();
            break;

            case Ventanas.Asignar.compound:
                createCompound();
            break;

            case Ventanas.Asignar.user_device:
                createUserDevice();
            break;

        }

    }

    private void createUserDevice() {
        String device = this.deviceUserView.deviceSelected();
        String user = this.deviceUserView.userSelected();
        HashMap<String, Object> hash = new HashMap<String,Object>();
        hash.put("dispositivo", device);
        hash.put("user", user);
        List<UserDispositivo> ud = UserDispositivoDAO.getInstance().queryByAllProperties(db, hash);
        if(ud.size() > 0)
        {
            JOptionPane.showMessageDialog(null, "Este usuario ya esta relacionado con este dispositivo"
                            , "Error",
                            JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            UserDispositivo u = new UserDispositivo(device,user);
            UserDispositivoDAO.getInstance().insert(db, u);
            JOptionPane.showMessageDialog(null, "Registro Satisfactorio", "Registro con éxito", JOptionPane.INFORMATION_MESSAGE);
            this.deviceUserView.dispose();
        }
    }

    private void createCompound() {
        String device = this.compounView.deviceSelected();
        String compound = this.compounView.compoundSelected();
        HashMap<String, Object> hash = new HashMap<String,Object>();
        hash.put("dispositivo", device);
        hash.put("componente", compound);
        List<Compuesto> ud = CompuestoDAO.getInstance().queryByAllProperties(db, hash);
        if(ud.size() > 0)
        {
            JOptionPane.showMessageDialog(null, "dispositivos ya compuestos"
                            , "Error",
                            JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            Compuesto u = new Compuesto(device,compound);
            CompuestoDAO.getInstance().insert(db, u);
            JOptionPane.showMessageDialog(null, "Registro Satisfactorio", "Registro con éxito", JOptionPane.INFORMATION_MESSAGE);
            this.compounView.dispose();
        }
   }

    private void createAuthorization() {

        String dep = this.authorizationView.departmentSelected();
        String app = this.authorizationView.appSelected();
        HashMap<String, Object> hash = new HashMap<String,Object>();
        hash.put("departamento", dep);
        hash.put("aplicacion", app);
        List<Autorizacion> ud = AutorizacionDAO.getInstance().queryByAllProperties(db, hash);
        if(ud.size() > 0)
        {
            JOptionPane.showMessageDialog(null, "Departamento ya autorizado para usar esta aplicacion"
                            , "Error",
                            JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            Autorizacion u = new Autorizacion(app,dep);
            AutorizacionDAO.getInstance().insert(db, u);
            JOptionPane.showMessageDialog(null, "Registro Satisfactorio", "Registro con éxito", JOptionPane.INFORMATION_MESSAGE);
            this.authorizationView.dispose();
        }
    }

    private void createAppDevice() {

        String device = this.appdevice.deviceSelected();
        String app = this.appdevice.appSelected();
        HashMap<String, Object> hash = new HashMap<String,Object>();
        hash.put("dispositivo", device);
        hash.put("aplicacion", app);
        List<AppDispositivo> ud = AppDispositivoDAO.getInstance().queryByAllProperties(db, hash);
        if(ud.size() > 0)
        {
            JOptionPane.showMessageDialog(null, "Este dipositivo ya posee esta aplicacion"
                            , "Error",
                            JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            AppDispositivo u = new AppDispositivo(device,app);
            AppDispositivoDAO.getInstance().insert(db, u);
            JOptionPane.showMessageDialog(null, "Registro Satisfactorio", "Registro con éxito", JOptionPane.INFORMATION_MESSAGE);
            this.appdevice.dispose();
        }
    }

    private void createDamage() {
     String device = this.damage_register.deviceSelected();
     String descripcion = this.damage_register.descripcion.getText();
     String costo = this.damage_register.costo.getText();
     String date = this.damage_register.fecha.getText();
     boolean resuelto = this.damage_register.resuelto.isSelected();

    if (descripcion.isEmpty() || costo.isEmpty() || date.isEmpty()) {

        JOptionPane.showMessageDialog(null, "Ningun campo puede estar vacio"
                            , "Error",
                            JOptionPane.ERROR_MESSAGE);
        return;
    }

     Daño d = new Daño(descripcion,resuelto,Double.valueOf(costo),new Date(date),device);
     DañoDAO.getInstance().insert(db, d);
     JOptionPane.showMessageDialog(null, "Registro Satisfactorio", "Registro con éxito", JOptionPane.INFORMATION_MESSAGE);
     this.damage_register.dispose();

    }


    private void makeTableView(int Ventana) {

        Object c = null;
        String [] properties = null;
        String [] columns = null;
        switch(Ventana)
        {
          case Ventanas.Ver.app:
            c = Aplicacion.class;
            properties = new String[] {"id","nombre", "marca", "version"};
            columns = new String[] {"ID","Nombre", "Marca", "Version"};
          break;
          case Ventanas.Ver.componente:
            c = Compuesto.class;
            properties = new String[] {"dispositivo", "componente"};
            columns = new String[] {"ID Disp", "ID Componente"};
          break;
          case Ventanas.Ver.dep:
            c = Departamento.class;
            properties = new String[] {"id", "nombre"};
            columns = new String[] {"ID", "Nombre"};
          break;
          case Ventanas.Ver.device:
            c = Dispositivo.class;
            properties = new String[] {"id", "nombre", "marca","descripcion","cantidad", "componente"};
            columns = new String[] {"ID","Nombre","Marca","Descripcion","Cantidad","Componente"};
          break;
          case Ventanas.Ver.empleado:
            c = User.class;
            properties = new String[] { "ci", "nombre","apellido","departamento"};
            columns = new String[] {"Cedula","Nombre","Apellido", "ID Dept"};
          break;
          case Ventanas.Ver.admin:
            c = Admin.class;
            properties = new String[] {"cedula"};
            columns = new String[] {"Cedula"};
          break;
          case Ventanas.Ver.damage:
            c = Daño.class;
            properties = new String[] {"descripcion","costo","fecha","resuelto","dispositivo"}; //TODO
            columns = new String[] {"Descripcion","Costo","Fecha","Resuelto","ID Disp"};
          break;
          case Ventanas.Ver.transaccion:
            c = Transaccion.class;
            properties = new String[] {"id","tipo","clase","objeto"};
            columns = new String[] {"ID","Tipo","Clase","Objeto"};
          break;
          
          case Ventanas.Ver.app_device:
            c = AppDispositivo.class;
            properties = new String[] {"dispositivo","aplicacion"};
            columns = new String[] {"Dispositivo","Aplicacion"};
          break;
              
          case Ventanas.Ver.user_device:
            c = UserDispositivo.class;
            properties = new String[] {"user","dispositivo"};
            columns = new String[] {"Empleado","Dispositivo"};
          break;
          
          case Ventanas.Ver.authorization:
            c = Autorizacion.class;
            properties = new String[] {"departamento","aplicacion"};
            columns = new String[] {"Departamento","Aplicacion"};
          break;
                       
        
        }
        if (c != null) {
        EventList list = new BasicEventList();
        db.open();
        list.addAll(AdapterDB4O.queryByClass(db, (Class)c));
        db.close();
        TableFormat tableFormat = GlazedLists.tableFormat((Class)c,properties, columns);
        EventTableModel tableModel = new EventTableModel(list, tableFormat);
        final TableView tableView = new TableView();
        final Object cl = c;
        tableView.editar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               int selected = tableView.getSelectedRow();
               if(selected > -1)
               {
                   Object o = list.get(selected);
                    editar(o,cl);
                    tableView.dispose();
               }
               
            }
        });
        
        tableView.eliminar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               int selected = tableView.getSelectedRow();
               Object o = list.get(selected);
               if(selected > -1)
               {
                   if(eliminar(o,cl))
                   list.remove(o);
               }
               
            }
        });
        
        tableView.setEditable(isEditable(Ventana));
        tableView.setTableModel(tableModel);
        tableView.setVisible(true);
        }

    }
    
    private boolean isEditable(int ventana) {
        for (int i = 0; i < editables.length; i++) {
            if (editables[i] == ventana) {
                return true;
            }
        }
        return false;
    }
    
    private boolean eliminar(Object o, Object clazz)
    {
        int reply = JOptionPane.showConfirmDialog(null, "¿Estas Seguro?", "Eliminar", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) 
        {
          AdapterDB4O.deleteByClass(db, (Class)clazz, o);
          return true;
        }
        return false;
    }
    
    private void editar(Object o, Object clazz)
    {   
    
        editClass = (Class)clazz;
        if (editClass == Aplicacion.class) 
        {
           Aplicacion a = (Aplicacion)o;
           lastEditId = a.getId();
           this.app_register.registrarAppButton.setText("Editar");
           this.app_register.registrarAppButton.setActionCommand("Editar");
           this.app_register.setApp(a);
           this.app_register.setVisible(true);
        }
        else if(editClass == Departamento.class)
        {
           Departamento a = (Departamento)o;
           lastEditId = a.getId();
           this.dpto_register.registrarDptoButton.setText("Editar");
           this.dpto_register.registrarDptoButton.setActionCommand("Editar");
           this.dpto_register.setDepartment(a);
           this.dpto_register.setVisible(true);
        }
        else if(editClass == Dispositivo.class)
        {
           Dispositivo a = (Dispositivo)o;
           lastEditId = a.getId();
           this.dispo_register.registrarButton.setText("Editar");
           this.dispo_register.registrarButton.setActionCommand("Editar");
           this.dispo_register.setDispositivo(a);
           this.dispo_register.setVisible(true);
        }
        else if(editClass == User.class)
        {
           User a = (User)o;
           lastEditId = a.getCi();
           this.user_register.registrarButton.setText("Editar");
           this.user_register.registrarButton.setActionCommand("Editar");
           this.user_register.cargarDepartamentos(db);
           this.user_register.setUser(a);
           this.user_register.setVisible(true);
        }
        else if (editClass == Daño.class)
        {
           Daño a = (Daño)o;
           lastEditId = a.getId();
           this.damage_register.registrarButton.setText("Editar");
           this.damage_register.registrarButton.setActionCommand("Editar");
           this.damage_register.cargarDispositivos(db);
           this.damage_register.setDamage(a);
           this.damage_register.setVisible(true);            
        }
    }
    
    private void editModelByLastEditClass() 
    {
        if (editClass == Aplicacion.class) 
        {
            editApp();
        }
        else if(editClass == Departamento.class)
        {
            editDepartment();
        }
        else if(editClass == Dispositivo.class)
        {
            editDevice();
        }
        else if(editClass == User.class)
        {
            editUser();
        }
        else if(editClass == Daño.class)
        {
            editDamage();
        }
    }
    
    private void editApp()
    {
       if (validaciones() != true) {

            HashMap<String, Object> appComparison = new HashMap<String,Object>();
            appComparison.put("nombre", this.app_register.nameAppField.getText().toUpperCase());
            appComparison.put("version", this.app_register.versionField.getText().toUpperCase());

            Aplicacion app = new Aplicacion(this.app_register.nameAppField.getText().toUpperCase(),this.app_register.marcaAppField.getText().toUpperCase(), this.app_register.versionField.getText().toUpperCase());
            app.setId(lastEditId);
            List<Aplicacion> appExistente = appDao.queryByAllProperties(db, appComparison);
            removeAppWhereId(appExistente,lastEditId);
            if(appExistente.isEmpty())
            {
                appDao.update(db, app, lastEditId);
                this.app_register.dispose();
                JOptionPane.showMessageDialog(null, "La aplicación se ha editado correctamente", "Registro con éxito", JOptionPane.INFORMATION_MESSAGE);

            }
            else
            {
                JOptionPane.showMessageDialog(null, "Error:Esa aplicación ya existe", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;

            }
        }
    }
    
    private void editDepartment()
    {
        if (validacionesDpto() != true) {

            HashMap<String, Object> dptoComparison = new HashMap<String, Object>();
            dptoComparison.put("nombre", this.dpto_register.nameDptoField.getText().toUpperCase());

            Departamento departamento = new Departamento(this.dpto_register.nameDptoField.getText().toUpperCase());
            departamento.setId(lastEditId);
            List<Departamento> dptoExistente = dptoDao.queryByAllProperties(db, dptoComparison);

            if (dptoExistente.isEmpty()) 
            {
                dptoDao.update(db, departamento,lastEditId);
                this.dpto_register.dispose();
                JOptionPane.showMessageDialog(null, "El departamento se ha editado correctamente", "Registro con éxito", JOptionPane.INFORMATION_MESSAGE);
              
            } else {
                JOptionPane.showMessageDialog(null, "Error:Ese departamento ya existe", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;

            }
        }
    }
    
    private void editDevice()
    {
        if (validacionesDispo() != true) {

            HashMap<String, Object> dispoComparison = new HashMap<String, Object>();
            dispoComparison.put("nombre", this.dispo_register.nameField.getText().toUpperCase());
            dispoComparison.put("marca", this.dispo_register.marcaField.getText().toUpperCase());

            Dispositivo dispositivo = new Dispositivo(this.dispo_register.nameField.getText().toUpperCase(), this.dispo_register.marcaField.getText().toUpperCase(), this.dispo_register.descripcionArea.getText().toUpperCase(), false, Integer.parseInt(this.dispo_register.cantidadField.getText()));
            dispositivo.setId(lastEditId);
            List<Dispositivo> dispoExistente = dispoDao.queryByAllProperties(db, dispoComparison);
            removeDispWhereId(dispoExistente,lastEditId);

            if (dispoExistente.isEmpty()) {
                dispoDao.update(db, dispositivo,lastEditId);
                this.dispo_register.dispose();
                JOptionPane.showMessageDialog(null, "El dispositivo se ha registrado correctamente", "Registro con éxito", JOptionPane.INFORMATION_MESSAGE);
               
            } else {
                JOptionPane.showMessageDialog(null, "Error:Ese dispositivo ya existe", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;

            }
        }
    }
    
    private void removeDispWhereId(List<Dispositivo> disps, String id)
    {
        int index = -1;
        for (int i = 0; i < disps.size(); i++) {
            if (disps.get(i).getId().equals(id)) {
                index = i;
            }
        }
        if (index >= 0) {
            disps.remove(index);
        }
    }
    
    private void removeAppWhereId(List<Aplicacion> disps, String id)
    {
        int index = -1;
        for (int i = 0; i < disps.size(); i++) {
            if (disps.get(i).getId().equals(id)) {
                index = i;
            }
        }
        if (index >= 0) {
            disps.remove(index);
        }
    }
    
    private void editUser()
    {
        if (validacionesUser() != true) {

            HashMap<String, Object> userComparison = new HashMap<String, Object>();
            userComparison.put("ci", this.user_register.ciField.getText());
            User empleado = new User(this.user_register.ciField.getText(), this.user_register.nameField.getText().toUpperCase(), this.user_register.apellidoField.getText().toUpperCase(), this.user_register.getSelectedDepartment());
           
            userDao.update(db, empleado,lastEditId);
            this.user_register.dispose();
            JOptionPane.showMessageDialog(null, "El empleado se ha editado correctamente", "Registro con éxito", JOptionPane.INFORMATION_MESSAGE);
           
           
        }
    }
    
    private void editDamage()
    {
        String device = this.damage_register.deviceSelected();
        String descripcion = this.damage_register.descripcion.getText();
        String costo = this.damage_register.costo.getText();
        String date = this.damage_register.fecha.getText();
        boolean resuelto = this.damage_register.resuelto.isSelected();

       if (descripcion.isEmpty() || costo.isEmpty() || date.isEmpty()) {

           JOptionPane.showMessageDialog(null, "Ningun campo puede estar vacio"
                               , "Error",
                               JOptionPane.ERROR_MESSAGE);
           return;
       }

        Daño d = new Daño(descripcion,resuelto,Double.valueOf(costo),new Date(date),device);
        d.setId(lastEditId);
        DañoDAO.getInstance().update(db, d,lastEditId);
        JOptionPane.showMessageDialog(null, "Registro Satisfactorio", "Registro con éxito", JOptionPane.INFORMATION_MESSAGE);
        this.damage_register.dispose();
    }

}    
