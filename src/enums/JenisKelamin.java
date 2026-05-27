package enums;

public enum JenisKelamin {
    LAKI_LAKI("Laki-laki"),
    PEREMPUAN("Perempuan");

    private final String label;

    JenisKelamin(String label) {
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