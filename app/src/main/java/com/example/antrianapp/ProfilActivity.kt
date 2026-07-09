package com.example.antrianapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
class ProfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)
        val tvEditProfil = findViewById<TextView>(R.id.teks_edit_profil)

        tvEditProfil.setOnClickListener {
            val intent = Intent(this, EditProfilActivity::class.java)
            startActivity(intent)
        }
    val navRiwayat = findViewById<ImageView>(R.id.navigasi_riwayat)
    val navhome = findViewById<ImageView>(R.id.navigasi_dashboard)

    navRiwayat.setOnClickListener {
        val intent = Intent(this, RiwayatActivity::class.java)
        startActivity(intent)
        overridePendingTransition(0, 0)
    }

    navhome.setOnClickListener {
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
        overridePendingTransition(0, 0)
    }
}
}