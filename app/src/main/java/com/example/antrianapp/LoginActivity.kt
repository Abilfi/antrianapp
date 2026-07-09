package com.example.antrianapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.antrianapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {
            handleLogin()
        }

        binding.tvGoRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        // Kalau user sudah login sebelumnya, langsung ke Home
        if (auth.currentUser != null) {
            goToHome()
        }
    }

    private fun handleLogin() {
        val email = binding.etUsername.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            showValidasi("Username dan password wajib diisi!")
            return
        }

        binding.tvValidasi.visibility = android.view.View.GONE
        binding.btnLogin.isEnabled = false

        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                binding.btnLogin.isEnabled = true
                goToHome()
            }
            .addOnFailureListener { e ->
                binding.btnLogin.isEnabled = true
                showValidasi("Login gagal: username/password salah")
            }
    }

    private fun goToHome() {
        // TODO: ganti MainActivity dengan halaman Home pasien kamu
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun showValidasi(pesan: String) {
        binding.tvValidasi.text = pesan
        binding.tvValidasi.visibility = android.view.View.VISIBLE
    }
}