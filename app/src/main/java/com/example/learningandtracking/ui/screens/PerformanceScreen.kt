package com.example.learningandtracking.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.draw.clip
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learningandtracking.data.*

@Composable
fun PerformanceScreen(
    performance: PerformanceRating,
    onClose: () -> Unit
) {
    val gradientColors = listOf(
        Color(0xFF1E3C72),
        Color(0xFF2A5298)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(gradientColors))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(64.dp))
            
            // Performance Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.95f))
            ) {
                Column(
                    modifier = Modifier.padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Text(
                        text = "Today's Performance",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0F172A),
                        textAlign = TextAlign.Center
                    )
                    
                    // Stars
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        repeat(5) { index ->
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Star",
                                tint = if (index < performance.stars) Color(0xFFFFD700) else Color(0xFFD1D5DB),
                                modifier = Modifier.size(48.dp)
                            )
                        }
                    }
                    
                    // Performance Message
                    Text(
                        text = performance.message,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = getPerformanceColor(performance.stars),
                        textAlign = TextAlign.Center
                    )
                    
                    // Percentage
                    Text(
                        text = "${(performance.percentage * 100).toInt()}% Complete",
                        fontSize = 18.sp,
                        color = Color(0xFF6B7280),
                        textAlign = TextAlign.Center
                    )
                    
                    // Progress Bar
                    LinearProgressIndicator(
                        progress = performance.percentage,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(12.dp)
                            .clip(RoundedCornerShape(6.dp)),
                        color = getPerformanceColor(performance.stars),
                        trackColor = Color(0xFFE5E7EB)
                    )
                    
                    // Encouraging Message
                    Text(
                        text = getEncouragingMessage(performance.stars),
                        fontSize = 16.sp,
                        color = Color(0xFF6B7280),
                        textAlign = TextAlign.Center,
                        lineHeight = 24.sp
                    )
                }
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            // Close Button
            Button(
                onClick = onClose,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3B82F6)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Continue Tomorrow",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

fun getPerformanceColor(stars: Int): Color {
    return when (stars) {
        5 -> Color(0xFF10B981) // Green - Excellent
        4 -> Color(0xFF3B82F6) // Blue - Good
        3 -> Color(0xFFF59E0B) // Orange - Average
        2 -> Color(0xFFEF4444) // Red - Poor
        1 -> Color(0xFF8B5CF6) // Purple - Very Poor
        else -> Color(0xFF6B7280) // Gray
    }
}

fun getEncouragingMessage(stars: Int): String {
    return when (stars) {
        5 -> "Amazing work! You're a learning superstar! 🌟 Keep up this excellent performance!"
        4 -> "Great job! You're doing really well. Keep pushing forward! 💪"
        3 -> "Good effort! You're making progress. Try to complete a bit more tomorrow! 📚"
        2 -> "You can do better! Let's work on completing more tasks tomorrow! 💪"
        1 -> "Don't worry! Tomorrow is a new day. Let's try our best! 🌅"
        else -> "Every day is a chance to improve! Keep learning! 📖"
    }
}

// Sample performance data for testing
fun getSamplePerformance(): PerformanceRating {
    return PerformanceRating(
        stars = 4,
        message = "Good Job!",
        percentage = 0.8f
    )
}
