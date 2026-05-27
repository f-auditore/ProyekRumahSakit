
import enums.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.*;

public class Main extends JFrame {

    private static final ArrayList<Pasien> dataPasien = new ArrayList<>();
    private static final ArrayList<Dokter> dataDokter = new ArrayList<>();
    private static final ArrayList<Icu> dataICU = new ArrayList<>();
    private static final ArrayList<Jadwal> dataJadwal = new ArrayList<>();
    private static final ArrayList<RekamMedis> dataRekamMedis = new ArrayList<>();

    private JComboBox<String> cbKategori;
    private JTable mainTable;
    private DefaultTableModel[] models;
    private CardLayout cardLayout;
    private JPanel formCards, btnPanel;
    private JButton btnTambah, btnUpdate, btnHapus, btnCari, btnReset;

    private JTextField txtPasienNik, txtPasienNama, txtPasienUsia, txtPasienTempat, txtPasienTanggal, txtPasienTelp, txtPasienPenyakit, txtPasienCariNik;
    private JComboBox<JenisKelamin> cbPasienJk;
    private JTextField txtIcuId, txtIcuKapasitas, txtRmId, txtRmDiagnosis;
    private JComboBox<StatusRuangan> cbIcuStatus;
    private JComboBox<LevelPerawatan> cbIcuLevel;
    private JComboBox<Hari> cbJadwalHari;
    private JComboBox<Jam> cbJadwalMulai, cbJadwalSelesai;
    private JComboBox<String> cbJadwalDokter, cbJadwalPasien, cbJadwalRuangan, cbRmJadwal;
    private JTextField txtIdJadwal;

    private final String[] kats = {"Manajemen Pasien", "Daftar Dokter (View Only)", "Ruangan ICU", "Jadwal Kontrol", "Rekam Medis"};

    public Main() {
        setTitle("Dashboard Rumah Sakit Terpadu");
        setSize(1200, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initDummyData();
        initModels();
        initComponents();
        initEvents();
        syncUI();
    }

    private void initModels() {
        models = new DefaultTableModel[]{
            new DefaultTableModel(new String[]{"NIK", "Nama", "Usia", "Tempat", "Tanggal", "Jenis Kelamin", "Telepon", "Penyakit"}, 0),
            new DefaultTableModel(new String[]{"NIK", "Nama Dokter", "Peran/Pangkat", "Detail Spesialisasi"}, 0),
            new DefaultTableModel(new String[]{"ID Ruangan", "Status", "Kapasitas Bed", "Level Perawatan"}, 0),
            new DefaultTableModel(new String[]{"ID Jadwal", "Hari", "Mulai", "Selesai", "Dokter", "Pasien", "Ruangan"}, 0),
            new DefaultTableModel(new String[]{"ID Rekam Medis", "Hasil Diagnosis", "Waktu Pelaksanaan"}, 0)
        };
    }

    private void initComponents() {
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        northPanel.add(new JLabel("Pilih Menu Kategori:"));
        northPanel.add(cbKategori = new JComboBox<>(kats));
        northPanel.add(new JLabel("  |  Cari NIK Pasien:"));
        northPanel.add(txtPasienCariNik = new JTextField(12));
        northPanel.add(btnCari = new JButton("Cari"));
        northPanel.add(btnReset = new JButton("Reset"));

        cardLayout = new CardLayout();
        formCards = new JPanel(cardLayout);

        JPanel pPasien = new JPanel(new GridLayout(8, 2, 4, 4));
        txtPasienNik = createField(pPasien, "NIK:");
        txtPasienNama = createField(pPasien, "Nama:");
        txtPasienUsia = createField(pPasien, "Usia:");
        txtPasienTempat = createField(pPasien, "Tempat Lahir:");
        txtPasienTanggal = createField(pPasien, "Tgl Lahir:");
        cbPasienJk = createCombo(pPasien, "Jenis Kelamin:", JenisKelamin.values());
        txtPasienTelp = createField(pPasien, "No Telp:");
        txtPasienPenyakit = createField(pPasien, "Penyakit:");

        JPanel pIcu = new JPanel(new GridLayout(4, 2, 4, 4));
        txtIcuId = createField(pIcu, "ID ICU:");
        cbIcuStatus = createCombo(pIcu, "Status:", StatusRuangan.values());
        txtIcuKapasitas = createField(pIcu, "Kapasitas Bed:");
        cbIcuLevel = createCombo(pIcu, "Level:", LevelPerawatan.values());

        JPanel pJadwal = new JPanel(new GridLayout(7, 2, 4, 4));
        txtIdJadwal = createField(pJadwal, "ID-Jadwal:");
        cbJadwalHari = createCombo(pJadwal, "Hari:", Hari.values());
        cbJadwalMulai = createCombo(pJadwal, "Mulai:", Jam.values());
        cbJadwalSelesai = createCombo(pJadwal, "Selesai:", Jam.values());
        cbJadwalDokter = createCombo(pJadwal, "Dokter Spec:", null);
        cbJadwalPasien = createCombo(pJadwal, "Pasien:", null);
        cbJadwalRuangan = createCombo(pJadwal, "Ruangan ICU:", null);

        JPanel pRm = new JPanel(new GridLayout(3, 2, 4, 4));
        txtRmId = createField(pRm, "ID Rekam Medis:");
        txtRmDiagnosis = createField(pRm, "Diagnosis:");
        cbRmJadwal = createCombo(pRm, "Acuan Jadwal:", null);

        formCards.add(pPasien, kats[0]);
        formCards.add(new JLabel("Tidak ada input untuk data Dokter."), kats[1]);
        formCards.add(pIcu, kats[2]);
        formCards.add(pJadwal, kats[3]);
        formCards.add(pRm, kats[4]);

        JPanel leftPanel = new JPanel(new BorderLayout(10, 10));
        leftPanel.setBorder(BorderFactory.createTitledBorder(" Form Input "));
        leftPanel.setPreferredSize(new Dimension(340, 0));
        leftPanel.add(formCards, BorderLayout.CENTER);

        btnPanel = new JPanel(new FlowLayout());
        btnPanel.add(btnTambah = new JButton("Simpan"));
        btnPanel.add(btnUpdate = new JButton("Update"));
        btnPanel.add(btnHapus = new JButton("Hapus"));
        leftPanel.add(btnPanel, BorderLayout.SOUTH);

        mainTable = new JTable(models[0]);
        JScrollPane scrollTable = new JScrollPane(mainTable);
        scrollTable.setBorder(BorderFactory.createTitledBorder(" Lembar Data Informasi "));

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(scrollTable, BorderLayout.CENTER);
        add(mainPanel);
    }

    private void initEvents() {
        cbKategori.addActionListener(e -> {
            int idx = cbKategori.getSelectedIndex();
            cardLayout.show(formCards, kats[idx]);
            mainTable.setModel(models[idx]);
            btnTambah.setVisible(idx != 1 && idx != 2);
            btnPanel.revalidate();
            btnPanel.repaint();
        });

        mainTable.getSelectionModel().addListSelectionListener(e -> handleTableSelection());

        btnTambah.addActionListener(e -> handleTambah());
        btnUpdate.addActionListener(e -> handleUpdate());
        btnHapus.addActionListener(e -> handleHapus());

        btnCari.addActionListener(e -> {
            models[0].setRowCount(0);
            dataPasien.stream()
                    .filter(p -> p.getNik().startsWith(txtPasienCariNik.getText().trim()))
                    .forEach(p -> models[0].addRow(new Object[]{p.getNik(), p.getNamaLengkap(), p.getUsia(), p.getTempatLahir(), p.getTanggalLahir(), p.getJenisKelamin(), p.getNoTelp(), p.getPenyakit()}));
        });
        btnReset.addActionListener(e -> syncUI());
    }

    private void handleTableSelection() {
        int row = mainTable.getSelectedRow();
        if (row < 0) {
            return;
        }

        switch (cbKategori.getSelectedIndex()) {
            case 0 -> {
                txtPasienNik.setText(mainTable.getValueAt(row, 0).toString());
                txtPasienNama.setText(mainTable.getValueAt(row, 1).toString());
                txtPasienUsia.setText(mainTable.getValueAt(row, 2).toString());
                txtPasienTempat.setText(mainTable.getValueAt(row, 3).toString());
                txtPasienTanggal.setText(mainTable.getValueAt(row, 4).toString());
                cbPasienJk.setSelectedItem(mainTable.getValueAt(row, 5));
                txtPasienTelp.setText(mainTable.getValueAt(row, 6).toString());
                txtPasienPenyakit.setText(mainTable.getValueAt(row, 7).toString());
            }
            case 2 -> {
                txtIcuId.setText(mainTable.getValueAt(row, 0).toString());
                cbIcuStatus.setSelectedItem(mainTable.getValueAt(row, 1));
                txtIcuKapasitas.setText(mainTable.getValueAt(row, 2).toString());
                cbIcuLevel.setSelectedItem(mainTable.getValueAt(row, 3));
            }
            case 3 -> {
                txtIdJadwal.setText(mainTable.getValueAt(row, 0).toString());
                cbJadwalHari.setSelectedItem(mainTable.getValueAt(row, 1));
                cbJadwalMulai.setSelectedItem(mainTable.getValueAt(row, 2));
                cbJadwalSelesai.setSelectedItem(mainTable.getValueAt(row, 3));
                cbJadwalDokter.setSelectedItem(mainTable.getValueAt(row, 4));
                cbJadwalPasien.setSelectedItem(mainTable.getValueAt(row, 5));
                cbJadwalRuangan.setSelectedItem(mainTable.getValueAt(row, 6));
            }
            case 4 -> {
                txtRmId.setText(mainTable.getValueAt(row, 0).toString());
                txtRmDiagnosis.setText(mainTable.getValueAt(row, 1).toString());
                if (row >= 0 && row < dataRekamMedis.size()) {
                    RekamMedis rm = dataRekamMedis.get(row);
                    Jadwal j = rm.getJadwal();
                    if (j != null) {
                        String targetItem = j.getHari() + " | " + (j.getDokter() != null ? j.getDokter().getNamaLengkap() : "") + " -> " + (j.getPasien() != null ? j.getPasien().getNamaLengkap() : "");
                        cbRmJadwal.setSelectedItem(targetItem);
                    } else {
                        cbRmJadwal.setSelectedIndex(-1);
                    }
                }
            }
        }
    }

    private void handleTambah() {
        try {
            switch (cbKategori.getSelectedIndex()) {
                case 0 ->
                    dataPasien.add(new Pasien(txtPasienNik.getText(), txtPasienNama.getText(), Integer.parseInt(txtPasienUsia.getText()), txtPasienTempat.getText(), txtPasienTanggal.getText(), (JenisKelamin) cbPasienJk.getSelectedItem(), txtPasienTelp.getText(), txtPasienPenyakit.getText()));
                case 3 -> {
                    String selectedDoc = (String) cbJadwalDokter.getSelectedItem();
                    DokterSpesialis ds = dataDokter.stream()
                            .filter(d -> d instanceof DokterSpesialis && d.getNamaLengkap().equals(selectedDoc))
                            .map(d -> (DokterSpesialis) d)
                            .findFirst().orElse(null);
                    // String newId = "JDW-" + String.format("%03d", dataJadwal.size() + 1);
                    dataJadwal.add(new Jadwal( txtIdJadwal.getText(), (Hari) cbJadwalHari.getSelectedItem(), (Jam) cbJadwalMulai.getSelectedItem(), (Jam) cbJadwalSelesai.getSelectedItem(), ds, dataPasien.get(cbJadwalPasien.getSelectedIndex()), dataICU.get(cbJadwalRuangan.getSelectedIndex())));
                }
                case 4 ->
                    dataRekamMedis.add(new RekamMedis(txtRmId.getText(), txtRmDiagnosis.getText(), dataJadwal.get(cbRmJadwal.getSelectedIndex())));
            }
            syncUI();
            clearAllFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Input tidak valid / data relasi kosong.");
        }
    }

    private void handleUpdate() {
        int row = mainTable.getSelectedRow();
        if (row < 0) {
            return;
        }
        String key = mainTable.getValueAt(row, 0).toString();

        try {
            switch (cbKategori.getSelectedIndex()) {
                case 0 ->
                    dataPasien.stream().filter(p -> p.getNik().equals(key)).findFirst().ifPresent(p -> {
                        p.setNamaLengkap(txtPasienNama.getText());
                        p.setUsia(Integer.parseInt(txtPasienUsia.getText()));
                        p.setTempatLahir(txtPasienTempat.getText());
                        p.setTanggalLahir(txtPasienTanggal.getText());
                        p.setJenisKelamin((JenisKelamin) cbPasienJk.getSelectedItem());
                        p.setNoTelp(txtPasienTelp.getText());
                        p.setPenyakit(txtPasienPenyakit.getText());
                    });
                case 2 ->
                    dataICU.stream().filter(icu -> icu.getIdRuangan().equals(key)).findFirst().ifPresent(icu -> {
                        icu.setStatus((StatusRuangan) cbIcuStatus.getSelectedItem());
                        icu.setKapasitasBed(Integer.parseInt(txtIcuKapasitas.getText()));
                        icu.setLevelPerawatan((LevelPerawatan) cbIcuLevel.getSelectedItem());
                    });
                case 3 -> dataJadwal.stream().filter(jadwal -> jadwal.getIdJadwal().equals(key)).findFirst().ifPresent(jadwal -> {
                        jadwal.setIdJadwal(txtIdJadwal.getText());
                        jadwal.setHari((Hari) cbJadwalHari.getSelectedItem());
                        jadwal.setJamMulai((Jam) cbJadwalMulai.getSelectedItem());
                        jadwal.setJamSelesai((Jam) cbJadwalSelesai.getSelectedItem());
                        // jadwal.setDokter((DokterSpesialis) cbJadwalDokter.getSelectedItem());
                        // jadwal.setPasien((Pasien) cbJadwalPasien.getSelectedItem());
                        // jadwal.setRuangan((Icu) cbJadwalRuangan.getSelectedItem());
                        String selectedDoc = (String) cbJadwalDokter.getSelectedItem();
                        DokterSpesialis ds = dataDokter.stream()
                                .filter(d -> d instanceof DokterSpesialis && d.getNamaLengkap().equals(selectedDoc))
                                .map(d -> (DokterSpesialis) d)
                                .findFirst().orElse(null);
                        jadwal.setDokter(ds);

                        int pIdx = cbJadwalPasien.getSelectedIndex();
                        if (pIdx >= 0 && pIdx < dataPasien.size()) {
                            jadwal.setPasien(dataPasien.get(pIdx));
                        }
                        
                        int rIdx = cbJadwalRuangan.getSelectedIndex();
                        if (rIdx >= 0 && rIdx < dataICU.size()) {
                            jadwal.setRuangan(dataICU.get(rIdx));
                        }
                    });
                case 4 -> dataRekamMedis.stream().filter(rekamMedis -> rekamMedis.getId().equals(key)).findFirst().ifPresent(rekamMedis -> {
                    rekamMedis.setId(txtRmId.getText());
                    rekamMedis.setHasilDiagnosis(txtRmDiagnosis.getText());
                    int selectedJadwalIndex = cbRmJadwal.getSelectedIndex();
                    if (selectedJadwalIndex >= 0 && selectedJadwalIndex < dataJadwal.size()) {
                        rekamMedis.setJadwal(dataJadwal.get(selectedJadwalIndex));
                    } else {
                        rekamMedis.setJadwal(null);
                    }
                });
            }
            syncUI();
            clearAllFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Gagal memperbarui data. Pastikan format benar.");
        }
    }

    private void handleHapus() {
        int row = mainTable.getSelectedRow();
        if (row < 0) {
            return;
        }
        String key = mainTable.getValueAt(row, 0).toString();

        switch (cbKategori.getSelectedIndex()) {
            case 0 ->
                dataPasien.removeIf(p -> p.getNik().equals(key));
            case 2 ->
                dataICU.removeIf(icu -> icu.getIdRuangan().equals(key));
            case 3 ->
                dataJadwal.remove(row);
            case 4 ->
                dataRekamMedis.remove(row);
        }
        syncUI();
        clearAllFields();
    }

    private void syncUI() {
        for (DefaultTableModel model : models) {
            model.setRowCount(0);
        }

        dataPasien.forEach(p -> models[0].addRow(new Object[]{p.getNik(), p.getNamaLengkap(), p.getUsia(), p.getTempatLahir(), p.getTanggalLahir(), p.getJenisKelamin(), p.getNoTelp(), p.getPenyakit()}));
        dataDokter.forEach(d -> models[1].addRow(new Object[]{d.getNik(), d.getNamaLengkap(), d.getPeran(), d instanceof DokterSpesialis ds ? ds.getSpesialis() : "Lisensi: " + d.getNoLisensi()}));
        dataICU.forEach(i -> models[2].addRow(new Object[]{i.getIdRuangan(), i.getStatus(), i.getKapasitasBed(), i.getLevelPerawatan()}));
        dataJadwal.forEach(j -> models[3].addRow(new Object[]{j.getIdJadwal(), j.getHari(), j.getJamMulai(), j.getJamSelesai(), j.getDokter() != null ? j.getDokter().getNamaLengkap() : "-", j.getPasien() != null ? j.getPasien().getNamaLengkap() : "-", j.getRuangan() != null ? j.getRuangan().getIdRuangan() : "-"}));
        dataRekamMedis.forEach(rm -> models[4].addRow(new Object[]{rm.getId(), rm.getHasilDiagnosis(), rm.getJadwal() != null ? rm.getJadwal().getHari() + " [" + rm.getJadwal().getJamMulai() + "]" : "-"}));

        cbJadwalDokter.removeAllItems();
        dataDokter.stream().filter(d -> d instanceof DokterSpesialis).forEach(d -> cbJadwalDokter.addItem(d.getNamaLengkap()));

        cbJadwalPasien.removeAllItems();
        dataPasien.forEach(p -> cbJadwalPasien.addItem(p.getNamaLengkap()));

        cbJadwalRuangan.removeAllItems();
        dataICU.forEach(i -> cbJadwalRuangan.addItem(i.getIdRuangan()));

        cbRmJadwal.removeAllItems();
        dataJadwal.forEach(j -> cbRmJadwal.addItem(j.getHari() + " | " + (j.getDokter() != null ? j.getDokter().getNamaLengkap() : "") + " -> " + (j.getPasien() != null ? j.getPasien().getNamaLengkap() : "")));
    }

    private JTextField createField(JPanel p, String lbl) {
        p.add(new JLabel(lbl));
        JTextField tf = new JTextField();
        p.add(tf);
        return tf;
    }

    private <T> JComboBox<T> createCombo(JPanel p, String lbl, T[] items) {
        p.add(new JLabel(lbl));
        JComboBox<T> cb = items != null ? new JComboBox<>(items) : new JComboBox<>();
        p.add(cb);
        return cb;
    }

    private void clearAllFields() {
        JTextField[] fields = {txtPasienNik, txtPasienNama, txtPasienUsia, txtPasienTempat, txtPasienTanggal, txtPasienTelp, txtPasienPenyakit, txtIcuId, txtIcuKapasitas, txtRmId, txtRmDiagnosis};
        for (JTextField f : fields) {
            if (f != null) {
                f.setText("");
            }
        }
    }

    private void initDummyData() {
        if (dataPasien.isEmpty()) {
            dataPasien.add(new Pasien("3171022804950003", "Rian Santoso", 31, "Jakarta", "28-04-1995", JenisKelamin.LAKI_LAKI, "081298345712", "Hipertensi"));
            dataPasien.add(new Pasien("3515041203990001", "Siti Aminah", 27, "Surabaya", "12-03-1999", JenisKelamin.PEREMPUAN, "081345678901", "Diabetes Melitus"));
            dataPasien.add(new Pasien("5201040508810002", "Budi Darmawan", 45, "Mataram", "05-08-1981", JenisKelamin.LAKI_LAKI, "087864123456", "Asma Kronis"));
            dataPasien.add(new Pasien("3273014509920004", "Dewi Lestari", 34, "Bandung", "15-09-1992", JenisKelamin.PEREMPUAN, "082112345678", "Demam Berdarah"));

            dataDokter.add(new Dokter("3273052110940005", "dr. Indah Permatasari", 31, "Bandung", "21-10-1994", JenisKelamin.PEREMPUAN, "085711928344", "DK10293847561029"));

            DokterSpesialis ds1 = new DokterSpesialis("3171031407880002", "dr. Aris Munandar, Sp.PD", 38, "Yogyakarta", "14-07-1988", JenisKelamin.LAKI_LAKI, "082188456723", "DS30495867120394", "Spesialis Penyakit Dalam", 12, 450);
            DokterSpesialis ds2 = new DokterSpesialis("5201032211850001", "dr. Ahmad Fauzi, Sp.A", 40, "Mataram", "22-11-1985", JenisKelamin.LAKI_LAKI, "081907123456", "DS50695867120300", "Spesialis Anak", 15, 500);
            DokterSpesialis ds3 = new DokterSpesialis("3578021101890003", "dr. Citra Amelia, Sp.JP", 37, "Surabaya", "11-01-1989", JenisKelamin.PEREMPUAN, "081234432112", "DS70815867120311", "Spesialis Jantung", 10, 600);

            dataDokter.add(ds1);
            dataDokter.add(ds2);
            dataDokter.add(ds3);

            dataICU.add(new Icu("ICU-01", StatusRuangan.KOSONG, 10, LevelPerawatan.TINGGI));
            dataICU.add(new Icu("ICU-02", StatusRuangan.values().length > 1 ? StatusRuangan.values()[1] : StatusRuangan.KOSONG, 5, LevelPerawatan.values().length > 1 ? LevelPerawatan.values()[1] : LevelPerawatan.TINGGI));
            dataICU.add(new Icu("ICU-03", StatusRuangan.KOSONG, 8, LevelPerawatan.TINGGI));
            dataICU.add(new Icu("ICU-04", StatusRuangan.values().length > 1 ? StatusRuangan.values()[1] : StatusRuangan.KOSONG, 12, LevelPerawatan.values().length > 1 ? LevelPerawatan.values()[1] : LevelPerawatan.TINGGI));

            Jadwal j1 = new Jadwal("JDW-001", Hari.SENIN, Jam.JAM_08_00, Jam.values().length > 2 ? Jam.values()[2] : Jam.JAM_12_00, ds1, dataPasien.get(0), dataICU.get(0));
            Jadwal j2 = new Jadwal("JDW-002", Hari.values().length > 1 ? Hari.values()[1] : Hari.SENIN, Jam.JAM_08_00, Jam.values().length > 2 ? Jam.values()[2] : Jam.JAM_12_00, ds2, dataPasien.get(1), dataICU.get(1));
            Jadwal j3 = new Jadwal("JDW-003", Hari.SENIN, Jam.values().length > 2 ? Jam.values()[2] : Jam.JAM_12_00, Jam.values()[Jam.values().length - 1], ds3, dataPasien.get(2), dataICU.get(2));

            dataJadwal.add(j1);
            dataJadwal.add(j2);
            dataJadwal.add(j3);

            dataRekamMedis.add(new RekamMedis("RM-001", "Hipertensi Essensial Stadium 1", j1));
            dataRekamMedis.add(new RekamMedis("RM-002", "Gula Darah Puasa Tinggi", j2));
            dataRekamMedis.add(new RekamMedis("RM-003", "Gejala Asma Bronkial Akut", j3));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}