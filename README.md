FOLDER UTIL, SERVICE, EXCEPTION BELUM KELIATAN SOALNYA GADA ISINYA (GITHUB OTOMATIS HANYA HILANGIN FOLDER KOSONG)

# Make GitHub Yaaa... (Disuruh Juan)
*Kalau ada tambahan, boleh di tambah*

Oh iya, make GitHUbnya pas ngoding di Code Editor lewat terminal

lebih mudah lagi kalau udh download Git, ini link download Git nya :

[Halaman Download Git](https://git-scm.com/install/windows)

---

Git ini kayak semacam pelacak atau kontrol project biar mudah saat kolaborasi

---

## 1. Setup
Dipake pas mau collab

* **`git clone <url-repository>`**
  * **Maksudnya:** "Ayo download proyeknya ke laptop ku"
  * **Fungsi:** Menyalin proyek dari GitHub ke laptop kamu, bisa lewat vs code kok
* **`git init`**
  * **Maksudnya:** "Woi Git, pantau folder ini ya"
  * **Fungsi:** Bikin folder lokal kamu jadi kayak code online (bisa mantau pokoknya)

---

## 2. IN COllab
jangan ngoding lngsung di branch `main` atau `master` ya ges. SOal nya itu induk

* **`git branch <nama-branch-baru>`**
  * **Maksudnya:** "Aku bikin jalur ngoding baru ya, biar ga tabrakan sama kalian"
  * **Fungsi:** Buat cabang baru biar kerjaan mu terpisah dari kode utama
* **`git checkout <nama-branch>`**
  * **Maksudnya:** "Pindah dong, aku mau ngoding di branch ini"
  * **Fungsi:** Lompat ke branch lain yang sudah ada (harus sama ya namanya)
* **`git checkout -b <nama-branch-baru>`**
  * **Maksudnya:** "Bikin branch baru sekalian aku langsung pindah ke sana"
  * **Fungsi:** bikin branch + langsung pindah

---

## 3. Save sama Update
Ini pas kerjaan koding atau taskmu udh selesai ya, atau bisa pas lagi edit sesuatu

1. **`git status`**
   * Cek file apa aja yang keganti
2. **`git add .`**
   * Masukin semua perubahan lu ke 'keranjang' atau 'Checkout' buat disimpen
3. **`git commit -m "pesan penjelasan"`**
   * Simpen permanen ke Git. Tulis pesan yang jelas yaa... (contohnya: "fitur login kelar").
4. **`git push origin <nama-branch>`**
   * Upload branch kamu beserta isinya ke cloud GitHub

---

## 4. Ambil Update Kerjaaan Temen
Ini juga penting pas kerja bareng

* **`git fetch`**
  * **Maksudnya:** "DI GitHub ada update baru gak?"
* **`git pull origin <nama-branch>`**
  * **Maksudnya:** "Ambil update terbaru, langsung pasang di laptop kamu"
* **`git merge <nama-branch-lain>`**
  * **Maksudnya:** "Gabungin kode dari branch itu ke branch yang mau di pake sekarang"

---

## 5. Hapus sama batal
ini pake pas salah ngoding (ga sengaja koding) atau mau bersih bersih file yang ga guna 

* **`git restore <nama-file>`**
  * **Maksudnya:** "Kodingan ku ngaco nih... merah merona. Balikin file nya ke kondisi terakhir sebelum di save"
* **`git reset --soft HEAD~1`**
  * **Maksudnya:** "Sorry... salah commit, batalin commit terakhir dulu"
* **`git branch -d <nama-branch>`**
  * **Maksudnya:** "Hapus branch ini di laptop ku karena udah kelar tugasnya"
* **`git push origin --delete <nama-branch>`**
  * **Maksudnya:** "Hapus branch yang ada di GitHub sana"

---
