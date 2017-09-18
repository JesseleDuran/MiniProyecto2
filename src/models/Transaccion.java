/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 *
 * @author Jessele
 */
public class Transaccion {
    private String id;
    private String tipo;
    private String clase;
    private String objeto;
    private Date creado;

    public Transaccion(String tipo, String clase, String objeto) {
        this.id = UUID.randomUUID().toString();
        this.tipo = tipo;
        this.clase = clase;
        this.creado = new Date();
        this.objeto = objeto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getClase() {
        return clase;
    }
    
    public String getObjeto()
    {
        return objeto;
    }
    
    public void setClase(String clase) {
        this.clase = clase;
    }

    @Override
    public String toString() {
        return "Transaccion{" + "id=" + id + ", tipo=" + tipo + ", clase=" + clase + '}';
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
        final Transaccion other = (Transaccion) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}
