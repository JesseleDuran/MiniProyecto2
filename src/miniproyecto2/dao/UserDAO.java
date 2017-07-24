/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproyecto2.dao;

import connections.DB4OConnection;
import models.User;

/**
 *
 * @author Mota
 */
public class UserDAO extends AdapterDB4O<User>
{
    private static UserDAO instance;
    
    private UserDAO()
    {
        super();
    }
    
    public static UserDAO getInstance()
    {
        if(instance == null)
            instance = new UserDAO();
        return instance;
    }
    
}
