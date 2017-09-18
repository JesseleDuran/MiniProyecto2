/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproyecto2.dao;

import models.Autorizacion;

/**
 *
 * @author Jessele
 */
public class AutorizacionDAO extends AdapterDB4O<Autorizacion>
{

  private static AutorizacionDAO instance;

  private AutorizacionDAO()
  {
      super();
  }

  public static AutorizacionDAO getInstance()
  {
      if(instance == null)
          instance = new AutorizacionDAO();
      return instance;
  }
}
