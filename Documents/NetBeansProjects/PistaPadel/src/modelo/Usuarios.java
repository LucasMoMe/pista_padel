/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author isard
 */
public class Usuarios {
    public String correo;
    public String user;
    public String nombre;
    public String apellidos;
    public String contrsaeña;
    public boolean socio;

    public String getContrsaeña() {
        return contrsaeña;
    }

    public void setContrsaeña(String contrsaeña) {
        this.contrsaeña = contrsaeña;
    }
   
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public boolean isSocio() {
        return socio;
    }

    public void setSocio(boolean socio) {
        this.socio = socio;
    }
}
