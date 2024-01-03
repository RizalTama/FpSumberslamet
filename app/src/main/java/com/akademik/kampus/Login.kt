package com.akademik.kampus

import android.content.Intent
import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val edt_email:EditText = findViewById(R.id.edt_email)
        val edt_password:EditText = findViewById(R.id.edt_password)
        val btn_login:Button = findViewById(R.id.btn_login)

        btn_login.setOnClickListener {
            val isi_email:String=edt_email.text.toString()
            val isi_password:String=edt_password.text.toString()

            val dbfinalprojek: SQLiteDatabase = openOrCreateDatabase("finalprojek", MODE_PRIVATE, null)
            val query = dbfinalprojek.rawQuery("SELECT * FROM admin WHERE email_admin='$isi_email' AND password ='$isi_password'", null)
            val cek = query.moveToNext()

            if (cek) {
                val id = query.getString(0)
                val email = query.getString(1)
                val password = query.getString(2)
                val nama = query.getString(3)

                val session:SharedPreferences = getSharedPreferences("admin", MODE_PRIVATE)
                val buattiket = session.edit()
                buattiket.putString("id_admin", id)
                buattiket.putString("email_admin", email)
                buattiket.putString("password", password)
                buattiket.putString("nama_admin", nama)
                buattiket.commit()

                val pindah: Intent = Intent (this,Dashboard::class.java)
                startActivity(pindah)
            } else {
                Toast.makeText(this, "Email atau password salah!", Toast.LENGTH_LONG).show()
            }
        }
    }
}