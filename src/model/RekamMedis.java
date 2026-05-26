package model;

import java.util.ArrayList;

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

    public void setId(String id) {
        if (id != null && !id.trim().isEmpty()) {
            this.id = id;
        }
    }

    public String getHasilDiagnosis() {
        return hasilDiagnosis;
    }

    public void setHasilDiagnosis(String hasilDiagnosis) {
        if (hasilDiagnosis != null && !hasilDiagnosis.trim().isEmpty()) {
            this.hasilDiagnosis = hasilDiagnosis;
        }
    }

    public Jadwal getJadwal() {
        return jadwal;
    }

    public void setJadwal(Jadwal jadwal) {
        this.jadwal = jadwal;
    }

    public void tambahRekamMedis(ArrayList<RekamMedis> dataRekamMedis, String id, String hasilDiagnosis, Jadwal jadwal) {
        dataRekamMedis.add(new RekamMedis(id, hasilDiagnosis, jadwal));
    }

    public void updateRekamMedis(ArrayList<RekamMedis> dataRekamMedis, String id, String newHasilDiagnosis, Jadwal newJadwal) {
        for (int i = 0; i < dataRekamMedis.size(); i++) {
            if (dataRekamMedis.get(i).getId().equals(id)) {
                RekamMedis rekamUbah = dataRekamMedis.get(i);
                rekamUbah.setHasilDiagnosis(newHasilDiagnosis);
                rekamUbah.setJadwal(newJadwal);
                break;
            }
        }
    }

    public void hapusRekamMedis(ArrayList<RekamMedis> dataRekamMedis, String id) {
        for (int i = 0; i < dataRekamMedis.size(); i++) {
            if (dataRekamMedis.get(i).getId().equals(id)) {
                dataRekamMedis.remove(i);
                break;
            }
        }
    }

    public void outputInfoRekamMedis() {
        System.out.println("ID Rekam Medis\t\t: " + id);
        System.out.println("Hasil Diagnosis\t\t: " + hasilDiagnosis);
        if (jadwal != null) {
            System.out.println("--- Info Jadwal ---");
            System.out.println(jadwal.toString());
        } else {
            System.out.println("Jadwal\t\t\t: Belum ditentukan");
        }
    }
}
