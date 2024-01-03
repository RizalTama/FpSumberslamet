package com.akademik.kampus

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class Pasien_ubah : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pasien_ubah)

        val id_pasien_terpilih:String = intent.getStringExtra("id_pasien_terpilih").toString()

        val dbfinalprojek:SQLiteDatabase = openOrCreateDatabase("finalprojek", MODE_PRIVATE, null)
        val ambil = dbfinalprojek.rawQuery("SELECT * FROM pasien WHERE id_pasien = '$id_pasien_terpilih' ", null)
        ambil.moveToNext()


        val isi_nama:String = ambil.getString(1)
        val isi_keluhan:String = ambil.getString(2)

        val edt_keluhan :EditText = findViewById(R.id.edt_keluhan)
        val edt_nama:EditText = findViewById(R.id.edt_namapasien)
        val btn_simpan:Button = findViewById(R.id.btn_simpan)

        edt_keluhan.setText(isi_keluhan)
        edt_nama.setText(isi_nama)

        btn_simpan.setOnClickListener {
            val keluhan_baru:String = edt_keluhan.text.toString()
            val nama_baru:String = edt_nama.text.toString()

            val ubah = dbfinalprojek.rawQuery("UPDATE pasien SET keluhan_pasien = '$keluhan_baru', nama_pasien =  '$nama_baru' WHERE id_pasien = '$id_pasien_terpilih'", null)
            ubah.moveToNext()

            val pindah: Intent = Intent(this, Dokter::class.java )
            startActivity(pindah)
        }
    }

}