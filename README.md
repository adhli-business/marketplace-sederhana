---

## 👥 **Soal 2 – “Marketplace Sederhana”**

### 📌 Deskripsi:

Buat aplikasi marketplace dengan **tampilan produk**, **detail**, dan **keranjang belanja (dummy)**.

### 🧩 Requirement:

1. Gunakan **1 Activity** dan **5 Fragment**:

   - `LoginFragment`: form login sederhana
   - `ProductListFragment`: tampilkan 5 produk (nama, harga, tombol detail)
   - `ProductDetailFragment`: tampilkan detail produk, tombol “Tambah ke Keranjang”
   - `CartFragment`: tampilkan list produk dummy yang "dibeli"
   - `AboutFragment`: tampilkan info tim

2. Navigasi:

   - Setelah login, pindah ke ProductListFragment
   - Klik produk → buka detail
   - Tombol “Keranjang” bisa dari toolbar/menu sederhana

3. Semua data dummy/hardcoded

### 🧱 Komponen:

- `RecyclerView`, `TextView`, `Button`, `ImageView`
- Fragment transaksi + komunikasi antar fragment
- Dummy produk di adapter

### 🎯 Tujuan:

- Simulasi alur aplikasi nyata
- Fragment banyak arah + kondisi berpindah
- List & detail fragment + cart logic sederhana

---
