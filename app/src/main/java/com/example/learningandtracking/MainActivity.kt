package com.example.learningandtracking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.learningandtracking.data.*
import com.example.learningandtracking.ui.screens.*
import com.example.learningandtracking.ui.theme.LearningAndTrackingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearningAndTrackingTheme {
                LearningTrackerApp()
            }
        }
    }
}

@Composable
fun LearningTrackerApp() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }
    var childProfile by remember { mutableStateOf(ChildProfile("")) }
    var learningItems by remember { mutableStateOf(getSampleLearningItems()) }
    var showPerformance by remember { mutableStateOf(false) }

    when (val screen = currentScreen) {
        Screen.Home -> {
            HomeScreen(
                onProfileClick = { currentScreen = Screen.Profile },
                onItemClick = { clickedItem ->
                    currentScreen = Screen.ItemDetail(clickedItem)
                },
                learningItems = learningItems,
                onItemCompleted = { completedItem ->
                    val updatedItems = learningItems.map { 
                        if (it.id == completedItem.id) completedItem.copy(isCompleted = true, completedAt = System.currentTimeMillis()) else it
                    }
                    learningItems = updatedItems
                }
            )
        }
        Screen.Profile -> {
            ProfileScreen(
                profile = childProfile,
                onSave = { profile ->
                    childProfile = profile
                    currentScreen = Screen.Home
                },
                onBack = { currentScreen = Screen.Home }
            )
        }
        is Screen.ItemDetail -> {
            ItemDetailScreen(
                item = screen.item,
                onMarkCompleted = { completedItem ->
                    val updatedItems = learningItems.map { 
                        if (it.id == completedItem.id) completedItem.copy(isCompleted = true, completedAt = System.currentTimeMillis()) else it
                    }
                    learningItems = updatedItems
                    currentScreen = Screen.Home
                },
                onBack = { currentScreen = Screen.Home }
            )
        }
    }

    // Show performance screen if needed
    if (showPerformance) {
        PerformanceScreen(
            performance = calculatePerformance(learningItems),
            onClose = { showPerformance = false }
        )
    }
}

sealed class Screen {
    object Home : Screen()
    object Profile : Screen()
    data class ItemDetail(val item: LearningItem) : Screen()
}

fun calculatePerformance(items: List<LearningItem>): PerformanceRating {
    val completed = items.count { it.isCompleted }
    val total = items.size
    val percentage = if (total > 0) completed.toFloat() / total else 0f
    
    val stars = when {
        percentage >= 0.8f -> 5
        percentage >= 0.6f -> 4
        percentage >= 0.4f -> 3
        percentage >= 0.2f -> 2
        else -> 1
    }
    
    val message = when (stars) {
        5 -> "Excellent!"
        4 -> "Good Job!"
        3 -> "Average"
        2 -> "Poor"
        else -> "Very Poor"
    }
    
    return PerformanceRating(stars, message, percentage)
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

