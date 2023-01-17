package com.mycompany.factu.responses;

public class Constancia {
    private int id_datosSAT, id_cliente, id_archivosSAT;
    private String fecha, rfc, idCIF, liga;

    public int getId_datosSAT() {
        return id_datosSAT;
    }

    public void setId_datosSAT(int id_datosSAT) {
        this.id_datosSAT = id_datosSAT;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_archivosSAT() {
        return id_archivosSAT;
    }

    public void setId_archivosSAT(int id_archivosSAT) {
        this.id_archivosSAT = id_archivosSAT;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getIdCIF() {
        return idCIF;
    }

    public void setIdCIF(String idCIF) {
        this.idCIF = idCIF;
    }

    public String getLiga() {
        return liga;
    }

    public void setLiga(String liga) {
        this.liga = liga;
    }
}
