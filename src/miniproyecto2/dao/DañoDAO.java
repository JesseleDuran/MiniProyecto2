/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproyecto2.dao;

import models.Daño;

/**
 *
 * @author Slaush
 */
public class DañoDAO extends AdapterDB4O<Daño>
{

  private static DañoDAO instance;

  private DañoDAO()
  {
      super();
  }

  public static DañoDAO getInstance()
  {
      if(instance == null)
          instance = new DañoDAO();
      return instance;
  }
}
