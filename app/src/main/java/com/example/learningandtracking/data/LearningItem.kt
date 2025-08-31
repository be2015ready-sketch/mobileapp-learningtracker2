package com.example.learningandtracking.data

import androidx.compose.ui.graphics.Color

enum class LearningCategory {
    ENGLISH_WORDS,
    SURAHS,
    ARABIC_WORDS,
    POEMS
}

enum class TimeSlot {
    MORNING,
    AFTERNOON,
    EVENING,
    NIGHT,
    END_OF_DAY
}

enum class ItemStatus {
    UPCOMING,    // Blue - before 15 min
    DUE,         // Orange - within 15 min
    OVERDUE,     // Red - past time
    COMPLETED    // Green - marked as done
}

data class LearningItem(
    val id: String,
    val title: String,
    val category: LearningCategory,
    val timeSlot: TimeSlot,
    val isCompleted: Boolean = false,
    val completedAt: Long? = null
)

data class DailyProgress(
    val date: Long,
    val totalItems: Int,
    val completedItems: Int,
    val items: List<LearningItem>
)

data class WeeklyProgress(
    val weekStart: Long,
    val dailyProgress: List<DailyProgress>
)

data class ChildProfile(
    val name: String,
    val dailyGoal: Int = 4,
    val weeklyGoal: Int = 28
)

data class PerformanceRating(
    val stars: Int, // 1-5 stars
    val message: String,
    val percentage: Float
)
