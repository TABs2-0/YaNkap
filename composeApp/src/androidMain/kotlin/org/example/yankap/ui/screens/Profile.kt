package org.example.yankap.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileScreen() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .verticalScroll(scrollState)
    ) {
        // Top Profile Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(bottom = 24.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFE8F5EE)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = Color(0xFF1A7A4A),
                        modifier = Modifier.size(60.dp)
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    "Frank Nkeng",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "+237 670 123 456",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE8F5EE))
                ) {
                    Text("Edit Profile", color = Color(0xFF1A7A4A), fontSize = 14.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Settings Groups
        SettingsGroup("Account Settings", listOf(
            SettingItem("Bank Accounts", Icons.Default.AccountBalance),
            SettingItem("My Cards", Icons.Default.CreditCard),
            SettingItem("Transaction Limits", Icons.Default.TrendingUp)
        ))

        Spacer(modifier = Modifier.height(16.dp))

        SettingsGroup("Security", listOf(
            SettingItem("Change PIN", Icons.Default.Lock),
            SettingItem("Biometric Login", Icons.Default.Fingerprint),
            SettingItem("Privacy Policy", Icons.Default.Shield)
        ))

        Spacer(modifier = Modifier.height(16.dp))

        SettingsGroup("General", listOf(
            SettingItem("Support Help", Icons.Default.HeadsetMic),
            SettingItem("Language", Icons.Default.Language),
            SettingItem("About YaNkap", Icons.Default.Info)
        ))

        Spacer(modifier = Modifier.height(24.dp))

        TextButton(
            onClick = {},
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            colors = ButtonDefaults.textButtonColors(contentColor = Color.Red)
        ) {
            Icon(Icons.Default.Logout, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Logout", fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun SettingsGroup(title: String, items: List<SettingItem>) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Gray,
            modifier = Modifier.padding(start = 4.dp, bottom = 8.dp)
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(Color.White)
        ) {
            Column {
                items.forEachIndexed { index, item ->
                    SettingRow(item)
                    if (index < items.size - 1) {
                        HorizontalDivider(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            color = Color(0xFFF5F5F5),
                            thickness = 1.dp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SettingRow(item: SettingItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = null,
            tint = Color(0xFF1A7A4A),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = item.label,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
            tint = Color.LightGray,
            modifier = Modifier.size(20.dp)
        )
    }
}

data class SettingItem(val label: String, val icon: ImageVector)
