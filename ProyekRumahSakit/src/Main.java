
import java.util.ArrayList;
import java.util.Scanner;
import model.Dokter;
import model.DokterSpesialis;
import model.Pasien;
import model.RekamMedis;
import model.Ugd;

public class Main {

    static ArrayList<Pasien> dataPasien = new ArrayList<>();
    static ArrayList<Dokter> dataDokter = new ArrayList<>();
    static ArrayList<Ugd> dataUGD = new ArrayList<>();
    static ArrayList<RekamMedis> dataRekamMedis = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        dataPasien.add(new Pasien(null, null, 0, null, null, null, null));
        dataDokter.add(new Dokter(null, null, 0, null, null, 0));
        dataDokter.add(new DokterSpesialis(null, null, 0, null, null, 0, null));
        dataUGD.add(new Ugd("UGD001", "Tersedia", null, "08:00-16:00", "dnjajndajnd", "awjndijad"));
        dataRekamMedis.add(new RekamMedis("RM001", "Rabies", "2024-06-01"));

        Scanner sc = new Scanner(System.in);
        System.out.println("=== SISTEM MANAJEMEN DATA PASIEN === \n1. Daftarkan Pasien Baru \n2. Lihat Semua Pasien \n3. Cari Pasien Berdasarkan ID \n4. Perbarui Diagnosis Pasien \n5. Hapus Data Pasien \n6. Tugaskan Dokter ke Pasien \n7. Lihat Rekam Medis\n8. Lihat Ruangan");
        int pilihan = sc.nextInt();
        switch (pilihan) {
            case 1 ->
                daftarkanPasienBaru(sc);
            case 2 ->
                daftarPasien();
            case 3 ->
                cariPasien(sc);
            case 4 ->
                perbaruiDiagnosis(sc);
            case 5 ->
                hapusDatapasien(sc);
            case 6 ->
                tugaskanDokterKePasien(sc);
            case 7 ->
                lihatRekamMedis();
            case 8 ->
                pilihRuangan();

            default ->
                throw new AssertionError("Pilihan tidak valid.");
        }
    }

    public static void daftarkanPasienBaru(Scanner sc) {
        sc.nextLine();
    }

    public static void daftarPasien() {

    }

    public static void cariPasien(Scanner sc) {

    }

    public static void perbaruiDiagnosis(Scanner sc) {

    }

    public static void hapusDatapasien(Scanner sc) {

    }

    public static void tugaskanDokterKePasien(Scanner sc) {

    }

    // 7
    public static void lihatRekamMedis() {
        if (dataRekamMedis.isEmpty() || (dataRekamMedis.size() == 1 && dataRekamMedis.get(0).getId() == null)) {
            System.out.println("Belum ada data rekam medis yang tersedia.");
        } else {
            System.out.println("============= Data Rekam Medis ============");
            for (RekamMedis rekam : dataRekamMedis) {
                if (rekam.getId() == null || rekam.getId().trim().isEmpty()) {
                    continue;
                }
                rekam.uotputInfoRekamMedis();
                System.out.println("-----------------------------");
            }
        }
    }

    // 8
    public static void pilihRuangan() {
        System.out.println("Pilih Ruangan: \n1. UGD");
        try (Scanner sc = new Scanner(System.in)) {
            int pilihanRuangan = sc.nextInt();
            switch (pilihanRuangan) {
                case 1 ->
                    lihatRuanganUgd();
                default ->
                    System.out.println("Pilihan ruangan tidak valid.");
            }
        }
    }

    // 8.1
    public static void lihatRuanganUgd() {
        if (dataUGD.isEmpty() || (dataUGD.size() == 1 && dataUGD.get(0).getIdRuangan() == null)) {
            System.out.println("Belum ada data ruangan yang tersedia.");
        } else {
            System.out.println("============= Data Ruangan UGD ============");
            for (Ugd ugd : dataUGD) {
                if (ugd.getIdRuangan() == null || ugd.getIdRuangan().trim().isEmpty()) {
                    continue;
                }
                ugd.outputInfoUgd();
                System.out.println("-----------------------------");
            }
        }
    }
}
