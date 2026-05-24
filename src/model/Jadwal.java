package model;

public class Jadwal {

    public enum Hari {
        SENIN("Senin"),
        SELASA("Selasa"),
        RABU("Rabu"),
        KAMIS("Kamis"),
        JUMAT("Jumat"),
        SABTU("Sabtu"),
        MINGGU("Minggu");

        private final String label;

        Hari(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    private Hari hari;
    private Jam jamMulai;
    private Jam jamSelesai;

    // public Jadwal() {}

    public Jadwal(Hari hari, Jam jamMulai, Jam jamSelesai) {
        this.hari = hari;
        this.jamMulai = jamMulai;
        this.jamSelesai = jamSelesai;
    }

    public Hari getHari() {
        return hari;
    }

    public void setHari(Hari hari) {
        this.hari = hari;
    }

    public Jam getJamMulai() {
        return jamMulai;
    }

    public void setJamMulai(Jam jamMulai) {
        this.jamMulai = jamMulai;
    }

    public Jam getJamSelesai() {
        return jamSelesai;
    }

    public void setJamSelesai(Jam jamSelesai) {
        this.jamSelesai = jamSelesai;
    }

    @Override
    public String toString() {
        return hari + " | " + jamMulai + " - " + jamSelesai;
    }
}
