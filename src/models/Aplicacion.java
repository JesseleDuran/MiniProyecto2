/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.UUID;

/**
 *
 * @author Jessele
 */
public class Aplicacion {
    private String id;
    private String nombre;
    private String marca;
    private String version;

    public Aplicacion(String nombre, String marca, String version) {
        this.nombre = nombre;
        this.marca = marca;
        this.version = version;
        this.id = UUID.randomUUID().toString();
    }
    
    
    
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Aplicacion{" + "id=" + id + ", nombre=" + nombre + ", marca=" + marca + ", version=" + version + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Aplicacion other = (Aplicacion) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public void setId(String id) {
        this.id = id;
    }
   
    
}
