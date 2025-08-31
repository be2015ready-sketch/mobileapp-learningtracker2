package com.example.learningandtracking.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learningandtracking.data.*

@Composable
fun HomeScreen(
    onProfileClick: () -> Unit,
    onItemClick: (LearningItem) -> Unit,
    learningItems: List<LearningItem>,
    onItemCompleted: (LearningItem) -> Unit
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header with Profile Icon
            item {
                HeaderSection(onProfileClick = onProfileClick)
            }

            // Progress Cards
            item {
                ProgressSection(learningItems)
            }

            // Learning Items Section
            item {
                Text(
                    text = "Today's Learning Items",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            // Learning Items
            items(learningItems) { item ->
                LearningItemCard(
                    item = item,
                    onClick = { onItemClick(item) },
                    onMarkCompleted = { onItemCompleted(item) }
                )
            }
        }
    }
}

@Composable
fun HeaderSection(onProfileClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Learning Tracker",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        
        IconButton(
            onClick = onProfileClick,
            modifier = Modifier
                .background(Color.White.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                .size(48.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun ProgressSection(learningItems: List<LearningItem>) {
    val completedToday = learningItems.count { it.isCompleted }
    val totalToday = learningItems.size
    val completedThisWeek = completedToday * 7 // Simplified weekly calculation
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.9f))
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Progress",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0F172A)
            )
            
            // Daily Progress
            ProgressBar(
                title = "Daily Progress",
                current = completedToday,
                total = totalToday,
                color = Color(0xFF3B82F6)
            )
            
            // Weekly Progress
            ProgressBar(
                title = "Weekly Progress",
                current = completedThisWeek,
                total = totalToday * 7,
                color = Color(0xFF10B981)
            )
        }
    }
}

@Composable
fun ProgressBar(
    title: String,
    current: Int,
    total: Int,
    color: Color
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                fontSize = 14.sp,
                color = Color(0xFF374151)
            )
            Text(
                text = "$current/$total",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF374151)
            )
        }
        
        Spacer(modifier = Modifier.height(4.dp))
        
        LinearProgressIndicator(
            progress = current.toFloat() / total.toFloat(),
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp)),
            color = color,
            trackColor = Color(0xFFE5E7EB)
        )
    }
}

@Composable
fun LearningItemCard(
    item: LearningItem,
    onClick: () -> Unit,
    onMarkCompleted: () -> Unit
) {
    val status = getItemStatus(item)
    val backgroundColor = when (status) {
        ItemStatus.UPCOMING -> Color(0xFF3B82F6) // Blue
        ItemStatus.DUE -> Color(0xFFF59E0B) // Orange
        ItemStatus.OVERDUE -> Color(0xFFEF4444) // Red
        ItemStatus.COMPLETED -> Color(0xFF10B981) // Green
    }
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor.copy(alpha = 0.9f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "${item.category.name.replace("_", " ")} • ${item.timeSlot.name.replace("_", " ")}",
                    fontSize = 12.sp,
                    color = Color.White.copy(alpha = 0.8f),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            
            if (item.isCompleted) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Completed",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            } else {
                IconButton(
                    onClick = onMarkCompleted,
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Mark as Completed",
                        tint = Color.White.copy(alpha = 0.7f),
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

fun getItemStatus(item: LearningItem): ItemStatus {
    if (item.isCompleted) return ItemStatus.COMPLETED
    
    // This is a simplified logic - in real app, you'd check actual time
    return when (item.timeSlot) {
        TimeSlot.MORNING -> ItemStatus.UPCOMING
        TimeSlot.AFTERNOON -> ItemStatus.DUE
        TimeSlot.EVENING -> ItemStatus.OVERDUE
        TimeSlot.NIGHT -> ItemStatus.OVERDUE
        TimeSlot.END_OF_DAY -> ItemStatus.OVERDUE
    }
}

fun getSampleLearningItems(): List<LearningItem> {
    return listOf(
        LearningItem(
            id = "1",
            title = "English Words - Lesson 1",
            category = LearningCategory.ENGLISH_WORDS,
            timeSlot = TimeSlot.MORNING
        ),
        LearningItem(
            id = "2",
            title = "Surah Al-Fatiha",
            category = LearningCategory.SURAHS,
            timeSlot = TimeSlot.AFTERNOON
        ),
        LearningItem(
            id = "3",
            title = "Arabic Words - Colors",
            category = LearningCategory.ARABIC_WORDS,
            timeSlot = TimeSlot.EVENING
        ),
        LearningItem(
            id = "4",
            title = "Poem - Nature",
            category = LearningCategory.POEMS,
            timeSlot = TimeSlot.NIGHT
        )
    )
}
