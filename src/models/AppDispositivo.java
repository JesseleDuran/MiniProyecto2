/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Jessele
 */
public class AppDispositivo 
{
    private String dispositivo;
    private String aplicacion;

    public AppDispositivo(String dispositivo, String aplicacion) {
        this.dispositivo = dispositivo;
        this.aplicacion = aplicacion;
    }
    
    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    public String getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(String aplicacion) {
        this.aplicacion = aplicacion;
    }

    @Override
    public String toString() {
        return "AppDispositivo{" + "dispositivo=" + dispositivo + ", aplicacion=" + aplicacion + '}';
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
        final AppDispositivo other = (AppDispositivo) obj;
        if (this.dispositivo == null ? other.dispositivo != null : !this.dispositivo.equals(other.dispositivo)) {
            return false;
        }
        if (this.aplicacion == null ? other.aplicacion != null : !this.aplicacion.equals(other.aplicacion)) {
            return false;
        }
        return true;
    }
    
    
}
