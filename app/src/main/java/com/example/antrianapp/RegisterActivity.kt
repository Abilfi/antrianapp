package com.example.antrianapp

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.example.antrianapp.databinding.ActivityRegisterBinding
import com.example.antrianapp.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        binding.btnRegister.setOnClickListener {
            handleRegister()
        }
    }

    private fun handleRegister() {
        val nama = binding.etNamaLengkap.text.toString().trim()
        val email = binding.etUsername.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        val konfirmasi = binding.etKonfirmasiPassword.text.toString().trim()

        // Validasi input
        if (nama.isEmpty() || email.isEmpty() || password.isEmpty() || konfirmasi.isEmpty()) {
            showValidasi("Semua field wajib diisi!")
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showValidasi("Format email tidak valid!")
            return
        }

        if (password.length < 6) {
            showValidasi("Password minimal 6 karakter!")
            return
        }

        if (password != konfirmasi) {
            showValidasi("Konfirmasi password tidak sama!")
            return
        }

        binding.tvValidasi.visibility = android.view.View.GONE
        binding.btnRegister.isEnabled = false

        // Buat akun di Firebase Auth
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->
                val uid = result.user?.uid ?: return@addOnSuccessListener

                val user = User(
                    uid = uid,
                    namaLengkap = nama,
                    username = email,
                    email = email
                )

                // Simpan data profil ke Firestore
                firestore.collection("users")
                    .document(uid)
                    .set(user)
                    .addOnSuccessListener {
                        binding.btnRegister.isEnabled = true
                        startActivity(Intent(this, DashboardActivity::class.java))
                        finish()
                    }
                    .addOnFailureListener { e ->
                        binding.btnRegister.isEnabled = true
                        showValidasi("Gagal menyimpan data: ${e.message}")
                    }
            }
            .addOnFailureListener { e ->
                binding.btnRegister.isEnabled = true
                showValidasi("Registrasi gagal: ${e.message}")
            }
    }

    private fun showValidasi(pesan: String) {
        binding.tvValidasi.text = pesan
        binding.tvValidasi.visibility = android.view.View.VISIBLE
    }
}