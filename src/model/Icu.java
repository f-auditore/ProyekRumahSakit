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

    public void outputInfoIcu() {
        System.out.println("ID Ruangan    : " + getIdRuangan());
        System.out.println("Status Ruangan: " + getStatus());
        System.out.println("Kapasitas Bed : " + kapasitasBed);
        System.out.println("Level Perawatan: " + levelPerawatan);
    }
}