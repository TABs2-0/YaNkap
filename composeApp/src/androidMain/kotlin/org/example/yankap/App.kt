package org.example.yankap

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.yankap.ui.screens.*

enum class AppScreen(val label: String, val icon: ImageVector) {
    HOME("Home", Icons.Default.Home),
    OPERATIONS("Operations", Icons.Default.GridView),
    TRANSFER("Transfer", Icons.Default.Refresh),
    HISTORY("History", Icons.Default.History),
    PROFILE("Profile", Icons.Default.AccountCircle)
}

enum class AuthStep {
    PHONE_ENTRY,
    OTP_VERIFICATION,
    AUTHENTICATED
}

@Suppress("FunctionName")
@Composable
fun App() {
    var authStep by remember { mutableStateOf(AuthStep.PHONE_ENTRY) }
    var currentScreen by remember { mutableStateOf(AppScreen.HOME) }
    var phoneNumber by remember { mutableStateOf("") }
    
    val green = Color(0xFF1A7A4A)
    val lightGreen = Color(0xFFE8F5EE)

    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {
            if (authStep != AuthStep.AUTHENTICATED) {
                when (authStep) {
                    AuthStep.PHONE_ENTRY -> {
                        PhoneEntryScreen(onSendOtp = { num ->
                            phoneNumber = num
                            authStep = AuthStep.OTP_VERIFICATION
                        })
                    }
                    AuthStep.OTP_VERIFICATION -> {
                        OtpVerificationScreen(
                            phoneNumber = phoneNumber,
                            onVerify = { code ->
                                // Simulate verification
                                authStep = AuthStep.AUTHENTICATED
                            },
                            onBack = {
                                authStep = AuthStep.PHONE_ENTRY
                            }
                        )
                    }

                    else -> {}
                }
            } else {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        NavigationBar(containerColor = Color.White) {
                            AppScreen.values().forEach { screen ->
                                NavigationBarItem(
                                    selected = currentScreen == screen,
                                    onClick = { currentScreen = screen },
                                    icon = {
                                        Icon(
                                            imageVector = screen.icon,
                                            contentDescription = screen.label,
                                            modifier = Modifier.size(22.dp)
                                        )
                                    },
                                    label = {
                                        Text(screen.label, fontSize = 11.sp)
                                    },
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedIconColor = green,
                                        selectedTextColor = green,
                                        indicatorColor = lightGreen,
                                        unselectedIconColor = Color.Gray,
                                        unselectedTextColor = Color.Gray
                                    )
                                )
                            }
                        }
                    }
                ) { paddingValues ->
                    Box(modifier = Modifier.padding(paddingValues)) {
                        when (currentScreen) {
                            AppScreen.HOME -> HomeScreenContent()
                            AppScreen.OPERATIONS -> OperationsScreen()
                            AppScreen.TRANSFER -> TransferScreen()
                            AppScreen.HISTORY -> HistoryScreen()
                            AppScreen.PROFILE -> ProfileScreen()
                        }
                    }
                }
            }
        }
    }
}