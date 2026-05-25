package model;

public abstract class Individu {
    protected String nik;
    protected String namaLengkap;
    protected int usia;
    protected String tempatLahir;
    protected String tanggalLahir;
    protected char jenisKelamin;
    protected String noTelp;

    public Individu(String nik, String namaLengkap, int usia, String tempatLahir, String tanggalLahir, char jenisKelamin, String noTelp){
        this.nik = nik;
        this.namaLengkap = namaLengkap;
        this.usia = usia;
        this.tempatLahir = tempatLahir;
        this.tanggalLahir = tanggalLahir;
        this.jenisKelamin = jenisKelamin;
        this.noTelp = noTelp;
    }

    abstract String getPeran();
    abstract void tampilkanInfo();
    
    public String getNik(){
        return nik;
    }

    public String getNamaLengkap(){
        return namaLengkap;
    }

    public void setNamaLengkap(String newNamaLengkap){
        if (newNamaLengkap != null && !newNamaLengkap.trim().isEmpty()) {
            this.namaLengkap = newNamaLengkap;
        }
    }

    public void setNik(String newNik){
        if (newNik.length() == 16 && newNik.matches("\\d+")) { 
            this.nik = newNik;
        }
    }
    public void setTempatLahir(String newTempatLahir){
        if (newTempatLahir != null && !newTempatLahir.trim().isEmpty()) {
            this.tempatLahir = newTempatLahir;
        }
    }
    public void setTanggalLahir(String newTanggalLahir){
        if (newTanggalLahir.matches("\\d{2}-\\d{2}-\\d{4}")) {
            this.tanggalLahir = newTanggalLahir;
        }
    }
    public void setJenisKelamin(char newJenisKelamin){
        this.jenisKelamin = newJenisKelamin;
    }
    public void setUsia(int newUsia){
        if(newUsia > 0 && newUsia < 150){
            this.usia = newUsia;
        }
    }

    public void setNoTelp(String newNoTelp){
        if (newNoTelp.matches("\\d{10,12}")) {
            this.noTelp = newNoTelp;
        }
    }
}
