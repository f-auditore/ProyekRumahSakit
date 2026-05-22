package model;

public class Ugd extends Ruangan {
    private String statusPasien;
    private String statusDokter;

    public Ugd(String idRuangan, String status, DokterSpesialis spesialis, String jadwal, String statusPasien, String statusDokter) {
        super(idRuangan, status, spesialis, jadwal);
        this.statusPasien = statusPasien;
        this.statusDokter = statusDokter;
    }

    public void updateStatusPasien(String statusPasien) {
        if (statusPasien != null && !statusPasien.trim().isEmpty()) {
            this.statusPasien = statusPasien;
        }
    }

    public void updateStatusDokter(String statusDokter) {
        if (statusDokter != null && !statusDokter.trim().isEmpty()) {
            this.statusDokter = statusDokter;
        }
    }

    public String getStatusPasien() {
        return statusPasien;
    }
    public String getStatusDokter() {
        return statusDokter;
    }

    public void outputInfoUgd() {
        outputInfoRuangan();
        System.out.println("Status Pasien: " + statusPasien);
        System.out.println("Status Dokter: " + statusDokter);
    }
    

    @Override
    public void updateStatus(String status) {
        if (status != null && !status.trim().isEmpty()) {
            this.status = status;
        }
    }

    @Override
    public void updateJadwal(String jadwal) {
        if (jadwal != null && !jadwal.trim().isEmpty()) {
            this.jadwal = jadwal;
        }
    }

    @Override
    public void hapusRuangan() {
        this.idRuangan = null;
        this.status = null;
        this.spesialis = null;
        this.jadwal = null;
        this.statusPasien = null;
        this.statusDokter = null;
    }
    
}
