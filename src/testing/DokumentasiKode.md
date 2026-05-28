# DOKUMENTASI KODE DISINI YA

## 1. PENDAHULUAN
Kelas `Main` merupakan program ini dari Project kami **Sistem Manajemen Rumah Sakit**. Program ini menggunakan antarmuka pengguna berbasis grafis (GUI) memanfaatkan pustaka **Java Swing** dan **AWT**. Desain GUI ini dirancang untuk menyatukan dan mengelola 4 model rumah sakit: **Pasien**, **Dokter**, **Ruangan ICU**, dan **Jadwal Kontrol**.

---

## 2. STRUKTUR DATA & MANAJEMEN STATE (REPOSITORY)
Aplikasi ini menggunakan penyimpanan memori `ArrayList`

```java
// Repository Data Utama
private static final ArrayList<Pasien> dataPasien = new ArrayList<>();
private static final ArrayList<Dokter> dataDokter = new ArrayList<>();
private static final ArrayList<Icu> dataICU = new ArrayList<>();
private static final ArrayList<Jadwal> dataJadwal = new ArrayList<>();
```

* `dataPasien` menyimpan semua rekam data pasien yang terdaftar
* `dataDokter` menyimpan data personil dokter. Koleksi ini mendukung polimorfisme, di mana objek bertipe sub-class seperti `DokterSpesialis` dapat disimpan di dalamnya
* `dataICU` menyimpan status dan spesifikasi kapasitas dari ruangan ICU yang tersedia di rumah sakit
* `dataJadwal` Menyimpan data penjadwalan pemeriksaan antara dokter, pasien, dan ruangan

---

## 3. UI & LAYOUTING
Dasbor dirancang pakai pembagian visual interaktif menggunakan kombinasi beberapa Layout Manager:
1. `BorderLayout` (Tata Letak Utama)
   
   Membagi jendela menjadi beberapa region (North, West, Center) untuk memisahkan navigasi, formulir, dan tabel data
2. `CardLayout` (Mekanisme Form Dinamis)
  
   Diterapkan di panel `formCards`. Saat pengguna mengganti kategori menu pada dropdown atas, `CardLayout` secara instan menukar panel formulir di sebelah kiri agar sesuai dengan data yang sedang dikelola.

```java
// Form Cards (CardLayout)
cardLayout = new CardLayout();
formCards = new JPanel(cardLayout);

// Menambahkan form ke CardLayout berdasarkan kategori kats[]
formCards.add(pPasien, kats[0]);
formCards.add(new JLabel("Tidak ada input untuk data Dokter"), kats[1]);
formCards.add(pIcu, kats[2]);
formCards.add(pJadwal, kats[3]);
```

3. Tata Letak
   `GridLayout` digunakan untuk menyusun label dan field input agar sejajar secara presisi, sedangkan `FlowLayout` digunakan untuk barisan tombol aksi.

---

## 4. DEKONSTRUKSI METODE DAN LOGIKA KODE
1. Alur Inisialisasi (Constructor)
   
   Konstruktor mengatur parameter dasar jendela aplikasi (judul, ukuran `1200x650`, posisi tengah) dan memanggil inisialisasi yang teratur:
   ```java
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
    ```

2. Pengaturan Model Tabel Ringkas
   
   Metode `initModels()` memanfaatkan array dari objek `DefaultTableModel[]`. Setiap indeks array mewakili struktur kolom dari masing-masing kategori menu secara ringkas:

   ```java
   private void initModels() {
    models = new DefaultTableModel[]{
        new DefaultTableModel(new String[]{"NIK", "Nama", "Usia", "Tempat", "Tanggal", "Jenis Kelamin", "Telepon", "Penyakit"}, 0),
        new DefaultTableModel(new String[]{"NIK", "Nama Dokter", "Peran/Pangkat", "Detail Spesialisasi"}, 0),
        new DefaultTableModel(new String[]{"ID Ruangan", "Status", "Kapasitas Bed", "Level Perawatan"}, 0),
        new DefaultTableModel(new String[]{"Hari", "Mulai", "Selesai", "Dokter", "Pasien", "Ruangan"}, 0)
    };
   }
   ```

3. Event Listener
   
   Mengatur perilaku aplikasi saat berinteraksi dengan pengguna:
   * Dropdown Kategori Menu (`cbKategori`)
     
     Mengubah tampilan form dan model tabel aktif secara bersamaan, serta mengatur visibilitas tombol Simpan.
     ```java
     cbKategori.addActionListener(e -> {
       int idx = cbKategori.getSelectedIndex();
       cardLayout.show(formCards, kats[idx]);
       mainTable.setModel(models[idx]);
       btnTambah.setVisible(idx != 1 && idx != 2); // Sembunyikan tombol Simpan pada Dokter & ICU
       btnPanel.revalidate();
       btnPanel.repaint();
     });
     ```
     
   * Pencarian Berbasis Stream (`btnCari`)

     Menyaring data pasien yang dicocokkan berdasarkan awalan teks NIK (`startsWith`)
     ```java
     btnCari.addActionListener(e -> {
       models[0].setRowCount(0);
       dataPasien.stream()
         .filter(p -> p.getNik().startsWith(txtPasienCariNik.getText().trim()))
         .forEach(p -> models[0].addRow(new Object[]{p.getNik(), p.getNamaLengkap(), p.getUsia(), p.getTempatLahir(), p.getTanggalLahir(), p.getJenisKelamin(), p.getNoTelp(), p.getPenyakit()}));
     });
     ```

4. Penanganan Logika CRUD
   * `handleTableSelection()`

     ektrak data dari baris tabel yang di-klik pengguna untuk dimasukkan kembali ke kolom input formulir (otomatisasi auto-fill untuk edit/update)
   * `handleTambah()`

     Menambahkan data baru ke tabel berdasarkan kategori menu  dengan validasi blok `try-catch` terhadap kesalahan format angka (`NumberFormatException`).
   * `handleUpdate()` & `handleHapus()`

     Menggunakan pencarian kunci unik (NIK/ID) untuk memperbarui atau menghapus elemen spesifik di dalam tabel menggunakan `removeIf`.
    ```java
    private void handleHapus() {
      int row = mainTable.getSelectedRow();
      if (row < 0) return;
      String key = mainTable.getValueAt(row, 0).toString();

      switch (cbKategori.getSelectedIndex()) {
        case 0 -> dataPasien.removeIf(p -> p.getNik().equals(key));
        case 2 -> dataICU.removeIf(icu -> icu.getIdRuangan().equals(key));
        case 3 -> dataJadwal.remove(row);
      }
    syncUI();
    clearAllFields();
    }
    ```
    
5. Sinkronisasi Otomatis UI
   
   `syncUI()` adalah inti penjamin konsistensi data. Setiap terjadi modifikasi data, metode ini akan mengosongkan tabel lama, merender ulang data terbaru dari tabel, dan memperbarui item relasi pada dropdown (`JComboBox`) di formulir Jadwal Kontrol
    ```java
    private void syncUI() {
    for (DefaultTableModel model : models) {
        model.setRowCount(0);
    }

    dataPasien.forEach(p -> models[0].addRow(new Object[]{p.getNik(), p.getNamaLengkap(), p.getUsia(), p.getTempatLahir(), p.getTanggalLahir(), p.getJenisKelamin(), p.getNoTelp(), p.getPenyakit()}));
    dataDokter.forEach(d -> models[1].addRow(new Object[]{d.getNik(), d.getNamaLengkap(), d.getPeran(), d instanceof DokterSpesialis ds ? ds.getSpesialis() : "Lisensi: " + d.getNoLisensi()}));
    dataICU.forEach(i -> models[2].addRow(new Object[]{i.getIdRuangan(), i.getStatus(), i.getKapasitasBed(), i.getLevelPerawatan()}));
    dataJadwal.forEach(j -> models[3].addRow(new Object[]{j.getHari(), j.getJamMulai(), j.getJamSelesai(), j.getDokter() != null ? j.getDokter().getNamaLengkap() : "-", j.getPasien() != null ? j.getPasien().getNamaLengkap() : "-", j.getRuangan() != null ? j.getRuangan().getIdRuangan() : "-"}));

    // Sinkronisasi dropdown JComboBox Relasi secara Dinamis
    cbJadwalDokter.removeAllItems();
    dataDokter.stream().filter(d -> d instanceof DokterSpesialis).forEach(d -> cbJadwalDokter.addItem(d.getNamaLengkap()));

    cbJadwalPasien.removeAllItems();
    dataPasien.forEach(p -> cbJadwalPasien.addItem(p.getNamaLengkap()));

    cbJadwalRuangan.removeAllItems();
    dataICU.forEach(i -> cbJadwalRuangan.addItem(i.getIdRuangan()));
    }
    ```

## 5. FITUR MODERN JAVA YANG DIGUNAKAN
1. Pattern Matching for `instanceof`
  
   Terlihat pada metode `syncUI()`, di mana pengecekan objek dan casting tipe dilakukan secara langsung dan ringkas tanpa konversi manual terpisah:
   ```java
   d instanceof DokterSpesialis ds ? ds.getSpesialis() : "Lisensi: " + d.getNoLisensi()
   ```
2. Perulangan
   
   Menggantikan perulangan `for` yang panjang saat melakukan filter dengan `.forEach()`
