package model;

import enums.Hari;
import enums.Jam;

public class Jadwal {

    private final Hari hari;
    private final Jam jamMulai;
    private final Jam jamSelesai;
    private final DokterSpesialis dokter;
    private final Pasien pasien;
    private final Icu ruangan;

    public Jadwal(Hari hari, Jam jamMulai, Jam jamSelesai, DokterSpesialis dokter, Pasien pasien, Icu ruangan) {
        this.hari = hari;
        this.jamMulai = jamMulai;
        this.jamSelesai = jamSelesai;
        this.dokter = dokter;
        this.pasien = pasien;
        this.ruangan = ruangan;
    }

    public Hari getHari() {
        return hari;
    }

    public Jam getJamMulai() {
        return jamMulai;
    }

    public Jam getJamSelesai() {
        return jamSelesai;
    }

    public DokterSpesialis getDokter() {
        return dokter;
    }

    public Pasien getPasien() {
        return pasien;
    }

    public Icu getRuangan() {
        return ruangan;
    }

    @Override
    public String toString() {
        return hari + " (" + jamMulai + " - " + jamSelesai + ")";
    }
}
