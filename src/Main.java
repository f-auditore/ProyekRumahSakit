
import java.util.ArrayList;
import java.util.Scanner;
import model.Dokter;
import model.DokterSpesialis;
import model.Icu;
import model.Jadwal;
import model.Pasien;
import model.RekamMedis;

public class Main {
    static ArrayList<Pasien> dataPasien = new ArrayList<>();
    static ArrayList<Dokter> dataDokter = new ArrayList<>();
    static ArrayList<Icu> dataICU = new ArrayList<>();
    static ArrayList<Jadwal> dataJadwal = new ArrayList<>();
    static ArrayList<RekamMedis> dataRekamMedis = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        dataDummy();

        Scanner sc = new Scanner(System.in);
        
        while (true) { 
            try {
                System.out.println("=== SISTEM MANAJEMEN DATA PASIEN === \n1. Lihat Daftar Pasien \n2. Cari Pasien Berdasarkan NIK \n3. Update data pasien \n4. Hapus Data Pasien \n5.Lihat Rekam Medis \n6.Pilih Ruangan \n0. Keluar");
                System.out.print("Pilihan: ");
                int pilihan = sc.nextInt();
                sc.nextLine();
                switch (pilihan) {
                    case 1 -> daftarPasien();
                    case 2 -> cariPasienNik(sc);
                    case 3 -> updatePasien();
                    case 4 -> hapusPasien();
                    case 5 -> lihatRekamMedis();
                    case 6 -> pilihRuangan();
                    case 0 -> {
                        System.out.println("Terima Kasih!");
                        System.exit(0);
                    }
                    default -> throw new AssertionError();
                }
            } catch (Exception e) {
                System.out.println("Pilihan tidak valid");
            }
        }
        
    }

    public static void dataDummy(){
        dataPasien.add(new Pasien("3171022804950003", "Rian Santoso", 31, "Jakarta", "28-04-1995", 'L', "081298345712",
                "Hipertensi"));
        dataPasien.add(new Pasien("323456789", "Ani", 19, "Bandung", "12-06-2006", 'P', "0812345", "Diabetes"));
        dataDokter.add(new Dokter("3273052110940005", "dr. Indah Permatasari", 31, "Bandung", "21-10-1994", 'P',
                "085711928344", "DK10293847561029"));
        dataDokter.add(new DokterSpesialis("3171031407880002", "dr. Aris Munandar, Sp.PD", 38, "Yogyakarta",
                "14-07-1988", 'L', "082188456723", "DS30495867120394", "Spesialis Penyakit Dalam", 12, 450,
                enums.StatusDokter.STANDBY));
        dataICU.add(new Icu("ICU001", enums.StatusRuangan.KOSONG, 10, enums.LevelPerawatan.TINGGI));

        Jadwal jadwal1 = new Jadwal(enums.Hari.SENIN, enums.Jam.JAM_08_00, enums.Jam.JAM_12_00,
                (DokterSpesialis) dataDokter.get(1), dataPasien.get(0), dataICU.get(0));
        dataJadwal.add(jadwal1);
        dataRekamMedis.add(new RekamMedis("RM001", "Rabies", jadwal1));

    }

    // 1
    public static void daftarPasien() {
        System.out.println("\n============= Data Pasien =============");
        for (int i = 0; i < dataPasien.size(); i++) {
            System.out.println("\t### Pasien " + (i + 1) + " ###");
            dataPasien.get(i).tampilkanInfo();
        }
        System.out.println("-----------------------------");
    }

    // 2
    public static void cariPasienNik(Scanner sc) {
        System.out.println("\n============= Mencari Pasien Berdasarkan NIK =============");
        System.out.print("\tMasukkan awalan NIK: ");
        String pilihNik = sc.nextLine();
        System.out.println("");
        Pasien.cariPasienNik(dataPasien, pilihNik);
        System.out.println("-----------------------------");
    }

    //3
    public static void updatePasien(){
        boolean ditemukan = false;
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Masukkan NIK Pasien yang Ingin Diupdate Datanya: ");
            String nik = sc.nextLine();

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
            
            Pasien.updatePasien(dataPasien, nik, newNamaLengkap, newUsia,newTempatLahir, newTanggalLahir, newjenisKelamin, newNoTelp, newPenyakit);
            System.out.println("Data pasien berhasil diperbarui...");
            System.out.println("-----------------------------");
        }
        if (!ditemukan) {
            System.out.println("Pencarian tidak ditemukan.");
        }
    }

    //4
    public static void hapusPasien(){
        System.out.println("Data pasien berhasil dihapus...");
        System.out.println("-----------------------------");
    }

    //5
    public static void lihatRekamMedis() {
        if (dataRekamMedis.isEmpty() || (dataRekamMedis.size() == 1 && dataRekamMedis.get(0).getId() == null)) {
            System.out.println("Belum ada data rekam medis yang tersedia.");
        } else {
            System.out.println("============= Data Rekam Medis ============");
            for (RekamMedis rekam : dataRekamMedis) {
                if (rekam.getId() == null || rekam.getId().trim().isEmpty()) {
                    continue;
                }
                rekam.outputInfoRekamMedis();
                System.out.println("-----------------------------");
            }
        }
    }

    //6
    public static void pilihRuangan() {
        System.out.println("Pilih Ruangan: \n1. ICU");
        try (Scanner sc = new Scanner(System.in)) {
            int pilihanRuangan = sc.nextInt();
            switch (pilihanRuangan) {
                case 1 ->
                    lihatRuanganIcu();
                default ->
                    System.out.println("Pilihan ruangan tidak valid.");
            }
        }
    }

    // 4.1
    public static void lihatRuanganIcu() {
        if (dataICU.isEmpty() || (dataICU.size() == 1 && dataICU.get(0).getIdRuangan() == null)) {
            System.out.println("Belum ada data ruangan yang tersedia.");
        } else {
            System.out.println("============= Data Ruangan ICU ============");
            for (Icu icu : dataICU) {
                if (icu.getIdRuangan() == null || icu.getIdRuangan().trim().isEmpty()) {
                    continue;
                }
                icu.outputInfoIcu();
                System.out.println("-----------------------------");
            }
        }
    }
}
