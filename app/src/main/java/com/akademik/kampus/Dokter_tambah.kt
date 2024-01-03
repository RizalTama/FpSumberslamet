package com.akademik.kampus

import android.app.Activity
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import java.io.ByteArrayOutputStream

class Dokter_tambah : AppCompatActivity() {
    var urlgambar:Uri?=null
    var bitmapgmabar:Bitmap?=null
    var iv_upload:ImageView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dokter_tambah)

        val edt_spsialis :EditText = findViewById(R.id.edt_spesialis)
        val edt_nama:EditText = findViewById(R.id.edt_nama)
        val btn_simpan:Button = findViewById(R.id.btn_simpan)
        iv_upload = findViewById(R.id.iv_upload)

        iv_upload?.setOnClickListener {
            val bukagaleri:Intent=Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            pilih_gambar.launch(bukagaleri)
        }

        btn_simpan.setOnClickListener {
            val isi_spesialis:String = edt_spsialis.text.toString()
            val isi_nama:String = edt_nama.text.toString()

            val bos = ByteArrayOutputStream()
            bitmapgmabar?.compress(Bitmap.CompressFormat.JPEG, 100, bos)
            val bytesarraygambar = bos.toByteArray()

            val dbfinalprojek:SQLiteDatabase = openOrCreateDatabase("finalprojek", MODE_PRIVATE, null)
            val sql = "INSERT INTO dokter (spesialis_dokter , nama_dokter,foto_dokter) VALUES (?,?,?)"
            val statement = dbfinalprojek.compileStatement(sql)
            statement.clearBindings()
            statement.bindString(1, isi_nama)
            statement.bindString(2, isi_spesialis)
            statement.bindBlob(3, bytesarraygambar)
            statement.executeInsert()

            val pindah:Intent = Intent(this, Dokter::class.java)
            startActivity(pindah)
        }
    }
    val pilih_gambar = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode==Activity.RESULT_OK) {
            val gambardiperoleh = it.data

            if (gambardiperoleh!=null) {
                urlgambar = gambardiperoleh.data

                bitmapgmabar = MediaStore.Images.Media.getBitmap(contentResolver, urlgambar)
                iv_upload?.setImageBitmap(bitmapgmabar)

            }
        }
    }
}