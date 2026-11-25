package com.example.myapplication // Conserva aquí el nombre exacto de tu paquete original

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarse2)

        val etNombre = findViewById<EditText>(R.id.etRegNombre)
        val etUsuario = findViewById<EditText>(R.id.etRegUsuario)
        val etEmail = findViewById<EditText>(R.id.etRegEmail)
        val etPassword = findViewById<EditText>(R.id.etRegPassword)
        val btnCrearCuenta = findViewById<Button>(R.id.btnCrearCuenta)
        val tvIrALogin = findViewById<TextView>(R.id.tvIrALogin)

        btnCrearCuenta.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val usuario = etUsuario.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (nombre.isEmpty() || usuario.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos para registrarte", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.length < 6) {
                Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, HomeActivity::class.java).apply {
                putExtra("USER_NAME", nombre)
                putExtra("USER_USERNAME", usuario)
                putExtra("USER_EMAIL", email)
            }
            startActivity(intent)
            finish()
        }

        tvIrALogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}