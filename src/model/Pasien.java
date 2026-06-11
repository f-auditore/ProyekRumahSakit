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

    public void setPenyakit(String newPenyakit) {
        this.penyakit = newPenyakit;
    }

    public String getPenyakit() {
        return penyakit;
    }

    public static void tambahPasien(ArrayList<Pasien> dataPasien, Scanner sc) {
        System.out.println("\tMasukkan Data Pasien");

        Pasien pasienBaru = new Pasien("", "", 0, "", "", ' ', "", "");
        pasienBaru.tambahData(sc);

        String penyakit;
        do {
            System.out.print("Penyakit: ");
            penyakit = sc.nextLine();
        } while (penyakit.trim().isEmpty());

        pasienBaru.setPenyakit(penyakit);
        dataPasien.add(pasienBaru); //simpan ke arraylist
        System.out.println("Data berhasil ditambahkan.");

    }

    public static void cariPasienNik(ArrayList<Pasien> dataPasien, Scanner sc) {
        System.out.print("\tMasukkan NIK: ");
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

    //UPDATE PASIEN
    public static void updatePasien(ArrayList<Pasien> dataPasien, Scanner sc) {
        System.out.print("Masukkan NIK Pasien yang Ingin Diupdate Datanya: ");
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

                dataPasien.get(i).updateData(sc);

                ditemukan = true;
                System.out.println("Data pasien berhasil diperbarui...");
                break;
            }
        }
        if (!ditemukan) {
            System.out.println("Pasien tidak ditemukan.");
        }
    }

    @Override
    protected void menuTambahan() {
        System.out.println("7. Penyakit");
    }

    @Override
    protected void handlePilihanTambahan(Scanner sc, int pilihField) {
        switch (pilihField) {
            case 7 -> {
                String newPenyakit;
                do {
                    System.out.print("Penyakit: ");
                    newPenyakit = sc.nextLine(); //sc.nextLine() tdk mengembalikan nilai null
                    if (newPenyakit.trim().isEmpty()) {
                        System.out.println("Penyakit tidak boleh kosong");
                    }
                } while (newPenyakit.trim().isEmpty());

                setPenyakit(newPenyakit);
            }
            default -> System.out.println("Invalid: ulangi");   
        }
    }

    public static void hapusPasien(ArrayList<Pasien> dataPasien, Scanner sc) {
        System.out.print("Masukkan NIK Pasien yang Ingin Dihapus Datanya: ");
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
        if (!ditemukan) {
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
