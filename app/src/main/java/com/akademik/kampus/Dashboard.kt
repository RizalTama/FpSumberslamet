package com.akademik.kampus

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)
        val sesion : TextView= findViewById(R.id.txt_nama_admin)
        val tiket: SharedPreferences = getSharedPreferences("admin", MODE_PRIVATE)
        val admin :String = tiket.getString("nama_admin",null).toString()
        sesion.text = admin
        val btn_logout: Button = findViewById(R.id.btn_logout)
        btn_logout.setOnClickListener {
            val edittiket = tiket.edit()
            edittiket.clear()
            edittiket.commit()

            val keluar: Intent = Intent(this, Login::class.java)
            startActivity(keluar)
            finish()
        }

        val btn_dokter: Button = findViewById(R.id.btn_dokter)
        btn_dokter.setOnClickListener {
            val pindah: Intent = Intent(this, Dokter::class.java)
            startActivity(pindah)
        }

        val btn_obat: Button = findViewById(R.id.btn_obat)
        btn_obat.setOnClickListener {
            val pindah: Intent = Intent(this, Obat::class.java)
            startActivity(pindah)

        }
        val btn_pasien: Button = findViewById(R.id.btn_pasien)
        btn_pasien.setOnClickListener {
            val pindah: Intent = Intent(this, Pasien::class.java)
            startActivity(pindah)
        }
    }
}