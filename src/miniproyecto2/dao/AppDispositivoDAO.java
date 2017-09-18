/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproyecto2.dao;

import models.AppDispositivo;

/**
 *
 * @author Jessele
 */
public class AppDispositivoDAO extends AdapterDB4O<AppDispositivo> {
  private static AppDispositivoDAO instance;

  private AppDispositivoDAO()
  {
      super();
  }

  public static AppDispositivoDAO getInstance()
  {
      if(instance == null)
          instance = new AppDispositivoDAO();
      return instance;
  }
}
