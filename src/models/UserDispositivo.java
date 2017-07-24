/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Slaush
 */
public class UserDispositivo {
    private String dispositivo;
    private String user;

    public UserDispositivo(String dispositivo, String user) {
        this.dispositivo = dispositivo;
        this.user = user;
    }
   
    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserDispositivo{" + "dispositivo=" + dispositivo + ", user=" + user + '}';
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
        final UserDispositivo other = (UserDispositivo) obj;
        if (this.dispositivo == null ? other.dispositivo != null : !this.dispositivo.equals(other.dispositivo)) {
            return false;
        }
        if (this.user != other.user) {
            return false;
        }
        return true;
    }
    
    
}
