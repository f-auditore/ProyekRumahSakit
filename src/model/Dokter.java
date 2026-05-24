package model;

public class Dokter extends Individu {
    protected String noLisensi;

    public Dokter(String nik, String namaLengkap, int usia, String tempatLahir, String tanggalLahir, char jenisKelamin, String noTelp, String noLisensi){
        super(nik, namaLengkap, usia, tempatLahir, tanggalLahir, jenisKelamin, noTelp);
        this.noLisensi = noLisensi;
    }

    public void setNoLisensi(String noLisensi){
        if (noLisensi.matches("\\d{10,13}")) {
            this.noLisensi = noLisensi;
        }
    }

    @Override
    public void getPeran(){
        System.out.println("Dokter");
    }

    @Override
    public void tampilkanInfo(){
        System.out.println("NIK\t: " + nik + "\nNama\t: " + namaLengkap + "\nUsia\t: " + usia + "\nTempat Lahir\t: " + tempatLahir + "\nTanggal Lahir\t" + tanggalLahir +  "\nJenis Kelamin\t: " + jenisKelamin);
    }
}
