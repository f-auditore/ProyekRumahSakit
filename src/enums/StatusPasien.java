package enums;

public enum StatusPasien {
    KOSONG("Kosong"),
    ADA("Ada"),
    KRITIS("Kritis");

    private final String label;

    StatusPasien(String label) {
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
