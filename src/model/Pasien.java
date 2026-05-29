package model;

import java.util.ArrayList;
import java.util.Scanner;

public class Pasien extends Individu {

    protected String penyakit;

    public Pasien(String nik, String namaLengkap, int usia, String tempatLahir, String tanggalLahir, char jenisKelamin,
            String noTelp, String penyakit) {
        super(nik, namaLengkap, usia, tempatLahir, tanggalLahir, jenisKelamin, noTelp);
        this.penyakit = penyakit;
    }

    public void tambahPasien(ArrayList<Pasien> dataPasien, String nik, String namaLengkap, int usia, String tempatLahir, String tanggalLahir, char jenisKelamin, String noTelp, String penyakit) {
        dataPasien.add(new Pasien(nik, namaLengkap, usia, tempatLahir, tanggalLahir, jenisKelamin, noTelp, penyakit));
    }

    public static void cariPasienNik(ArrayList<Pasien> dataPasien, Scanner sc) {
        String pilihNik = sc.nextLine();
        System.out.println("");
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
        System.out.println("-----------------------------");
    }

    public static void updatePasien(ArrayList<Pasien> dataPasien, Scanner sc){
        System.out.print("Masukkan NIK Pasien yang Ingin Diupdate Datanya: ");
            String cariNik = sc.nextLine();
            boolean ditemukan = false;
            for (int i = 0; i < dataPasien.size(); i++) {
                if (dataPasien.get(i).getNik().equals(cariNik)) {
                    System.out.println("\tMasukkan Data Baru");
                    System.out.print("Nama: ");
                    String newNamaLengkap = sc.nextLine();
                    System.out.print("Usia: ");
                    int newUsia = sc.nextInt();
                                  sc.nextLine();
                    System.out.print("Tempat Lahir: ");
                    String newTempatLahir = sc.nextLine();
                    System.out.print("Tanggal Lahir: ");
                    String newTanggalLahir = sc.nextLine();
                    System.out.print("Jenis Kelamin: ");
                    char newjenisKelamin = sc.nextLine().charAt(0);
                    System.out.print("Nomor Telepon: ");
                    String newNoTelp = sc.nextLine();
                    System.out.print("Pengakit: ");
                    String newPenyakit = sc.nextLine();

                    Pasien pasienUbah = dataPasien.get(i);
                    pasienUbah.setNamaLengkap(newNamaLengkap);
                    pasienUbah.setUsia(newUsia);
                    pasienUbah.setTempatLahir(newTempatLahir);
                    pasienUbah.setTanggalLahir(newTanggalLahir);
                    pasienUbah.setJenisKelamin(newjenisKelamin);
                    pasienUbah.setNoTelp(newNoTelp);
                    pasienUbah.setPenyakit(newPenyakit);

                    System.out.println("Data pasien berhasil diperbarui...");
                    }
                }
                if (!ditemukan) {
                    System.out.println("Pasien tidak ditemukan.");
                }
                System.out.println("-----------------------------");
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
