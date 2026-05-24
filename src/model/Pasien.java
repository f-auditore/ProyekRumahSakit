package model;

import java.util.ArrayList;

public class Pasien extends Individu {
    
    protected String penyakit;
    
    public Pasien(String nik, String namaLengkap, int usia, String tempatLahir, String tanggalLahir, char jenisKelamin, String noTelp, String penyakit){
        super(nik, namaLengkap, usia, tempatLahir, tanggalLahir, jenisKelamin, noTelp);
        this.penyakit = penyakit;
    }
                                    //ArrayList dataPasien dipanggil dari Main
    public static void tambahPasien(ArrayList<Pasien> dataPasien, String nik, String namaLengkap, int usia, String tempatLahir, String tanggalLahir, char jenisKelamin, String noTelp, String penyakit) {
        dataPasien.add(new Pasien(nik, namaLengkap, usia, tempatLahir, tanggalLahir, jenisKelamin, noTelp, penyakit));
    }
    public static void hapusDatapasien(ArrayList<Pasien> dataPasien, String nik) {
        for (int i = 0; i < dataPasien.size(); i++) {
            if (dataPasien.get(i).getNik().equals(nik)) {
                dataPasien.remove(i);
                break; //keluar dari loop setelah pasien ditemukan dan dihapus 
            }
        }
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
