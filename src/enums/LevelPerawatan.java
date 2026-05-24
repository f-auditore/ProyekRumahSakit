package enums;

public enum LevelPerawatan {
    INTENSIF("Intensif"),
    TINGGI("Tinggi");

    private final String label;

    LevelPerawatan(String label) {
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
