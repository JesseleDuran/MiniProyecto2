/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.UUID;

/**
 *
 * @author Slaush
 */
public class Dispositivo {
    private String id;
    private String nombre;
    private String marca;
    private String descripcion;
    private boolean componente;

    public Dispositivo(String nombre, String marca, String descripcion, boolean componente) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.marca = marca;
        this.descripcion = descripcion;
        this.componente = componente;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isComponente() {
        return componente;
    }

    public void setComponente(boolean componente) {
        this.componente = componente;
    }

    @Override
    public String toString() {
        return "Dispositivo{" + "id=" + id + ", nombre=" + nombre + ", marca=" + marca + ", descripcion=" + descripcion + ", componente=" + componente + '}';
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
        final Dispositivo other = (Dispositivo) obj;
        if (this.id == null ? other.id != null : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}
