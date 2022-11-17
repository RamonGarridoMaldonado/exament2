package com.rgarmal.exament2.model;

public class LoginUsuario {
    private String usuario;
    private String password;

    public LoginUsuario() {
    }
    
    public LoginUsuario(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public LoginUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
