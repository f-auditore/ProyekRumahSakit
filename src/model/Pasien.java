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
        if (newPenyakit != null && !newPenyakit.trim().isEmpty()) {
            this.penyakit = newPenyakit;
        }
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

                    System.out.println("\tMasukkan Data Baru");
                    String newNik;
                    String newNamaLengkap;
                    int newUsia;
                    String newTempatLahir;
                    String newTanggalLahir;
                    char newJenisKelamin;
                    String newNoTelp;
                    String newPenyakit;
                    
                    
                    do {
                        System.out.print("NIK: ");
                        newNik = sc.nextLine(); 
                        if (!(newNik.length() == 16 && newNik.matches("\\d+"))) {
                            System.out.println("Invalid: NIK harus 16 digit angka.");
                        }                           
                    } while (!(newNik.length() == 16 && newNik.matches("\\d+")));

                    do {
                        System.out.print("Nama: "); 
                        newNamaLengkap = sc.nextLine();
                        if (!(newNamaLengkap.matches("[a-zA-Z\\s]+"))) {
                            System.out.println("Invalid: sesuaikan format.");
                        }
                    } while (!(newNamaLengkap.matches("[a-zA-Z\\s]+") ));

                    do {
                        System.out.print("Usia (tahun): ");
                        newUsia = sc.nextInt();
                        sc.nextLine();
                        if (!(newUsia > 0 && newUsia < 150)) {
                            System.out.println("Invalid: usia hanya antara 1-149 tahun");
                        } 
                    } while (!(newUsia > 0 && newUsia < 150));
                    
                    do { 
                        System.out.print("Tempat Lahir: ");
                        newTempatLahir = sc.nextLine();
                        if (!(newTempatLahir != null && !newTempatLahir.trim().isEmpty())) {
                            System.out.println("Tempat lahir tidak boleh kosong");
                        }
                    } while (!(newTempatLahir != null && !newTempatLahir.trim().isEmpty()));
                    
                    do { 
                        System.out.print("Tanggal Lahir (DD-MM-YYYY): ");
                        newTanggalLahir = sc.nextLine();
                        if (!(newTanggalLahir.matches("\\d{2}-\\d{2}-\\d{4}"))) {
                            System.out.println("Invalid: sesuaikan format");
                        }
                    } while (!(newTanggalLahir.matches("\\d{2}-\\d{2}-\\d{4}")));

                    do { 
                        System.out.print("Jenis Kelamin (L/P): ");
                        newJenisKelamin = sc.nextLine().toUpperCase().charAt(0);

                        if (!(newJenisKelamin == 'L' || newJenisKelamin == 'P')) {
                            System.out.println("Invalid: sesuaikan format");
                        }
                    } while (!(newJenisKelamin == 'L' || newJenisKelamin == 'P'));
                    
                    do {
                        System.out.print("Nomor Telepon: ");
                        newNoTelp = sc.nextLine();
                        if (!(newNoTelp.matches("\\d{10,12}"))) {
                            System.out.println("Invalid: digit minimal 10, max 12");
                        } 
                    } while (!(newNoTelp.matches("\\d{10,12}")));

                    do {
                        System.out.print("Penyakit: ");
                        newPenyakit = sc.nextLine();
                        if (!(newPenyakit != null && !newPenyakit.trim().isEmpty())) {
                            System.out.println("Penyakit tidak boleh kosong");
                        } 
                    } while (!(newPenyakit != null && !newPenyakit.trim().isEmpty()));


                    Pasien pasienUbah = dataPasien.get(i);
                    pasienUbah.setNik(newNik);
                    pasienUbah.setNamaLengkap(newNamaLengkap);
                    pasienUbah.setUsia(newUsia);
                    pasienUbah.setTempatLahir(newTempatLahir);
                    pasienUbah.setTanggalLahir(newTanggalLahir);
                    pasienUbah.setJenisKelamin(newJenisKelamin);
                    pasienUbah.setNoTelp(newNoTelp);
                    pasienUbah.setPenyakit(newPenyakit);
                    
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
