package model;

public class Pasien extends Individu {
    protected String golDarah;
    protected String riwayatPenyakit;
    protected String statusRawat;
    
    public Pasien(String id, String nama, int usia, String nomorTelepon, String golDarah, String riwayatPenyakit, String statusRawat){
        super(id, nama, usia, nomorTelepon);
        this.golDarah = golDarah;
        this.riwayatPenyakit = riwayatPenyakit;
        this.statusRawat = statusRawat;
    }

    public String getNama(){
        return nama;
    }

    


}
