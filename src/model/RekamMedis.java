package model;

public class RekamMedis {

    protected String id;
    protected String hasilDiagnosis;
    protected Jadwal jadwal;

    public RekamMedis(String id, String hasilDiagnosis, Jadwal jadwal) {
        this.id = id;
        this.hasilDiagnosis = hasilDiagnosis;
        this.jadwal = jadwal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getHasilDiagnosis() {
        return hasilDiagnosis;
    }

    public void setHasilDiagnosis(String hasilDiagnosis) {
        this.hasilDiagnosis = hasilDiagnosis;
    }

    public Jadwal getJadwal() {
        return jadwal;
    }

    public void setJadwal(Jadwal jadwal) {
        this.jadwal = jadwal;
    }
}
