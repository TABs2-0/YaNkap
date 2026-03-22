package org.example.yankap.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HistoryScreen() {
    val historyItems = listOf(
        HistoryItem("Send Money", "Jean Dupont", "Mar 17, 14:30", "FCFA 5,000", "Success", true),
        HistoryItem("Buy Airtime", "Self", "Mar 17, 10:15", "FCFA 1,000", "Success", true),
        HistoryItem("Receive Money", "Alice Wonder", "Mar 16, 18:45", "FCFA 15,000", "Success", false),
        HistoryItem("Pay Bill", "Eneo Cameroon", "Mar 15, 09:00", "FCFA 12,500", "Pending", true),
        HistoryItem("Bulk Payment", "Staff Salaries", "Mar 14, 11:20", "FCFA 450,000", "Failed", true),
        HistoryItem("Withdrawal", "Agent 237", "Mar 13, 16:10", "FCFA 10,000", "Success", true),
        HistoryItem("Receive Money", "Bob Marley", "Mar 12, 12:00", "FCFA 2,000", "Success", false)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        // Top Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "History",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            IconButton(onClick = {}) {
                Icon(Icons.Default.FilterList, contentDescription = "Filter")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(historyItems) { item ->
                HistoryCard(item)
            }
        }
    }
}

@Composable
fun HistoryCard(item: HistoryItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(if (item.isDebit) Color(0xFFFFF3E0) else Color(0xFFE8F5EE)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = if (item.isDebit) Icons.Default.ArrowOutward else Icons.Default.ArrowDownward,
                    contentDescription = null,
                    tint = if (item.isDebit) Color(0xFFF57C00) else Color(0xFF1A7A4A),
                    modifier = Modifier.size(20.dp)
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(item.title, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                Text(item.subtitle, fontSize = 13.sp, color = Color.Gray)
                Text(item.date, fontSize = 11.sp, color = Color.LightGray)
            }
            
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "${if (item.isDebit) "-" else "+"} ${item.amount}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = if (item.isDebit) Color.Black else Color(0xFF1A7A4A)
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = item.status,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = when(item.status) {
                        "Success" -> Color(0xFF1A7A4A)
                        "Pending" -> Color(0xFFF57C00)
                        else -> Color.Red
                    },
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            when(item.status) {
                                "Success" -> Color(0xFFE8F5EE)
                                "Pending" -> Color(0xFFFFF3E0)
                                else -> Color(0xFFFFEBEE)
                            }
                        )
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                )
            }
        }
    }
}

data class HistoryItem(
    val title: String,
    val subtitle: String,
    val date: String,
    val amount: String,
    val status: String,
    val isDebit: Boolean
)
