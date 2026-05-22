package model;

public class Dokter extends Individu {
    protected String noLisensi;
    protected int biaya;

    public Dokter(String id, String nama, int usia, String noTelp, String noLisensi, int biaya){
        super(id, nama, usia, noTelp);
        this.noLisensi = noLisensi;
        this.biaya = biaya;
    }
}
