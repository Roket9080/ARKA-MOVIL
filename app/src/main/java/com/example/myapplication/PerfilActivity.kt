package com.example.myapplication // Conserva aquí el nombre exacto de tu paquete original

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PerfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        val nombreRecibido = intent.getStringExtra("USER_NAME") ?: "Miguel"
        val emailRecibido = intent.getStringExtra("USER_EMAIL") ?: "usuario@arka.com"

        val tvAvatarGrande = findViewById<TextView>(R.id.tvPerfilAvatarGrande)
        val tvTituloNombre = findViewById<TextView>(R.id.tvPerfilTituloNombre)
        val tvSubtituloEmail = findViewById<TextView>(R.id.tvPerfilSubtituloEmail)
        val etNombre = findViewById<EditText>(R.id.etPerfilNombre)
        val etEmail = findViewById<EditText>(R.id.etPerfilEmail)

        tvAvatarGrande.text = nombreRecibido.firstOrNull()?.uppercase() ?: "M"
        tvTituloNombre.text = nombreRecibido
        tvSubtituloEmail.text = emailRecibido
        etNombre.setText(nombreRecibido)
        etEmail.setText(emailRecibido)

        findViewById<Button>(R.id.btnGuardarPerfil).setOnClickListener {
            val nuevoNombre = etNombre.text.toString().trim()
            val nuevoEmail = etEmail.text.toString().trim()

            if (nuevoNombre.isEmpty() || nuevoEmail.isEmpty()) {
                Toast.makeText(this, "Completa nombre y correo", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            tvTituloNombre.text = nuevoNombre
            tvSubtituloEmail.text = nuevoEmail
            tvAvatarGrande.text = nuevoNombre.firstOrNull()?.uppercase() ?: "M"
            Toast.makeText(this, "Perfil actualizado", Toast.LENGTH_SHORT).show()
        }

        findViewById<TextView>(R.id.btnVolverPerfil).setOnClickListener {
            finish()
        }
    }
}