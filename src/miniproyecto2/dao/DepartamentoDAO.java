/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproyecto2.dao;

import models.Departamento;

/**
 *
 * @author Jessele
 */
public class DepartamentoDAO extends AdapterDB4O<Departamento>
{

  private static DepartamentoDAO instance;

  private DepartamentoDAO()
  {
      super();
  }

  public static DepartamentoDAO getInstance()
  {
      if(instance == null)
          instance = new DepartamentoDAO();
      return instance;
  }
}
