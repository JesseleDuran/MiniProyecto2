/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Mota
 */
public class User {
    private String ci;
    private String nombre;
    private String apellido;
    private String departamento;

    public User(String ci, String nombre, String apellido, String departamento) {
        this.ci = ci;
        this.nombre = nombre;
        this.apellido = apellido;
        this.departamento = departamento;
        System.out.println("New User:"+toString());
    }
    
    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "User{" + "ci=" + ci + ", nombre=" + nombre + ", apellido=" + apellido + ", departamento=" + departamento + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.ci, other.ci)) {
            return false;
        }
        return true;
    }
    
    

}
