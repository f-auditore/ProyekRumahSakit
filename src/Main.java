
import enums.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.*;

public class Main extends JFrame {

    // Repository Data Utama
    private static final ArrayList<Pasien> dataPasien = new ArrayList<>();
    private static final ArrayList<Dokter> dataDokter = new ArrayList<>();
    private static final ArrayList<Icu> dataICU = new ArrayList<>();
    private static final ArrayList<Jadwal> dataJadwal = new ArrayList<>();

    // Komponen UI Utama
    private JComboBox<String> cbKategori;
    private JTable mainTable;
    private DefaultTableModel[] models;
    private CardLayout cardLayout;
    private JPanel formCards, btnPanel;
    private JButton btnTambah, btnUpdate, btnHapus, btnCari, btnReset;

    // Form Input Fields
    private JTextField txtPasienNik, txtPasienNama, txtPasienUsia, txtPasienTempat, txtPasienTanggal, txtPasienTelp, txtPasienPenyakit, txtPasienCariNik;
    private JComboBox<JenisKelamin> cbPasienJk;
    private JTextField txtIcuId, txtIcuKapasitas;
    private JComboBox<StatusRuangan> cbIcuStatus;
    private JComboBox<LevelPerawatan> cbIcuLevel;
    private JComboBox<Hari> cbJadwalHari;
    private JComboBox<Jam> cbJadwalMulai, cbJadwalSelesai;
    private JComboBox<String> cbJadwalDokter, cbJadwalPasien, cbJadwalRuangan;
    private JTextField txtIdJadwal;

    private final String[] kats = {"Manajemen Pasien", "Daftar Dokter (View Only)", "Ruangan ICU", "Jadwal Kontrol"};

    public Main() {
        setTitle("Dashboard Sistem Manajemen Rumah Sakit");
        setSize(1200, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initDummyData();
        initModels();
        initComponents();
        initEvents();
        syncUI();
    }

    // 1. Inisialisasi Model Tabel menggunakan Array agar ringkas
    private void initModels() {
        models = new DefaultTableModel[]{
            new DefaultTableModel(new String[]{"NIK", "Nama", "Usia", "Tempat", "Tanggal", "Jenis Kelamin", "Telepon", "Penyakit"}, 0),
            new DefaultTableModel(new String[]{"NIK", "Nama Dokter", "Peran/Pangkat", "Detail Spesialisasi"}, 0),
            new DefaultTableModel(new String[]{"ID Ruangan", "Status", "Kapasitas Bed", "Level Perawatan"}, 0),
            new DefaultTableModel(new String[]{"ID Jadwal","Hari", "Mulai", "Selesai", "Dokter", "Pasien", "Ruangan"}, 0)
        };
    }

    // 2. Pembuatan Komponen UI & Layouting
    private void initComponents() {
        // Panel Atas (Navigasi & Pencarian)
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        northPanel.add(new JLabel("Pilih Menu Kategori:"));
        northPanel.add(cbKategori = new JComboBox<>(kats));
        northPanel.add(new JLabel("  |  Cari NIK Pasien:"));
        northPanel.add(txtPasienCariNik = new JTextField(12));
        northPanel.add(btnCari = new JButton("Cari"));
        northPanel.add(btnReset = new JButton("Reset"));

        // Form Cards (CardLayout)
        cardLayout = new CardLayout();
        formCards = new JPanel(cardLayout);

        // Grid form builder
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
        txtIdJadwal = createField(pJadwal, "ID Jadwal:");
        cbJadwalHari = createCombo(pJadwal, "Hari:", Hari.values());
        cbJadwalMulai = createCombo(pJadwal, "Mulai:", Jam.values());
        cbJadwalSelesai = createCombo(pJadwal, "Selesai:", Jam.values());
        cbJadwalDokter = createCombo(pJadwal, "Dokter Spec:", null);
        cbJadwalPasien = createCombo(pJadwal, "Pasien:", null);
        cbJadwalRuangan = createCombo(pJadwal, "Ruangan ICU:", null);

        formCards.add(pPasien, kats[0]);
        formCards.add(new JLabel("Tidak ada input untuk data Dokter"), kats[1]);
        formCards.add(pIcu, kats[2]);
        formCards.add(pJadwal, kats[3]);

        // Panel Kiri (Form + Buttons)
        JPanel leftPanel = new JPanel(new BorderLayout(10, 10));
        leftPanel.setBorder(BorderFactory.createTitledBorder(" Form Input "));
        leftPanel.setPreferredSize(new Dimension(340, 0));
        leftPanel.add(formCards, BorderLayout.CENTER);

        btnPanel = new JPanel(new FlowLayout());
        btnPanel.add(btnTambah = new JButton("Simpan"));
        btnPanel.add(btnUpdate = new JButton("Update"));
        btnPanel.add(btnHapus = new JButton("Hapus"));
        leftPanel.add(btnPanel, BorderLayout.SOUTH);

        // JTable Utama (default index 0)
        mainTable = new JTable(models[0]);
        JScrollPane scrollTable = new JScrollPane(mainTable);
        scrollTable.setBorder(BorderFactory.createTitledBorder(" Tabel Informasi "));

        // Setup Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(scrollTable, BorderLayout.CENTER);
        add(mainPanel);
    }

    // 3. Event Listeners
    private void initEvents() {
        // Pindah Kategori Menu
        cbKategori.addActionListener(e -> {
            int idx = cbKategori.getSelectedIndex();
            cardLayout.show(formCards, kats[idx]);
            mainTable.setModel(models[idx]);
            btnTambah.setVisible(idx != 1 && idx != 2); // Sembunyikan tombol Simpan pada Dokter & ICU
            btnPanel.revalidate();
            btnPanel.repaint();
        });

        // Klik Baris Tabel
        mainTable.getSelectionModel().addListSelectionListener(e -> handleTableSelection());

        // Aksi CRUD
        btnTambah.addActionListener(e -> handleTambah());
        btnUpdate.addActionListener(e -> handleUpdate());
        btnHapus.addActionListener(e -> handleHapus());

        // Aksi Pencarian Pasien
        btnCari.addActionListener(e -> {
            models[0].setRowCount(0);
            dataPasien.stream()
                    .filter(p -> p.getNik().startsWith(txtPasienCariNik.getText().trim()))
                    .forEach(p -> models[0].addRow(new Object[]{p.getNik(), p.getNamaLengkap(), p.getUsia(), p.getTempatLahir(), p.getTanggalLahir(), p.getJenisKelamin(), p.getNoTelp(), p.getPenyakit()}));
        });
        btnReset.addActionListener(e -> syncUI());
    }

    // Menampilkan data pada form ketika baris tabel dipilih
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
        }
    }

    // MENAMBAH DATA BERDASARKAN INPUT FORM (Pasien, Jadwal) - Dokter & ICU tidak bisa ditambah melalui form, hanya update status/kapasitas/level saja  
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
                    dataJadwal.add(new Jadwal(txtIdJadwal.getText(), (Hari) cbJadwalHari.getSelectedItem(), (Jam) cbJadwalMulai.getSelectedItem(), (Jam) cbJadwalSelesai.getSelectedItem(), ds, dataPasien.get(cbJadwalPasien.getSelectedIndex()), dataICU.get(cbJadwalRuangan.getSelectedIndex())));
                }
            }
            syncUI();
            clearAllFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Input tidak valid / data kosong");
        }
    }

    //MENGUPDATE DATA BERDASARKAN KEY (NIK untuk Pasien, ID untuk ICU, dan Index untuk Jadwal)
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
                        icu.setIdRuangan(txtIcuId.getText());
                        icu.setStatus((StatusRuangan) cbIcuStatus.getSelectedItem());
                        icu.setKapasitasBed(Integer.parseInt(txtIcuKapasitas.getText()));
                        icu.setLevelPerawatan((LevelPerawatan) cbIcuLevel.getSelectedItem());
                    });

                case 3 ->
                    dataJadwal.stream().filter(j -> j.getIdJadwal().equals(key)).findFirst().ifPresent(j -> {
                        j.setIdJadwal(txtIdJadwal.getText());
                        j.setHari((Hari) cbJadwalHari.getSelectedItem());
                        j.setJamMulai((Jam) cbJadwalMulai.getSelectedItem());
                        j.setJamSelesai((Jam) cbJadwalSelesai.getSelectedItem());

                        String selectedDoc = (String) cbJadwalDokter.getSelectedItem();
                        DokterSpesialis ds = dataDokter.stream()
                                .filter(d -> d instanceof DokterSpesialis && d.getNamaLengkap().equals(selectedDoc))
                                .map(d -> (DokterSpesialis) d)
                                .findFirst().orElse(null);
                        j.setDokter(ds);

                        j.setPasien(dataPasien.get(cbJadwalPasien.getSelectedIndex()));
                        j.setRuangan(dataICU.get(cbJadwalRuangan.getSelectedIndex()));
                    });
            }
            syncUI();
            clearAllFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Gagal memperbarui data. Pastikan format benar");
        }
    }
    
    // MENGHAPUS DATA BERDASARKAN KEY (NIK untuk Pasien, ID untuk ICU, dan Index untuk Jadwal)
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
        }
        syncUI();
        clearAllFields();
    }

    // 4. Sinkronisasi Data JTable dan JComboBox secara Fungsional
    private void syncUI() {
        for (DefaultTableModel model : models) {
            model.setRowCount(0);
        }

        dataPasien.forEach(p -> models[0].addRow(new Object[]{p.getNik(), p.getNamaLengkap(), p.getUsia(), p.getTempatLahir(), p.getTanggalLahir(), p.getJenisKelamin(), p.getNoTelp(), p.getPenyakit()}));
        dataDokter.forEach(d -> models[1].addRow(new Object[]{d.getNik(), d.getNamaLengkap(), d.getPeran(), d instanceof DokterSpesialis ds ? ds.getSpesialis() : "Lisensi: " + d.getNoLisensi()}));
        dataICU.forEach(i -> models[2].addRow(new Object[]{i.getIdRuangan(), i.getStatus(), i.getKapasitasBed(), i.getLevelPerawatan()}));
        dataJadwal.forEach(j -> models[3].addRow(new Object[]{j.getIdJadwal() ,j.getHari(), j.getJamMulai(), j.getJamSelesai(), j.getDokter() != null ? j.getDokter().getNamaLengkap() : "-", j.getPasien() != null ? j.getPasien().getNamaLengkap() : "-", j.getRuangan() != null ? j.getRuangan().getIdRuangan() : "-"}));

        // Sinkronisasi dropdown JComboBox Relasi
        cbJadwalDokter.removeAllItems();
        dataDokter.stream().filter(d -> d instanceof DokterSpesialis).forEach(d -> cbJadwalDokter.addItem(d.getNamaLengkap()));

        cbJadwalPasien.removeAllItems();
        dataPasien.forEach(p -> cbJadwalPasien.addItem(p.getNamaLengkap()));

        cbJadwalRuangan.removeAllItems();
        dataICU.forEach(i -> cbJadwalRuangan.addItem(i.getIdRuangan()));
    }

    // METHOD MEMBUAT FIELD (FORM)
    private JTextField createField(JPanel p, String lbl) {
        p.add(new JLabel(lbl));
        JTextField tf = new JTextField();
        p.add(tf);
        return tf;
    }

    // METHOD MEMBUAT COMBOBOX (DROPDOWN)
    private <T> JComboBox<T> createCombo(JPanel p, String lbl, T[] items) {
        p.add(new JLabel(lbl));
        JComboBox<T> cb = items != null ? new JComboBox<>(items) : new JComboBox<>();
        p.add(cb);
        return cb;
    }

    // METHOD BERSIHKAN SEMUA FORM
    private void clearAllFields() {
        JTextField[] fields = {txtPasienNik, txtPasienNama, txtPasienUsia, txtPasienTempat, txtPasienTanggal, txtPasienTelp, txtPasienPenyakit, txtIcuId, txtIcuKapasitas};
        for (JTextField f : fields) {
            if (f != null) {
                f.setText("");
            }
        }
    }

    // DATA DUMMY
    private void initDummyData() {
        if (dataPasien.isEmpty()) {
            dataPasien.add(new Pasien("3171022804950003", "Rian Santoso", 31, "Jakarta", "28-04-1995", JenisKelamin.LAKI_LAKI, "081298345712", "Hipertensi"));
            dataPasien.add(new Pasien("3515041203990001", "Siti Aminah", 27, "Surabaya", "12-03-1999", JenisKelamin.PEREMPUAN, "081345678901", "Diabetes Melitus"));
            dataPasien.add(new Pasien("5201040508810002", "Budi Darmawan", 45, "Mataram", "05-08-1981", JenisKelamin.LAKI_LAKI, "087864123456", "Asma Kronis"));
            dataPasien.add(new Pasien("3273014509920004", "Dewi Lestari", 34, "Bandung", "15-09-1992", JenisKelamin.PEREMPUAN, "082112345678", "Demam Berdarah"));

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

            Jadwal j1 = new Jadwal("JADWAL-01", Hari.SENIN, Jam.JAM_08_00, Jam.values().length > 2 ? Jam.values()[2] : Jam.JAM_12_00, ds1, dataPasien.get(0), dataICU.get(0));
            Jadwal j2 = new Jadwal("JADWAL-02", Hari.values().length > 1 ? Hari.values()[1] : Hari.SENIN, Jam.JAM_08_00, Jam.values().length > 2 ? Jam.values()[2] : Jam.JAM_12_00, ds2, dataPasien.get(1), dataICU.get(1));
            Jadwal j3 = new Jadwal("JADWAL-03", Hari.SENIN, Jam.values().length > 2 ? Jam.values()[2] : Jam.JAM_12_00, Jam.values()[Jam.values().length - 1], ds3, dataPasien.get(2), dataICU.get(2));

            dataJadwal.add(j1);
            dataJadwal.add(j2);
            dataJadwal.add(j3);
        }
    }

    public static void main(String[] args) {
        new Main().setVisible(true);
    }
}
