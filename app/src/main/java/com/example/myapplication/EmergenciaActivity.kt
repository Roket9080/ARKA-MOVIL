package com.example.myapplication

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EmergenciaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_emergencia)

        val btnLlamar123 =
            findViewById<Button>(R.id.btnLlamar123)

        val btnLlamar106 =
            findViewById<Button>(R.id.btnLlamar106)

        val btnVolver =
            findViewById<Button>(R.id.btnVolver)

        // LLAMADA DE EMERGENCIA

        btnLlamar123.setOnClickListener {
            abrirMarcador("123")
        }

        // LÍNEA DE SALUD MENTAL

        btnLlamar106.setOnClickListener {
            abrirMarcador("106")
        }

        // REGRESAR A LA PRIMERA PANTALLA

        btnVolver.setOnClickListener {
            finish()
        }
    }

    // ABRIR MARCADOR TELEFÓNICO

    private fun abrirMarcador(numero: String) {

        val intent = Intent(
            Intent.ACTION_DIAL,
            Uri.parse("tel:$numero")
        )

        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {

            Toast.makeText(
                this,
                "No se encontró una aplicación de llamadas",
                Toast.LENGTH_LONG
            ).show()

        }
    }
}
