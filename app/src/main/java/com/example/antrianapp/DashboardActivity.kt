package com.example.antrianapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 1. Hubungkan dengan file XML dashboard kamu
        setContentView(R.layout.activity_dashboard)

        // ========================================================
        // BAGIAN 1: LOGIKA POP-UP TOMBOL AMBIL ANTREAN
        // ========================================================
        val btnAntrian = findViewById<Button>(R.id.btn_ambilantrian)

        btnAntrian.setOnClickListener {
            // Memanggil class AntreanBottomSheet yang benar-benar sebuah dialog pop-up
            val bottomSheet = AntreanBottomSheet()
            bottomSheet.show(supportFragmentManager, "AntreanBottomSheetTag")
        }

        // ========================================================
        // BAGIAN 2: LOGIKA NAVIGASI BAWAH (BOTTOM NAVIGATION)
        // ========================================================

        // Inisialisasi ID komponen dari XML (Pastikan ID ini sesuai dengan XML kamu)
        val navRiwayat = findViewById<ImageView>(R.id.navigasi_riwayat)     // Ikon kertas (kiri)
        val navDashboard = findViewById<ImageView>(R.id.navigasi_dashboard) // Ikon home (tengah)
        val navProfil = findViewById<ImageView>(R.id.navigasi_profil)       // Ikon profil (kanan)

        // Klik Ikon Kertas (Riwayat) -> Pindah ke Halaman PilihAntrianActivity
        navRiwayat.setOnClickListener {
            // PERBAIKAN: Diubah ke PilihAntrianActivity karena Intent butuh Activity, bukan BottomSheet
            val intent = Intent(this, AntrianActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, 0) // Menghilangkan animasi jeda biar smooth
        }

        // Klik Ikon Home (Dashboard) -> Karena sudah di Dashboard, tidak perlu pindah halaman
        navDashboard.setOnClickListener {
            // Opsional: Bisa dikosongkan atau diisi logika refresh data
        }

        // Klik Ikon Profil -> Pindah ke RegisterActivity
        navProfil.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }

    } // <-- Sekarang tanda kurung penutup onCreate berada di sini (setelah semua kode selesai)
}