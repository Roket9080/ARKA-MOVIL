package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // CAMPOS DEL FORMULARIO

        val edtNombre =
            findViewById<EditText>(R.id.edtNombre)

        val edtMotivo =
            findViewById<EditText>(R.id.edtMotivo)

        val edtAntecedentes =
            findViewById<EditText>(R.id.edtAntecedentes)

        val edtEvaluacion =
            findViewById<EditText>(R.id.edtEvaluacion)

        val grupoBienestar =
            findViewById<RadioGroup>(R.id.grupoBienestar)

        // BOTONES

        val btnSolicitar =
            findViewById<Button>(R.id.btnSolicitarAtencion)

        val btnEmergencia =
            findViewById<Button>(R.id.btnEmergencia)

        val btnIrEmergencia =
            findViewById<Button>(R.id.btnIrEmergencia)

        // PREPARAR SOLICITUD

        btnSolicitar.setOnClickListener {

            val nombre = edtNombre.text.toString().trim()
            val motivo = edtMotivo.text.toString().trim()

            if (nombre.isEmpty()) {
                edtNombre.error = "Ingresa tu nombre"
                edtNombre.requestFocus()
                return@setOnClickListener
            }

            if (motivo.isEmpty()) {
                edtMotivo.error = "Describe el motivo de atención"
                edtMotivo.requestFocus()
                return@setOnClickListener
            }

            val estado = when (grupoBienestar.checkedRadioButtonId) {
                R.id.radioBien -> "Me siento bien"
                R.id.radioRegular -> "He tenido algunas dificultades"
                R.id.radioMal -> "Estoy pasando por un momento difícil"
                else -> "No especificado"
            }

            val resumen = """
                Nombre: $nombre

                Motivo de atención:
                $motivo

                Estado emocional:
                $estado

                Este es un resumen de prueba.
                La solicitud no se ha enviado ni registrado.

                Para recibir atención debes comunicarte
                con un servicio de salud profesional.
            """.trimIndent()

            AlertDialog.Builder(this)
                .setTitle("Resumen de solicitud")
                .setMessage(resumen)
                .setPositiveButton("Entendido", null)
                .show()
        }

        // ABRIR SEGUNDA PANTALLA

        btnEmergencia.setOnClickListener {
            abrirEmergencia()
        }

        btnIrEmergencia.setOnClickListener {
            abrirEmergencia()
        }
    }

    private fun abrirEmergencia() {

        val intent = Intent(
            this,
            EmergenciaActivity::class.java
        )

        startActivity(intent)
    }
}
