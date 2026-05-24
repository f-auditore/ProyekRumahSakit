package model;

// public abstract class Individu {
//     protected String id;
//     protected String nama;
//     protected int usia;
//     protected String noTelp;

//     public Individu(String id, String nama, int usia, String noTelp){
//         this.id = id;
//         this.nama = nama;
//         this.usia = usia;
//         this.noTelp = noTelp;
//     }
// }

public abstract class Individu {
    protected String nik;
    protected String namaLengkap;
    protected int usia;
    protected String tempatLahir;
    protected String tanggalLahir;
    protected char jenisKelamin;

    public Individu(String nik, String namaLengkap, int usia, String tempatLahir, String tanggalLahir, char jenisKelamin){
        this.nik = nik;
        this.namaLengkap = namaLengkap;
        this.usia = usia;
        this.tempatLahir = tempatLahir;
        this.tanggalLahir = tanggalLahir;
        this.jenisKelamin = jenisKelamin;
    }

    abstract void getPeran();
    abstract void tampilkanInfo();

    public String getNik(){
        return nik;
    }

    public String getNama(){
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap){
        if (namaLengkap != null && !namaLengkap.trim().isEmpty()) {
            this.namaLengkap = namaLengkap;
        }
    }

    public void setNik(String nik){
        if (nik.length() == 16 && nik.matches("\\d+")) { //
            this.nik = nik;
        }
    }
    public void setTempatLahir(String tempatLahir){
        if (tempatLahir != null && !tempatLahir.trim().isEmpty()) {
            this.tempatLahir = tempatLahir;
        }
    }
    public void setTanggalLahir(String tanggalLahir){
        if (tanggalLahir.matches("\\d{2}-\\d{2}-\\d{4}")) {
            this.tanggalLahir = tanggalLahir;
        }
    }
    public void setJenisKelamin(char jenisKelamin){
        this.jenisKelamin = jenisKelamin;
    }
    public void setUsia(int usia){
        if(usia > 0 && usia < 150){

        }
    }
}
