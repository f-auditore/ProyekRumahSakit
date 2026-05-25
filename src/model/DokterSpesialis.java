package model;

public class DokterSpesialis extends Dokter{
    protected String spesialis;
    protected int pengalamanTahun;
    protected int jmlhPasien;
    //ruangan, status
    
    public DokterSpesialis(String nik, String namaLengkap, int usia, String tempatLahir, String tanggalLahir, char jenisKelamin, String noTelp, String noLisensi, String spesialis, int pengalamanTahun, int jmlhPasien){
        super(nik, namaLengkap, usia, tempatLahir, tanggalLahir, jenisKelamin, noTelp, noLisensi);
        this.spesialis = spesialis;
        this.pengalamanTahun = pengalamanTahun;
        this.jmlhPasien = jmlhPasien;
    }

    @Override
    public String getPeran(){
        return "Dokter Spesialis";
    }

    @Override
    public void tampilkanInfo(){
        System.out.println("NIK\t: " + nik + "\nNama\t: " + namaLengkap + "\nUsia\t: " + usia + "\nTempat Lahir\t: " + tempatLahir + "\nTanggal Lahir\t: " + tanggalLahir + "\nJenis Kelamin\t: " + jenisKelamin + "\nRiwayat Pendidikan\t: " + spesialis + "\nPengalaman\t: " + pengalamanTahun + "\nJumlah pasien\t: " + jmlhPasien);
    }

}

