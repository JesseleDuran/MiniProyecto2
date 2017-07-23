/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproyecto2.adaptersdb4o;

import com.db4o.ObjectSet;
import com.db4o.query.Query;
import connections.DB4OConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.lang.Class;
import java.lang.reflect.ParameterizedType;
import java.util.Map;

/**
 *
 * @author Slaush
 * @param <T>
 */
public abstract class AdapterDB4O<T> 
{
    
    private final Class<T> type;
    public AdapterDB4O()
    {
        //Esto Consigue la Clase del Tipo Generico !
        this.type = (Class<T>) ((ParameterizedType) getClass()
                            .getGenericSuperclass()).getActualTypeArguments()[0];
    }
    
    public boolean insertRecord(DB4OConnection db4, T record) 
    {
        // Guardar un Objeto de cualquier tipo en la BD 

        db4.open();
        db4.save(record);
        db4.close();
        return true;  
    }

   
    public boolean deleteRecord(DB4OConnection db4, T record) 
    {   
        db4.open();
        Query query = db4.getDb().query();
        query.constrain(record);
        ObjectSet<T> objSet = query.execute();
        while(objSet.hasNext())
            db4.delete(objSet.next());
        db4.close();
        return true;  
    }

    protected List<T> intersection(List<T> list1, List<T> list2) 
    {
        List<T> list = new ArrayList<T>();
        for (T t : list1) {
            if(list2.contains(t)) {
                list.add(t);
            }
        }

        return list;
    }
    
    public List<T> getAll(DB4OConnection db4o)
    {
        Query query = db4o.getDb().query();
        query.constrain(type);
        ObjectSet<T> objSet = query.execute();
        T[] lArray = (T[])objSet.toArray();
        return new ArrayList<>(Arrays.asList(lArray));
    }
    
    public List<T> queryByProperty(DB4OConnection db4o, String propertyName, Object property)
    {
        Query query = db4o.getDb().query();
        query.constrain(type);
        query.descend(propertyName).constrain(property);
        ObjectSet<T> objSet = query.execute();
        T[] lArray = (T[])objSet.toArray();
        if(objSet.isEmpty())
            return new ArrayList<>();
        else
            return new ArrayList<>(Arrays.asList(lArray));
    }
    
    public List<T> queryByAllPropertys(DB4OConnection db4o,HashMap<String, Object> hash)
    {
        List<T> result = new ArrayList<>();
        db4o.open();
        result = getAll(db4o);
        if(hash != null)
        {
            for (Map.Entry<String, Object>  e : hash.entrySet()) 
            {
                String key = e.getKey();
                Object value = e.getValue();                
                result = intersection(result,queryByProperty(db4o,key,value));
            }
        }
           
        db4o.close();
        return result;
    }
}
