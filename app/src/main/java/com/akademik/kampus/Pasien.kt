package com.akademik.kampus

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.ByteArrayInputStream
import java.lang.Exception

class Pasien : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pasien)

        val rv_pasien: RecyclerView = findViewById(R.id.rv_pasien)


        val id :MutableList<String> = mutableListOf()
        val nama : MutableList<String> = mutableListOf();
        val keluhan : MutableList<String> = mutableListOf();
        val foto: MutableList<Bitmap> = mutableListOf();

        val dbfinalprojek: SQLiteDatabase = openOrCreateDatabase("finalprojek", MODE_PRIVATE, null)
        val detail_pasien = dbfinalprojek.rawQuery("SELECT * FROM pasien", null)

        while (detail_pasien.moveToNext()) {
            try {
                val bis = ByteArrayInputStream(detail_pasien.getBlob(3))
                val gambarbitmap:Bitmap = BitmapFactory.decodeStream(bis)
                foto.add(gambarbitmap)
            } catch (e: Exception) {
                val gambarbitmap:Bitmap = BitmapFactory.decodeResource(this.resources,R.drawable.noimage)
                foto.add(gambarbitmap)
            }

            id.add(detail_pasien.getString(0))
            nama.add(detail_pasien.getString(1))
            keluhan.add(detail_pasien.getString(2))

        }

        val mi = Pasien_item(this, id, nama, keluhan, foto)
        rv_pasien.adapter=mi
        rv_pasien.layoutManager = GridLayoutManager(this, 2)

        val btn_tambah:Button = findViewById(R.id.btn_tambah)
        btn_tambah.setOnClickListener {
            val pindah:Intent = Intent(this, Pasien_tambah::class.java)
            startActivity(pindah)

        }

        val ibDokter: ImageButton = findViewById(R.id.ib_dokter)
        val btn_pulang : Button =findViewById(R.id.btn_dashboard)
        val ibObat: ImageButton = findViewById(R.id.ib_obat)
        val ibPasien: ImageButton = findViewById(R.id.ib_pasien)

        ibDokter.setOnClickListener {
            startActivity(Intent(this, Dokter::class.java))
        }

        ibObat.setOnClickListener {
            startActivity(Intent(this, Obat::class.java))
        }

        ibPasien.setOnClickListener {
            startActivity(Intent(this, Pasien::class.java))
        }

        btn_pulang.setOnClickListener{
            startActivity(Intent(this,Dashboard::class.java))
        }
    }
}