package com.example.learningandtracking.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.ui.draw.clip
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learningandtracking.data.ChildProfile

@Composable
fun ProfileScreen(
    profile: ChildProfile = ChildProfile(""),
    onSave: (ChildProfile) -> Unit,
    onBack: () -> Unit
) {
    val gradientColors = listOf(
        Color(0xFF1E3C72),
        Color(0xFF2A5298)
    )

    var childName by remember { mutableStateOf(profile.name) }
    var dailyGoal by remember { mutableStateOf(profile.dailyGoal.toString()) }
    var weeklyGoal by remember { mutableStateOf(profile.weeklyGoal.toString()) }
    
    // Time slot states
    var morningTime by remember { mutableStateOf("8:00 AM - 10:00 AM") }
    var afternoonTime by remember { mutableStateOf("2:00 PM - 4:00 PM") }
    var eveningTime by remember { mutableStateOf("6:00 PM - 8:00 PM") }
    var nightTime by remember { mutableStateOf("8:00 PM - 10:00 PM") }
    var endOfDayTime by remember { mutableStateOf("10:00 PM") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(gradientColors))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
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
                    text = "Profile Settings",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                
                IconButton(
                    onClick = {
                        val newProfile = ChildProfile(
                            name = childName,
                            dailyGoal = dailyGoal.toIntOrNull() ?: 4,
                            weeklyGoal = weeklyGoal.toIntOrNull() ?: 28
                        )
                        onSave(newProfile)
                    },
                    modifier = Modifier
                        .background(Color.White.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                        .size(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Save",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Profile Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.9f))
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Text(
                        text = "Child's Information",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0F172A)
                    )
                    
                    // Child Name
                    Column {
                        Text(
                            text = "Child's Name",
                            fontSize = 14.sp,
                            color = Color(0xFF374151),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        OutlinedTextField(
                            value = childName,
                            onValueChange = { childName = it },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text("Enter child's name") },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Next
                            ),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFF3B82F6),
                                unfocusedBorderColor = Color(0xFFD1D5DB),
                                focusedTextColor = Color(0xFF0F172A),
                                unfocusedTextColor = Color(0xFF0F172A),
                                cursorColor = Color(0xFF3B82F6)
                            )
                        )
                    }
                    
                    // Daily Goal
                    Column {
                        Text(
                            text = "Daily Learning Goal",
                            fontSize = 14.sp,
                            color = Color(0xFF374151),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        OutlinedTextField(
                            value = dailyGoal,
                            onValueChange = { dailyGoal = it },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text("4") },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            ),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFF3B82F6),
                                unfocusedBorderColor = Color(0xFFD1D5DB),
                                focusedTextColor = Color(0xFF0F172A),
                                unfocusedTextColor = Color(0xFF0F172A),
                                cursorColor = Color(0xFF3B82F6)
                            )
                        )
                        Text(
                            text = "Number of learning items per day",
                            fontSize = 12.sp,
                            color = Color(0xFF6B7280)
                        )
                    }
                    
                    // Weekly Goal
                    Column {
                        Text(
                            text = "Weekly Learning Goal",
                            fontSize = 14.sp,
                            color = Color(0xFF374151),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        OutlinedTextField(
                            value = weeklyGoal,
                            onValueChange = { weeklyGoal = it },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text("28") },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFF3B82F6),
                                unfocusedBorderColor = Color(0xFFD1D5DB),
                                focusedTextColor = Color(0xFF0F172A),
                                unfocusedTextColor = Color(0xFF0F172A),
                                cursorColor = Color(0xFF3B82F6)
                            )
                        )
                        Text(
                            text = "Total learning items per week",
                            fontSize = 12.sp,
                            color = Color(0xFF6B7280)
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Time Slots Info
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
                        text = "Learning Schedule",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0F172A)
                    )
                    
                    EditableTimeSlotInfo("Morning", morningTime, { morningTime = it }, Color(0xFF3B82F6))
                    EditableTimeSlotInfo("Afternoon", afternoonTime, { afternoonTime = it }, Color(0xFFF59E0B))
                    EditableTimeSlotInfo("Evening", eveningTime, { eveningTime = it }, Color(0xFFEF4444))
                    EditableTimeSlotInfo("Night", nightTime, { nightTime = it }, Color(0xFF8B5CF6))
                    EditableTimeSlotInfo("End of Day", endOfDayTime, { endOfDayTime = it }, Color(0xFF6B7280))
                }
            }
        }
    }
}

@Composable
fun EditableTimeSlotInfo(
    title: String,
    time: String,
    onTimeChange: (String) -> Unit,
    color: Color
) {
    var isEditing by remember { mutableStateOf(false) }
    var editTime by remember { mutableStateOf(time) }
    
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .background(color, RoundedCornerShape(6.dp))
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF374151)
                )
                if (isEditing) {
                    OutlinedTextField(
                        value = editTime,
                        onValueChange = { editTime = it },
                        modifier = Modifier.width(200.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = color,
                            unfocusedBorderColor = Color(0xFFD1D5DB),
                            focusedTextColor = Color(0xFF0F172A),
                            unfocusedTextColor = Color(0xFF0F172A),
                            cursorColor = color
                        ),
                        textStyle = androidx.compose.ui.text.TextStyle(fontSize = 12.sp)
                    )
                } else {
                    Text(
                        text = time,
                        fontSize = 12.sp,
                        color = Color(0xFF6B7280)
                    )
                }
            }
        }
        
        if (isEditing) {
            Row {
                IconButton(
                    onClick = {
                        onTimeChange(editTime)
                        isEditing = false
                    },
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Save",
                        tint = color,
                        modifier = Modifier.size(16.dp)
                    )
                }
                IconButton(
                    onClick = {
                        editTime = time
                        isEditing = false
                    },
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Cancel",
                        tint = Color(0xFFEF4444),
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        } else {
            IconButton(
                onClick = { isEditing = true },
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Edit",
                    tint = color,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}
