package enums;

public enum StatusDokter {
    STANDBY("Standby"),
    BERTUGAS("Bertugas"),
    OFF("Off");

    private final String label;

    StatusDokter(String label) {
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
