package model;

import java.util.ArrayList;

public class Dokter extends Individu {
    protected String noLisensi;

    public Dokter(String nik, String namaLengkap, int usia, String tempatLahir, String tanggalLahir, char jenisKelamin, String noTelp, String noLisensi){
        super(nik, namaLengkap, usia, tempatLahir, tanggalLahir, jenisKelamin, noTelp);
        this.noLisensi = noLisensi;
    }

    public void updateDokter(ArrayList<Dokter> dataDokter, String nik, String newNamaLengkap, int newUsia, String newTempatLahir, String newTanggalLahir, char newjenisKelamin, String newNoTelp, String newNoLisensi){
        for (int i = 0; i < dataDokter.size(); i++) {
            if (dataDokter.get(i).getNik().equals(nik)) {
                Dokter dokterUbah = dataDokter.get(i);
                dokterUbah.setNamaLengkap(newNamaLengkap);
                dokterUbah.setUsia(newUsia);
                dokterUbah.setTempatLahir(newTempatLahir);
                dokterUbah.setTanggalLahir(newTanggalLahir);
                dokterUbah.setJenisKelamin(newjenisKelamin);
                dokterUbah.setNoTelp(newNoTelp);
                dokterUbah.setNoLisensi(newNoLisensi);
                break; 
            }
        }
}

    public void setNoLisensi(String newNoLisensi){
        if (newNoLisensi.matches("\\d{10,13}")) {
            this.noLisensi = newNoLisensi;
        }
    }

    @Override
    public String getPeran(){
        return "Dokter";
    }

    @Override
    public void tampilkanInfo(){
        System.out.println("NIK\t: " + nik + "\nNama\t: " + namaLengkap + "\nUsia\t: " + usia + "\nTempat Lahir\t: " + tempatLahir + "\nTanggal Lahir\t" + tanggalLahir +  "\nJenis Kelamin\t: " + jenisKelamin);
    }
} 
