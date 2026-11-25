package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ARKAApp()
        }
    }
}

@Composable
fun ARKAApp() {
    var selectedTab by remember { mutableIntStateOf(0) }
    val primaryPurple = Color(0xFF7C3AED)
    val navBgColor = Color(0xFF16192B)

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = navBgColor,
                contentColor = Color.White
            ) {
                NavigationBarItem(
                    icon = { Text("📋", fontSize = 20.sp) },
                    label = { Text("Revisión", fontSize = 11.sp, fontWeight = FontWeight.Bold) },
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        selectedTextColor = primaryPurple,
                        unselectedIconColor = Color.Gray,
                        unselectedTextColor = Color.Gray,
                        indicatorColor = primaryPurple.copy(alpha = 0.3f)
                    )
                )
                NavigationBarItem(
                    icon = { Text("📊", fontSize = 20.sp) },
                    label = { Text("Reportes", fontSize = 11.sp, fontWeight = FontWeight.Bold) },
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        selectedTextColor = primaryPurple,
                        unselectedIconColor = Color.Gray,
                        unselectedTextColor = Color.Gray,
                        indicatorColor = primaryPurple.copy(alpha = 0.3f)
                    )
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedTab) {
                0 -> RevisionScreen()
                1 -> ReportScreen()
            }
        }
    }
}
