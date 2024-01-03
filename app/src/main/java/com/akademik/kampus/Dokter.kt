package com.akademik.kampus

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.ByteArrayInputStream
import java.lang.Exception

class Dokter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dokter)

        val rv_mahasiswa: RecyclerView = findViewById(R.id.rv_dokter)


        val id :MutableList<String> = mutableListOf()
        val nama : MutableList<String> = mutableListOf();
        val spesialis : MutableList<String> = mutableListOf();
        val foto: MutableList<Bitmap> = mutableListOf();

        val dbfinalprojek: SQLiteDatabase = openOrCreateDatabase("finalprojek", MODE_PRIVATE, null)
        val detail_dokter = dbfinalprojek.rawQuery("SELECT * FROM dokter", null)

        while (detail_dokter.moveToNext()) {
            try {
                val bis = ByteArrayInputStream(detail_dokter.getBlob(3))
                val gambarbitmap:Bitmap = BitmapFactory.decodeStream(bis)
                foto.add(gambarbitmap)
            } catch (e:Exception) {
                val gambarbitmap:Bitmap = BitmapFactory.decodeResource(this.resources,R.drawable.noimage)
                foto.add(gambarbitmap)
            }

            id.add(detail_dokter.getString(0))
            nama.add(detail_dokter.getString(1))
            spesialis.add(detail_dokter.getString(2))

        }

        val mi = Dokter_item(this, id, nama, spesialis, foto)
        rv_mahasiswa.adapter=mi
        rv_mahasiswa.layoutManager = GridLayoutManager(this, 2)

        val btn_tambah:Button = findViewById(R.id.btn_tambah)
        btn_tambah.setOnClickListener {
            val pindah:Intent = Intent(this, Dokter_tambah::class.java)
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

