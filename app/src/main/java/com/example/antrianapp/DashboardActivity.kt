package com.example.antrianapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val btnAntrian = findViewById<Button>(R.id.btn_ambilantrian)
        btnAntrian.setOnClickListener {
            val bottomSheet = AntreanBottomSheet()
            bottomSheet.show(supportFragmentManager, "AntreanBottomSheetTag")
        }

        val navRiwayat = findViewById<ImageView>(R.id.navigasi_riwayat)
        val navProfil = findViewById<ImageView>(R.id.navigasi_profil)

        navRiwayat.setOnClickListener {
            val intent = Intent(this, RiwayatActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, 0) // Menghilangkan animasi jeda biar smooth
        }

        navProfil.setOnClickListener {
            val intent = Intent(this, ProfilActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }

    }
}