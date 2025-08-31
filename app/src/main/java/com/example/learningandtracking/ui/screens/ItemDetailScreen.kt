package com.example.learningandtracking.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
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
fun ItemDetailScreen(
    item: LearningItem,
    onMarkCompleted: (LearningItem) -> Unit,
    onBack: () -> Unit
) {
    val gradientColors = listOf(
        Color(0xFF1E3C72),
        Color(0xFF2A5298)
    )

    val status = getItemStatus(item)
    val backgroundColor = when (status) {
        ItemStatus.UPCOMING -> Color(0xFF3B82F6) // Blue
        ItemStatus.DUE -> Color(0xFFF59E0B) // Orange
        ItemStatus.OVERDUE -> Color(0xFFEF4444) // Red
        ItemStatus.COMPLETED -> Color(0xFF10B981) // Green
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(gradientColors))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBack,
                    modifier = Modifier
                        .background(Color.White.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                        .size(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
                
                Text(
                    text = "Learning Item",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.width(48.dp))
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Item Details Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = backgroundColor.copy(alpha = 0.9f))
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = item.title,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        StatusChip(
                            text = item.category.name.replace("_", " "),
                            color = Color.White.copy(alpha = 0.8f)
                        )
                        StatusChip(
                            text = item.timeSlot.name.replace("_", " "),
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    }
                    
                    if (item.isCompleted) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Completed",
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Completed",
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Content Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.9f))
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Learning Content",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0F172A)
                    )
                    
                    when (item.category) {
                        LearningCategory.ENGLISH_WORDS -> EnglishWordsContent()
                        LearningCategory.SURAHS -> SurahsContent()
                        LearningCategory.ARABIC_WORDS -> ArabicWordsContent()
                        LearningCategory.POEMS -> PoemsContent()
                    }
                }
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            // Action Button
            if (!item.isCompleted) {
                Button(
                    onClick = { onMarkCompleted(item) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF10B981)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Mark as Completed",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Mark as Completed",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
fun StatusChip(
    text: String,
    color: Color
) {
    Surface(
        color = color,
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

@Composable
fun EnglishWordsContent() {
    Column {
        Text(
            text = "Today's English Words:",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF374151)
        )
        Spacer(modifier = Modifier.height(8.dp))
        WordItem("Apple", "A round fruit with red or green skin")
        WordItem("Beautiful", "Pleasing to look at")
        WordItem("Courage", "The ability to face danger or difficulty")
        WordItem("Dream", "A series of thoughts during sleep")
    }
}

@Composable
fun SurahsContent() {
    Column {
        Text(
            text = "Surah Al-Fatiha:",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF374151)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ",
            fontSize = 18.sp,
            color = Color(0xFF374151),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "In the name of Allah, the Entirely Merciful, the Especially Merciful",
            fontSize = 14.sp,
            color = Color(0xFF6B7280),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ArabicWordsContent() {
    Column {
        Text(
            text = "Arabic Colors:",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF374151)
        )
        Spacer(modifier = Modifier.height(8.dp))
        WordItem("أحمر (Ahmar)", "Red")
        WordItem("أزرق (Azraq)", "Blue")
        WordItem("أخضر (Akhdar)", "Green")
        WordItem("أصفر (Asfar)", "Yellow")
    }
}

@Composable
fun PoemsContent() {
    Column {
        Text(
            text = "Nature Poem:",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF374151)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "The sun is shining bright today,\n" +
                   "The flowers bloom in every way.\n" +
                   "Birds are singing in the trees,\n" +
                   "Dancing gently in the breeze.",
            fontSize = 14.sp,
            color = Color(0xFF6B7280),
            lineHeight = 20.sp
        )
    }
}

@Composable
fun WordItem(
    word: String,
    meaning: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = word,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF374151)
        )
        Text(
            text = meaning,
            fontSize = 14.sp,
            color = Color(0xFF6B7280)
        )
    }
}
