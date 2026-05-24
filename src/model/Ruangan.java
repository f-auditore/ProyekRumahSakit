package model;

public abstract class Ruangan {
    protected String idRuangan;
    protected String status;
    DokterSpesialis spesialis;
    protected String jadwal;

    public Ruangan(String idRuangan, String status, DokterSpesialis spesialis, String jadwal){
        this.idRuangan = idRuangan;
        this.status = status;
        this.spesialis = spesialis;
        this.jadwal = jadwal;
    }

    public String getIdRuangan() {
        return idRuangan;
    }
    public void getStatus() {
        System.out.println("Status: " + status);
    }

    public void getJadwalDanStatus() {
        System.out.println("Status: " + status);
        System.out.println("Jadwal: " + jadwal);
    }


    public void outputInfoRuangan(){
        System.out.println("ID Ruangan: " + idRuangan);
        System.out.println("Status: " + status);
        if (spesialis != null) {
            System.out.println("Dokter Spesialis: " + spesialis.namaLengkap);
        } else {
            System.out.println("Dokter Spesialis: Tidak ada");
        }
        System.out.println("Jadwal: " + jadwal);
    }

    public abstract void updateStatus(String status);
    public abstract void updateJadwal(String jadwal);
    public abstract void hapusRuangan();
}
