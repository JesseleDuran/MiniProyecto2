/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproyecto2.dao;

import models.Compuesto;

/**
 *
 * @author Jessele
 */
public class CompuestoDAO extends AdapterDB4O<Compuesto>
{

  private static CompuestoDAO instance;

  private CompuestoDAO()
  {
      super();
  }

  public static CompuestoDAO getInstance()
  {
      if(instance == null)
          instance = new CompuestoDAO();
      return instance;
  }
}
