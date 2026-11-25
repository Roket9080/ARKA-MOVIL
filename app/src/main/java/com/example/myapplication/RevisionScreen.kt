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
fun RevisionScreen() {
    var usuarioEnRiesgo by remember { mutableStateOf("") }
    var informeAsociado by remember { mutableStateOf("") }
    var estadoRevision by remember { mutableStateOf("Pendiente") }
    var fechaRevision by remember { mutableStateOf("25/11/2026") }
    var observaciones by remember { mutableStateOf("") }
    var filtroBusqueda by remember { mutableStateOf("") }
    var expandedEstado by remember { mutableStateOf(false) }

    val estados = listOf("Pendiente", "En proceso", "Aprobado", "Rechazado")

    val bgColor = Color(0xFF0F111A)
    val cardBgColor = Color(0xFF16192B)
    val inputBgColor = Color(0xFF1E2238)
    val borderColor = Color(0xFF2A2E4B)
    val primaryPurple = Color(0xFF7C3AED)
    val redBadge = Color(0xFFE11D48)
    val yellowBadge = Color(0xFFF59E0B)
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

        // Card Container for "Registrar / Gestionar Revisión"
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
                        text = "Registrar / Gestionar Revisión",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .border(1.dp, yellowBadge, RoundedCornerShape(50.dp))
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "Estado seleccionado: ",
                            color = Color.Gray,
                            fontSize = 12.sp
                        )
                        Text(
                            text = estadoRevision,
                            color = yellowBadge,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                HorizontalDivider(color = borderColor, thickness = 1.dp)

                // Usuario en Riesgo
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("USUARIO EN RIESGO:", color = Color.Gray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    OutlinedTextField(
                        value = usuarioEnRiesgo,
                        onValueChange = { usuarioEnRiesgo = it },
                        placeholder = { Text("Ej: Carlos Gómez", color = Color.Gray) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true,
                        textStyle = TextStyle(color = Color.White, fontSize = 14.sp),
                        colors = textFieldColors
                    )
                }

                // Informe Asociado
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("INFORME ASOCIADO:", color = Color.Gray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    OutlinedTextField(
                        value = informeAsociado,
                        onValueChange = { informeAsociado = it },
                        placeholder = { Text("Ej: Informe psicológico", color = Color.Gray) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true,
                        textStyle = TextStyle(color = Color.White, fontSize = 14.sp),
                        colors = textFieldColors
                    )
                }

                // Estado de Revisión & Fecha de Revisión
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text("ESTADO DE REVISIÓN:", color = Color.Gray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .background(inputBgColor)
                                .border(1.dp, borderColor, RoundedCornerShape(8.dp))
                                .clickable { expandedEstado = true }
                                .padding(14.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(estadoRevision, color = Color.White, fontSize = 14.sp)
                                Text("▼", color = Color.Gray, fontSize = 10.sp)
                            }
                            DropdownMenu(
                                expanded = expandedEstado,
                                onDismissRequest = { expandedEstado = false }
                            ) {
                                estados.forEach { est ->
                                    DropdownMenuItem(
                                        text = { Text(est) },
                                        onClick = {
                                            estadoRevision = est
                                            expandedEstado = false
                                        }
                                    )
                                }
                            }
                        }
                    }

                    Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text("FECHA DE REVISIÓN:", color = Color.Gray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        OutlinedTextField(
                            value = fechaRevision,
                            onValueChange = { fechaRevision = it },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp),
                            singleLine = true,
                            textStyle = TextStyle(color = Color.White, fontSize = 14.sp),
                            colors = textFieldColors
                        )
                    }
                }

                // Observaciones
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("OBSERVACIONES:", color = Color.Gray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    OutlinedTextField(
                        value = observaciones,
                        onValueChange = { observaciones = it },
                        placeholder = { Text("Acciones realizadas...", color = Color.Gray) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        shape = RoundedCornerShape(8.dp),
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
                        onClick = { /* Save action */ },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = primaryPurple)
                    ) {
                        Text("Guardar Revisión", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                    }

                    OutlinedButton(
                        onClick = {
                            usuarioEnRiesgo = ""
                            informeAsociado = ""
                            estadoRevision = "Pendiente"
                            observaciones = ""
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

        // Bottom Table Section: Casos en Seguimiento
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
                            text = "Casos en Seguimiento",
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
                        placeholder = { Text("Filtrar por usuario o estado...", color = Color.Gray, fontSize = 12.sp) },
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
                    Text("USUARIO", color = Color.Gray, fontSize = 11.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f))
                    Text("INFORME", color = Color.Gray, fontSize = 11.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f))
                    Text("ESTADO", color = Color.Gray, fontSize = 11.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
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
                    Text("Carlos Gómez", color = Color.White, fontSize = 12.sp, modifier = Modifier.weight(1.5f))
                    Text("Inf. Psicológico", color = Color.White, fontSize = 12.sp, modifier = Modifier.weight(1.5f))
                    Text("Pendiente", color = yellowBadge, fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                    Text("25/11/2026", color = Color.White, fontSize = 12.sp, modifier = Modifier.weight(1f))
                }
            }
        }
    }
}
