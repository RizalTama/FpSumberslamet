package com.akademik.kampus

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Pasien_hapus : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pasien_hapus)

        val id_terpilih:String = intent.getStringExtra("id_pasien_terpilih").toString()

        val dbfinalprojek: SQLiteDatabase = openOrCreateDatabase("finalprojek", MODE_PRIVATE, null)

        val query = dbfinalprojek.rawQuery("DELETE FROM pasien WHERE id_pasien='$id_terpilih'", null)
        query.moveToNext()

        val pindah:Intent = Intent(this, Pasien::class.java )
        startActivity(pindah)
    }
}