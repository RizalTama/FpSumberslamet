package com.akademik.kampus

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Dokter_hapus : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dokter_hapus)

        val id_dokter_terpilih:String = intent.getStringExtra("id_dokter_terpilih").toString()

        val dbfinalprojek: SQLiteDatabase = openOrCreateDatabase("finalprojek", MODE_PRIVATE, null)

        val query = dbfinalprojek.rawQuery("DELETE FROM dokter WHERE id_dokter='$id_dokter_terpilih'", null)
        query.moveToNext()

        val pindah:Intent = Intent(this, Dokter::class.java )
        startActivity(pindah)
    }
}