package model;

public abstract class Individu {
    protected String id;
    protected String nama;
    protected int usia;
    protected String noTelp;

    public Individu(String id, String nama, int usia, String noTelp){
        this.id = id;
        this.nama = nama;
        this.usia = usia;
        this.noTelp = noTelp;
    }
}
