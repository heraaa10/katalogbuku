package com.example.katalogbuku

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BukuAdapter(
    private val daftar: MutableList<Buku>,
    private val onKlik: (Buku) -> Unit,
    private val onTahan: (Int) -> Unit
) : RecyclerView.Adapter<BukuAdapter.BukuViewHolder>() {

    class BukuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvJudul: TextView = itemView.findViewById(R.id.tvJudul)
        val tvPenulis: TextView = itemView.findViewById(R.id.tvPenulis)
        val tvTahun: TextView = itemView.findViewById(R.id.tvTahun)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BukuViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_buku, parent, false)
        return BukuViewHolder(view)
    }

    override fun onBindViewHolder(holder: BukuViewHolder, position: Int) {
        val buku = daftar[position]
        holder.tvJudul.text = buku.judul
        holder.tvPenulis.text = buku.penulis
        holder.tvTahun.text = buku.tahun.toString()

        holder.itemView.setOnClickListener { onKlik(buku) }
        holder.itemView.setOnLongClickListener {
            val currentPos = holder.bindingAdapterPosition
            if (currentPos != RecyclerView.NO_POSITION) {
                onTahan(currentPos)
            }
            true
        }
    }

    override fun getItemCount(): Int = daftar.size

    fun tambah(buku: Buku) {
        daftar.add(buku)
        notifyItemInserted(daftar.size - 1)
    }

    fun hapus(posisi: Int) {
        if (posisi in 0 until daftar.size) {
            daftar.removeAt(posisi)
            notifyItemRemoved(posisi)
        }
    }
}