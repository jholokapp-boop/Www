package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Celebration
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MyApplicationTheme {
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = MaterialTheme.colorScheme.background
        ) {
          CongratulationsScreen()
        }
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CongratulationsScreen() {
  var showCongrats by remember { mutableStateOf(false) }
  var clickCount by remember { mutableIntStateOf(0) }

  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text("অভিনন্দন অ্যাপ", color = Color.White, fontWeight = FontWeight.Bold) },
        colors = TopAppBarDefaults.topAppBarColors(
          containerColor = Color(0xFF6200EE),
          titleContentColor = Color.White
        )
      )
    },
    containerColor = Color(0xFFFAFAFA)
  ) { paddingValues ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)
        .padding(24.dp),
      contentAlignment = Alignment.Center
    ) {
      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
      ) {
        Icon(
          imageVector = Icons.Default.Celebration,
          contentDescription = "Celebration",
          tint = Color(0xFF6200EE),
          modifier = Modifier.size(80.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
          text = "বাটনে ক্লিক করুন এবং অভিনন্দন উপভোগ করুন!",
          style = MaterialTheme.typography.titleLarge,
          textAlign = TextAlign.Center,
          fontWeight = FontWeight.Bold,
          color = Color(0xFF212121),
          modifier = Modifier.testTag("instruction_text")
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
          onClick = {
            showCongrats = true
            clickCount++
          },
          modifier = Modifier
            .height(56.dp)
            .fillMaxWidth(0.85f)
            .testTag("congrats_button"),
          shape = RoundedCornerShape(16.dp),
          colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF6200EE)
          )
        ) {
          Icon(
            imageVector = Icons.Default.ThumbUp,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "কংগ্রাচুলেশন!",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
          )
        }

        Spacer(modifier = Modifier.height(40.dp))

        if (showCongrats) {
          Card(
            modifier = Modifier
              .fillMaxWidth()
              .testTag("congrats_card"),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
              containerColor = Color(0xFFE8DEF8)
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
          ) {
            Column(
              modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
              horizontalAlignment = Alignment.CenterHorizontally
            ) {
              Text(
                text = "অভিনন্দন! 🎉",
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFF1D192B),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.testTag("congrats_title")
              )
              Spacer(modifier = Modifier.height(8.dp))
              Text(
                text = "আপনি সফলভাবে বাটনটি ক্লিক করেছেন! (ক্লিক সংখ্যা: $clickCount)",
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xFF49454F),
                textAlign = TextAlign.Center,
                modifier = Modifier.testTag("congrats_message")
              )
            }
          }
        }
      }
    }
  }
}
