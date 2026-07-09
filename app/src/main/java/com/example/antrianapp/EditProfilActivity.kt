package com.example.antrianapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EditProfilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profil)
        val etNamaBaru = findViewById<EditText>(R.id.et_namabaru)
        val etPasswordBaru = findViewById<EditText>(R.id.et_pwbaru)
        val etKonfirmasiPassword = findViewById<EditText>(R.id.et_konfirmasi_pwbaru)
        val btnSimpan = findViewById<Button>(R.id.btn_simpan)

        btnSimpan.setOnClickListener {
            val namaBaru = etNamaBaru.text.toString().trim()
            val passwordBaru = etPasswordBaru.text.toString().trim()
            val konfirmasiPassword = etKonfirmasiPassword.text.toString().trim()

            if (namaBaru.isEmpty()) {
                etNamaBaru.error = "Nama baru tidak boleh kosong!"
                etNamaBaru.requestFocus()
                return@setOnClickListener
            }
            if (passwordBaru.isEmpty()) {
                etPasswordBaru.error = "Password baru tidak boleh kosong!"
                etPasswordBaru.requestFocus()
                return@setOnClickListener
            }
            if (konfirmasiPassword.isEmpty()) {
                etKonfirmasiPassword.error = "Konfirmasi password tidak boleh kosong!"
                etKonfirmasiPassword.requestFocus()
                return@setOnClickListener
            }
            if (passwordBaru != konfirmasiPassword) {

                etKonfirmasiPassword.error = "Password konfirmasi tidak cocok!"
                etKonfirmasiPassword.requestFocus()
                return@setOnClickListener
            }
            Toast.makeText(this, "Profil berhasil diperbarui!", Toast.LENGTH_SHORT).show()
        }
    }
}