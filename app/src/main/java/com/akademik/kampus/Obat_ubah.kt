package com.akademik.kampus

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class Obat_ubah : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.obat_ubah)

        val id_obat_terpilih:String = intent.getStringExtra("id_obat_terpilih").toString()

        val dbfinalprojek:SQLiteDatabase = openOrCreateDatabase("finalprojek", MODE_PRIVATE, null)
        val ambil = dbfinalprojek.rawQuery("SELECT * FROM obat WHERE id_obat = '$id_obat_terpilih' ", null)
        ambil.moveToNext()

        val isi_nama:String = ambil.getString(1)

        val edt_nama:EditText = findViewById(R.id.edt_namaobat)
        val btn_simpan:Button = findViewById(R.id.btn_simpan)

        edt_nama.setText(isi_nama)

        btn_simpan.setOnClickListener {

            val nama_baru:String = edt_nama.text.toString()

            val ubah = dbfinalprojek.rawQuery("UPDATE obat SET nama_obat =  '$nama_baru' WHERE id_obat = '$id_obat_terpilih'", null)
            ubah.moveToNext()
            val pindah: Intent = Intent(this, Obat::class.java )
            startActivity(pindah)
        }
    }

}