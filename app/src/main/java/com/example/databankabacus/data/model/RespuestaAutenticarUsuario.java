package com.example.databankabacus.data.model;

public class RespuestaAutenticarUsuario {
    private int idUsuario;
    private String usuario;
    private String clave;
    private boolean valido;
    Result result = new Result();


    // Getter Methods

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getClave() {
        return clave;
    }

    public boolean getValido() {
        return valido;
    }

    public Result getResult() {
        return result;
    }

    // Setter Methods

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setValido(boolean valido) {
        this.valido = valido;
    }

    public void setResult(Result resultObject) {
        this.result = resultObject;
    }
}
