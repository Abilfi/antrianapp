package com.example.antrianapp.model

data class User(
    val uid: String = "",
    val namaLengkap: String = "",
    val username: String = "",
    val email: String = "",
    val createdAt: Long = System.currentTimeMillis()
)
