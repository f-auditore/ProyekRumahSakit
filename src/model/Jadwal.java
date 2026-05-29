package model;

import enums.Hari;
import enums.Jam;

public class Jadwal {

    private String idJadwal;
    private Hari hari;
    private Jam jamMulai;
    private Jam jamSelesai;
    private DokterSpesialis dokter;
    private Pasien pasien;
    private Icu ruangan;

    public Jadwal(Hari hari, Jam jamMulai, Jam jamSelesai, DokterSpesialis dokter, Pasien pasien, Icu ruangan) {
        this.hari = hari;
        this.jamMulai = jamMulai;
        this.jamSelesai = jamSelesai;
        this.dokter = dokter;
        this.pasien = pasien;
        this.ruangan = ruangan;
    }

    public String getIdJadwal(){
        return idJadwal;
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

    public Jam getJamSelesai() {
        return jamSelesai;
    }

    public DokterSpesialis getDokter() {
        return dokter;
    }

    public void setDokter(DokterSpesialis dokter) {
        this.dokter = dokter;
    }

    // --- Getter & Setter: Pasien ---
    public Pasien getPasien() {
        return pasien;
    }

    public void setPasien(Pasien pasien) {
        this.pasien = pasien;
    }

    // --- Getter & Setter: Ruangan ---
    public Icu getRuangan() {
        return ruangan;
    }

    public void setRuangan(Icu ruangan) {
        this.ruangan = ruangan;
    }

    @Override
    public String toString() {
        String infoDokter = (dokter != null)
                ? dokter.getNamaLengkap() + " | NIK: " + dokter.getNik() + " | Spesialis: " + dokter.getSpesialis()
                : "Dokter belum ditentukan";

        String infoPasien = (pasien != null)
                ? pasien.getNamaLengkap() + " | NIK: " + pasien.getNik() + " | Penyakit: " + pasien.getPenyakit()
                : "Pasien belum ditentukan";

        String infoRuangan = (ruangan != null)
                ? "ID: " + ruangan.getIdRuangan() + " | Status: " + ruangan.getStatus()
                + " | Kapasitas Bed: " + ruangan.getKapasitasBed()
                + " | Level Perawatan: " + ruangan.getLevelPerawatan()
                : "Ruangan belum ditentukan";

        return "=== Jadwal ==="
                + "\nHari      : " + hari
                + "\nJam       : " + jamMulai + " - " + jamSelesai
                + "\nDokter    : " + infoDokter
                + "\nPasien    : " + infoPasien
                + "\nRuangan   : " + infoRuangan;
    }
}
