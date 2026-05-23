package model;

public class DokterSpesialis extends Dokter{
    protected String bidang;
    
    public DokterSpesialis(String id, String nama, int usia, String noTelp, String noLisensi, int biaya, String bidang){
        super(id, nama, usia, noTelp, noLisensi, biaya);
        this.bidang = bidang;
    }

}
