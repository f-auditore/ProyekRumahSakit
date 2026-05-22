package model;

public class RekamMedis {
    protected String id;
    protected String diagnosis;
    protected String tanggalKunjungan;

    public RekamMedis(String id, String diagnosis, String tanggalKunjungan) {
        this.id = id;
        this.diagnosis = diagnosis;
        this.tanggalKunjungan = tanggalKunjungan;
    }

    public String getId() {
        return id;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getTanggalKunjungan() {
        return tanggalKunjungan;
    }

    public void uotputInfoRekamMedis() {
        System.out.println("ID Rekam Medis: " + id);
        System.out.println("Diagnosis: " + diagnosis);
        System.out.println("Tanggal Kunjungan: " + tanggalKunjungan);
    }

    public void updateDiagnosis(String diagnosis) {
        if (diagnosis != null && !diagnosis.trim().isEmpty()) {
            this.diagnosis = diagnosis;
        }
    }

    public void updateTanggalKunjungan(String tanggalKunjungan) {
        if (tanggalKunjungan != null && !tanggalKunjungan.trim().isEmpty()) {
            this.tanggalKunjungan = tanggalKunjungan;
        }
    }
}
