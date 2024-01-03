package com.akademik.kampus

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Obat_hapus : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.obat_hapus)

        val id_obat_terpilih:String = intent.getStringExtra("id_obat_terpilih").toString()

        val dbfinalprojek: SQLiteDatabase = openOrCreateDatabase("finalprojek", MODE_PRIVATE, null)

        val query = dbfinalprojek.rawQuery("DELETE FROM obat WHERE id_obat='$id_obat_terpilih'", null)
        query.moveToNext()

        val pindah: Intent = Intent(this, Obat::class.java )
        startActivity(pindah)
    }
}