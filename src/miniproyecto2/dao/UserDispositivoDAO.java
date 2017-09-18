/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproyecto2.dao;

import models.UserDispositivo;

/**
 *
 * @author Jessele
 */
public class UserDispositivoDAO extends AdapterDB4O<UserDispositivo>
{

  private static UserDispositivoDAO instance;

  private UserDispositivoDAO()
  {
      super();
  }

  public static UserDispositivoDAO getInstance()
  {
      if(instance == null)
          instance = new UserDispositivoDAO();
      return instance;
  }
}
