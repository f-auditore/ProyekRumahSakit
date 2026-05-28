package model;

import java.util.ArrayList;

public class Pasien extends Individu {

    protected String penyakit;

    public Pasien(String nik, String namaLengkap, int usia, String tempatLahir, String tanggalLahir, char jenisKelamin,
            String noTelp, String penyakit) {
        super(nik, namaLengkap, usia, tempatLahir, tanggalLahir, jenisKelamin, noTelp);
        this.penyakit = penyakit;
    }

    // ArrayList dataPasien dipanggil dari Main
    public void tambahPasien(ArrayList<Pasien> dataPasien, String nik, String namaLengkap, int usia, String tempatLahir,
            String tanggalLahir, char jenisKelamin, String noTelp, String penyakit) {
        dataPasien.add(new Pasien(nik, namaLengkap, usia, tempatLahir, tanggalLahir, jenisKelamin, noTelp, penyakit));
    }

    public static void cariPasienNik(ArrayList<Pasien> dataPasien, String pilihNik) {
        boolean ditemukan = false;
        for (int i = 0; i < dataPasien.size(); i++) {
            if (dataPasien.get(i).getNik().startsWith(pilihNik)) {
                dataPasien.get(i).tampilkanInfo();
                ditemukan = true;
                System.out.println("");
            }
        }
        if (!ditemukan) {
            System.out.println("Pencarian tidak ditemukan.");
        }
    }

    public static void updatePasien(ArrayList<Pasien> dataPasien, String nik, String newNamaLengkap, int newUsia, String newTempatLahir, String newTanggalLahir, char newjenisKelamin, String newNoTelp, String newPenyakit){
        for (int i = 0; i < dataPasien.size(); i++) {
            if (dataPasien.get(i).getNik().equals(nik)) {
                Pasien pasienUbah = dataPasien.get(i);
                pasienUbah.setNamaLengkap(newNamaLengkap);
                pasienUbah.setUsia(newUsia);
                pasienUbah.setTempatLahir(newTempatLahir);
                pasienUbah.setTanggalLahir(newTanggalLahir);
                pasienUbah.setJenisKelamin(newjenisKelamin);
                pasienUbah.setNoTelp(newNoTelp);
                pasienUbah.setPenyakit(newPenyakit);
                break; //keluar dari loop setelah pasien ditemukan dan dihapus 
            }
        }
        
    }

    public void setPenyakit(String newPenyakit){
        this.penyakit = newPenyakit;
    }

    public String getPenyakit() {
        return penyakit;
    }

    public static void hapusPasien(ArrayList<Pasien> dataPasien, String nik) {
        for (int i = 0; i < dataPasien.size(); i++) {
            if (dataPasien.get(i).getNik().equals(nik)) {
                dataPasien.remove(i);
                break;
            }
        }
    }

    @Override
    public String getPeran() {
        return "Pasien";

    }

    @Override
    public void tampilkanInfo() {
        System.out.println("NIK\t\t\t: " + nik + "\nNama\t\t\t: " + namaLengkap + "\nUsia\t\t\t: " + usia
                + "\nTempat Lahir\t\t: " + tempatLahir + "\nTanggal Lahir\t\t: " + tanggalLahir + "\nJenis Kelamin\t\t: "
                + jenisKelamin + "\nPenyakit\t\t: " + penyakit);
    }

}
