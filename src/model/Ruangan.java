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

    public void setIdRuangan(String idRuangan) {
        this.idRuangan = idRuangan;
    }

    public StatusRuangan getStatus() {
        return status;
    }

    public void setStatus(StatusRuangan status) {
        this.status = status;
    }

    public abstract void updateRuangan(String idRuangan, StatusRuangan status);

    public abstract void tampilkanInfoRuangan();
}
