/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Objects;

/**
 *
 * @author Jessele
 */
public class Compuesto {
    String componente;
    String dispositivo;

    public Compuesto(String componente, String dispositivo) {
        this.componente = componente;
        this.dispositivo = dispositivo;
    }

    public String getComponente() {
        return componente;
    }

    public void setComponente(String componente) {
        this.componente = componente;
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
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
        final Compuesto other = (Compuesto) obj;
        if (!Objects.equals(this.componente, other.componente)) {
            return false;
        }
        if (!Objects.equals(this.dispositivo, other.dispositivo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Compuesto{" + "componente=" + componente + ", dispositivo=" + dispositivo + '}';
    }
    
    
}
