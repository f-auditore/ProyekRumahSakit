package enums;

public enum StatusRuangan {
    KOSONG("Kosong"),
    TERPAKAI("Terpakai"), 
    MAINTENANCE("Maintenance");

    private final String label;

    StatusRuangan(String label) {
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