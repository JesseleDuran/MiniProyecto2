/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;
import java.util.UUID;

/**
 *
 * @author Slaush
 */
public class Daño {
    private String id;
    private String descripcion;
    private boolean resuelto;
    private double costo;
    private Date fecha;
    private String dispositivo;

    public Daño(String descripcion, boolean resuelto, double costo, Date fecha, String dispositivo) {
        this.id = UUID.randomUUID().toString();
        this.descripcion = descripcion;
        this.resuelto = resuelto;
        this.costo = costo;
        this.fecha = fecha;
        this.dispositivo = dispositivo;
    }
    
    public String getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isResuelto() {
        return resuelto;
    }

    public void setResuelto(boolean resuelto) {
        this.resuelto = resuelto;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }
    

    @Override
    public String toString() {
        return "Da\u00f1o{" + "id=" + id + ", descripcion=" + descripcion + ", resuelto=" + resuelto + ", costo=" + costo + ", fecha=" + fecha + '}';
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
        final Daño other = (Daño) obj;
        if (this.id == null ? other.id != null : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    public void setId(String id) {
      this.id = id;  
    }
    
    
}
