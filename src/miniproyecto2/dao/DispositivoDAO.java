/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproyecto2.dao;

import models.Dispositivo;

/**
 *
 * @author Slaush
 */
public class DispositivoDAO extends AdapterDB4O<Dispositivo>
{

  private static DispositivoDAO instance;

  private DispositivoDAO()
  {
      super();
  }

  public static DispositivoDAO getInstance()
  {
      if(instance == null)
          instance = new DispositivoDAO();
      return instance;
  }
}
