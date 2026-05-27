package model;

import enums.LevelPerawatan;
import enums.StatusRuangan;

public class Icu extends Ruangan {

    private int kapasitasBed;
    private LevelPerawatan levelPerawatan;

    public Icu(String idRuangan, StatusRuangan status, int kapasitasBed, LevelPerawatan levelPerawatan) {
        super(idRuangan, status);
        this.kapasitasBed = kapasitasBed;
        this.levelPerawatan = levelPerawatan;
    }

    public int getKapasitasBed() {
        return kapasitasBed;
    }

    public void setKapasitasBed(int kapasitasBed) {
        this.kapasitasBed = kapasitasBed;
    }

    public LevelPerawatan getLevelPerawatan() {
        return levelPerawatan;
    }

    public void setLevelPerawatan(LevelPerawatan levelPerawatan) {
        this.levelPerawatan = levelPerawatan;
    }

    @Override
    public void updateRuangan(String idRuangan, StatusRuangan status) {
        setIdRuangan(idRuangan);
        setStatus(status);
    }

    @Override
    public void tampilkanInfoRuangan() {
        System.out.println("ID ICU: " + getIdRuangan() + " | Status: " + getStatus());
    }
}
