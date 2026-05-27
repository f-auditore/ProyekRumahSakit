package model;

import enums.JenisKelamin;

public abstract class Individu {

    protected String nik;
    protected String namaLengkap;
    protected int usia;
    protected String tempatLahir;
    protected String tanggalLahir;
    private JenisKelamin jenisKelamin;
    protected String noTelp;

    public Individu(String nik, String namaLengkap, int usia, String tempatLahir, String tanggalLahir, JenisKelamin jenisKelamin, String noTelp) {
        this.nik = nik;
        this.namaLengkap = namaLengkap;
        this.usia = usia;
        this.tempatLahir = tempatLahir;
        this.tanggalLahir = tanggalLahir;
        this.jenisKelamin = jenisKelamin;
        this.noTelp = noTelp;
    }

    public String getNik() {
        return nik;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public int getUsia() {
        return usia;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public  JenisKelamin getJenisKelamin() {
        return jenisKelamin;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNamaLengkap(String newNamaLengkap) {
        if (newNamaLengkap != null && !newNamaLengkap.trim().isEmpty()) {
            this.namaLengkap = newNamaLengkap;
        }
    }

    public void setNik(String newNik) {
        if (newNik.length() == 16 && newNik.matches("\\d+")) {
            this.nik = newNik;
        }
    }

    public void setTempatLahir(String newTempatLahir) {
        if (newTempatLahir != null && !newTempatLahir.trim().isEmpty()) {
            this.tempatLahir = newTempatLahir;
        }
    }

    public void setTanggalLahir(String newTanggalLahir) {
        if (newTanggalLahir.matches("\\d{2}-\\d{2}-\\d{4}")) {
            this.tanggalLahir = newTanggalLahir;
        }
    }

    public void setJenisKelamin(JenisKelamin newJenisKelamin) {
        this.jenisKelamin = newJenisKelamin;
    }

    public void setUsia(int newUsia) {
        if (newUsia > 0 && newUsia < 150) {
            this.usia = newUsia;
        }
    }

    public void setNoTelp(String newNoTelp) {
        if (newNoTelp.matches("\\d{10,13}")) {
            this.noTelp = newNoTelp;
        }
    }

    public abstract String getPeran();

    public abstract void tampilkanInfo();
}
