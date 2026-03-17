package org.example.yankap.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import yankap.composeapp.generated.resources.Res
import yankap.composeapp.generated.resources.home_background

val Green = Color(0xFF1A7A4A)
val LightGreen = Color(0xFFE8F5EE)
val OrangeM = Color(0xFFF57C00)

data class NavItem(val label: String, val icon: ImageVector)
data class Transaction(val title: String, val subtitle: String, val date: String, val amount: String, val ref: String)
data class QuickAction(val label: String, val icon: ImageVector, val description: String)

class Home() {

    @SuppressLint("NotConstructor")
    @Composable
    fun Home() {
        val navItems = listOf(
            NavItem("Home", Icons.Default.Home),
            NavItem("Operations", Icons.Default.GridView),
            NavItem("Transfer", Icons.Default.Refresh),
            NavItem("History", Icons.Default.History),
            NavItem("Profile", Icons.Default.AccountCircle)
        )
        var selectedNav by remember { mutableStateOf(0) }

        val transactions = listOf(
            Transaction("Send Money", "Jean Dupont", "Mar 9", "FCFA 5,000", "REF123456789"),
            Transaction("Buy Airtime", "Self", "Mar 9", "FCFA 1,000", "REF123456788")
        )

        val quickActions = listOf(
            QuickAction("Send Money", Icons.Default.Send, "Send money to anyone"),
            QuickAction("Buy Airtime", Icons.Default.Refresh, "Buy airtime instantly"),
            QuickAction("Pay Bill", Icons.Default.GridView, "Pay utility bills and services"),
            QuickAction("Check Balance", Icons.Default.AccountCircle, "View your current balance")
        )

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                NavigationBar(containerColor = Color.White) {
                    navItems.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedNav == index,
                            onClick = { selectedNav = index },
                            icon = {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.label,
                                    modifier = Modifier.size(22.dp)
                                )
                            },
                            label = {
                                Text(item.label, fontSize = 11.sp)
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Green,
                                selectedTextColor = Green,
                                indicatorColor = LightGreen,
                                unselectedIconColor = Color.Gray,
                                unselectedTextColor = Color.Gray
                            )
                        )
                    }
                }
            }
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color(0xFFF5F5F5)),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(Green),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("Y", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                            }
                            Spacer(Modifier.width(10.dp))
                            Column {
                                Text("YaNkap", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                Text("Fast Transfers", fontSize = 12.sp, color = Color.Gray)
                            }
                        }
                        Button(
                            onClick = {},
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = LightGreen),
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Icon(imageVector = Icons.Default.Wifi, contentDescription = "Wifi", tint = Green, modifier = Modifier.size(16.dp))
                            Spacer(Modifier.width(4.dp))
                            Text("Online", color = Green, fontSize = 13.sp)
                        }
                    }
                }

                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(Color.White),
                        onClick = {}
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(OrangeM),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("M", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                }
                                Spacer(Modifier.width(10.dp))
                                Column {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text("MTN", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                        Spacer(Modifier.width(4.dp))
                                        Icon(imageVector = Icons.Default.ArrowDownward, contentDescription = "", modifier = Modifier.size(14.dp), tint = Color.Gray)
                                    }
                                    Text("+237 670 123 456", fontSize = 12.sp, color = Color.Gray)
                                }
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("45,000 FCFA", fontWeight = FontWeight.Bold, color = Green, fontSize = 14.sp)
                                Spacer(Modifier.width(4.dp))
                                Icon(imageVector = Icons.Default.ArrowDownward, contentDescription = "", modifier = Modifier.size(14.dp), tint = Color.Gray)
                            }
                        }
                    }
                }

                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(280.dp)
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .clip(RoundedCornerShape(16.dp))
                    ) {
                        androidx.compose.foundation.Image(
                            painter = painterResource(Res.drawable.home_background),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color(0x55000000))
                        )
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Welcome Back", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
                                Button(
                                    onClick = {},
                                    shape = RoundedCornerShape(20.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = Color(0x44FFFFFF)),
                                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp)
                                ) {
                                    Icon(imageVector = Icons.Default.Wifi, contentDescription = "", tint = Color.White, modifier = Modifier.size(14.dp))
                                    Spacer(Modifier.width(4.dp))
                                    Text("Online", color = Color.White, fontSize = 12.sp)
                                }
                            }
                            Text("MTN • +237 670 123 456", fontSize = 13.sp, color = Color.White.copy(alpha = 0.85f))
                            Spacer(Modifier.height(12.dp))
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(Color.White)
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text("Current Balance", fontWeight = FontWeight.Light, fontSize = 13.sp, color = Color.Gray)
                                        Icon(imageVector = Icons.Default.ArrowUpward, contentDescription = "", tint = Green, modifier = Modifier.size(18.dp))
                                    }
                                    Spacer(Modifier.height(4.dp))
                                    Text("FCFA 45,000", fontWeight = FontWeight.Bold, fontSize = 24.sp)
                                    Spacer(Modifier.height(4.dp))
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(OrangeM))
                                        Spacer(Modifier.width(6.dp))
                                        Text("MTN", fontSize = 12.sp, color = Color.Gray)
                                    }
                                }
                            }
                        }
                    }
                }

                item {
                    Text(
                        "Quick Actions",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        quickActions.take(2).forEach { action ->
                            Card(
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(16.dp),
                                colors = CardDefaults.cardColors(Color.White),
                                onClick = {}
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(56.dp)
                                            .clip(RoundedCornerShape(14.dp))
                                            .background(Green),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(imageVector = action.icon, contentDescription = action.label, tint = Color.White, modifier = Modifier.size(28.dp))
                                    }
                                    Spacer(Modifier.height(8.dp))
                                    Text(action.label, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                }
                            }
                        }
                    }
                    Spacer(Modifier.height(12.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        quickActions.drop(2).forEach { action ->
                            Card(
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(16.dp),
                                colors = CardDefaults.cardColors(Color.White),
                                onClick = {}
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(56.dp)
                                            .clip(RoundedCornerShape(14.dp))
                                            .background(Green),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(imageVector = action.icon, contentDescription = action.label, tint = Color.White, modifier = Modifier.size(28.dp))
                                    }
                                    Spacer(Modifier.height(8.dp))
                                    Text(action.label, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                    Spacer(Modifier.height(4.dp))
                                    Text(action.description, fontSize = 11.sp, color = Color.Gray, fontStyle = FontStyle.Normal)
                                }
                            }
                        }
                    }
                }

                item {
                    Spacer(Modifier.height(12.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Recent Activity", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Text("View All →", fontSize = 13.sp, color = Green, fontWeight = FontWeight.Medium)
                    }
                    transactions.forEach { tx ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 4.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(Color.White)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(tx.title, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                        Spacer(Modifier.width(8.dp))
                                        Box(
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(20.dp))
                                                .background(Color(0xFFE8F5EE))
                                                .padding(horizontal = 8.dp, vertical = 2.dp)
                                        ) {
                                            Text("success", color = Green, fontSize = 11.sp, fontWeight = FontWeight.Medium)
                                        }
                                    }
                                    Spacer(Modifier.height(2.dp))
                                    Text(tx.subtitle, fontSize = 12.sp, color = Color.Gray)
                                    Text(tx.date, fontSize = 12.sp, color = Color.Gray)
                                }
                                Column(horizontalAlignment = Alignment.End) {
                                    Text(tx.amount, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                    Spacer(Modifier.height(2.dp))
                                    Text(tx.ref, fontSize = 11.sp, color = Color.Gray)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}