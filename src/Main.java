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

    public void main(String[] args) throws Exception {

        dataDummy();

        Scanner sc = new Scanner(System.in);
        
        while (true) { 
            try {
                System.out.println("=== SISTEM MANAJEMEN DATA PASIEN ===");
                System.out.println("1. Lihat Daftar Pasien");
                System.out.println("2. Cari Pasien Berdasarkan NIK");
                System.out.println("3. Update data pasien");
                System.out.println("4. Hapus Data Pasien");
                System.out.println("5. Tambah Pasien");
                System.out.println("6. Lihat Rekam Medis");
                System.out.println("7. Pilih Ruangan");
                System.out.println("0. Keluar");

                System.out.print("Pilihan: ");
                int pilihan = Integer.parseInt(sc.nextLine());
                switch (pilihan) {
                    case 1 -> daftarPasien();
                    case 2 -> cariPasienNik(sc);
                    case 3 -> updatePasien(sc);
                    case 4 -> hapusPasien(sc);
                    case 5 -> tambahPasien(sc);
                    case 6 -> lihatRekamMedis();
                    case 7 -> pilihRuangan(sc);
                    case 0 -> {
                        System.out.println("Terima Kasih!");
                        System.exit(0);
                    }
                    default -> throw new AssertionError();
                }
            } catch (NumberFormatException e) {
                System.out.println("Pilihan tidak valid");
            }
        }
        
    }

    public static void dataDummy(){
        dataPasien.add(new Pasien("3171022804950003", "Rian Santoso", 31, "Jakarta", "28-04-1995", 'L', "081298345712",
                "Hipertensi"));
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
        if (dataPasien.isEmpty()) {
            System.out.println("Belum ada data pasien.");
        } else {
            for (int i = 0; i < dataPasien.size(); i++) {
            System.out.println("\t### Pasien " + (i + 1) + " ###");
            dataPasien.get(i).tampilkanInfo();
            }   
        }
        
        
        System.out.println("-----------------------------");
    }
    //2
    public static void cariPasienNik(Scanner sc){
        System.out.println("\n============= Mencari Pasien Berdasarkan NIK =============");
        Pasien.cariPasienNik(dataPasien, sc);
        System.out.println("-----------------------------");
    }

    //3
    public static void updatePasien(Scanner sc){
        System.out.println("\n============= Memperbarui Data Pasien =============");
        Pasien.updatePasien(dataPasien, sc);
        System.out.println("-----------------------------");
    }

    //4
    public static void hapusPasien(Scanner sc){
        System.out.println("\n============= Menghapus Data Pasien =============");
        Pasien.hapusPasien(dataPasien, sc);
        System.out.println("-----------------------------");
        
    }

    //5
    public void tambahPasien(Scanner sc) {
        System.out.println("\n============= Menambah Data Pasien Baru =============");
        Pasien.tambahPasien(dataPasien, sc);
        System.out.println("-----------------------------");
    }

    //6
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
    public static void pilihRuangan(Scanner sc) {
        System.out.println("Pilih Ruangan: \n1. ICU");
            int pilihanRuangan = sc.nextInt();
            sc.nextLine();
            switch (pilihanRuangan) {
                case 1 ->
                    lihatRuanganIcu();
                default ->
                    System.out.println("Pilihan ruangan tidak valid.");
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
