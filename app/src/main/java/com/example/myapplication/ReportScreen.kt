package com.example.myapplication

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ReportScreen() {
    var nivelRiesgo by remember { mutableStateOf("Bajo") }
    var tipoIntervencion by remember { mutableStateOf("Apoyo psicológico") }
    var fuenteReporte by remember { mutableStateOf("Autoevaluación") }
    var zonaTerritorio by remember { mutableStateOf("Popayán") }
    var fechaRegistro by remember { mutableStateOf("25/11/2026") }
    var filtroBusqueda by remember { mutableStateOf("") }

    var expandedNivel by remember { mutableStateOf(false) }
    var expandedIntervencion by remember { mutableStateOf(false) }

    val nivelesRiesgo = listOf("Bajo", "Medio", "Alto", "Crítico")
    val intervenciones = listOf("Apoyo psicológico", "Intervención en crisis", "Asesoría familiar", "Derivación médica")

    val bgColor = Color(0xFF0F111A)
    val cardBgColor = Color(0xFF16192B)
    val inputBgColor = Color(0xFF1E2238)
    val borderColor = Color(0xFF2A2E4B)
    val primaryPurple = Color(0xFF7C3AED)
    val redBadge = Color(0xFFE11D48)
    val greenBadge = Color(0xFF10B981)
    val accentLightPurple = Color(0xFFC084FC) // Moradito claro

    val textFieldColors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = Color.White,
        unfocusedTextColor = Color.White,
        focusedContainerColor = inputBgColor,
        unfocusedContainerColor = inputBgColor,
        focusedBorderColor = primaryPurple,
        unfocusedBorderColor = borderColor,
        cursorColor = primaryPurple
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Top Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo ARKA",
                    modifier = Modifier
                        .size(38.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "ARKA",
                    color = accentLightPurple,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .background(redBadge)
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "SECRETARÍA DE SALUD",
                    color = Color.White,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Card Container for "Registrar Datos de Reporte"
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = cardBgColor),
            border = BorderStroke(1.dp, borderColor)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Registrar Datos de Reporte",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .border(1.dp, greenBadge, RoundedCornerShape(50.dp))
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "Prioridad seleccionada: ",
                            color = Color.Gray,
                            fontSize = 12.sp
                        )
                        Text(
                            text = nivelRiesgo,
                            color = greenBadge,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                HorizontalDivider(color = borderColor, thickness = 1.dp)

                // Nivel de Riesgo & Tipo de Intervención
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text("NIVEL DE RIESGO:", color = Color.Gray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .background(inputBgColor)
                                .border(1.dp, borderColor, RoundedCornerShape(8.dp))
                                .clickable { expandedNivel = true }
                                .padding(14.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(nivelRiesgo, color = Color.White, fontSize = 14.sp)
                                Text("▼", color = Color.Gray, fontSize = 10.sp)
                            }
                            DropdownMenu(
                                expanded = expandedNivel,
                                onDismissRequest = { expandedNivel = false }
                            ) {
                                nivelesRiesgo.forEach { niv ->
                                    DropdownMenuItem(
                                        text = { Text(niv) },
                                        onClick = {
                                            nivelRiesgo = niv
                                            expandedNivel = false
                                        }
                                    )
                                }
                            }
                        }
                    }

                    Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text("TIPO DE INTERVENCIÓN:", color = Color.Gray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .background(inputBgColor)
                                .border(1.dp, borderColor, RoundedCornerShape(8.dp))
                                .clickable { expandedIntervencion = true }
                                .padding(14.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(tipoIntervencion, color = Color.White, fontSize = 13.sp, maxLines = 1)
                                Text("▼", color = Color.Gray, fontSize = 10.sp)
                            }
                            DropdownMenu(
                                expanded = expandedIntervencion,
                                onDismissRequest = { expandedIntervencion = false }
                            ) {
                                intervenciones.forEach { inte ->
                                    DropdownMenuItem(
                                        text = { Text(inte) },
                                        onClick = {
                                            tipoIntervencion = inte
                                            expandedIntervencion = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                }

                // Fuente del Reporte & Zona
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text("FUENTE DEL REPORTE:", color = Color.Gray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        OutlinedTextField(
                            value = fuenteReporte,
                            onValueChange = { fuenteReporte = it },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp),
                            singleLine = true,
                            textStyle = TextStyle(color = Color.White, fontSize = 14.sp),
                            colors = textFieldColors
                        )
                    }

                    Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text("ZONA / TERRITORIO:", color = Color.Gray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        OutlinedTextField(
                            value = zonaTerritorio,
                            onValueChange = { zonaTerritorio = it },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp),
                            singleLine = true,
                            textStyle = TextStyle(color = Color.White, fontSize = 14.sp),
                            colors = textFieldColors
                        )
                    }
                }

                // Fecha de Registro
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("FECHA DE REGISTRO:", color = Color.Gray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    OutlinedTextField(
                        value = fechaRegistro,
                        onValueChange = { fechaRegistro = it },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true,
                        textStyle = TextStyle(color = Color.White, fontSize = 14.sp),
                        colors = textFieldColors
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { /* Generate/Save Report action */ },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = primaryPurple)
                    ) {
                        Text("Generar / Guardar Reporte", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    }

                    OutlinedButton(
                        onClick = {
                            nivelRiesgo = "Bajo"
                            tipoIntervencion = "Apoyo psicológico"
                            fuenteReporte = ""
                            zonaTerritorio = ""
                            fechaRegistro = ""
                        },
                        modifier = Modifier
                            .weight(0.8f)
                            .height(50.dp),
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(1.dp, borderColor)
                    ) {
                        Text("Limpiar campos", color = Color.Gray, fontSize = 13.sp)
                    }
                }
            }
        }

        // Bottom Table Section
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = cardBgColor),
            border = BorderStroke(1.dp, borderColor)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Consolidado de Casos",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(RoundedCornerShape(50.dp))
                                .background(primaryPurple),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("1", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    OutlinedTextField(
                        value = filtroBusqueda,
                        onValueChange = { filtroBusqueda = it },
                        placeholder = { Text("Filtrar por nivel, zona o fuente...", color = Color.Gray, fontSize = 12.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true,
                        textStyle = TextStyle(color = Color.White, fontSize = 14.sp),
                        colors = textFieldColors
                    )
                }

                // Table Header
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF1E2238), RoundedCornerShape(6.dp))
                        .padding(horizontal = 12.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("ID", color = Color.Gray, fontSize = 11.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.5f))
                    Text("RIESGO", color = Color.Gray, fontSize = 11.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                    Text("INTERVENCIÓN", color = Color.Gray, fontSize = 11.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f))
                    Text("ZONA", color = Color.Gray, fontSize = 11.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                    Text("FECHA", color = Color.Gray, fontSize = 11.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                }

                // Sample Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("1", color = Color.White, fontSize = 12.sp, modifier = Modifier.weight(0.5f))
                    Text("Bajo", color = greenBadge, fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                    Text("Apoyo psicológico", color = Color.White, fontSize = 12.sp, modifier = Modifier.weight(1.5f))
                    Text("Popayán", color = Color.White, fontSize = 12.sp, modifier = Modifier.weight(1f))
                    Text("25/11/2026", color = Color.White, fontSize = 12.sp, modifier = Modifier.weight(1f))
                }
            }
        }
    }
}
