package com.example.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Dashboard() {
    Scaffold(
        containerColor = Color(0xFFB2F7EF) // light cyan background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding) // safe to include
                .padding(horizontal = 16.dp) // your content padding
        ) {
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFF6F61)) // coral header
                    .padding(vertical = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "WELCOME",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFBFFF00), // lime green
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Tagline
            Text(
                text = "Start recording transactions and make your financial planning simple. Quick and efficient.",
                fontSize = 18.sp,
                color = Color(0xFFFF6F61),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Banner image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.pic),
                    contentDescription = "Financial Journey",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // This works because it's inside Box
                Text(
                    text = "TRACK FINANCIAL SERVICES",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.Center) // only valid inside Box
                        .background(Color(0x40000000))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }


            Spacer(modifier = Modifier.weight(1f))

            // CTA button
            Button(
                onClick = { /* Navigate */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6F61)),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(56.dp)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "LOGIN / REGISTER",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
