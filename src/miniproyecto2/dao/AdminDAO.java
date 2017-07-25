/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproyecto2.dao;

import models.Admin;

/**
 *
 * @author Mota
 */
public class AdminDAO extends AdapterDB4O<Admin> 
{
    private static AdminDAO instance;
    
    private AdminDAO()
    {
        super();
    }
    
    public static AdminDAO getInstance()
    {
        if(instance == null)
            instance = new AdminDAO();
        return instance;
    }
    
}
