/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproyecto2.dao;

import models.Transaccion;

/**
 *
 * @author Slaush
 */
public class TransaccionDAO extends AdapterDB4O<Transaccion>
{

  private static TransaccionDAO instance;

  private TransaccionDAO()
  {
      super();
  }

  public static TransaccionDAO getInstance()
  {
      if(instance == null)
          instance = new TransaccionDAO();
      return instance;
  }
}
