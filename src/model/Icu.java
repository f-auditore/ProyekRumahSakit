package model;

import java.util.ArrayList;
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

    public void tambahIcu(ArrayList<Icu> dataIcu, String idRuangan, StatusRuangan status, int kapasitasBed, LevelPerawatan levelPerawatan) {
        dataIcu.add(new Icu(idRuangan, status, kapasitasBed, levelPerawatan));
    }

    public void updateIcu(ArrayList<Icu> dataIcu, String idRuangan, StatusRuangan newStatus, int newKapasitasBed, LevelPerawatan newLevelPerawatan) {
        for (int i = 0; i < dataIcu.size(); i++) {
            if (dataIcu.get(i).getIdRuangan().equals(idRuangan)) {
                Icu icuUbah = dataIcu.get(i);
                icuUbah.setStatus(newStatus);
                icuUbah.setKapasitasBed(newKapasitasBed);
                icuUbah.setLevelPerawatan(newLevelPerawatan);
                break;
            }
        }
    }

    public void hapusIcu(ArrayList<Icu> dataIcu, String idRuangan) {
        for (int i = 0; i < dataIcu.size(); i++) {
            if (dataIcu.get(i).getIdRuangan().equals(idRuangan)) {
                dataIcu.remove(i);
                break;
            }
        }
    }

    @Override
    public void updateRuangan(String idRuangan, StatusRuangan status) {
        setIdRuangan(idRuangan);
        setStatus(status);
    }

    @Override
    public void tampilkanInfoRuangan() {
        System.out.println("ID Ruangan\t\t: " + getIdRuangan());
        System.out.println("Status Ruangan\t\t: " + getStatus());
        System.out.println("Kapasitas Bed\t\t: " + kapasitasBed);
        System.out.println("Level Perawatan\t\t: " + levelPerawatan);
    }

    public void outputInfoIcu() {
        System.out.println("ID Ruangan\t\t: " + getIdRuangan());
        System.out.println("Status Ruangan\t: " + getStatus());
        System.out.println("Kapasitas Bed\t\t: " + kapasitasBed);
        System.out.println("Level Perawatan\t: " + levelPerawatan);
    }
}