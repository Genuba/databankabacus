package com.example.databankabacus.data.model;

public class RespuestaAutenticarUsuario {
    private int idUsuario;
    private String usuario;
    private String clave;
    private boolean valido;
    Result ResultObject;


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
        return ResultObject;
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
        this.ResultObject = resultObject;
    }
}
class Result {
    private float valor;
    private String mensaje;


    // Getter Methods

    public float getValor() {
        return valor;
    }

    public String getMensaje() {
        return mensaje;
    }

    // Setter Methods

    public void setValor(float valor) {
        this.valor = valor;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}