/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproyecto2.dao;

import models.Aplicacion;

/**
 *
 * @author Jessele
 */
public class AplicacionDAO extends AdapterDB4O<Aplicacion>
{

  private static AplicacionDAO instance;

  private AplicacionDAO()
  {
      super();
  }

  public static AplicacionDAO getInstance()
  {
      if(instance == null)
          instance = new AplicacionDAO();
      return instance;
  }
}
