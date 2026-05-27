package enums;

public enum Jam {
    JAM_00_00("00:00"), JAM_01_00("01:00"), JAM_02_00("02:00"), JAM_03_00("03:00"),
    JAM_04_00("04:00"), JAM_05_00("05:00"), JAM_06_00("06:00"), JAM_07_00("07:00"),
    JAM_08_00("08:00"), JAM_09_00("09:00"), JAM_10_00("10:00"), JAM_11_00("11:00"),
    JAM_12_00("12:00"), JAM_13_00("13:00"), JAM_14_00("14:00"), JAM_15_00("15:00"),
    JAM_16_00("16:00"), JAM_17_00("17:00"), JAM_18_00("18:00"), JAM_19_00("19:00"),
    JAM_20_00("20:00"), JAM_21_00("21:00"), JAM_22_00("22:00"), JAM_23_00("23:00");

    private final String label;

    Jam(String label) {
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
