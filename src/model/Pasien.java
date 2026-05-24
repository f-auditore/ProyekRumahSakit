package model;

public class Pasien extends Individu {
    protected String penyakit;
    
    public Pasien(String nik, String namaLengkap, int usia, String tempatLahir, String tanggalLahir, char jenisKelamin, String penyakit){
        super(nik, namaLengkap, usia, tempatLahir, tanggalLahir, jenisKelamin);
        this.penyakit = penyakit;
    }

    @Override
    public void getPeran(){
        System.out.println("Pasien");

    }
    @Override
    public void tampilkanInfo(){
        System.out.println("NIK\t\t\t: " + nik + "\nNama\t\t\t: " + namaLengkap + "\nUsia\t\t\t: " + usia + "\nTempat Lahir\t: " + tempatLahir + "\nTanggal Lahir\t" + tanggalLahir + "\nJenis Kelamin\t\t: " + jenisKelamin + "\nPenyakit\t\t: " + penyakit); 
    }


}
