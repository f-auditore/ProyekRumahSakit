package model;

import enums.StatusRuangan;

public abstract class Ruangan {

    protected String idRuangan;
    protected StatusRuangan status;

    public Ruangan(String idRuangan, StatusRuangan status) {
        this.idRuangan = idRuangan;
        this.status = status;
    }

    public String getIdRuangan() {
        return idRuangan;
    }

    public StatusRuangan getStatus() {
        return status;
    }
}