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

    public void setPenyakit(String newPenyakit){
            this.penyakit = newPenyakit;
    }

    public String getPenyakit() {
        return penyakit;
    }


    public static void tambahPasien(ArrayList<Pasien> dataPasien, Scanner sc) {
        System.out.print("NIK: ");
        String nik = sc.nextLine();
        System.out.print("Nama: ");
        String namaLengkap = sc.nextLine();
        System.out.print("Usia: ");
        int usia = sc.nextInt();
               sc.nextLine();
        System.out.print("Tempat Lahir: ");
        String tempatLahir = sc.nextLine();
        System.out.print("Tanggal Lahir: ");
        String tanggalLahir = sc.nextLine();
        System.out.print("Jenis Kelamin: ");
        char jenisKelamin = sc.nextLine().charAt(0);
        System.out.print("Nomor Telepon: ");
        String noTelp = sc.nextLine();
        System.out.print("Penyakit: ");
        String penyakit = sc.nextLine();

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
    }

    public static void updatePasien(ArrayList<Pasien> dataPasien, Scanner sc){
            String cariNik = sc.nextLine();
            boolean ditemukan = false;
            for (int i = 0; i < dataPasien.size(); i++) {
                if (dataPasien.get(i).getNik().equals(cariNik)) {
                    String konfirmasi;
                    do { 
                        System.out.print("Update pasien atas nama " + dataPasien.get(i).getNamaLengkap() + "? (Y/N): ");
                        konfirmasi = sc.nextLine().toUpperCase();
                    } while (!konfirmasi.equals("Y") && !konfirmasi.equals("N"));

                    if (konfirmasi.equals("N")) {
                        return;
                    }

                    System.out.println("\tMasukkan Data Baru");
                    dataPasien.get(i).updateData(sc, dataPasien.get(i));
                    
                    String newPenyakit;
                    do {
                        System.out.print("Penyakit: ");
                        newPenyakit = sc.nextLine();
                        if (!(newPenyakit != null && !newPenyakit.trim().isEmpty())) {
                            System.out.println("Penyakit tidak boleh kosong");
                        } 
                    } while (!(newPenyakit != null && !newPenyakit.trim().isEmpty()));


                    dataPasien.get(i).setPenyakit(newPenyakit);
                    
                    ditemukan = true;
                    System.out.println("Data pasien berhasil diperbarui...");
                    }
                }
                if (!ditemukan) {
                    System.out.println("Pasien tidak ditemukan.");
                }
    }

    public static void hapusPasien(ArrayList<Pasien> dataPasien, Scanner sc) {
        String pilihnik = sc.nextLine();
        boolean ditemukan = false;
        for (int i = 0; i < dataPasien.size(); i++) {
            if (dataPasien.get(i).getNik().equals(pilihnik)) {
                dataPasien.remove(i);
                System.out.println("Data pasien berhasil dihapus...");
                ditemukan = true;
                break;
            }
        }
        if (!ditemukan){
            System.out.println("Pasien tidak ditemukan");
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
