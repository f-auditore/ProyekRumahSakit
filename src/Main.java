
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
        dataPasien.add(new Pasien("3171022804950003", "Rian Santoso", 31, "Jakarta", "28-04-1995",'L', "081298345712", "Hipertensi"));
        dataDokter.add(new Dokter("3273052110940005", "dr. Indah Permatasari", 31, "Bandung", "21-10-1994", 'P', "085711928344", "DK10293847561029"));
        dataDokter.add(new DokterSpesialis("3171031407880002", "dr. Aris Munandar, Sp.PD", 38, "Yogyakarta", "14-07-1988", 'L', "082188456723", "DS30495867120394", "Spesialis Penyakit Dalam", 12, 450));
        dataUGD.add(new Ugd(
                enums.StatusPasien.KOSONG,
                enums.StatusDokter.STANDBY,
                "UGD001",
                enums.StatusRuangan.KOSONG
        ));
        dataRekamMedis.add(new RekamMedis("RM001", "Rabies", "2024-06-01"));

        Scanner sc = new Scanner(System.in);
        System.out.println("=== SISTEM MANAJEMEN DATA PASIEN === \n1. Lihat Daftar Pasien \n2. Cari Pasien Berdasarkan NIK \n3. Lihat Rekam Medis\n4. Lihat Ruangan");
        int pilihan = sc.nextInt();
        switch (pilihan) {
            case 1 ->
                daftarPasien();
            case 2 ->
                cariPasienNik(sc);
            case 3 -> 
                lihatRekamMedis();
            case 4 ->
                pilihRuangan();              
            default ->
                throw new AssertionError("Pilihan tidak valid.");
        }
    }

    //1
    public static void daftarPasien() {
        System.out.println("\n============= Data Pasien =============");
        for (int i = 0; i < dataPasien.size(); i++) {
            System.out.println("\t### Pasien " + (i+1) + " ###");
            dataPasien.get(i).tampilkanInfo();
        }
        System.out.println("Data pasien baru berhasil disimpan...");
        System.out.println("-----------------------------");
    }

    //2
    public static void cariPasienNik(Scanner sc) {
        System.out.println("\n============= Mencari Pasien Berdasarkan NIK =============");
        System.out.print("\tMasukkan awalan NIK: ");
        String pilihNik = sc.nextLine();
        System.out.println("");

        //Looping per digit nik
        boolean ditemukan = false;
        for (int i = 0; i < dataPasien.size(); i++) {
            if (dataPasien.get(i).getNik().startsWith(pilihNik)) { //Cari NIK yg awalanny angka inputan e.g. input "32" -> cari NIK yang dimulai dengan "32"
                dataPasien.get(i).tampilkanInfo(); 
                ditemukan = true;
                System.out.println("");
            }
        }
        if (!ditemukan){
            System.out.println("Pencarian tidak ditemukan.");
        }
        System.out.println("-----------------------------");
    }


    // 3
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

    // 4
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

    // 4.1
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
