package model;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Individu {

    protected String nik;
    protected String namaLengkap;
    protected int usia;
    protected String tempatLahir;
    protected String tanggalLahir;
    protected char jenisKelamin;
    protected String noTelp;

    boolean hasilUsia;

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
    public String validasiNik(String newNik) {
        if (newNik.length() == 16 && newNik.matches("\\d+")) {
            return newNik;
        }
        System.out.println("Data NIK tidak valid");
        return null;
    }

    public String validasiNamaLengkap(String newNamaLengkap) {
        if (newNamaLengkap.matches("[a-zA-Z\\s]+")) {
            return newNamaLengkap;
        }
        System.out.println("Invalid: sesuaikan format.");
        return null;
    }

    public boolean validasiUsia(int newUsia) {
        if (newUsia > 0 && newUsia < 150) {
            return true;
        }
        System.out.println("Invalid: usia hanya antara 1-149 tahun");
        return false;
    }

    public String validasiTempatlahir(String newTempatLahir) {
        if (newTempatLahir != null && !newTempatLahir.trim().isEmpty()) {
            return newTempatLahir;
        }
        System.out.println("Tempat lahir tidak boleh kosong");
        return null;
    }

    public String validasiTanggalLahir(String newTanggalLahir) {
        if (newTanggalLahir.matches("\\d{2}-\\d{2}-\\d{4}")) {
            return newTanggalLahir;
        }
        System.out.println("Invalid: sesuaikan format");
        return null;
    }

    public char validasiJenisKelamin(char newJenisKelamin) {
        if (newJenisKelamin == 'L' || newJenisKelamin == 'P') {
            return newJenisKelamin;
        }
        System.out.println("Invalid: sesuaikan format");
        return 0;
    }

    public String validasiNoTelp(String newNoTelp) {
        if (newNoTelp.matches("\\d{10,12}")) {
            return newNoTelp;
        }
        System.out.println("Invalid: digit minimal 10, max 12");
        return null;
    }

    //UPDATE DATA
    public void updateData(Scanner sc, Object data) {
        do {
            System.out.print("NIK: ");
            nik = sc.nextLine();
            validasiNik(nik);
        } while (!(nik.length() == 16 && nik.matches("\\d+")));

        do {
            System.out.print("Nama Lengkap: ");
            this.namaLengkap = sc.nextLine();
            validasiNamaLengkap(namaLengkap);
        } while (!(namaLengkap.matches("[a-zA-Z\\s]+")));

        do {
            try {
                System.out.print("Usia: ");
                usia = sc.nextInt(); 
                sc.nextLine(); 
                hasilUsia = validasiUsia(usia);

            } catch (InputMismatchException e) {
                System.out.println("Invalid: Input harus berupa angka (tidak boleh huruf/simbol)!");
                sc.nextLine(); 
                hasilUsia = false;
            }
        } while (hasilUsia == false);

        do {
            System.out.print("Tempat Lahir: ");
            tempatLahir = sc.nextLine();
            validasiTempatlahir(tempatLahir);
        } while (!(tempatLahir != null && !tempatLahir.trim().isEmpty()));

        do {
            System.out.print("Tanggal Lahir: ");
            tanggalLahir = sc.nextLine();
            validasiTanggalLahir(tanggalLahir);
        } while (!(tanggalLahir.matches("\\d{2}-\\d{2}-\\d{4}")));

        do {
            System.out.println("jenis Kelamin: ");
            jenisKelamin = sc.nextLine().toUpperCase().charAt(0);
            validasiJenisKelamin(jenisKelamin);
        } while (!(jenisKelamin == 'L' || jenisKelamin == 'P'));

        do {
            System.out.println("Nomor Telepon");
            noTelp = sc.nextLine();
            validasiNoTelp(noTelp);
        } while (!(noTelp.matches("\\d{10,12}")));

        setNik(nik);
        setNamaLengkap(namaLengkap);
        setUsia(usia);
        setTempatLahir(tempatLahir);
        setTanggalLahir(tanggalLahir);
        setJenisKelamin(jenisKelamin);
        setNoTelp(noTelp);

    }

    public abstract String getPeran();

    public abstract void tampilkanInfo();
}
