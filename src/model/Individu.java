package model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Scanner;

public abstract class Individu {

    protected String nik;
    protected String namaLengkap;
    protected int usia;
    protected String tempatLahir;
    protected String tanggalLahir;
    protected char jenisKelamin;
    protected String noTelp;

    public Individu(String nik, String namaLengkap, int usia, String tempatLahir, String tanggalLahir, char jenisKelamin, String noTelp) {
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

    //SETTER
    public void setNik(String newNik) {
        if (validasiNik(newNik) != null) {
            this.nik = newNik;
        }
    }

    public void setNamaLengkap(String newNamaLengkap) {
        if (validasiNamaLengkap(newNamaLengkap) != null) {
            this.namaLengkap = newNamaLengkap;
        }
    }

    public void setUsia(int newUsia) {
        this.usia = newUsia;
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

    public void setJenisKelamin(char newJenisKelamin) {
        if (newJenisKelamin == 'L' || newJenisKelamin == 'P') {
            this.jenisKelamin = newJenisKelamin;
        }
    }

    public void setNoTelp(String newNoTelp) {
        if (newNoTelp.matches("\\d{10,12}")) {
            this.noTelp = newNoTelp;
        }
    }

    //VALIDASI
    public static String validasiNik(String newNik) {
        if (newNik.length() == 16 && newNik.matches("\\d+")) {
            return newNik;
        }
        System.out.println("Invalid: input 16 digit");
        return null;
    }

    public String validasiNamaLengkap(String newNamaLengkap) {
        if (newNamaLengkap.matches("[a-zA-Z\\s]+")) {
            return newNamaLengkap;
        }
        System.out.println("Invalid: input huruf");
        return null;
    }

    public String validasiTempatlahir(String newTempatLahir) {
        if (newTempatLahir != null && !newTempatLahir.trim().isEmpty()) {
            return newTempatLahir;
        }
        System.out.println("Tidak boleh kosong");
        return null;
    }

    public String validasiTanggalLahir(String newTanggalLahir) {
        if (newTanggalLahir.matches("\\d{2}-\\d{2}-\\d{4}")) {
            try {
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-uuuu").withResolverStyle(ResolverStyle.STRICT);
                LocalDate lahir = LocalDate.parse(newTanggalLahir, format); //Konversi string newTanggalLahir jadi object LocalDate 

                int tahun = lahir.getYear();

                if (tahun < 1900 || lahir.isAfter(LocalDate.now())) {
                    System.out.println("Invalid: sesuaikan penanggalan"); //hrs sesuai range
                    return null;
                }

                this.usia = Period.between(lahir, LocalDate.now()).getYears();
                System.out.println("Usia:  " + usia);

            } catch (DateTimeParseException e) {
                System.out.println("Invalid: sesuaikan penanggalan"); //tanggal hrs exist di kalender
                return null;
            }
            return newTanggalLahir;
        }
        System.out.println("Invalid: sesuaikan format"); // hrs \\d{2}-\\d{2}-\\d{4}
        return null;
    }

    public char validasiJenisKelamin(char newJenisKelamin) {
        if (newJenisKelamin == 'L' || newJenisKelamin == 'P') {
            return newJenisKelamin;
        }
        System.out.println("Invalid: input L/P");
        return 0;
    }

    public String validasiNoTelp(String newNoTelp) {
        if (newNoTelp.matches("\\d{10,12}")) {
            return newNoTelp;
        }
        System.out.println("Invalid: input 10-12 digit");
        return null;
    }

    //UPDATE DATA
    public void updateData(Scanner sc) {
        int pilihField;
        do {
            System.out.println("\nField nama yang ingin diubah?");
            System.out.println("1. NIK");
            System.out.println("2. Nama Lengkap");
            System.out.println("3. Tempat Lahir");
            System.out.println("4. Tanggal Lahir");
            System.out.println("5. Jenis Kelamin");
            System.out.println("6. Nomor Telepon");
            menuTambahan();
            System.out.println("0. Back");
            System.out.print("Pilihan: ");
            pilihField = Integer.parseInt(sc.nextLine());

            switch (pilihField) {
                case 1 -> {
                    do {
                        System.out.print("NIK: ");
                        nik = sc.nextLine();
                    } while (validasiNik(nik) == null);
                    setNik(nik);
                }
                case 2 -> {
                    do {
                        System.out.print("Nama Lengkap: ");
                        this.namaLengkap = sc.nextLine();
                    } while (validasiNamaLengkap(namaLengkap) == null);
                    setNamaLengkap(namaLengkap);
                }
                case 3 -> {
                    do {
                        System.out.print("Tempat Lahir: ");
                        tempatLahir = sc.nextLine();
                    } while (validasiTempatlahir(tempatLahir) == null);
                    setTempatLahir(tempatLahir);
                }
                case 4 -> {
                    do {
                        System.out.print("Tanggal Lahir (DD-MM-YYYY): ");
                        tanggalLahir = sc.nextLine();
                    } while (validasiTanggalLahir(tanggalLahir) == null);
                    setTanggalLahir(tanggalLahir);
                    setUsia(usia);
                }
                case 5 -> {
                    do {
                        System.out.print("jenis Kelamin: ");
                        jenisKelamin = sc.nextLine().toUpperCase().charAt(0);
                    } while (validasiJenisKelamin(jenisKelamin) == 0);
                    setJenisKelamin(jenisKelamin);
                }
                case 6 -> {
                    do {
                        System.out.print("Nomor Telepon");
                        noTelp = sc.nextLine();
                    } while (validasiNoTelp(noTelp) == null);
                    setNoTelp(noTelp);
                }
                case 0 -> {
                }
                default -> handlePilihanTambahan(sc, pilihField);
            }

        } while (pilihField != 0);

        /* 
        User input 0
        1. Masuk case 0, tidak melakukan apa-apa
        2. Balik ke kondisi while (pilihField != 0) -> false -> loop berhenti 
        */
    }

    //TAMBAH DATA
    public void tambahData(Scanner sc) {
        do {
            System.out.print("NIK: ");
            nik = sc.nextLine();
        } while (validasiNik(nik) == null);
        setNik(nik);

        do {
            System.out.print("Nama Lengkap: ");
            this.namaLengkap = sc.nextLine();
        } while (validasiNamaLengkap(namaLengkap) == null);
        setNamaLengkap(namaLengkap);

        do {
            System.out.print("Tempat Lahir: ");
            tempatLahir = sc.nextLine();
        } while (validasiTempatlahir(tempatLahir) == null);
        setTempatLahir(tempatLahir);

        do {
            System.out.print("Tanggal Lahir (DD-MM-YYYY): ");
            tanggalLahir = sc.nextLine();
        } while (validasiTanggalLahir(tanggalLahir) == null);
        setUsia(usia);
        setTanggalLahir(tanggalLahir);

        do {
            System.out.print("jenis Kelamin: ");
            jenisKelamin = sc.nextLine().toUpperCase().charAt(0);
        } while (validasiJenisKelamin(jenisKelamin) == 0);
        setJenisKelamin(jenisKelamin);

        do {
            System.out.print("Nomor Telepon");
            noTelp = sc.nextLine();
        } while (validasiNoTelp(noTelp) == null);
        setNoTelp(noTelp);

    }

    protected abstract void menuTambahan();

    protected abstract void handlePilihanTambahan(Scanner sc, int pilihField);

    public abstract String getPeran();

    public abstract void tampilkanInfo();

}
