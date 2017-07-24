/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproyecto2;

import connections.DB4OConnection;
import miniproyecto2.dao.DepartamentoDAO;
import miniproyecto2.dao.UserDAO;
import models.Departamento;
import models.User;

/**
 *
 * @author Mota
 */
public class MiniProyecto2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DB4OConnection db = new DB4OConnection("BD.jessele");
        
    }       
    
}
