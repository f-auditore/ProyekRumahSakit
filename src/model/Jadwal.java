package model;

import enums.Hari;
import enums.Jam;

public class Jadwal {

    private Hari hari;
    private Jam jamMulai;
    private Jam jamSelesai;

    // public Jadwal() {}

    public Jadwal(Hari hari, Jam jamMulai, Jam jamSelesai) {
        this.hari = hari;
        this.jamMulai = jamMulai;
        this.jamSelesai = jamSelesai;
    }

    public Hari getHari() {
        return hari;
    }

    public void setHari(Hari hari) {
        this.hari = hari;
    }

    public Jam getJamMulai() {
        return jamMulai;
    }

    public void setJamMulai(Jam jamMulai) {
        this.jamMulai = jamMulai;
    }

    public Jam getJamSelesai() {
        return jamSelesai;
    }

    public void setJamSelesai(Jam jamSelesai) {
        this.jamSelesai = jamSelesai;
    }

    @Override
    public String toString() {
        return hari + " | " + jamMulai + " - " + jamSelesai;
    }
}
