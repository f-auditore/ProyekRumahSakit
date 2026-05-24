package model;

public class Dokter extends Individu {
    protected String noLisensi;
    protected String riwayatPendidikan;

    public Dokter(String nik, String namaLengkap, int usia, String tempatLahir, String tanggalLahir, char jenisKelamin, String noTelp, String noLisensi, String riwayatPendidikan){
        super(nik, namaLengkap, usia, tempatLahir, tanggalLahir, jenisKelamin, noTelp);
        this.noLisensi = noLisensi;
        this.riwayatPendidikan = riwayatPendidikan;
    }

    @Override
    public void getPeran(){
        System.out.println("Dokter");
    }

    @Override
    public void tampilkanInfo(){
        System.out.println("NIK\t: " + nik + "\nNama\t: " + namaLengkap + "\nUsia\t: " + usia + "\nTempat Lahir\t: " + tempatLahir + "\nTanggal Lahir\t" + tanggalLahir +  "\nJenis Kelamin\t: " + jenisKelamin + "\nRiwayat Pendidikan\t: " + riwayatPendidikan);
    }
}
