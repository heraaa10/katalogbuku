package com.example.katalogbuku

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: BukuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerBuku)

        // 1. LayoutManager: Diubah menjadi Grid 2 Kolom
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        // 2. Garis pemisah (DividerItemDecoration) dihapus karena tampilan sudah menggunakan CardView dengan margin

        // 3. Pasang Adapter
        adapter = BukuAdapter(
            daftar = siapkanData(),
            onKlik = { buku ->
                Toast.makeText(this, "Dipilih: ${buku.judul}", Toast.LENGTH_SHORT).show()
            },
            onTahan = { posisi -> adapter.hapus(posisi) }
        )
        recyclerView.adapter = adapter

        // Listener Tombol Tambah Buku
        findViewById<Button>(R.id.btnTambah).setOnClickListener {
            val nomor = adapter.itemCount + 1
            adapter.tambah(Buku("Buku Baru $nomor", "Penulis Baru", 2026))
        }
    }

    private fun siapkanData(): MutableList<Buku> = mutableListOf(
        Buku("Pemrograman Android", "Budi Santoso", 2022),
        Buku("Dasar Kotlin", "Siti Aminah", 2023),
        Buku("Jetpack Compose", "Andi Pratama", 2024),
        Buku("Desain UI/UX", "Maya Lestari", 2020),
        Buku("Basis Data", "Rudi Hartono", 2019)
    )
}