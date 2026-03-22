package org.example.yankap.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneEntryScreen(onSendOtp: (String) -> Unit) {
    var phoneNumber by remember { mutableStateOf("") }
    val green = Color(0xFF1A7A4A)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFE8F5EE)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Phone,
                contentDescription = null,
                tint = green,
                modifier = Modifier.size(40.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Welcome to YaNkap",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = "Enter your phone number to continue",
            fontSize = 14.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(48.dp))

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { if (it.length <= 15) phoneNumber = it },
            label = { Text("Phone Number") },
            placeholder = { Text("670 123 456") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            leadingIcon = {
                Row(
                    modifier = Modifier.padding(start = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("+237", fontWeight = FontWeight.Bold, color = green)
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(modifier = Modifier.width(1.dp).height(24.dp).background(Color.LightGray))
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = green,
                focusedLabelColor = green
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { if (phoneNumber.isNotEmpty()) onSendOtp(phoneNumber) },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = green),
            enabled = phoneNumber.length >= 8
        ) {
            Text("Send OTP", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.weight(1f))
        
        Text(
            text = "By continuing, you agree to our Terms and Conditions",
            fontSize = 12.sp,
            color = Color.LightGray,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtpVerificationScreen(phoneNumber: String, onVerify: (String) -> Unit, onBack: () -> Unit) {
    var otpCode by remember { mutableStateOf("") }
    val green = Color(0xFF1A7A4A)
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
    ) {
        IconButton(onClick = onBack) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Verify Phone",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = "Enter the 4-digit code sent to +237 $phoneNumber",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(48.dp))

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
            ) {
                repeat(4) { index ->
                    val char = otpCode.getOrNull(index)?.toString() ?: ""
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(if (char.isNotEmpty()) Color(0xFFE8F5EE) else Color(0xFFF5F5F5))
                            .border(
                                width = 1.dp,
                                color = if (char.isNotEmpty()) green else Color.Transparent,
                                shape = RoundedCornerShape(12.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = char,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = green
                        )
                    }
                }
            }

            // Hidden TextField to manage input
            TextField(
                value = otpCode,
                onValueChange = { if (it.length <= 4 && it.all { c -> c.isDigit() }) otpCode = it },
                modifier = Modifier
                    .size(280.dp, 60.dp)
                    .alpha(0f)
                    .focusRequester(focusRequester),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = { if (otpCode.length == 4) onVerify(otpCode) },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = green),
            enabled = otpCode.length == 4
        ) {
            Text("Verify & Continue", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(24.dp))

        var timer by remember { mutableStateOf(60) }
        LaunchedEffect(Unit) {
            while (timer > 0) {
                delay(1000L)
                timer -= 1
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Didn't receive code? ", fontSize = 14.sp, color = Color.Gray)
            if (timer > 0) {
                Text(
                    "Resend in ${timer}s",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
            } else {
                TextButton(
                    onClick = { timer = 60 },
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        "Resend",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = green
                    )
                }
            }
        }
    }
}
