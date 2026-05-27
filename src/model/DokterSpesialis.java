package model;

import enums.JenisKelamin;

public class DokterSpesialis extends Dokter {

    protected String spesialis;
    protected int pengalamanTahun;
    protected int jmlhPasien;

    public DokterSpesialis(String nik, String namaLengkap, int usia, String tempatLahir, String tanggalLahir,
            JenisKelamin jenisKelamin, String noTelp, String noLisensi, String spesialis, int pengalamanTahun, int jmlhPasien) {
        super(nik, namaLengkap, usia, tempatLahir, tanggalLahir, jenisKelamin, noTelp, noLisensi);
        this.spesialis = spesialis;
        this.pengalamanTahun = pengalamanTahun;
        this.jmlhPasien = jmlhPasien;
    }

    public String getSpesialis() {
        return spesialis;
    }

    @Override
    public String getPeran() {
        return "Dokter Spesialis";
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("Nama\t: " + namaLengkap + "\nSpesialis\t: " + spesialis);
    }
}
