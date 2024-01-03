package com.akademik.kampus

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class Dokter_ubah : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dokter_ubah)

        val id_dokter_terpilih:String = intent.getStringExtra("id_dokter_terpilih").toString()

        val dbfinalprojek:SQLiteDatabase = openOrCreateDatabase("finalprojek", MODE_PRIVATE, null)
        val ambil = dbfinalprojek.rawQuery("SELECT * FROM dokter WHERE id_dokter = '$id_dokter_terpilih' ", null)
        ambil.moveToNext()


        val isi_nama:String = ambil.getString(1)
        val isi_spesialis:String = ambil.getString(2)

        val edt_spesialis :EditText = findViewById(R.id.edt_spesialis)
        val edt_nama:EditText = findViewById(R.id.edt_nama)
        val btn_simpan:Button = findViewById(R.id.btn_simpan)

        edt_spesialis.setText(isi_spesialis)
        edt_nama.setText(isi_nama)

        btn_simpan.setOnClickListener {
            val spesialis_baru:String = edt_spesialis.text.toString()
            val nama_baru:String = edt_nama.text.toString()

            val ubah = dbfinalprojek.rawQuery("UPDATE dokter SET spesialis_dokter = '$spesialis_baru', nama_dokter =  '$nama_baru' WHERE id_dokter = '$id_dokter_terpilih'", null)
            ubah.moveToNext()

            val pindah: Intent = Intent(this, Dokter::class.java )
            startActivity(pindah)
        }
    }

}