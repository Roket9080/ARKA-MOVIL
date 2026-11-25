package com.example.myapplication // Conserva aquí el nombre exacto de tu paquete original

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etEmail = findViewById<EditText>(R.id.etLoginEmail)
        val etPassword = findViewById<EditText>(R.id.etLoginPassword)
        val btnEntrar = findViewById<Button>(R.id.btnEntrar)
        val tvIrARegistro = findViewById<TextView>(R.id.tvIrARegistro)

        btnEntrar.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor completa correo y contraseña", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Toma la primera parte del correo como nombre provisional para mostrar en el perfil
            val nombreUsuario = email.substringBefore("@").replaceFirstChar { it.uppercase() }

            val intent = Intent(this, HomeActivity::class.java).apply {
                putExtra("USER_NAME", nombreUsuario)
                putExtra("USER_EMAIL", email)
            }
            startActivity(intent)
            finish()
        }

        tvIrARegistro.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }
}