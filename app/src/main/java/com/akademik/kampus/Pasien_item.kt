package com.akademik.kampus

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Pasien_item (val ini:Context, val id:MutableList<String>, val nama:MutableList<String>, val keluhan :MutableList<String>, val foto:MutableList<Bitmap>) : RecyclerView.Adapter<Pasien_item.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pasien_item,parent,false)
        return ViewHolder(view)
    }
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val txt_nama : TextView = itemView.findViewById(R.id.txt_nama)
        val txt_keluhan : TextView = itemView.findViewById(R.id.txt_keluhan)
        val iv_foto : ImageView = itemView.findViewById(R.id.iv_foto)
        val btn_hapus:Button=ItemView.findViewById(R.id.btn_hapus)
        val btn_ubah:Button=ItemView.findViewById(R.id.btn_ubah)
    }

    override fun getItemCount(): Int {
        return nama.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txt_nama.text = nama.get(position)
        holder.txt_keluhan.text = keluhan.get(position)
        holder.iv_foto.setImageBitmap(foto.get(position))
        holder.btn_hapus.setOnClickListener {
            val id_dokter_terpilih:String = id.get(position)
            val pindah:Intent = Intent(ini, Pasien_hapus::class.java)
            pindah.putExtra("id_pasien_terpilih", id_dokter_terpilih)
            ini.startActivity(pindah)
        }
        holder.btn_ubah.setOnClickListener {
            val id_dokter_terpilih:String = id.get(position)
            val pindah:Intent = Intent(ini, Pasien_ubah::class.java)
            pindah.putExtra("id_pasien_terpilih", id_dokter_terpilih)
            ini.startActivity(pindah)
        }
    }
}