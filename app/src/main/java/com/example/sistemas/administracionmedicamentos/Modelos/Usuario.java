package com.example.sistemas.administracionmedicamentos.Modelos;

public class Usuario {

    public String usuario,passwd,nombre,descripcion,ip_address,usuario_id,rol_id;

    public Usuario(String usuario_id, String rol_id, String usuario, String passwd, String nombre, String descripcion, String ip_address) {
        this.usuario_id = usuario_id;
        this.rol_id = rol_id;
        this.usuario = usuario;
        this.passwd = passwd;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ip_address = ip_address;
    }

    public Usuario(){
    }

    public String getUsuario_id() {
        return usuario_id;
    }

    public String getRol_id() {
        return rol_id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPasswd() {
        return passwd;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
