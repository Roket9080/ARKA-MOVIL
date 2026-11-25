package com.example.myapplication // Conserva aquí el nombre exacto de tu paquete original

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Recibir datos desde Login o Registro (priorizando el nombre de usuario si existe)
        val nombre = intent.getStringExtra("USER_NAME") ?: "Miguel"
        val usuario = intent.getStringExtra("USER_USERNAME")
        val email = intent.getStringExtra("USER_EMAIL") ?: "usuario@arka.com"

        val tvSaludoUsuario = findViewById<TextView>(R.id.tvSaludoUsuario)
        val btnAvatarPerfil = findViewById<TextView>(R.id.btnAvatarPerfil)

        val nombreMostrar = if (!usuario.isNullOrEmpty()) usuario else nombre
        tvSaludoUsuario.text = "Hola, $nombreMostrar"
        btnAvatarPerfil.text = nombre.firstOrNull()?.uppercase() ?: "M"

        // Menú desplegable al tocar el avatar circular
        btnAvatarPerfil.setOnClickListener { view ->
            val popup = PopupMenu(this, view)
            popup.menuInflater.inflate(R.menu.menu_perfil, popup.menu)

            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_editar_perfil -> {
                        val intentPerfil = Intent(this, PerfilActivity::class.java).apply {
                            putExtra("USER_NAME", nombre)
                            putExtra("USER_EMAIL", email)
                        }
                        startActivity(intentPerfil)
                        true
                    }
                    R.id.action_configuracion -> {
                        Toast.makeText(this, "Abriendo Configuración...", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.action_cerrar_sesion -> {
                        val intent = Intent(this, MainActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        }
                        startActivity(intent)
                        finish()
                        true
                    }
                    else -> false
                }
            }
            popup.show()
        }

        // Eventos de los botones y módulos
        findViewById<Button>(R.id.btnSosHome).setOnClickListener {
            Toast.makeText(this, "Conectando con Línea de Crisis 24/7...", Toast.LENGTH_SHORT).show()
        }

        findViewById<LinearLayout>(R.id.optBitacora).setOnClickListener {
            Toast.makeText(this, "Abriendo Bitácora Emocional...", Toast.LENGTH_SHORT).show()
        }

        findViewById<LinearLayout>(R.id.optChatbot).setOnClickListener {
            Toast.makeText(this, "Iniciando Asistente de Apoyo...", Toast.LENGTH_SHORT).show()
        }

        findViewById<LinearLayout>(R.id.optEspecialistas).setOnClickListener {
            Toast.makeText(this, "Cargando Red de Especialistas...", Toast.LENGTH_SHORT).show()
        }
    }
}