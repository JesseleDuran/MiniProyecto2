/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproyecto2.dao;

import com.db4o.ObjectSet;
import com.db4o.query.Query;
import connections.DB4OConnection;
import models.Transaccion;
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
    
    @Override
    public boolean update(DB4OConnection db4, User record, String id)
    {   
        db4.open();
        User obj = queryByProperty(db4,"ci",id).get(0);
        Query query = db4.getDb().query();
        query.constrain(obj);
        ObjectSet<User> objSet = query.execute();
        while(objSet.hasNext())
            db4.delete(objSet.next());
        db4.save(record);
        db4.save(new Transaccion("update",User.class.getName(),record.toString()));
        db4.close();
        return true;
    }
    
}
