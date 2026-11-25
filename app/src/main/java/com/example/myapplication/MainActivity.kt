package com.example.myapplication // Conserva aquí el nombre exacto de tu paquete original

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Pintar "cuidar la vida" en morado dentro del título principal
        val tvHeroTitle = findViewById<TextView>(R.id.tvHeroTitle)
        val textoHero = "Tecnología con propósito\npara cuidar la vida"
        val spannableHero = SpannableString(textoHero)
        spannableHero.setSpan(
            ForegroundColorSpan(Color.parseColor("#8B5CF6")),
            textoHero.indexOf("cuidar la vida"),
            textoHero.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        tvHeroTitle.text = spannableHero

        // 2. Pintar "ARKA" en morado en "¿Cómo funciona ARKA?"
        val tvComoFunciona = findViewById<TextView>(R.id.tvComoFunciona)
        val textoComo = "¿Cómo funciona ARKA?"
        val spannableComo = SpannableString(textoComo)
        spannableComo.setSpan(
            ForegroundColorSpan(Color.parseColor("#8B5CF6")),
            textoComo.indexOf("ARKA"),
            textoComo.indexOf("ARKA") + 4,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        tvComoFunciona.text = spannableComo

        // 3. Navegación hacia Iniciar Sesión, Registrarse y Línea de Crisis
        val btnIrLogin = findViewById<Button>(R.id.btnIrLogin)
        val btnIrRegistro = findViewById<Button>(R.id.btnIrRegistro)
        val btnLineaCrisis = findViewById<Button>(R.id.btnLineaCrisis)

        btnIrLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        btnIrRegistro.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        btnLineaCrisis.setOnClickListener {
            Toast.makeText(this, "Abriendo Línea de Crisis 24/7...", Toast.LENGTH_SHORT).show()
        }
    }
}