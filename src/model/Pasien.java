package model;

import enums.JenisKelamin;

public class Pasien extends Individu {
    protected String penyakit;

    public Pasien(String nik, String namaLengkap, int usia, String tempatLahir, String tanggalLahir, JenisKelamin jenisKelamin, String noTelp, String penyakit) {
        super(nik, namaLengkap, usia, tempatLahir, tanggalLahir, jenisKelamin, noTelp);
        this.penyakit = penyakit;
    }

    public void setPenyakit(String newPenyakit){ this.penyakit = newPenyakit; }
    public String getPenyakit() { return penyakit; }

    @Override
    public String getPeran() { return "Pasien"; }

    @Override
    public void tampilkanInfo() {
        System.out.println("NIK\t: " + nik + "\nNama\t: " + namaLengkap + "\nPenyakit: " + penyakit);
    }
}