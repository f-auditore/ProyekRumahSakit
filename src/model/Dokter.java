package model;

import enums.JenisKelamin;

public class Dokter extends Individu {

    protected String noLisensi;

    public Dokter(String nik, String namaLengkap, int usia, String tempatLahir, String tanggalLahir, JenisKelamin jenisKelamin, String noTelp, String noLisensi) {
        super(nik, namaLengkap, usia, tempatLahir, tanggalLahir, jenisKelamin, noTelp);
        this.noLisensi = noLisensi;
    }

    public String getNoLisensi() {
        return noLisensi;
    }

    public void setNoLisensi(String newNoLisensi) {
        if (newNoLisensi.matches("\\d{10,13}")) {
            this.noLisensi = newNoLisensi;
        }
    }

    @Override
    public String getPeran() {
        return "Dokter Umum";
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("NIK\t: " + nik + "\nNama\t: " + namaLengkap);
    }
}
